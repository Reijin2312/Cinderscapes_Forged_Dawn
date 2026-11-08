package com.terraformersmc.terraform.shapes.impl.filler;

import com.terraformersmc.terraform.shapes.api.Filler;
import com.terraformersmc.terraform.shapes.api.Position;
import java.util.Random;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.block.state.BlockState;

/** Mojmap-native replacement for the Terraform Shapes Fabric class. */
public class RandomSimpleFiller implements Filler {
    private final LevelWriter world;
    private final BlockState state;
    private final int flags;
    private final RandomSource random;
    private final float probability;

    public RandomSimpleFiller(LevelWriter world, BlockState state, int flags, RandomSource random, float probability) {
        this.world = world; this.state = state; this.flags = flags; this.random = random; this.probability = probability;
    }
    @Deprecated
    public RandomSimpleFiller(LevelWriter world, BlockState state, int flags, Random random, float probability) {
        this(world, state, flags, RandomSource.create(random.nextLong()), probability);
    }
    public RandomSimpleFiller(LevelWriter world, BlockState state, RandomSource random, float probability) {
        this(world, state, 3, random, probability);
    }
    @Deprecated
    public RandomSimpleFiller(LevelWriter world, BlockState state, Random random, float probability) {
        this(world, state, 3, random, probability);
    }
    public static RandomSimpleFiller of(LevelWriter world, BlockState state, int flags, RandomSource random, float probability) {
        return new RandomSimpleFiller(world, state, flags, random, probability);
    }
    @Deprecated
    public static RandomSimpleFiller of(LevelWriter world, BlockState state, int flags, Random random, float probability) {
        return new RandomSimpleFiller(world, state, flags, random, probability);
    }
    public static RandomSimpleFiller of(LevelWriter world, BlockState state, RandomSource random, float probability) {
        return new RandomSimpleFiller(world, state, random, probability);
    }
    @Deprecated
    public static RandomSimpleFiller of(LevelWriter world, BlockState state, Random random, float probability) {
        return new RandomSimpleFiller(world, state, random, probability);
    }

    @Override
    public void accept(Position position) {
        if (random.nextFloat() < probability) world.setBlock(position.toBlockPos(), state, flags);
    }
}
