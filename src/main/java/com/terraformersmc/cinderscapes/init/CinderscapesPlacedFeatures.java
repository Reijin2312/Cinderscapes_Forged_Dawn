package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class CinderscapesPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DEBRIS_ORE_LARGE = createRegistryKey("ashy_shoals/debris_ore_large");
    public static final ResourceKey<PlacedFeature> DEBRIS_ORE_SMALL = createRegistryKey("ashy_shoals/debris_ore_small");
    public static final ResourceKey<PlacedFeature> ASH_PILES = createRegistryKey("ashy_shoals/ash_piles");
    public static final ResourceKey<PlacedFeature> ASHY_VEGETATION = createRegistryKey("ashy_shoals/vegetation");
    public static final ResourceKey<PlacedFeature> BRAMBLE_BERRY_BUSHES = createRegistryKey("ashy_shoals/bramble_berry_bushes");
    public static final ResourceKey<PlacedFeature> ASHY_SOUL_SAND = createRegistryKey("ashy_shoals/soul_sand");
    public static final ResourceKey<PlacedFeature> ASHY_SOUL_SOIL = createRegistryKey("ashy_shoals/soul_soil");
    public static final ResourceKey<PlacedFeature> ASHY_GRAVEL = createRegistryKey("ashy_shoals/gravel");
    public static final ResourceKey<PlacedFeature> ASH_TOP_LAYER = createRegistryKey("ashy_shoals/ash_top_layer");
    public static final ResourceKey<PlacedFeature> DEAD_TREES = createRegistryKey("ashy_shoals/dead_trees");
    public static final ResourceKey<PlacedFeature> BLACKSTONE_WEEPING_VINES = createRegistryKey("blackstone_shales/weeping_vines");
    public static final ResourceKey<PlacedFeature> PATCH_CRIMSON_ROOTS = createRegistryKey("blackstone_shales/patch_crimson_roots");
    public static final ResourceKey<PlacedFeature> SHALES_SOUL_SAND = createRegistryKey("blackstone_shales/soul_sand");
    public static final ResourceKey<PlacedFeature> SHALES_SOUL_SOIL = createRegistryKey("blackstone_shales/soul_soil");
    public static final ResourceKey<PlacedFeature> SHALES = createRegistryKey("blackstone_shales/shales");
    public static final ResourceKey<PlacedFeature> SHROOMLIGHT_BUSHES = createRegistryKey("luminous_grove/shroomlight_bushes");
    public static final ResourceKey<PlacedFeature> UMBRAL_VINES = createRegistryKey("luminous_grove/umbral_vines");
    public static final ResourceKey<PlacedFeature> LUMINOUS_VEGETATION = createRegistryKey("luminous_grove/vegetation");
    public static final ResourceKey<PlacedFeature> LUMINOUS_PODS = createRegistryKey("luminous_grove/luminous_pods");
    public static final ResourceKey<PlacedFeature> TALL_PHOTOFERNS = createRegistryKey("luminous_grove/tall_photoferns");
    public static final ResourceKey<PlacedFeature> CANOPIED_HUGE_FUNGUS = createRegistryKey("luminous_grove/canopied_huge_fungus");
    public static final ResourceKey<PlacedFeature> QUARTZ_VEGETATION = createRegistryKey("quartz_cavern/vegetation");
    public static final ResourceKey<PlacedFeature> QUARTZ_ORE = createRegistryKey("quartz_cavern/quartz_ore");
    public static final ResourceKey<PlacedFeature> SULFUR_QUARTZ_ORE = createRegistryKey("quartz_cavern/sulfur_quartz_ore");
    public static final ResourceKey<PlacedFeature> ROSE_QUARTZ_ORE = createRegistryKey("quartz_cavern/rose_quartz_ore");
    public static final ResourceKey<PlacedFeature> SMOKY_QUARTZ_ORE = createRegistryKey("quartz_cavern/smoky_quartz_ore");
    public static final ResourceKey<PlacedFeature> GOLD_ORE = createRegistryKey("quartz_cavern/gold_ore");
    public static final ResourceKey<PlacedFeature> SULFUR_ORE = createRegistryKey("quartz_cavern/sulfur_ore");
    public static final ResourceKey<PlacedFeature> CEILING_SHARD_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_quartz");
    public static final ResourceKey<PlacedFeature> CEILING_SHARD_SULFUR_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_sulfur_quartz");
    public static final ResourceKey<PlacedFeature> CEILING_SHARD_ROSE_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_rose_quartz");
    public static final ResourceKey<PlacedFeature> CEILING_SHARD_SMOKY_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_smoky_quartz");
    public static final ResourceKey<PlacedFeature> CEILING_SHARDS = createRegistryKey("quartz_cavern/ceiling_shards");
    public static final ResourceKey<PlacedFeature> FLOOR_SHARD_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_quartz");
    public static final ResourceKey<PlacedFeature> FLOOR_SHARD_SULFUR_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_sulfur_quartz");
    public static final ResourceKey<PlacedFeature> FLOOR_SHARD_ROSE_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_rose_quartz");
    public static final ResourceKey<PlacedFeature> FLOOR_SHARD_SMOKY_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_smoky_quartz");
    public static final ResourceKey<PlacedFeature> FLOOR_SHARDS = createRegistryKey("quartz_cavern/floor_shards");
    public static final ResourceKey<PlacedFeature> POLYPITE_QUARTZ = createRegistryKey("quartz_cavern/polypite_quartz");
    public static final ResourceKey<PlacedFeature> POLYPITE_SULFUR_QUARTZ = createRegistryKey("quartz_cavern/polypite_sulfur_quartz");
    public static final ResourceKey<PlacedFeature> POLYPITE_ROSE_QUARTZ = createRegistryKey("quartz_cavern/polypite_rose_quartz");
    public static final ResourceKey<PlacedFeature> POLYPITE_SMOKY_QUARTZ = createRegistryKey("quartz_cavern/polypite_smoky_quartz");

    private CinderscapesPlacedFeatures() {}

    private static ResourceKey<PlacedFeature> createRegistryKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Cinderscapes.id(name));
    }
}
