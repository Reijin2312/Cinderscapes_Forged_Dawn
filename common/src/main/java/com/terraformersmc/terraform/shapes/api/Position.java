package com.terraformersmc.terraform.shapes.api;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Mojmap-native replacement for Terraform Shapes' Yarn-compiled Position interface.
 *
 * <p>The published 1.21.11 artifact calls Yarn's {@code BlockPos.ofFloored}; the
 * Mojmap runtime name is {@link BlockPos#containing(double, double, double)}.</p>
 */
public interface Position {
    double getX();
    double getY();
    double getZ();
    void setX(double d);
    void setY(double d);
    void setZ(double d);
    Position move(double x, double y, double z);

    static Position of(double ofX, double ofY, double ofZ) {
        return new Position() {
            private double x = ofX;
            private double y = ofY;
            private double z = ofZ;

            @Override public double getX() { return x; }
            @Override public double getY() { return y; }
            @Override public double getZ() { return z; }
            @Override public void setX(double d) { x = d; }
            @Override public void setY(double d) { y = d; }
            @Override public void setZ(double d) { z = d; }
            @Override public Position move(double dx, double dy, double dz) {
                return Position.of(x + dx, y + dy, z + dz);
            }
        };
    }

    static Position of(Position pos) {
        return of(pos.getX(), pos.getY(), pos.getZ());
    }

    default Position copy() {
        return of(this);
    }

    static Position of(BlockPos pos) {
        return Position.of(pos.getX(), pos.getY(), pos.getZ());
    }

    default BlockPos toBlockPos() {
        return BlockPos.containing(getX(), getY(), getZ());
    }

    static Stream<Position> stream(Position start, Position end) {
        List<Position> positions = new ArrayList<>();
        IntStream.range((int) Math.floor(Double.min(start.getX(), end.getX())),
                (int) Math.ceil(Double.max(start.getX(), end.getX()))).forEach(x ->
                IntStream.range((int) Math.floor(Double.min(start.getY(), end.getY())),
                        (int) Math.ceil(Double.max(start.getY(), end.getY()))).forEach(y ->
                        IntStream.range((int) Math.floor(Double.min(start.getZ(), end.getZ())),
                                (int) Math.ceil(Double.max(start.getZ(), end.getZ()))).forEach(z ->
                                positions.add(Position.of(x, y, z)))));
        return positions.stream();
    }

    static List<Position> vertices(Position start, Position end) {
        return Arrays.asList(
                Position.of(start.getX(), start.getY(), start.getZ()),
                Position.of(start.getX(), start.getY(), end.getZ()),
                Position.of(start.getX(), end.getY(), start.getZ()),
                Position.of(start.getX(), end.getY(), end.getZ()),
                Position.of(end.getX(), start.getY(), start.getZ()),
                Position.of(end.getX(), end.getY(), start.getZ()),
                Position.of(end.getX(), end.getY(), end.getZ()),
                Position.of(end.getX(), start.getY(), end.getZ())
        );
    }

    static Position of(Quaternion q) {
        return Position.of(q.getI(), q.getJ(), q.getK());
    }

    default Quaternion toQuaternion() {
        return Quaternion.of(0, getX(), getY(), getZ());
    }

    default Position rotateBy(Quaternion q) {
        Quaternion newQuat = q.copy();
        Quaternion conjugate = q.conjugate();
        return newQuat.multiply(toQuaternion()).multiply(conjugate).toPosition();
    }
}
