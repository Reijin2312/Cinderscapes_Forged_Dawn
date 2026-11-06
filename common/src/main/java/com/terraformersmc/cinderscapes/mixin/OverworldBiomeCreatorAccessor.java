package com.terraformersmc.cinderscapes.mixin;

import net.minecraft.data.worldgen.biome.OverworldBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OverworldBiomes.class)
public interface OverworldBiomeCreatorAccessor {
    @Invoker("calculateSkyColor")
    static int cinderscapes$callGetSkyColor(float temperature) {
        throw new AssertionError("mixin");
    }
}
