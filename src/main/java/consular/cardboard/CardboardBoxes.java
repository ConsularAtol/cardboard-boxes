package consular.cardboard;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import consular.cardboard.registry.ModBlockEntityTypes;
import consular.cardboard.registry.ModBlocks;
import consular.cardboard.registry.ModScreenHandlers;

public class CardboardBoxes implements ModInitializer {
	public static final String MOD_ID = "cardboard_boxes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModBlockEntityTypes.registerBlockEntities();
		ModScreenHandlers.registerAllHandlers();
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            content.add(ModBlocks.CARDBOARD_BOX);
			content.add(ModBlocks.WHITE_CARDBOARD_BOX);
			content.add(ModBlocks.LIGHT_GRAY_CARDBOARD_BOX);
			content.add(ModBlocks.GRAY_CARDBOARD_BOX);
			content.add(ModBlocks.BLACK_CARDBOARD_BOX);
			content.add(ModBlocks.BROWN_CARDBOARD_BOX);
			content.add(ModBlocks.RED_CARDBOARD_BOX);
			content.add(ModBlocks.ORANGE_CARDBOARD_BOX);
			content.add(ModBlocks.YELLOW_CARDBOARD_BOX);
			content.add(ModBlocks.LIME_CARDBOARD_BOX);
			content.add(ModBlocks.GREEN_CARDBOARD_BOX);
			content.add(ModBlocks.CYAN_CARDBOARD_BOX);
			content.add(ModBlocks.LIGHT_BLUE_CARDBOARD_BOX);
			content.add(ModBlocks.BLUE_CARDBOARD_BOX);
			content.add(ModBlocks.PURPLE_CARDBOARD_BOX);
			content.add(ModBlocks.MAGENTA_CARDBOARD_BOX);
			content.add(ModBlocks.PINK_CARDBOARD_BOX);
        });
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.add(ModBlocks.CARDBOARD_BOX);
			content.add(ModBlocks.WHITE_CARDBOARD_BOX);
			content.add(ModBlocks.LIGHT_GRAY_CARDBOARD_BOX);
			content.add(ModBlocks.GRAY_CARDBOARD_BOX);
			content.add(ModBlocks.BLACK_CARDBOARD_BOX);
			content.add(ModBlocks.BROWN_CARDBOARD_BOX);
			content.add(ModBlocks.RED_CARDBOARD_BOX);
			content.add(ModBlocks.ORANGE_CARDBOARD_BOX);
			content.add(ModBlocks.YELLOW_CARDBOARD_BOX);
			content.add(ModBlocks.LIME_CARDBOARD_BOX);
			content.add(ModBlocks.GREEN_CARDBOARD_BOX);
			content.add(ModBlocks.CYAN_CARDBOARD_BOX);
			content.add(ModBlocks.LIGHT_BLUE_CARDBOARD_BOX);
			content.add(ModBlocks.BLUE_CARDBOARD_BOX);
			content.add(ModBlocks.PURPLE_CARDBOARD_BOX);
			content.add(ModBlocks.MAGENTA_CARDBOARD_BOX);
			content.add(ModBlocks.PINK_CARDBOARD_BOX);
        });
		LOGGER.info("Cardboard Boxes initialized");
	}
}