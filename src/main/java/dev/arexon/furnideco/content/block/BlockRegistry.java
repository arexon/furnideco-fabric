package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.FurniDeco;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    private static Block registerBlock(String name, Block block) {

        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(FurniDeco.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {

        return Registry.register(Registry.ITEM, new Identifier(FurniDeco.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(FurniDeco.ITEM_GROUP)));
    }

    public static Block STOOL = new Stool();

    public static void register() {
        registerBlock("stool", STOOL);
    }
}
