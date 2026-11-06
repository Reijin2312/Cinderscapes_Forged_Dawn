package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.AlterGroundEvent;
import net.minecraft.world.level.block.Blocks;

public final class CinderscapesNativeEvents {
    private CinderscapesNativeEvents() { }

    public static void onAlterGround(AlterGroundEvent event) {
        AlterGroundEvent.StateProvider original = event.getStateProvider();
        event.setStateProvider((random, pos) -> event.getContext().level().isStateAtPosition(pos, state -> state.is(Blocks.NETHERRACK))
                ? CinderscapesBlocks.NODZOL.defaultBlockState()
                : original.getState(random, pos));
    }

    public static void onBlockToolModification(BlockEvent.BlockToolModificationEvent event) {
        if (event.getItemAbility() != ItemAbilities.AXE_STRIP) return;
        BlockState state = event.getState();
        Block source = state.getBlock();
        Block target = source == CinderscapesBlocks.SCORCHED_STEM ? CinderscapesBlocks.STRIPPED_SCORCHED_STEM
                : source == CinderscapesBlocks.SCORCHED_HYPHAE ? CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE
                : source == CinderscapesBlocks.UMBRAL_STEM ? CinderscapesBlocks.STRIPPED_UMBRAL_STEM
                : source == CinderscapesBlocks.UMBRAL_HYPHAE ? CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE
                : null;
        if (target != null) {
            BlockState result = target.defaultBlockState();
            if (state.hasProperty(net.minecraft.world.level.block.RotatedPillarBlock.AXIS)) {
                result = result.setValue(net.minecraft.world.level.block.RotatedPillarBlock.AXIS,
                        state.getValue(net.minecraft.world.level.block.RotatedPillarBlock.AXIS));
            }
            event.setFinalState(result);
        }
    }
}
