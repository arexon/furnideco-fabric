package dev.arexon.furnideco.content.util;

import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class VoxelShapeUtils {

    public static VoxelShape rotateHorizontal(VoxelShape shape, float amount) {
        MutableVec2d a = new MutableVec2d(shape.getMin(Direction.Axis.X), shape.getMin(Direction.Axis.Z)).rotate(amount);
        MutableVec2d b = new MutableVec2d(shape.getMax(Direction.Axis.X), shape.getMax(Direction.Axis.Z)).rotate(amount);

        double x1 = Math.min(a.x, b.x);
        double z1 = Math.min(a.y, b.y);
        double x2 = Math.max(a.x, b.x);
        double z2 = Math.max(a.y, b.y);

        return VoxelShapes.cuboid(x1, shape.getMin(Direction.Axis.Y), z1, x2, shape.getMax(Direction.Axis.Y), z2);
    }

    public static class MutableVec2d {
        public double x;
        public double y;

        public MutableVec2d(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public MutableVec2d rotate(double amount) {
            amount *= Math.PI/180.0;
            double tx = x-0.5;
            double ty = y-0.5;

            double theta = Math.atan2(ty, tx);
            double r = Math.sqrt(tx*tx+ty*ty);

            x = r * Math.cos(theta-amount); x+=0.5;
            y = r * Math.sin(theta-amount); y+=0.5;

            return this;
        }
    }
}
