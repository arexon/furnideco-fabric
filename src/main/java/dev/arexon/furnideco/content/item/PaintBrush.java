package dev.arexon.furnideco.content.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class PaintBrush extends Item {

    public PaintBrush() {

        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

}
