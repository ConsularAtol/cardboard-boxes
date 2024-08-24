package consular.cardboard.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import consular.cardboard.block.entity.CardboardBoxBlockEntity;
import consular.cardboard.registry.ModBlockEntityTypes;
import consular.cardboard.registry.ModBlocks;

import java.util.List;
import java.util.Optional;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CardboardBoxBlock extends BlockWithEntity {
	public static final MapCodec<CardboardBoxBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(DyeColor.CODEC.optionalFieldOf("color").forGetter(block -> Optional.ofNullable(block.color)), createSettingsCodec())
				.apply(instance, (color, settings) -> new CardboardBoxBlock((DyeColor)color.orElse(null), settings))
	);
	private static final Text UNKNOWN_CONTENTS_TEXT = Text.translatable("container.shulkerBox.unknownContents");
	public static final Identifier CONTENTS_DYNAMIC_DROP_ID = Identifier.ofVanilla("contents");
	protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
	@Nullable
	private final DyeColor color;

	@Override
	public MapCodec<CardboardBoxBlock> getCodec() {
		return CODEC;
	}

	public CardboardBoxBlock(@Nullable DyeColor color, AbstractBlock.Settings settings) {
		super(settings);
		this.color = color;
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CardboardBoxBlockEntity(this.color, pos, state);
	}

	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntityTypes.CARDBOARD_BOX_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

	@Override
	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (world.isClient) {
			return ActionResult.SUCCESS;
		} else if (player.isSpectator()) {
			return ActionResult.CONSUME;
		} else if (world.getBlockEntity(pos) instanceof CardboardBoxBlockEntity CardboardBoxBlockEntity) {
				player.openHandledScreen(CardboardBoxBlockEntity);
				player.incrementStat(Stats.OPEN_SHULKER_BOX);
				PiglinBrain.onGuardedBlockInteracted(player, true);
			return ActionResult.CONSUME;
		} else {
			return ActionResult.PASS;
		}
	}

	@Override
	public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof CardboardBoxBlockEntity CardboardBoxBlockEntity) {
			if (!world.isClient && player.isCreative() && !CardboardBoxBlockEntity.isEmpty()) {
				ItemStack itemStack = getItemStack(this.getColor());
				itemStack.applyComponentsFrom(blockEntity.createComponentMap());
				ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, itemStack);
				itemEntity.setToDefaultPickupDelay();
				world.spawnEntity(itemEntity);
			} else {
				CardboardBoxBlockEntity.generateLoot(player);
			}
		}

		return super.onBreak(world, pos, state, player);
	}

	@Override
	protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
		BlockEntity blockEntity = builder.getOptional(LootContextParameters.BLOCK_ENTITY);
		if (blockEntity instanceof CardboardBoxBlockEntity CardboardBoxBlockEntity) {
			builder = builder.addDynamicDrop(CONTENTS_DYNAMIC_DROP_ID, lootConsumer -> {
				for (int i = 0; i < CardboardBoxBlockEntity.size(); i++) {
					lootConsumer.accept(CardboardBoxBlockEntity.getStack(i));
				}
			});
		}

		return super.getDroppedStacks(state, builder);
	}

	@Override
	protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (!state.isOf(newState.getBlock())) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			super.onStateReplaced(state, world, pos, newState, moved);
			if (blockEntity instanceof CardboardBoxBlockEntity) {
				world.updateComparators(pos, state.getBlock());
			}
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
		super.appendTooltip(stack, context, tooltip, options);
		if (stack.contains(DataComponentTypes.CONTAINER_LOOT)) {
			tooltip.add(UNKNOWN_CONTENTS_TEXT);
		}

		int i = 0;
		int j = 0;

		for (ItemStack itemStack : stack.getOrDefault(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT).iterateNonEmpty()) {
			j++;
			if (i <= 4) {
				i++;
				tooltip.add(Text.translatable("container.shulkerBox.itemCount", itemStack.getName(), itemStack.getCount()));
			}
		}

		if (j - i > 0) {
			tooltip.add(Text.translatable("container.shulkerBox.more", j - i).formatted(Formatting.ITALIC));
		}
	}

	@Override
	protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.fullCube();
	}

	@Override
	protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}

	@Override
	protected boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	@Override
	protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
	}

	@Override
	public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
		ItemStack itemStack = super.getPickStack(world, pos, state);
		world.getBlockEntity(pos, ModBlockEntityTypes.CARDBOARD_BOX_BLOCK_ENTITY).ifPresent(blockEntity -> blockEntity.setStackNbt(itemStack, world.getRegistryManager()));
		return itemStack;
	}

	@Nullable
	public static DyeColor getColor(Item item) {
		return getColor(Block.getBlockFromItem(item));
	}

	@Nullable
	public static DyeColor getColor(Block block) {
		return block instanceof CardboardBoxBlock ? ((CardboardBoxBlock)block).getColor() : null;
	}

	public static Block get(@Nullable DyeColor dyeColor) {
		if (dyeColor == null) {
			return ModBlocks.CARDBOARD_BOX;
		} else {
			return switch (dyeColor) {
				case WHITE -> ModBlocks.WHITE_CARDBOARD_BOX;
				case ORANGE -> ModBlocks.ORANGE_CARDBOARD_BOX;
				case MAGENTA -> ModBlocks.MAGENTA_CARDBOARD_BOX;
				case LIGHT_BLUE -> ModBlocks.LIGHT_BLUE_CARDBOARD_BOX;
				case YELLOW -> ModBlocks.YELLOW_CARDBOARD_BOX;
				case LIME -> ModBlocks.LIME_CARDBOARD_BOX;
				case PINK -> ModBlocks.PINK_CARDBOARD_BOX;
				case GRAY -> ModBlocks.GRAY_CARDBOARD_BOX;
				case LIGHT_GRAY -> ModBlocks.LIGHT_GRAY_CARDBOARD_BOX;
				case CYAN -> ModBlocks.CYAN_CARDBOARD_BOX;
				case BLUE -> ModBlocks.BLUE_CARDBOARD_BOX;
				case BROWN -> ModBlocks.BROWN_CARDBOARD_BOX;
				case GREEN -> ModBlocks.GREEN_CARDBOARD_BOX;
				case RED -> ModBlocks.RED_CARDBOARD_BOX;
				case BLACK -> ModBlocks.BLACK_CARDBOARD_BOX;
				case PURPLE -> ModBlocks.PURPLE_CARDBOARD_BOX;
			};
		}
	}

	@Nullable
	public DyeColor getColor() {
		return this.color;
	}

	public static ItemStack getItemStack(@Nullable DyeColor color) {
		return new ItemStack(get(color));
	}
}
