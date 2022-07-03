package dev.arexon.furnideco.content.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class FurniDecoMaterials {

    public static final Material NON_SOLID_MATERIAL = new FabricMaterialBuilder(MapColor.CLEAR).notSolid().lightPassesThrough().build();

}
