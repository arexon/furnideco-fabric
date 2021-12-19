package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.content.util.FurniDecoMaterials;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

@SuppressWarnings("deprecation")
public class StepLadder extends Block {

    protected static final VoxelShape SHAPE = VoxelShapes.cuboid(.0d/16, .0d/16, 2.d/16, 16.d/16, 16.d/16, 14.d/16);
    protected static final VoxelShape SHAPE1 = VoxelShapes.cuboid(3.d/16, 0.d/16, .0d/16, 13.d/16, 9.d/16, 3.d/16);
    protected static final VoxelShape SHAPE2 = VoxelShapes.combine(SHAPE, SHAPE1, BooleanBiFunction.OR);
    public StepLadder() {

        super(FabricBlockSettings.of(FurniDecoMaterials.NON_SOLID_MATERIAL).requiresTool().nonOpaque());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPE2;
    }


}
