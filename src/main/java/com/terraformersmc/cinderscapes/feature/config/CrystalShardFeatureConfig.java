package com.terraformersmc.cinderscapes.feature.config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public record CrystalShardFeatureConfig(BlockState state, Direction dir, List<BlockState> whitelist) {
    public static final MapCodec<CrystalShardFeatureConfig> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    BlockState.CODEC.fieldOf("state").forGetter((config) -> config.state),
                    Direction.CODEC.fieldOf("direction").forGetter((config) -> config.dir),
                    BlockState.CODEC.listOf().fieldOf("whitelist").forGetter((config) -> config.whitelist)
            ).apply(instance, CrystalShardFeatureConfig::new));
}
