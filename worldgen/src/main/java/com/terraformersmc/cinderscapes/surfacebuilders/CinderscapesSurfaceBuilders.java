package com.terraformersmc.cinderscapes.surfacebuilders;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.block.AshLayerBlock;
import com.terraformersmc.cinderscapes.init.CinderscapesBiomes;
import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import java.util.HashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class CinderscapesSurfaceBuilders {
	private static final HashMap<ResourceLocation, BiolithSurfaceBuilder> builders = new HashMap<>(8);

	public static void init() {
		builders.put(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "surface/ashy_shoals"),
				new AshyShoalsSurfaceBuilder(
						CinderscapesBlocks.ASH.defaultBlockState().setValue(AshLayerBlock.LAYERS, 1),
						Blocks.MAGMA_BLOCK.defaultBlockState(),
						Blocks.LAVA.defaultBlockState()
				).setBiomeKey(CinderscapesBiomes.ASHY_SHOALS));

		builders.put(ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, "surface/luminous_grove"),
				new LuminousGroveSurfaceBuilder(
						CinderscapesBlocks.UMBRAL_NYLIUM.defaultBlockState(),
						CinderscapesBlocks.UMBRAL_WART_BLOCK.defaultBlockState(),
						Blocks.NETHERRACK.defaultBlockState()
				).setBiomeKey(CinderscapesBiomes.LUMINOUS_GROVE));
	}

	public static HashMap<ResourceLocation, BiolithSurfaceBuilder> getBuilders() {
		return builders;
	}
}
