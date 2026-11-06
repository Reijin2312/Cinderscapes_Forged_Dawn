package com.terraformersmc.cinderscapes.tag;

import com.terraformersmc.cinderscapes.Cinderscapes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CinderscapesItemTags {
    public static final TagKey<Item> ROSE_QUARTZ_CONVERTIBLES = CinderscapesItemTags.of("rose_quartz_convertibles");
    public static final TagKey<Item> SCORCHED_STEMS = CinderscapesItemTags.of("scorched_stems");
    public static final TagKey<Item> SMOKY_QUARTZ_CONVERTIBLES = CinderscapesItemTags.of("smoky_quartz_convertibles");
    public static final TagKey<Item> SULFUR_QUARTZ_CONVERTIBLES = CinderscapesItemTags.of("sulfur_quartz_convertibles");
    public static final TagKey<Item> UMBRAL_STEMS = CinderscapesItemTags.of("umbral_stems");

    public static final TagKey<Item> DARK_ASHES_DUSTS = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "dark_ashes_dusts"));
    public static final TagKey<Item> QUARTZ = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "quartz"));
    public static final TagKey<Item> QUARTZ_BLOCKS = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "quartz_blocks"));
    public static final TagKey<Item> STORAGE_BLOCKS_SULFUR = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/sulfur"));
    public static final TagKey<Item> STRIPPED_LOGS = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "stripped_logs"));
    public static final TagKey<Item> STRIPPED_WOOD = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "stripped_wood"));
    public static final TagKey<Item> SULFUR_ORES = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "sulfur_ores"));
    public static final TagKey<Item> SULFURS_ORES = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "ores/sulfurs"));
    public static final TagKey<Item> SULFURS = CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath("c", "sulfurs"));

    @SuppressWarnings("UnnecessaryReturnStatement")
    private CinderscapesItemTags() {
        return;
    }

    private static TagKey<Item> of(String path) {
        return CinderscapesItemTags.of(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, path));
    }

    private static TagKey<Item> of(ResourceLocation id) {
        return TagKey.create(Registries.ITEM, id);
    }
}
