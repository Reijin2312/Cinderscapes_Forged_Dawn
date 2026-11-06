package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.block.AshLayerBlock;
import com.terraformersmc.cinderscapes.block.BrambleBerryBushBlock;
import com.terraformersmc.cinderscapes.block.CinderscapesNetherPlantBlock;
import com.terraformersmc.cinderscapes.block.CinderscapesNetherTallPlantBlock;
import com.terraformersmc.cinderscapes.block.CinderscapesNyliumBlock;
import com.terraformersmc.cinderscapes.block.CinderscapesOreBlock;
import com.terraformersmc.cinderscapes.block.CinderscapesTransparentBlock;
import com.terraformersmc.cinderscapes.block.CrystiniumBlock;
import com.terraformersmc.cinderscapes.block.GhastlyEctoplasmBlock;
import com.terraformersmc.cinderscapes.block.PhotofernBlock;
import com.terraformersmc.cinderscapes.block.PolypiteQuartzBlock;
import com.terraformersmc.cinderscapes.block.PottedCrystiniumBlock;
import com.terraformersmc.cinderscapes.block.PottedPyracinthBlock;
import com.terraformersmc.cinderscapes.block.PyracinthBlock;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import com.terraformersmc.terraform.wood.api.block.PillarLogHelper;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

public class CinderscapesBlocks {

    /////////////////
    // Ashy Shoals //
    /////////////////

    public static Block SCORCHED_STEM;
    public static Block STRIPPED_SCORCHED_STEM;

    public static Block SCORCHED_HYPHAE;
    public static Block STRIPPED_SCORCHED_HYPHAE;

    public static Block SCORCHED_PLANKS;
    public static Block SCORCHED_SLAB;

    public static Block SCORCHED_PRESSURE_PLATE;
    public static Block SCORCHED_FENCE;
    public static Block SCORCHED_TRAPDOOR;
    public static Block SCORCHED_FENCE_GATE;
    public static Block SCORCHED_STAIRS;
    public static Block SCORCHED_BUTTON;
    public static Block SCORCHED_DOOR;

    public static StandingSignBlock SCORCHED_SIGN;
    public static WallSignBlock SCORCHED_WALL_SIGN;
    public static CeilingHangingSignBlock SCORCHED_HANGING_SIGN;
    public static WallHangingSignBlock SCORCHED_WALL_HANGING_SIGN;

    public static Block SCORCHED_SHRUB;
    public static Block POTTED_SCORCHED_SHRUB;

    public static Block SCORCHED_SPROUTS;
    public static Block SCORCHED_TENDRILS;
    public static Block POTTED_SCORCHED_TENDRILS;

    public static Block ASH;
    public static Block ASH_BLOCK;

    public static Block PYRACINTH;
    public static Block POTTED_PYRACINTH;

    ////////////////////
    // Luminous Grove //
    ////////////////////

    // Umbral Nylia

    public static Block UMBRAL_STEM;
    public static Block STRIPPED_UMBRAL_STEM;

    public static Block UMBRAL_HYPHAE;
    public static Block STRIPPED_UMBRAL_HYPHAE;

    public static Block UMBRAL_NYLIUM;

    public static Block UMBRAL_FUNGUS;
    public static Block POTTED_UMBRAL_FUNGUS;

    public static Block UMBRAL_WART_BLOCK;
    public static Block UMBRAL_FLESH_BLOCK;

    public static Block UMBRAL_PLANKS;
    public static Block UMBRAL_SLAB;
    public static Block UMBRAL_PRESSURE_PLATE;
    public static Block UMBRAL_FENCE;
    public static Block UMBRAL_TRAPDOOR;
    public static Block UMBRAL_FENCE_GATE;
    public static Block UMBRAL_STAIRS;
    public static Block UMBRAL_BUTTON;
    public static Block UMBRAL_DOOR;

    public static StandingSignBlock UMBRAL_SIGN;
    public static WallSignBlock UMBRAL_WALL_SIGN;
    public static CeilingHangingSignBlock UMBRAL_HANGING_SIGN;
    public static WallHangingSignBlock UMBRAL_WALL_HANGING_SIGN;

