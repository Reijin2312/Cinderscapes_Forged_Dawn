package com.terraformersmc.terraform.shapes.impl.layer.transform;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import net.minecraft.util.RandomSource;
import java.util.function.Predicate;

public class NoiseTranslateLayer implements Layer {
    private final double magnitude;
    private final RandomSource random;
    public NoiseTranslateLayer(double magnitude, RandomSource random) { this.magnitude = magnitude; this.random = random; }
    public static NoiseTranslateLayer of(double magnitude, RandomSource random) { return new NoiseTranslateLayer(magnitude, random); }
    @Override public Position modifyMax(Shape shape) { return shape.max().move(magnitude, magnitude, magnitude); }
    @Override public Position modifyMin(Shape shape) { return shape.min().move(-magnitude, -magnitude, -magnitude); }
    @Override public Predicate<Position> modifyEquation(Shape shape) {
        return pos -> {
            pos.setX(pos.getX() + (random.nextFloat() - random.nextFloat()) * magnitude);
            pos.setY(pos.getY() + (random.nextFloat() - random.nextFloat()) * magnitude);
            pos.setZ(pos.getZ() + (random.nextFloat() - random.nextFloat()) * magnitude);
            return shape.equation().test(pos);
        };
    }
}
