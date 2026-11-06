package com.terraformersmc.terraform.wood.api.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class PillarLogHelper {
    private PillarLogHelper() { }

    public static RotatedPillarBlock ofNether(MapColor color) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(color)
                .instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS)
                .strength(2.0F).sound(SoundType.STEM));
    }

    public static RotatedPillarBlock ofNether(MapColor wood, MapColor bark) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of()
                .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? wood : bark)
                .instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS)
                .strength(2.0F).sound(SoundType.STEM));
    }
}
