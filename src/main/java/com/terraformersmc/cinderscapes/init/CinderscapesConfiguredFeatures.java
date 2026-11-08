package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;

public final class CinderscapesConfiguredFeatures {
    public static final ResourceKey<Feature> DEBRIS_ORE_LARGE = createRegistryKey("ashy_shoals/debris_ore_large");
    public static final ResourceKey<Feature> DEBRIS_ORE_SMALL = createRegistryKey("ashy_shoals/debris_ore_small");
    public static final ResourceKey<Feature> ASH_PILE = createRegistryKey("ashy_shoals/ash_pile");
    public static final ResourceKey<Feature> ASHY_VEGETATION = createRegistryKey("ashy_shoals/vegetation");
    public static final ResourceKey<Feature> BRAMBLE_BERRY_BUSHES = createRegistryKey("ashy_shoals/bramble_berry_bushes");
    public static final ResourceKey<Feature> ASHY_SOUL_SAND = createRegistryKey("ashy_shoals/soul_sand");
    public static final ResourceKey<Feature> ASHY_SOUL_SOIL = createRegistryKey("ashy_shoals/soul_soil");
    public static final ResourceKey<Feature> ASHY_GRAVEL = createRegistryKey("ashy_shoals/gravel");
    public static final ResourceKey<Feature> ASH_TOP_LAYER = createRegistryKey("ashy_shoals/ash_top_layer");
    public static final ResourceKey<Feature> DEAD_TREE = createRegistryKey("ashy_shoals/dead_tree");
    public static final ResourceKey<Feature> BLACKSTONE_WEEPING_VINES = createRegistryKey("blackstone_shales/weeping_vines");
    public static final ResourceKey<Feature> SHALES_SOUL_SAND = createRegistryKey("blackstone_shales/soul_sand");
    public static final ResourceKey<Feature> SHALES_SOUL_SOIL = createRegistryKey("blackstone_shales/soul_soil");
    public static final ResourceKey<Feature> SHALE = createRegistryKey("blackstone_shales/shale");
    public static final ResourceKey<Feature> SHROOMLIGHT_BUSH = createRegistryKey("luminous_grove/shroomlight_bush");
    public static final ResourceKey<Feature> UMBRAL_VINE = createRegistryKey("luminous_grove/umbral_vine");
    public static final ResourceKey<Feature> LUMINOUS_VEGETATION = createRegistryKey("luminous_grove/vegetation");
    public static final ResourceKey<Feature> LUMINOUS_POD = createRegistryKey("luminous_grove/luminous_pod");
    public static final ResourceKey<Feature> TALL_PHOTOFERN = createRegistryKey("luminous_grove/tall_photofern");
    public static final ResourceKey<Feature> CANOPIED_HUGE_FUNGUS = createRegistryKey("luminous_grove/canopied_huge_fungus");
    public static final ResourceKey<Feature> CANOPIED_HUGE_FUNGUS_PLANTED = createRegistryKey("luminous_grove/canopied_huge_fungus_planted");
    public static final ResourceKey<Feature> QUARTZ_VEGETATION = createRegistryKey("quartz_cavern/vegetation");
    public static final ResourceKey<Feature> QUARTZ_ORE = createRegistryKey("quartz_cavern/quartz_ore");
    public static final ResourceKey<Feature> SULFUR_QUARTZ_ORE = createRegistryKey("quartz_cavern/sulfur_quartz_ore");
    public static final ResourceKey<Feature> ROSE_QUARTZ_ORE = createRegistryKey("quartz_cavern/rose_quartz_ore");
    public static final ResourceKey<Feature> SMOKY_QUARTZ_ORE = createRegistryKey("quartz_cavern/smoky_quartz_ore");
    public static final ResourceKey<Feature> GOLD_ORE = createRegistryKey("quartz_cavern/gold_ore");
    public static final ResourceKey<Feature> SULFUR_ORE = createRegistryKey("quartz_cavern/sulfur_ore");
    public static final ResourceKey<Feature> CEILING_SHARD_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_quartz");
    public static final ResourceKey<Feature> CEILING_SHARD_SULFUR_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_sulfur_quartz");
    public static final ResourceKey<Feature> CEILING_SHARD_ROSE_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_rose_quartz");
    public static final ResourceKey<Feature> CEILING_SHARD_SMOKY_QUARTZ = createRegistryKey("quartz_cavern/ceiling_shard_smoky_quartz");
    public static final ResourceKey<Feature> CEILING_SHARDS = createRegistryKey("quartz_cavern/ceiling_shards");
    public static final ResourceKey<Feature> FLOOR_SHARD_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_quartz");
    public static final ResourceKey<Feature> FLOOR_SHARD_SULFUR_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_sulfur_quartz");
    public static final ResourceKey<Feature> FLOOR_SHARD_ROSE_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_rose_quartz");
    public static final ResourceKey<Feature> FLOOR_SHARD_SMOKY_QUARTZ = createRegistryKey("quartz_cavern/floor_shard_smoky_quartz");
    public static final ResourceKey<Feature> FLOOR_SHARDS = createRegistryKey("quartz_cavern/floor_shards");
    public static final ResourceKey<Feature> POLYPITE_QUARTZ = createRegistryKey("quartz_cavern/polypite_quartz");
    public static final ResourceKey<Feature> POLYPITE_SULFUR_QUARTZ = createRegistryKey("quartz_cavern/polypite_sulfur_quartz");
    public static final ResourceKey<Feature> POLYPITE_ROSE_QUARTZ = createRegistryKey("quartz_cavern/polypite_rose_quartz");
    public static final ResourceKey<Feature> POLYPITE_SMOKY_QUARTZ = createRegistryKey("quartz_cavern/polypite_smoky_quartz");

    private CinderscapesConfiguredFeatures() {}

    private static ResourceKey<Feature> createRegistryKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Cinderscapes.id(name));
    }
}
