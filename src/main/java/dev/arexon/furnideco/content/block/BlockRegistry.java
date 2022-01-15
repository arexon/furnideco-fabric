package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.FurniDeco;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    private static void registerBlock(String name, Block block) {

        registerBlockItem(name, block);
        Registry.register(Registry.BLOCK, new Identifier(FurniDeco.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {

        Registry.register(Registry.ITEM, new Identifier(FurniDeco.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(FurniDeco.ITEM_GROUP)));
    }

    public static Block ACACIA_STOOL = new Stool();
    public static Block BIRCH_STOOL = new Stool();
    public static Block CRIMSON_STOOL = new Stool();
    public static Block DARK_OAK_STOOL = new Stool();
    public static Block JUNGLE_STOOL = new Stool();
    public static Block OAK_STOOL = new Stool();
    public static Block SPRUCE_STOOL = new Stool();
    public static Block WARPED_STOOL = new Stool();
    public static Block STEP_LADDER = new StepLadder();
    public static Block TELEVISION = new Television();

    public static void register() {

        registerBlock("acacia_stool", ACACIA_STOOL);
        registerBlock("birch_stool", BIRCH_STOOL);
        registerBlock("crimson_stool", CRIMSON_STOOL);
        registerBlock("dark_oak_stool", DARK_OAK_STOOL);
        registerBlock("jungle_stool", JUNGLE_STOOL);
        registerBlock("oak_stool", OAK_STOOL);
        registerBlock("spruce_stool", SPRUCE_STOOL);
        registerBlock("warped_stool", WARPED_STOOL);
        registerBlock("step_ladder", STEP_LADDER);
        registerBlock("television", TELEVISION);
    }
}