    public static final WoodType SCORCHED_WOOD_TYPE = WoodType.register(new WoodType("cinderscapes:scorched", BlockSetType.CRIMSON));
    public static final WoodType UMBRAL_WOOD_TYPE = WoodType.register(new WoodType("cinderscapes:umbral", BlockSetType.WARPED));

    // Other

    public static Block TWILIGHT_VINE_BLOCK;
    public static Block TWILIGHT_TENDRILS;
    public static Block POTTED_TWILIGHT_TENDRILS;
    public static Block TWILIGHT_FESCUES;

    public static Block PHOTOFERN;
    public static Block POTTED_PHOTOFERN;

    public static Block TALL_PHOTOFERN;

    public static Block LUMINOUS_POD;
    public static Block POTTED_LUMINOUS_POD;
    public static Block GHASTLY_ECTOPLASM;

    ///////////
    // Other //
    ///////////

    public static Block BRAMBLE_BERRY_BUSH;

    public static Block NODZOL;

    public static Block SULFUR_BLOCK;
    public static Block SULFUR_ORE;

    ///////////////////
    // Quartz Cavern //
    ///////////////////

    public static Block CRYSTINIUM;
    public static Block POTTED_CRYSTINIUM;

    public static Block CRYSTALLINE_QUARTZ;
    public static Block POLYPITE_QUARTZ;
    public static Block POTTED_POLYPITE_QUARTZ;

    // Sulfur Quartz

    public static Block SULFUR_QUARTZ_ORE;
    public static Block SULFUR_QUARTZ_BLOCK;
    public static Block CHISELED_SULFUR_QUARTZ_BLOCK;
    public static Block SULFUR_QUARTZ_PILLAR;
    public static Block SULFUR_QUARTZ_STAIRS;
    public static Block SULFUR_QUARTZ_SLAB;
    public static Block SMOOTH_SULFUR_QUARTZ;
    public static Block SMOOTH_SULFUR_QUARTZ_STAIRS;
    public static Block SMOOTH_SULFUR_QUARTZ_SLAB;
    public static Block SULFUR_QUARTZ_BRICKS;

    public static Block CRYSTALLINE_SULFUR_QUARTZ;
    public static Block POLYPITE_SULFUR_QUARTZ;
    public static Block POTTED_POLYPITE_SULFUR_QUARTZ;

    // Rose Quartz

    public static Block ROSE_QUARTZ_ORE;
    public static Block ROSE_QUARTZ_BLOCK;
    public static Block CHISELED_ROSE_QUARTZ_BLOCK;
    public static Block ROSE_QUARTZ_PILLAR;
    public static Block ROSE_QUARTZ_STAIRS;
    public static Block ROSE_QUARTZ_SLAB;
    public static Block SMOOTH_ROSE_QUARTZ;
    public static Block SMOOTH_ROSE_QUARTZ_STAIRS;
    public static Block SMOOTH_ROSE_QUARTZ_SLAB;
    public static Block ROSE_QUARTZ_BRICKS;

    public static Block CRYSTALLINE_ROSE_QUARTZ;
    public static Block POLYPITE_ROSE_QUARTZ;
    public static Block POTTED_POLYPITE_ROSE_QUARTZ;

    // Smoky Quartz

    public static Block SMOKY_QUARTZ_ORE;
    public static Block SMOKY_QUARTZ_BLOCK;
    public static Block CHISELED_SMOKY_QUARTZ_BLOCK;
    public static Block SMOKY_QUARTZ_PILLAR;
    public static Block SMOKY_QUARTZ_STAIRS;
    public static Block SMOKY_QUARTZ_SLAB;
    public static Block SMOOTH_SMOKY_QUARTZ;
    public static Block SMOOTH_SMOKY_QUARTZ_STAIRS;
    public static Block SMOOTH_SMOKY_QUARTZ_SLAB;
    public static Block SMOKY_QUARTZ_BRICKS;

    public static Block CRYSTALLINE_SMOKY_QUARTZ;
    public static Block POLYPITE_SMOKY_QUARTZ;
    public static Block POTTED_POLYPITE_SMOKY_QUARTZ;

