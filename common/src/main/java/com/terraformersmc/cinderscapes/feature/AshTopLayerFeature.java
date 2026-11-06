package com.terraformersmc.cinderscapes.feature;

import com.terraformersmc.cinderscapes.init.CinderscapesBiomes;
import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AshTopLayerFeature extends Feature<NoneFeatureConfiguration> {
    public AshTopLayerFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    private static Set<Biome> VALID_BIOMES = null;

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        if (VALID_BIOMES == null) {
            VALID_BIOMES = new HashSet<>();
            VALID_BIOMES.add(world.registryAccess().registryOrThrow(Registries.BIOME).get(CinderscapesBiomes.ASHY_SHOALS));
            // TODO: fix this or figure out better system
            /*world.getRegistryManager().get(Registry.BIOME_KEY).stream()
                    .filter(biome -> biome.getGenerationSettings().isFeatureAllowed(CinderscapesPlacedFeatures.ASH_TOP_LAYER.value()))
                    .forEach(biome -> VALID_BIOMES.add(biome));*/
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    BlockPos testPosition = pos.offset(x, y, z);
                    BlockState testState = world.getBlockState(testPosition);

                    if (!testState.is(Blocks.MAGMA_BLOCK) && testState.isFaceSturdy(world, testPosition, Direction.UP) && world.isEmptyBlock(testPosition.above()) && VALID_BIOMES.contains(world.getBiome(testPosition).value())) {
                        world.setBlock(testPosition.above(), CinderscapesBlocks.ASH.defaultBlockState(), 2);
                    }
                }
            }
        }

        return true;
    }
}
