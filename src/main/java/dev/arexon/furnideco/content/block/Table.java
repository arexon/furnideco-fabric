package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.content.util.FurniDecoMaterials;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class Table extends Block {

    public Table() {

        super(FabricBlockSettings.of(FurniDecoMaterials.NON_SOLID_MATERIAL).requiresTool().nonOpaque());
    }

}
