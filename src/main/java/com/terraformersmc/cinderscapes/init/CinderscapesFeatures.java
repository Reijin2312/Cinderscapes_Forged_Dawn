package com.terraformersmc.cinderscapes.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.feature.*;
import com.terraformersmc.cinderscapes.feature.config.VegetationFeatureConfig;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import java.util.Collections;

public final class CinderscapesFeatures {
    public static final MapCodec<AshPileFeature> ASH_PILE = register("ash_pile", AshPileFeature.CODEC);
    public static final MapCodec<VegetationFeature> CINDERSCAPES_VEGETATION = register("vegetation", VegetationFeature.CODEC);
    public static final MapCodec<AshTopLayerFeature> ASH_TOP_LAYER = register("ash_top_layer", AshTopLayerFeature.CODEC);
    public static final MapCodec<DeadTreeFeature> DEAD_TREE = register("dead_tree", DeadTreeFeature.CODEC);
    public static final MapCodec<BlackstoneWeepingVinesFeature> BLACKSTONE_WEEPING_VINES = register("blackstone_weeping_vines", BlackstoneWeepingVinesFeature.CODEC);
    public static final MapCodec<ShaleFeature> SHALE_FEATURE = register("shale", ShaleFeature.CODEC);
    public static final MapCodec<ShroomlightBushFeature> SHROOMLIGHT_BUSH = register("shroomlight_bush", ShroomlightBushFeature.CODEC);
    public static final MapCodec<UmbralVineFeature> UMBRAL_VINE = register("umbral_vine", UmbralVineFeature.CODEC);
    public static final MapCodec<CanopiedHugeFungusFeature> CANOPIED_HUGE_FUNGUS = register("canopied_huge_fungus", CanopiedHugeFungusFeature.CODEC);
    public static final MapCodec<CrystalShardFeature> CRYSTAL_SHARD_FEATURE = register("crystal_shard", CrystalShardFeature.CODEC);
    public static final MapCodec<PolypiteQuartzFeature> POLYPITE_QUARTZ = register("polypite_quartz", PolypiteQuartzFeature.CODEC);

    public static final VegetationFeatureConfig LUMINOUS_GROVE_VEGETATION_CONFIG = new VegetationFeatureConfig(
            new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.WARPED_ROOTS.defaultBlockState(), 1)
                    .add(Blocks.NETHER_SPROUTS.defaultBlockState(), 1)
                    .add(Blocks.WARPED_FUNGUS.defaultBlockState(), 1)
                    .add(CinderscapesBlocks.PHOTOFERN.defaultBlockState(), 2)
                    .add(CinderscapesBlocks.TWILIGHT_FESCUES.defaultBlockState(), 5)
                    .add(CinderscapesBlocks.TWILIGHT_TENDRILS.defaultBlockState(), 5)
                    .add(CinderscapesBlocks.UMBRAL_FUNGUS.defaultBlockState(), 2).build()),
            Collections.singletonList(CinderscapesBlocks.UMBRAL_NYLIUM.defaultBlockState())
    );

    private CinderscapesFeatures() {}

    private static <F extends Feature> MapCodec<F> register(String name, MapCodec<F> codec) {
        return CinderscapesRegistry.register(Registries.FEATURE_TYPE, Cinderscapes.id(name), codec);
    }

    public static void init() {}
}
