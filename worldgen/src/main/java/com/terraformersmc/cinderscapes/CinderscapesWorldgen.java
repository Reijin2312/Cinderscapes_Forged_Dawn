package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.biomegen.CinderscapesBiolithGeneration;
import com.terraformersmc.cinderscapes.surfacebuilders.CinderscapesSurfaceBuilders;
import net.neoforged.fml.ModList;

public final class CinderscapesWorldgen {
	private CinderscapesWorldgen() { }

	public static void initialize() {
		// For the time being, our TerraBlender support is unused.
		if (ModList.get().isLoaded("biolith")) {
			Cinderscapes.LOGGER.info("Enabling Cinderscapes' native NeoForge Biolith worldgen module.");

			Cinderscapes.callbackWhenInitialized(CinderscapesSurfaceBuilders::init);
			Cinderscapes.callbackWhenInitialized(new CinderscapesBiolithGeneration());
		} else {
			Cinderscapes.LOGGER.warn("Cinderscapes world generation disabled; Biolith is not present.");
		}
	}
}
