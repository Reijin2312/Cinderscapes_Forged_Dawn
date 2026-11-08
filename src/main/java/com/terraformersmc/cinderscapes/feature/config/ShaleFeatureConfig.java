package com.terraformersmc.cinderscapes.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;

public record ShaleFeatureConfig(BlockState state, int min, int max) {
    public static final MapCodec<ShaleFeatureConfig> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    BlockState.CODEC.fieldOf("state").forGetter((config) -> config.state),
                    Codec.INT.fieldOf("min").forGetter((config) -> config.min),
                    Codec.INT.fieldOf("max").forGetter((config) -> config.max)
            ).apply(instance, ShaleFeatureConfig::new));
}
