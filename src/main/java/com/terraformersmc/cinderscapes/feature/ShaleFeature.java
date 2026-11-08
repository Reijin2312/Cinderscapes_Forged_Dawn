package com.terraformersmc.cinderscapes.feature;

import com.mojang.serialization.MapCodec;

import com.terraformersmc.cinderscapes.feature.config.ShaleFeatureConfig;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.Shapes;
import com.terraformersmc.terraform.shapes.api.filler.Filler;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ShaleFeature implements Feature {
    public static final MapCodec<ShaleFeature> CODEC = ShaleFeatureConfig.CODEC.xmap(ShaleFeature::new, feature -> feature.config);
    private final ShaleFeatureConfig config;

    public ShaleFeature(ShaleFeatureConfig config) {
        this.config = config;
    }

    @Override
    public MapCodec<ShaleFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos pos) {

        if (level.isEmptyBlock(pos) || !level.getBlockState(pos.above()).isAir() || level.getBlockState(pos).is(Blocks.BLACKSTONE)) {
            return false;
        }

        float radius = random.nextInt(config.max() - config.min()) + config.min();
        if (level.getBlockState(pos).is(Blocks.LAVA)) {
            radius = radius * 1.5f;
        }
        float ztheta = (random.nextFloat() * 30) - 15;
        float ytheta = random.nextFloat() * 360;

        Shapes.ellipsoid(2, radius / 1.5, radius)
                .applyLayer(Layer.rotate(Quaternion.of(0, ytheta, ztheta, true)))
                .applyLayer(Layer.translate(Position.of(pos)))
                .fill(Filler.simple(level, config.state()));

        return true;
    }
}
