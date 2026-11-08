package com.terraformersmc.cinderscapes.feature;

import com.mojang.serialization.MapCodec;

import com.terraformersmc.cinderscapes.feature.config.PolypiteQuartzFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;

@NullMarked
public class PolypiteQuartzFeature implements Feature {
    public static final MapCodec<PolypiteQuartzFeature> CODEC = PolypiteQuartzFeatureConfig.CODEC.xmap(PolypiteQuartzFeature::new, feature -> feature.config);
    private final PolypiteQuartzFeatureConfig config;

    public PolypiteQuartzFeature(PolypiteQuartzFeatureConfig config) {
        this.config = config;
    }

    @Override
    public MapCodec<PolypiteQuartzFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos pos) {
        if (level.isEmptyBlock(pos)) {
            List<Direction> valid_faces = new ArrayList<>();
            for (Direction dir : Direction.values()) {
                BlockPos placeOnPos = pos.relative(dir);
                BlockState placeOnState = level.getBlockState(placeOnPos);
                if (placeOnState.is(Blocks.NETHERRACK)) {
                    valid_faces.add(dir);
                }
            }
            if (!valid_faces.isEmpty()) {
                Direction setDir = valid_faces.get(random.nextInt(valid_faces.size()));
                level.setBlock(pos, config.quartzMaterial().stateOf(setDir), 0);
                return true;
            }
        }

        return false;
    }
}
