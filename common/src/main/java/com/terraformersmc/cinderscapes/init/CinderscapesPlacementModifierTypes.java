package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.placementmodifier.SafelistRangeCeilingPlacementModifier;
import com.terraformersmc.cinderscapes.placementmodifier.SafelistRangeFloorPlacementModifier;
import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class CinderscapesPlacementModifierTypes {
    public static final PlacementModifierType<SafelistRangeCeilingPlacementModifier> COUNT_CEILING = () -> SafelistRangeCeilingPlacementModifier.MODIFIER_CODEC;
    public static final PlacementModifierType<SafelistRangeFloorPlacementModifier> COUNT_FLOOR  = () -> SafelistRangeFloorPlacementModifier.MODIFIER_CODEC;

    public static void init() {
        CinderscapesRegistry.register(Registries.PLACEMENT_MODIFIER_TYPE, Cinderscapes.id("safelist_range_ceiling"), COUNT_CEILING);
        CinderscapesRegistry.register(Registries.PLACEMENT_MODIFIER_TYPE, Cinderscapes.id("safelist_range_floor"), COUNT_FLOOR);
    }
}
