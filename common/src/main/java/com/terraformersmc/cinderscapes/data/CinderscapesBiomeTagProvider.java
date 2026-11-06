package com.terraformersmc.cinderscapes.data;

import com.terraformersmc.cinderscapes.init.CinderscapesBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import java.util.concurrent.CompletableFuture;

public class CinderscapesBiomeTagProvider extends FabricTagProvider<Biome> {
	protected CinderscapesBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.BIOME, registriesFuture);
	}

	@Override
	public void addTags(HolderLookup.Provider registries) {
		/*
		 * Vanilla and Conventional biome categories
		 */
		tag(BiomeTags.IS_NETHER)
			.addOptional(CinderscapesBiomes.ASHY_SHOALS)
			.addOptional(CinderscapesBiomes.BLACKSTONE_SHALES)
			.addOptional(CinderscapesBiomes.LUMINOUS_GROVE)
			.addOptional(CinderscapesBiomes.QUARTZ_CAVERN);

		tag(ConventionalBiomeTags.IS_NETHER_FOREST)
			.addOptional(CinderscapesBiomes.LUMINOUS_GROVE);

		tag(ConventionalBiomeTags.IS_WASTELAND)
			.addOptional(CinderscapesBiomes.ASHY_SHOALS);

		tag(ConventionalBiomeTags.IS_VEGETATION_SPARSE)
			.addOptional(CinderscapesBiomes.BLACKSTONE_SHALES);


		/*
		 * Biome structure generation tags
		 */
		tag(BiomeTags.HAS_BASTION_REMNANT)
			.addOptional(CinderscapesBiomes.ASHY_SHOALS)
			.addOptional(CinderscapesBiomes.LUMINOUS_GROVE);

		tag(BiomeTags.HAS_NETHER_FORTRESS)
			.addOptional(CinderscapesBiomes.BLACKSTONE_SHALES);

		tag(BiomeTags.HAS_NETHER_FOSSIL)
			.addOptional(CinderscapesBiomes.ASHY_SHOALS)
			.addOptional(CinderscapesBiomes.BLACKSTONE_SHALES);

		tag(BiomeTags.HAS_RUINED_PORTAL_NETHER)
			.addOptional(CinderscapesBiomes.ASHY_SHOALS);
	}
}
