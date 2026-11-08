package com.terraformersmc.cinderscapes.init;

import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public final class CinderscapesBlockEntityTypes {
    private CinderscapesBlockEntityTypes() {}

    public static void onAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityTypes.SIGN,
                CinderscapesBlocks.SCORCHED_SIGN, CinderscapesBlocks.SCORCHED_WALL_SIGN,
                CinderscapesBlocks.UMBRAL_SIGN, CinderscapesBlocks.UMBRAL_WALL_SIGN);
        event.modify(BlockEntityTypes.HANGING_SIGN,
                CinderscapesBlocks.SCORCHED_HANGING_SIGN, CinderscapesBlocks.SCORCHED_WALL_HANGING_SIGN,
                CinderscapesBlocks.UMBRAL_HANGING_SIGN, CinderscapesBlocks.UMBRAL_WALL_HANGING_SIGN);
        event.modify(BlockEntityTypes.SHELF, CinderscapesBlocks.SCORCHED_SHELF, CinderscapesBlocks.UMBRAL_SHELF);
    }
}
