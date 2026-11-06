package com.terraformersmc.terraform.shapes.api;

import net.minecraft.core.BlockPos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface Position {
    double getX();
    double getY();
    double getZ();
    void setX(double value);
    void setY(double value);
    void setZ(double value);
    Position move(double x, double y, double z);

    static Position of(double initialX, double initialY, double initialZ) {
        return new Position() {
            private double x = initialX, y = initialY, z = initialZ;
            public double getX() { return x; }
            public double getY() { return y; }
            public double getZ() { return z; }
            public void setX(double value) { x = value; }
            public void setY(double value) { y = value; }
            public void setZ(double value) { z = value; }
            public Position move(double dx, double dy, double dz) { return Position.of(x + dx, y + dy, z + dz); }
        };
    }

    static Position of(Position pos) { return of(pos.getX(), pos.getY(), pos.getZ()); }
    static Position of(BlockPos pos) { return of(pos.getX(), pos.getY(), pos.getZ()); }
    static Position of(Quaternion value) { return of(value.getI(), value.getJ(), value.getK()); }
    default Position copy() { return of(this); }
    default BlockPos toBlockPos() { return BlockPos.containing(getX(), getY(), getZ()); }

    static Stream<Position> stream(Position start, Position end) {
        List<Position> result = new ArrayList<>();
        IntStream.range((int)Math.floor(Math.min(start.getX(), end.getX())), (int)Math.ceil(Math.max(start.getX(), end.getX()))).forEach(x ->
                IntStream.range((int)Math.floor(Math.min(start.getY(), end.getY())), (int)Math.ceil(Math.max(start.getY(), end.getY()))).forEach(y ->
                        IntStream.range((int)Math.floor(Math.min(start.getZ(), end.getZ())), (int)Math.ceil(Math.max(start.getZ(), end.getZ()))).forEach(z -> result.add(of(x, y, z)))));
        return result.stream();
    }

    static List<Position> vertices(Position start, Position end) {
        return Arrays.asList(of(start.getX(), start.getY(), start.getZ()), of(start.getX(), start.getY(), end.getZ()),
                of(start.getX(), end.getY(), start.getZ()), of(start.getX(), end.getY(), end.getZ()),
                of(end.getX(), start.getY(), start.getZ()), of(end.getX(), end.getY(), start.getZ()),
                of(end.getX(), end.getY(), end.getZ()), of(end.getX(), start.getY(), end.getZ()));
    }

    default Quaternion toQuaternion() { return Quaternion.of(0, getX(), getY(), getZ()); }
    default Position rotateBy(Quaternion value) {
        Quaternion rotated = value.copy().multiply(toQuaternion()).multiply(value.conjugate());
        return rotated.toPosition();
    }
}
