package com.terraformersmc.cinderscapes.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public final class CinderscapesBlockEntityTypes {
    private CinderscapesBlockEntityTypes() {}

    public static void onAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                CinderscapesBlocks.SCORCHED_SIGN, CinderscapesBlocks.SCORCHED_WALL_SIGN,
                CinderscapesBlocks.UMBRAL_SIGN, CinderscapesBlocks.UMBRAL_WALL_SIGN);
        event.modify(BlockEntityType.HANGING_SIGN,
                CinderscapesBlocks.SCORCHED_HANGING_SIGN, CinderscapesBlocks.SCORCHED_WALL_HANGING_SIGN,
                CinderscapesBlocks.UMBRAL_HANGING_SIGN, CinderscapesBlocks.UMBRAL_WALL_HANGING_SIGN);
        event.modify(BlockEntityType.SHELF, CinderscapesBlocks.SCORCHED_SHELF, CinderscapesBlocks.UMBRAL_SHELF);
    }
}
