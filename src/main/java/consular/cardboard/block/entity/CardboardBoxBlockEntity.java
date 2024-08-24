package consular.cardboard.block.entity;

import java.util.stream.IntStream;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import consular.cardboard.block.CardboardBoxBlock;
import consular.cardboard.registry.ModBlockEntityTypes;
import consular.cardboard.screen.CardboardBoxScreenHandler;

public class CardboardBoxBlockEntity extends LootableContainerBlockEntity implements SidedInventory {
	public static final int INVENTORY_SIZE = 9;
	private static final int[] AVAILABLE_SLOTS = IntStream.range(0, 9).toArray();
	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
	private int viewerCount;
	@Nullable
	private final DyeColor cachedColor;

	public CardboardBoxBlockEntity(@Nullable DyeColor color, BlockPos pos, BlockState state) {
		super(ModBlockEntityTypes.CARDBOARD_BOX_BLOCK_ENTITY, pos, state);
		this.cachedColor = color;
	}

	public CardboardBoxBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntityTypes.CARDBOARD_BOX_BLOCK_ENTITY, pos, state);
		this.cachedColor = CardboardBoxBlock.getColor(state.getBlock());
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		//world.setBlockState(pos, ModBlocks.CARDBOARD_BOX.getDefaultState());
	}

	@Override
	public int size() {
		return this.inventory.size();
	}

	@Override
	public void onOpen(PlayerEntity player) {
		if (!this.removed && !player.isSpectator()) {
			this.viewerCount++;
			this.world.addSyncedBlockEvent(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);
			this.world.emitGameEvent(player, GameEvent.CONTAINER_OPEN, this.pos);
		}
	}

	@Override
	public void onClose(PlayerEntity player) {
		if (!this.removed && !player.isSpectator()) {
			this.world.emitGameEvent(player, GameEvent.CONTAINER_CLOSE, this.pos);
		}
	}

	@Override
	protected Text getContainerName() {
		return Text.translatable("container.cardboard_box");
	}

	@Override
	protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
		super.readNbt(nbt, registryLookup);
		this.readInventoryNbt(nbt, registryLookup);
	}

	@Override
	protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
		super.writeNbt(nbt, registryLookup);
		if (!this.writeLootTable(nbt)) {
			Inventories.writeNbt(nbt, this.inventory, false, registryLookup);
		}
	}

	public void readInventoryNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
		this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		if (!this.readLootTable(nbt) && nbt.contains("Items", NbtElement.LIST_TYPE)) {
			Inventories.readNbt(nbt, this.inventory, registries);
		}
	}

	@Override
	protected DefaultedList<ItemStack> getHeldStacks() {
		return this.inventory;
	}

	@Override
	protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
		this.inventory = inventory;
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return AVAILABLE_SLOTS;
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return !(Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock);
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return true;
	}

	@Nullable
	public DyeColor getColor() {
		return this.cachedColor;
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new CardboardBoxScreenHandler(syncId, playerInventory, this);
	}
}
