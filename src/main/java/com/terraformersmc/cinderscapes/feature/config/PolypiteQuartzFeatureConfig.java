package com.terraformersmc.cinderscapes.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.cinderscapes.block.PolypiteQuartzBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public record PolypiteQuartzFeatureConfig(PolypiteQuartzBlock quartzMaterial) {
    public static final MapCodec<PolypiteQuartzFeatureConfig> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    Codec.STRING.fieldOf("quartz_material").forGetter((config) -> BuiltInRegistries.BLOCK.getKey(config.quartzMaterial).toString())
            ).apply(instance, PolypiteQuartzFeatureConfig::from));

    private static PolypiteQuartzFeatureConfig from(String id) {
        return new PolypiteQuartzFeatureConfig((PolypiteQuartzBlock) BuiltInRegistries.BLOCK.getValue(Identifier.tryParse(id)));
    }
}
