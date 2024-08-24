package consular.cardboard.registry;

import consular.cardboard.CardboardBoxes;
import consular.cardboard.block.entity.CardboardBoxBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
    public static final BlockEntityType<CardboardBoxBlockEntity> CARDBOARD_BOX_BLOCK_ENTITY = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        Identifier.of(CardboardBoxes.MOD_ID, "cardboard_boxes"),
        BlockEntityType.Builder.create(CardboardBoxBlockEntity::new, 
        ModBlocks.CARDBOARD_BOX, 
        ModBlocks.WHITE_CARDBOARD_BOX, 
        ModBlocks.LIGHT_GRAY_CARDBOARD_BOX, 
        ModBlocks.GRAY_CARDBOARD_BOX, 
        ModBlocks.BLACK_CARDBOARD_BOX, 
        ModBlocks.BROWN_CARDBOARD_BOX, 
        ModBlocks.RED_CARDBOARD_BOX, 
        ModBlocks.ORANGE_CARDBOARD_BOX, 
        ModBlocks.YELLOW_CARDBOARD_BOX, 
        ModBlocks.LIME_CARDBOARD_BOX, 
        ModBlocks.GREEN_CARDBOARD_BOX, 
        ModBlocks.CYAN_CARDBOARD_BOX, 
        ModBlocks.LIGHT_BLUE_CARDBOARD_BOX, 
        ModBlocks.BLUE_CARDBOARD_BOX, 
        ModBlocks.PURPLE_CARDBOARD_BOX, 
        ModBlocks.MAGENTA_CARDBOARD_BOX, 
        ModBlocks.PINK_CARDBOARD_BOX
        ).build()
    );

    public static void registerBlockEntities() {
        CardboardBoxes.LOGGER.info("Registering Block Entities for " + CardboardBoxes.MOD_ID);
    }
}
