package com.terraformersmc.cinderscapes.block;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.cinderscapes.util.StateShapeRegistry;
import com.terraformersmc.cinderscapes.util.StateShapeSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CinderscapesNetherPlantBlock extends BushBlock {
    public static final MapCodec<CinderscapesNetherPlantBlock> CODEC = CinderscapesNetherPlantBlock.simpleCodec(CinderscapesNetherPlantBlock::new);

    public CinderscapesNetherPlantBlock(Properties settings, StateShapeSupplier supplier) {
        super(settings.offsetType(BlockBehaviour.OffsetType.XZ));

        StateShapeRegistry.put(this, supplier);
    }

    public CinderscapesNetherPlantBlock(Properties settings) {
        super(settings.offsetType(BlockBehaviour.OffsetType.XZ));
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(BlockTags.NYLIUM) || floor.is(Blocks.SOUL_SOIL) || floor.is(Blocks.NETHERRACK) || super.mayPlaceOn(floor, world, pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return StateShapeRegistry.getShape(state);
    }
}
