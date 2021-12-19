package dev.arexon.furnideco.content.item;

import dev.arexon.furnideco.FurniDeco;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class PaintBrush extends Item {

    public PaintBrush() {

        super(new FabricItemSettings().group(FurniDeco.ITEM_GROUP));
    }

}