    public static void init() {

        /////////////////
        // Ashy Shoals //
        /////////////////

        SCORCHED_STEM = CinderscapesRegistry.register("scorched_stem", PillarLogHelper.ofNether(MapColor.COLOR_GRAY, MapColor.TERRACOTTA_GRAY));
        STRIPPED_SCORCHED_STEM = CinderscapesRegistry.register("stripped_scorched_stem", PillarLogHelper.ofNether(MapColor.COLOR_GRAY));

        SCORCHED_HYPHAE = CinderscapesRegistry.register("scorched_hyphae", PillarLogHelper.ofNether(MapColor.TERRACOTTA_GRAY));
        STRIPPED_SCORCHED_HYPHAE = CinderscapesRegistry.register("stripped_scorched_hyphae", PillarLogHelper.ofNether(MapColor.COLOR_GRAY));

        SCORCHED_PLANKS = CinderscapesRegistry.register("scorched_planks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        SCORCHED_SLAB = CinderscapesRegistry.register("scorched_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB).mapColor(MapColor.COLOR_LIGHT_GRAY)));

        SCORCHED_PRESSURE_PLATE = CinderscapesRegistry.register("scorched_pressure_plate", new PressurePlateBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        SCORCHED_FENCE = CinderscapesRegistry.register("scorched_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        SCORCHED_TRAPDOOR = CinderscapesRegistry.register("scorched_trapdoor", new TrapDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        SCORCHED_FENCE_GATE = CinderscapesRegistry.register("scorched_fence_gate", new FenceGateBlock(WoodType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        SCORCHED_STAIRS = CinderscapesRegistry.register("scorched_stairs", new StairBlock(SCORCHED_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        SCORCHED_BUTTON = CinderscapesRegistry.register("scorched_button", new ButtonBlock(BlockSetType.CRIMSON, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        SCORCHED_DOOR = CinderscapesRegistry.register("scorched_door", new DoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR).mapColor(MapColor.COLOR_LIGHT_GRAY)));

        SCORCHED_SIGN = CinderscapesRegistry.register("scorched_sign", new StandingSignBlock(SCORCHED_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN).mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.NETHER_WOOD)));
        SCORCHED_WALL_SIGN = CinderscapesRegistry.register("scorched_wall_sign", new WallSignBlock(SCORCHED_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN).mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.NETHER_WOOD).dropsLike(SCORCHED_SIGN)));
        SCORCHED_HANGING_SIGN = CinderscapesRegistry.register("scorched_hanging_sign", new CeilingHangingSignBlock(SCORCHED_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN).mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.NETHER_WOOD_HANGING_SIGN)));
        SCORCHED_WALL_HANGING_SIGN = CinderscapesRegistry.register("scorched_wall_hanging_sign", new WallHangingSignBlock(SCORCHED_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN).mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.NETHER_WOOD_HANGING_SIGN).dropsLike(SCORCHED_HANGING_SIGN)));

        SCORCHED_SHRUB = CinderscapesRegistry.register("scorched_shrub", new CinderscapesNetherPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH).mapColor(MapColor.TERRACOTTA_GRAY), (state) -> Block.box(2.0, 0.0, 2.0, 14.0, 14.0, 14.0)));
        POTTED_SCORCHED_SHRUB = CinderscapesRegistry.register("potted_scorched_shrub", new FlowerPotBlock(SCORCHED_SHRUB, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_DEAD_BUSH)));

        SCORCHED_SPROUTS = CinderscapesRegistry.register("scorched_sprouts", new CinderscapesNetherPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_GRAY), (state) -> Block.box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0)));
        SCORCHED_TENDRILS = CinderscapesRegistry.register("scorched_tendrils", new CinderscapesNetherPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_ROOTS).mapColor(MapColor.COLOR_GRAY), (state) -> Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)));
        POTTED_SCORCHED_TENDRILS = CinderscapesRegistry.register("potted_scorched_tendrils", new FlowerPotBlock(SCORCHED_TENDRILS, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_CRIMSON_ROOTS)));

        ASH = CinderscapesRegistry.register("ash", new AshLayerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW).mapColor(MapColor.COLOR_GRAY)));
        ASH_BLOCK = CinderscapesRegistry.register("ash_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).mapColor(MapColor.COLOR_GRAY)));

        PYRACINTH = CinderscapesRegistry.register("pyracinth", new PyracinthBlock());
        POTTED_PYRACINTH = CinderscapesRegistry.register("potted_pyracinth", new PottedPyracinthBlock());

        ////////////////////
        // Luminous Grove //
        ////////////////////

        // Umbral Nylia

        UMBRAL_STEM = CinderscapesRegistry.register("umbral_stem", PillarLogHelper.ofNether(MapColor.CLAY, MapColor.COLOR_BLUE));
        STRIPPED_UMBRAL_STEM = CinderscapesRegistry.register("stripped_umbral_stem", PillarLogHelper.ofNether(MapColor.CLAY));

        UMBRAL_HYPHAE = CinderscapesRegistry.register("umbral_hyphae", PillarLogHelper.ofNether(MapColor.COLOR_BLUE));
        STRIPPED_UMBRAL_HYPHAE = CinderscapesRegistry.register("stripped_umbral_hyphae", PillarLogHelper.ofNether(MapColor.CLAY));

        UMBRAL_NYLIUM = CinderscapesRegistry.register("umbral_nylium", new CinderscapesNyliumBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_NYLIUM).mapColor(MapColor.CLAY)));

        UMBRAL_FUNGUS = CinderscapesRegistry.register("umbral_fungus", new FungusBlock(CinderscapesConfiguredFeatures.CANOPIED_HUGE_FUNGUS_PLANTED, UMBRAL_NYLIUM, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS).mapColor(MapColor.COLOR_BLUE).lightLevel(state -> 15)));
        POTTED_UMBRAL_FUNGUS = CinderscapesRegistry.register("potted_umbral_fungus", new FlowerPotBlock(UMBRAL_FUNGUS, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_WARPED_FUNGUS)));

        UMBRAL_WART_BLOCK = CinderscapesRegistry.register("umbral_wart_block", new CinderscapesTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WART_BLOCK).mapColor(MapColor.COLOR_BLUE).noOcclusion()));
        UMBRAL_FLESH_BLOCK = CinderscapesRegistry.register("umbral_flesh_block", new CinderscapesTransparentBlock(BlockBehaviour.Properties.ofFullCopy(UMBRAL_WART_BLOCK).lightLevel(state -> 15)));

        UMBRAL_PLANKS = CinderscapesRegistry.register("umbral_planks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_SLAB = CinderscapesRegistry.register("umbral_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_PRESSURE_PLATE = CinderscapesRegistry.register("umbral_pressure_plate", new PressurePlateBlock(BlockSetType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PRESSURE_PLATE).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_FENCE = CinderscapesRegistry.register("umbral_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_TRAPDOOR = CinderscapesRegistry.register("umbral_trapdoor", new TrapDoorBlock(BlockSetType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_TRAPDOOR).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_FENCE_GATE = CinderscapesRegistry.register("umbral_fence_gate", new FenceGateBlock(WoodType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE_GATE).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_STAIRS = CinderscapesRegistry.register("umbral_stairs", new StairBlock(UMBRAL_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STAIRS).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_BUTTON = CinderscapesRegistry.register("umbral_button", new ButtonBlock(BlockSetType.WARPED, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_BUTTON).mapColor(MapColor.COLOR_BLUE)));
        UMBRAL_DOOR = CinderscapesRegistry.register("umbral_door", new DoorBlock(BlockSetType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_DOOR).mapColor(MapColor.COLOR_BLUE)));

        UMBRAL_SIGN = CinderscapesRegistry.register("umbral_sign", new StandingSignBlock(UMBRAL_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SIGN).mapColor(MapColor.COLOR_BLUE).sound(SoundType.NETHER_WOOD)));
        UMBRAL_WALL_SIGN = CinderscapesRegistry.register("umbral_wall_sign", new WallSignBlock(UMBRAL_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_SIGN).mapColor(MapColor.COLOR_BLUE).sound(SoundType.NETHER_WOOD).dropsLike(UMBRAL_SIGN)));
        UMBRAL_HANGING_SIGN = CinderscapesRegistry.register("umbral_hanging_sign", new CeilingHangingSignBlock(UMBRAL_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HANGING_SIGN).mapColor(MapColor.COLOR_BLUE).sound(SoundType.NETHER_WOOD_HANGING_SIGN)));
        UMBRAL_WALL_HANGING_SIGN = CinderscapesRegistry.register("umbral_wall_hanging_sign", new WallHangingSignBlock(UMBRAL_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_HANGING_SIGN).mapColor(MapColor.COLOR_BLUE).sound(SoundType.NETHER_WOOD_HANGING_SIGN).dropsLike(UMBRAL_HANGING_SIGN)));

        // Other

        TWILIGHT_VINE_BLOCK = CinderscapesRegistry.register("twilight_vine_block", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).instrument(NoteBlockInstrument.BASS).strength(1.0F).sound(SoundType.STEM)));
        TWILIGHT_TENDRILS = CinderscapesRegistry.register("twilight_tendrils", new CinderscapesNetherPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS).mapColor(MapColor.ICE).lightLevel((state) -> 15), (state) -> Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)));
        POTTED_TWILIGHT_TENDRILS = CinderscapesRegistry.register("potted_twilight_tendrils", new FlowerPotBlock(TWILIGHT_TENDRILS, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_WARPED_ROOTS).lightLevel((state) -> 15)));
        TWILIGHT_FESCUES = CinderscapesRegistry.register("twilight_fescues", new CinderscapesNetherPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_PURPLE), (state) -> Block.box(2.0, 0.0, 2.0, 14.0, 5.0, 14.0)));

        PHOTOFERN = CinderscapesRegistry.register("photofern", new PhotofernBlock());
        POTTED_PHOTOFERN = CinderscapesRegistry.register("potted_photofern", new FlowerPotBlock(PHOTOFERN, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_FERN)));

        TALL_PHOTOFERN = CinderscapesRegistry.register("tall_photofern", new CinderscapesNetherTallPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS).mapColor(MapColor.COLOR_PURPLE), (state) -> Block.box(2.0, 0, 2.0, 14.0, 16.0, 14.0)));

        LUMINOUS_POD = CinderscapesRegistry.register("luminous_pod", new CinderscapesNetherTallPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_ROOTS).mapColor(MapColor.GRASS).lightLevel((state) -> 15), (state) -> Block.box(2.0, 0, 2.0, 14.0, 16.0, 14.0)));
        POTTED_LUMINOUS_POD = CinderscapesRegistry.register("potted_luminous_pod", new FlowerPotBlock(LUMINOUS_POD, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_WARPED_ROOTS).lightLevel((state) -> 15)));
        GHASTLY_ECTOPLASM = CinderscapesRegistry.register("ghastly_ectoplasm", new GhastlyEctoplasmBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES_PLANT).mapColor(MapColor.QUARTZ).sound(SoundType.ROOTS)));

        ///////////
        // Other //
        ///////////

        BRAMBLE_BERRY_BUSH = CinderscapesRegistry.register("bramble_berry_bush", new BrambleBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).mapColor(MapColor.TERRACOTTA_BLACK)));

        NODZOL = CinderscapesRegistry.register("nodzol", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).mapColor(MapColor.COLOR_BROWN)));

        SULFUR_BLOCK = CinderscapesRegistry.register("sulfur_block", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F, 0.5f).sound(SoundType.GRAVEL)));
        SULFUR_ORE = CinderscapesRegistry.register("sulfur_ore", new CinderscapesOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE).mapColor(MapColor.COLOR_YELLOW).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops()));

        ///////////////////
        // Quartz Cavern //
        ///////////////////

        CRYSTINIUM = CinderscapesRegistry.register("crystinium", new CrystiniumBlock());
        POTTED_CRYSTINIUM = CinderscapesRegistry.register("potted_crystinium", new PottedCrystiniumBlock());

        CRYSTALLINE_QUARTZ = CinderscapesRegistry.register("crystalline_quartz", new CinderscapesTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).sound(SoundType.GLASS).noOcclusion().isValidSpawn(CinderscapesBlocks::never).isRedstoneConductor(CinderscapesBlocks::never).isSuffocating(CinderscapesBlocks::never).isViewBlocking(CinderscapesBlocks::never)));
        POLYPITE_QUARTZ = CinderscapesRegistry.register("polypite_quartz", new PolypiteQuartzBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTALLINE_QUARTZ)));
        POTTED_POLYPITE_QUARTZ = CinderscapesRegistry.register("potted_polypite_quartz", new FlowerPotBlock(POLYPITE_QUARTZ, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

        // Sulfur Quartz

        SULFUR_QUARTZ_ORE = CinderscapesRegistry.register("sulfur_quartz_ore", new CinderscapesOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE).mapColor(MapColor.COLOR_YELLOW).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops()));
        SULFUR_QUARTZ_BLOCK = CinderscapesRegistry.register("sulfur_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
        CHISELED_SULFUR_QUARTZ_BLOCK = CinderscapesRegistry.register("chiseled_sulfur_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
        SULFUR_QUARTZ_PILLAR = CinderscapesRegistry.register("sulfur_quartz_pillar", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).mapColor(MapColor.COLOR_YELLOW)));
        SULFUR_QUARTZ_STAIRS = CinderscapesRegistry.register("sulfur_quartz_stairs", new StairBlock(SULFUR_QUARTZ_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS).mapColor(MapColor.COLOR_YELLOW)));
        SULFUR_QUARTZ_SLAB = CinderscapesRegistry.register("sulfur_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB).mapColor(MapColor.COLOR_YELLOW)));
        SMOOTH_SULFUR_QUARTZ = CinderscapesRegistry.register("smooth_sulfur_quartz", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ).mapColor(MapColor.COLOR_YELLOW)));
        SMOOTH_SULFUR_QUARTZ_STAIRS = CinderscapesRegistry.register("smooth_sulfur_quartz_stairs", new StairBlock(SMOOTH_SULFUR_QUARTZ.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_STAIRS).mapColor(MapColor.COLOR_YELLOW)));
        SMOOTH_SULFUR_QUARTZ_SLAB = CinderscapesRegistry.register("smooth_sulfur_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_SLAB).mapColor(MapColor.COLOR_YELLOW)));
        SULFUR_QUARTZ_BRICKS = CinderscapesRegistry.register("sulfur_quartz_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).mapColor(MapColor.COLOR_YELLOW)));

        CRYSTALLINE_SULFUR_QUARTZ = CinderscapesRegistry.register("crystalline_sulfur_quartz", new CinderscapesTransparentBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTALLINE_QUARTZ).mapColor(MapColor.COLOR_YELLOW)));
        POLYPITE_SULFUR_QUARTZ = CinderscapesRegistry.register("polypite_sulfur_quartz", new PolypiteQuartzBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTALLINE_SULFUR_QUARTZ)));
        POTTED_POLYPITE_SULFUR_QUARTZ = CinderscapesRegistry.register("potted_polypite_sulfur_quartz", new FlowerPotBlock(POLYPITE_SULFUR_QUARTZ, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

        // Rose Quartz

        ROSE_QUARTZ_ORE = CinderscapesRegistry.register("rose_quartz_ore", new CinderscapesOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE).mapColor(MapColor.COLOR_PINK).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops()));
        ROSE_QUARTZ_BLOCK = CinderscapesRegistry.register("rose_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.COLOR_PINK)));
        CHISELED_ROSE_QUARTZ_BLOCK = CinderscapesRegistry.register("chiseled_rose_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.COLOR_PINK)));
        ROSE_QUARTZ_PILLAR = CinderscapesRegistry.register("rose_quartz_pillar", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).mapColor(MapColor.COLOR_PINK)));
        ROSE_QUARTZ_STAIRS = CinderscapesRegistry.register("rose_quartz_stairs", new StairBlock(ROSE_QUARTZ_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS).mapColor(MapColor.COLOR_PINK)));
        ROSE_QUARTZ_SLAB = CinderscapesRegistry.register("rose_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB).mapColor(MapColor.COLOR_PINK)));
        SMOOTH_ROSE_QUARTZ = CinderscapesRegistry.register("smooth_rose_quartz", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ).mapColor(MapColor.COLOR_PINK)));
        SMOOTH_ROSE_QUARTZ_STAIRS = CinderscapesRegistry.register("smooth_rose_quartz_stairs", new StairBlock(SMOOTH_ROSE_QUARTZ.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_STAIRS).mapColor(MapColor.COLOR_PINK)));
        SMOOTH_ROSE_QUARTZ_SLAB = CinderscapesRegistry.register("smooth_rose_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_SLAB).mapColor(MapColor.COLOR_PINK)));
        ROSE_QUARTZ_BRICKS = CinderscapesRegistry.register("rose_quartz_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).mapColor(MapColor.COLOR_PINK)));

        CRYSTALLINE_ROSE_QUARTZ = CinderscapesRegistry.register("crystalline_rose_quartz", new CinderscapesTransparentBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTALLINE_QUARTZ).mapColor(MapColor.COLOR_PINK)));
        POLYPITE_ROSE_QUARTZ = CinderscapesRegistry.register("polypite_rose_quartz", new PolypiteQuartzBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTALLINE_ROSE_QUARTZ)));
        POTTED_POLYPITE_ROSE_QUARTZ = CinderscapesRegistry.register("potted_polypite_rose_quartz", new FlowerPotBlock(POLYPITE_ROSE_QUARTZ, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

        // Smoky Quartz

        SMOKY_QUARTZ_ORE = CinderscapesRegistry.register("smoky_quartz_ore", new CinderscapesOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE).mapColor(MapColor.COLOR_GRAY).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops()));
        SMOKY_QUARTZ_BLOCK = CinderscapesRegistry.register("smoky_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.COLOR_GRAY)));
        CHISELED_SMOKY_QUARTZ_BLOCK = CinderscapesRegistry.register("chiseled_smoky_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.COLOR_GRAY)));
        SMOKY_QUARTZ_PILLAR = CinderscapesRegistry.register("smoky_quartz_pillar", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).mapColor(MapColor.COLOR_GRAY)));
        SMOKY_QUARTZ_STAIRS = CinderscapesRegistry.register("smoky_quartz_stairs", new StairBlock(SMOKY_QUARTZ_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS).mapColor(MapColor.COLOR_GRAY)));
        SMOKY_QUARTZ_SLAB = CinderscapesRegistry.register("smoky_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB).mapColor(MapColor.COLOR_GRAY)));
        SMOOTH_SMOKY_QUARTZ = CinderscapesRegistry.register("smooth_smoky_quartz", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ).mapColor(MapColor.COLOR_GRAY)));
        SMOOTH_SMOKY_QUARTZ_STAIRS = CinderscapesRegistry.register("smooth_smoky_quartz_stairs", new StairBlock(SMOOTH_SMOKY_QUARTZ.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_STAIRS).mapColor(MapColor.COLOR_GRAY)));
        SMOOTH_SMOKY_QUARTZ_SLAB = CinderscapesRegistry.register("smooth_smoky_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_SLAB).mapColor(MapColor.COLOR_GRAY)));
        SMOKY_QUARTZ_BRICKS = CinderscapesRegistry.register("smoky_quartz_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).mapColor(MapColor.COLOR_GRAY)));

        CRYSTALLINE_SMOKY_QUARTZ = CinderscapesRegistry.register("crystalline_smoky_quartz", new CinderscapesTransparentBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTALLINE_QUARTZ).mapColor(MapColor.COLOR_GRAY)));
        POLYPITE_SMOKY_QUARTZ = CinderscapesRegistry.register("polypite_smoky_quartz", new PolypiteQuartzBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTALLINE_SMOKY_QUARTZ)));
        POTTED_POLYPITE_SMOKY_QUARTZ = CinderscapesRegistry.register("potted_polypite_smoky_quartz", new FlowerPotBlock(POLYPITE_SMOKY_QUARTZ, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

    }

    private static boolean never(BlockState blockState, BlockGetter blockView, BlockPos pos) {
        return false;
    }

    private static boolean never(BlockState blockState, BlockGetter blockView, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}
