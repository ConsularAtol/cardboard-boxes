package consular.cardboard.registry;


import consular.cardboard.CardboardBoxes;
import consular.cardboard.block.CardboardBoxBlock;
import consular.cardboard.item.BoxBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CARDBOARD_BOX = registerBox("cardboard_box", new CardboardBoxBlock(null, AbstractBlock.Settings.create().mapColor(MapColor.BROWN).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block WHITE_CARDBOARD_BOX = registerBox("white_cardboard_box", new CardboardBoxBlock(DyeColor.WHITE, AbstractBlock.Settings.create().mapColor(MapColor.WHITE).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block LIGHT_GRAY_CARDBOARD_BOX = registerBox("light_gray_cardboard_box", new CardboardBoxBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block GRAY_CARDBOARD_BOX = registerBox("gray_cardboard_box", new CardboardBoxBlock(DyeColor.GRAY, AbstractBlock.Settings.create().mapColor(MapColor.GRAY).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block BLACK_CARDBOARD_BOX = registerBox("black_cardboard_box", new CardboardBoxBlock(DyeColor.BLACK, AbstractBlock.Settings.create().mapColor(MapColor.BLACK).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block BROWN_CARDBOARD_BOX = registerBox("brown_cardboard_box", new CardboardBoxBlock(DyeColor.BROWN, AbstractBlock.Settings.create().mapColor(MapColor.BROWN).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block RED_CARDBOARD_BOX = registerBox("red_cardboard_box", new CardboardBoxBlock(DyeColor.RED, AbstractBlock.Settings.create().mapColor(MapColor.RED).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block ORANGE_CARDBOARD_BOX = registerBox("orange_cardboard_box", new CardboardBoxBlock(DyeColor.ORANGE, AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block YELLOW_CARDBOARD_BOX = registerBox("yellow_cardboard_box", new CardboardBoxBlock(DyeColor.YELLOW, AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block LIME_CARDBOARD_BOX = registerBox("lime_cardboard_box", new CardboardBoxBlock(DyeColor.LIME, AbstractBlock.Settings.create().mapColor(MapColor.LIME).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block GREEN_CARDBOARD_BOX = registerBox("green_cardboard_box", new CardboardBoxBlock(DyeColor.GREEN, AbstractBlock.Settings.create().mapColor(MapColor.GREEN).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block CYAN_CARDBOARD_BOX = registerBox("cyan_cardboard_box", new CardboardBoxBlock(DyeColor.CYAN, AbstractBlock.Settings.create().mapColor(MapColor.CYAN).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block LIGHT_BLUE_CARDBOARD_BOX = registerBox("light_blue_cardboard_box", new CardboardBoxBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block BLUE_CARDBOARD_BOX = registerBox("blue_cardboard_box", new CardboardBoxBlock(DyeColor.BLUE, AbstractBlock.Settings.create().mapColor(MapColor.BLUE).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block PURPLE_CARDBOARD_BOX = registerBox("purple_cardboard_box", new CardboardBoxBlock(DyeColor.PURPLE, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block MAGENTA_CARDBOARD_BOX = registerBox("magenta_cardboard_box", new CardboardBoxBlock(DyeColor.MAGENTA, AbstractBlock.Settings.create().mapColor(MapColor.MAGENTA).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));
    public static final Block PINK_CARDBOARD_BOX = registerBox("pink_cardboard_box", new CardboardBoxBlock(DyeColor.PINK, AbstractBlock.Settings.create().mapColor(MapColor.PINK).solid().breakInstantly().dynamicBounds().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WOOL)));

    //private static Block registerBlock(String name, Block block){
    //    registerBlockItem(name, block);
    //    return Registry.register(Registries.BLOCK, Identifier.of(CardboardBoxes.MOD_ID, name), block);
    //}

    private static Block registerBox(String name, Block block){
        registerBoxItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CardboardBoxes.MOD_ID, name), block);
    }

    private static Item registerBoxItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(CardboardBoxes.MOD_ID, name), 
            new BoxBlockItem(block, new Settings().maxCount(1)));
    }

    //private static Item registerBlockItem(String name, Block block){
    //    return Registry.register(Registries.ITEM, Identifier.of(CardboardBoxes.MOD_ID, name), 
    //        new BlockItem(block, new Settings()));
    //}
    
    public static void registerModBlocks(){
        CardboardBoxes.LOGGER.info("Registering blocks");;
    }
}
