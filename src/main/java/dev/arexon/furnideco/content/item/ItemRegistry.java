package dev.arexon.furnideco.content.item;

import dev.arexon.furnideco.FurniDeco;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    private static Item registerItem(String name, Item item) {

        return Registry.register(Registry.ITEM, new Identifier(FurniDeco.MOD_ID, name), item);
    }

    public static void register() {
        registerItem("paint_brush", new PaintBrush());
    }
}
