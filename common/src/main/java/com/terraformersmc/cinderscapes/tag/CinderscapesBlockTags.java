package com.terraformersmc.cinderscapes.tag;

import com.terraformersmc.cinderscapes.Cinderscapes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CinderscapesBlockTags {
    public static final TagKey<Block> ASH_PERMEABLE = CinderscapesBlockTags.of("ash_permeable");
    public static final TagKey<Block> SCORCHED_STEMS = CinderscapesBlockTags.of("scorched_stems");
    public static final TagKey<Block> UMBRAL_STEMS = CinderscapesBlockTags.of("umbral_stems");

    public static final TagKey<Block> STRIPPED_LOGS = CinderscapesBlockTags.of(ResourceLocation.fromNamespaceAndPath("c", "stripped_logs"));
    public static final TagKey<Block> STRIPPED_WOOD = CinderscapesBlockTags.of(ResourceLocation.fromNamespaceAndPath("c", "stripped_wood"));

    @SuppressWarnings("UnnecessaryReturnStatement")
    private CinderscapesBlockTags() {
        return;
    }

    private static TagKey<Block> of(String path) {
        return CinderscapesBlockTags.of(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, path));
    }

    private static TagKey<Block> of(ResourceLocation id) {
        return TagKey.create(Registries.BLOCK, id);
    }
}
