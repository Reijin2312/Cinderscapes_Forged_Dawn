package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.biomegen.CinderscapesBiolithGeneration;
import com.terraformersmc.cinderscapes.surfacebuilders.CinderscapesSurfaceBuilders;
import net.minecraftforge.fml.ModList;

public final class CinderscapesWorldgen {
    public void initialize() {
        if (ModList.get().isLoaded("biolith")) {
            Cinderscapes.LOGGER.info("Enabling Cinderscapes' native Forge Biolith worldgen module.");
            CinderscapesSurfaceBuilders.init();
            new CinderscapesBiolithGeneration().run();
        } else {
            Cinderscapes.LOGGER.warn("Cinderscapes world generation disabled; Biolith is not present.");
        }
    }
}
