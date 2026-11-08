package com.terraformersmc.cinderscapes.gametest;

import com.terraformersmc.cinderscapes.block.*;
import com.terraformersmc.cinderscapes.config.CinderscapesConfig;
import com.terraformersmc.cinderscapes.init.*;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.filler.RandomSimpleFiller;
import com.terraformersmc.terraform.shapes.impl.filler.SimpleFiller;
import com.terraformersmc.terraform.shapes.impl.validator.AirValidator;
import com.terraformersmc.terraform.shapes.impl.validator.SafelistValidator;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.FunctionGameTestInstance;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@EventBusSubscriber(modid = "cinderscapes")
public final class CinderscapesGameTests {
    private static final String TEMPLATE = "empty";
    private CinderscapesGameTests() { }

    @SubscribeEvent
    public static void registerGameTests(RegisterGameTestsEvent event) {
        Holder<TestEnvironmentDefinition> environment = event.registerEnvironment(
                id("mechanics"), new TestEnvironmentDefinition.AllOf());
        register(event, environment, "all_registrations_and_worldgen_keys_are_present", CinderscapesGameTests::allRegistrationsAndWorldgenKeysArePresent);
        register(event, environment, "every_block_has_item_and_loot", CinderscapesGameTests::everyBlockHasItemAndLoot);
        register(event, environment, "recipes_tags_and_food_are_loaded", CinderscapesGameTests::recipesTagsAndFoodAreLoaded);
        register(event, environment, "all_plant_soils_and_nodzol_work", CinderscapesGameTests::allPlantSoilsAndNodzolWork);
        register(event, environment, "photofern_bonemeal_creates_both_tall_halves", CinderscapesGameTests::photofernBonemealCreatesBothTallHalves);
        register(event, environment, "bramble_harvest_damage_and_immunities_work", CinderscapesGameTests::brambleHarvestDamageAndImmunitiesWork);
        register(event, environment, "ash_never_melts_and_drops_every_layer", CinderscapesGameTests::ashNeverMeltsAndDropsEveryLayer);
        register(event, environment, "axes_strip_every_stem_and_hypha", CinderscapesGameTests::axesStripEveryStemAndHypha);
        register(event, environment, "ectoplasm_and_polypite_support_rules_work", CinderscapesGameTests::ectoplasmAndPolypiteSupportRulesWork);
        register(event, environment, "netherrack_bonemeal_copies_umbral_nylium", CinderscapesGameTests::netherrackBonemealCopiesUmbralNylium);
        register(event, environment, "every_compostable_has_its_exact_chance", CinderscapesGameTests::everyCompostableHasItsExactChance);
        register(event, environment, "mason_trades_and_piglin_barter_expose_all_quartz", CinderscapesGameTests::masonTradesAndPiglinBarterExposeAllQuartz);
        register(event, environment, "light_and_transparent_block_properties_are_correct", CinderscapesGameTests::lightAndTransparentBlockPropertiesAreCorrect);
        register(event, environment, "block_entities_creative_tab_and_config_are_wired", CinderscapesGameTests::blockEntitiesCreativeTabAndConfigAreWired);
        register(event, environment, "every_custom_feature_has_configured_data", CinderscapesGameTests::everyCustomFeatureHasConfiguredData);
        register(event, environment, "terraform_position_uses_mojmap_blockpos_method", CinderscapesGameTests::terraformPositionUsesMojmapBlockPosMethod);
        register(event, environment, "terraform_world_access_uses_mojmap_methods", CinderscapesGameTests::terraformWorldAccessUsesMojmapMethods);
    }

    private static void register(RegisterGameTestsEvent event, Holder<TestEnvironmentDefinition> environment,
                                 String name, java.util.function.Consumer<GameTestHelper> test) {
        TestData<Holder<TestEnvironmentDefinition>> data = new TestData<>(
                environment, Identifier.withDefaultNamespace(TEMPLATE), 200, 0, true);
        event.registerTest(id(name), new DirectTestInstance(data, test));
    }

    private static final class DirectTestInstance extends GameTestInstance {
        private final java.util.function.Consumer<GameTestHelper> test;
        private DirectTestInstance(TestData<Holder<TestEnvironmentDefinition>> data,
                                   java.util.function.Consumer<GameTestHelper> test) {
            super(data);
            this.test = test;
        }
        @Override public void run(GameTestHelper helper) { test.accept(helper); }
        @Override public MapCodec<? extends GameTestInstance> codec() { return FunctionGameTestInstance.CODEC; }
        @Override protected MutableComponent typeDescription() { return Component.literal("Cinderscapes mechanics"); }
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath("cinderscapes", path);
    }

    public static void allRegistrationsAndWorldgenKeysArePresent(GameTestHelper helper) {
        try {
            assertRegisteredFields(helper, CinderscapesBlocks.class, Block.class, BuiltInRegistries.BLOCK);
            assertRegisteredFields(helper, CinderscapesItems.class, Item.class, BuiltInRegistries.ITEM);
            assertRegisteredFields(helper, CinderscapesFeatures.class, net.minecraft.world.level.levelgen.feature.Feature.class, BuiltInRegistries.FEATURE);
            assertRegisteredFields(helper, CinderscapesPlacementModifierTypes.class,
                    net.minecraft.world.level.levelgen.placement.PlacementModifierType.class, BuiltInRegistries.PLACEMENT_MODIFIER_TYPE);
            assertRegisteredFields(helper, CinderscapesSoundEvents.class, net.minecraft.sounds.SoundEvent.class, BuiltInRegistries.SOUND_EVENT);
            assertDynamicKeys(helper, CinderscapesBiomes.class, Registries.BIOME);
            assertDynamicKeys(helper, CinderscapesConfiguredFeatures.class, Registries.CONFIGURED_FEATURE);
            assertDynamicKeys(helper, CinderscapesPlacedFeatures.class, Registries.PLACED_FEATURE);
            assertDynamicKeys(helper, CinderscapesArmorTrimMaterials.class, Registries.TRIM_MATERIAL);
        } catch (Exception exception) {
            throw new RuntimeException("Unable to inspect Cinderscapes registries", exception);
        }
        helper.succeed();
    }

    public static void terraformPositionUsesMojmapBlockPosMethod(GameTestHelper helper) {
        BlockPos result = Position.of(3.75D, -2.25D, 8.99D).toBlockPos();
        helper.assertTrue(result.equals(new BlockPos(3, -3, 8)),
                "Terraform Position produced the wrong floored BlockPos: " + result);
        helper.succeed();
    }

    public static void terraformWorldAccessUsesMojmapMethods(GameTestHelper helper) {
        BlockPos relative = new BlockPos(1, 2, 1);
        Position position = Position.of(helper.absolutePos(relative));
        helper.setBlock(relative, Blocks.AIR);

        helper.assertTrue(AirValidator.of((LevelReader) helper.getLevel()).test(position),
                "Terraform AirValidator failed on air");
        helper.assertTrue(SafelistValidator.of(helper.getLevel(), Blocks.NETHERRACK.defaultBlockState()).test(position),
                "Terraform SafelistValidator failed on air");

        SimpleFiller.of(helper.getLevel(), Blocks.NETHERRACK.defaultBlockState()).accept(position);
        helper.assertTrue(helper.getBlockState(relative).is(Blocks.NETHERRACK),
                "Terraform SimpleFiller did not place its block");
        helper.assertTrue(SafelistValidator.of(helper.getLevel(), Blocks.NETHERRACK.defaultBlockState()).test(position),
                "Terraform SafelistValidator rejected a safe state");

        RandomSimpleFiller.of(helper.getLevel(), Blocks.SOUL_SAND.defaultBlockState(),
                net.minecraft.util.RandomSource.create(1L), 1.0F).accept(position);
        helper.assertTrue(helper.getBlockState(relative).is(Blocks.SOUL_SAND),
                "Terraform RandomSimpleFiller did not place at probability 1");
        helper.succeed();
    }

    public static void everyBlockHasItemAndLoot(GameTestHelper helper) {
        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);
            if (!id.getNamespace().equals("cinderscapes") || id.getPath().startsWith("potted_") || id.getPath().contains("wall_sign")) continue;
            helper.assertTrue(block.asItem() != Items.AIR, "No item for " + id);
            helper.assertTrue(block.getLootTable().map(key -> helper.getLevel().getServer().reloadableRegistries().getLootTable(key) != LootTable.EMPTY).orElse(false),
                    "No loot table for " + id);
        }
        helper.succeed();
    }

    public static void recipesTagsAndFoodAreLoaded(GameTestHelper helper) {
        long recipes = helper.getLevel().getServer().getRecipeManager().getRecipes().stream()
                .filter(r -> r.id().identifier().getNamespace().equals("cinderscapes")).count();
        helper.assertTrue(recipes >= 60, "Only " + recipes + " recipes loaded");
        helper.assertTrue(CinderscapesItems.BRAMBLE_BERRIES.getDefaultInstance().has(DataComponents.FOOD), "Berries are not food");
        helper.assertTrue(CinderscapesItems.SCORCHED_STEM.getDefaultInstance().is(ItemTags.LOGS), "Scorched stem absent from logs");
        helper.assertTrue(CinderscapesItems.UMBRAL_STEM.getDefaultInstance().is(ItemTags.LOGS), "Umbral stem absent from logs");
        helper.succeed();
    }

    public static void allPlantSoilsAndNodzolWork(GameTestHelper helper) {
        BlockPos pos = new BlockPos(1, 2, 1);
        for (Block soil : new Block[]{Blocks.NETHERRACK, Blocks.SOUL_SOIL, CinderscapesBlocks.UMBRAL_NYLIUM}) {
            helper.setBlock(pos.below(), soil);
            for (Block plant : new Block[]{CinderscapesBlocks.PYRACINTH, CinderscapesBlocks.CRYSTINIUM,
                    CinderscapesBlocks.PHOTOFERN, CinderscapesBlocks.TWILIGHT_TENDRILS})
                helper.assertTrue(plant.defaultBlockState().canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                        plant + " cannot survive on " + soil);
        }
        for (Block soil : new Block[]{Blocks.NETHERRACK, Blocks.SOUL_SOIL, Blocks.SOUL_SAND, Blocks.GRAVEL}) {
            helper.setBlock(pos.below(), soil);
            helper.assertTrue(CinderscapesBlocks.BRAMBLE_BERRY_BUSH.defaultBlockState().canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                    "Bramble cannot survive on " + soil);
        }
        helper.setBlock(pos.below(), CinderscapesBlocks.NODZOL);
        helper.assertTrue(Blocks.DANDELION.defaultBlockState().canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                "Vanilla plant cannot survive on nodzol");
        helper.succeed();
    }

    public static void photofernBonemealCreatesBothTallHalves(GameTestHelper helper) {
        BlockPos pos = new BlockPos(1, 2, 1);
        helper.setBlock(pos.below(), Blocks.NETHERRACK);
        helper.setBlock(pos.above(), Blocks.AIR);
        helper.setBlock(pos, CinderscapesBlocks.PHOTOFERN);
        helper.assertTrue(CinderscapesBlocks.TALL_PHOTOFERN.defaultBlockState()
                        .canSurvive(helper.getLevel(), helper.absolutePos(pos)),
                "Tall photofern precondition failed; floor=" + helper.getBlockState(pos.below()));
        helper.assertTrue(helper.getBlockState(pos.above()).isAir(), "Tall photofern upper position is occupied");
        BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), helper.getLevel(), helper.absolutePos(pos));
        helper.assertTrue(helper.getBlockState(pos).is(CinderscapesBlocks.TALL_PHOTOFERN)
                && helper.getBlockState(pos).getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER,
                "Missing lower tall photofern");
        helper.assertTrue(helper.getBlockState(pos.above()).is(CinderscapesBlocks.TALL_PHOTOFERN)
                && helper.getBlockState(pos.above()).getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER,
                "Missing upper tall photofern");
        helper.succeed();
    }

    public static void brambleHarvestDamageAndImmunitiesWork(GameTestHelper helper) {
        BlockPos pos = new BlockPos(2, 2, 2);
        BlockState bush = CinderscapesBlocks.BRAMBLE_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3);
        helper.setBlock(pos, bush);
        var player = helper.makeMockPlayer(GameType.SURVIVAL);
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH).useWithoutItem(bush, helper.getLevel(),
                helper.absolutePos(pos), player, hit(helper.absolutePos(pos)));
        helper.assertTrue(helper.getBlockState(pos).getValue(SweetBerryBushBlock.AGE) == 1, "Harvest did not reset age");
        helper.assertItemEntityPresent(CinderscapesItems.BRAMBLE_BERRIES, pos, 5.0);
        bush = bush.setValue(SweetBerryBushBlock.AGE, 2);
        helper.setBlock(pos, bush);
        player.setPos(player.getX() + 0.25, player.getY(), player.getZ());
        player.xOld = player.getX() - 0.25;
        player.invulnerableTime = 0;
        float before = player.getHealth();
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH).entityInside(
                bush, helper.getLevel(), helper.absolutePos(pos), player, InsideBlockEffectApplier.NOOP, false);
        helper.assertTrue(player.getHealth() == before - 1, "Bramble did not damage moving player; health=" + player.getHealth());
        Hoglin hoglin = helper.spawn(EntityType.HOGLIN, pos);
        hoglin.setPos(hoglin.getX() + 0.25, hoglin.getY(), hoglin.getZ());
        hoglin.xOld = hoglin.getX() - 0.25;
        hoglin.invulnerableTime = 0;
        before = hoglin.getHealth();
        ((BrambleBerryBushBlock) CinderscapesBlocks.BRAMBLE_BERRY_BUSH).entityInside(
                bush, helper.getLevel(), helper.absolutePos(pos), hoglin, InsideBlockEffectApplier.NOOP, false);
        helper.assertTrue(hoglin.getHealth() == before, "Bramble damaged immune hoglin");
        helper.succeed();
    }

    public static void ashNeverMeltsAndDropsEveryLayer(GameTestHelper helper) {
        BlockPos pos = new BlockPos(2, 2, 2);
        var player = helper.makeMockPlayer(GameType.SURVIVAL);
        for (int layers = 1; layers <= 8; layers++) {
            BlockState state = CinderscapesBlocks.ASH.defaultBlockState().setValue(SnowLayerBlock.LAYERS, layers);
            helper.setBlock(pos, state);
            state.randomTick(helper.getLevel(), helper.absolutePos(pos), helper.getLevel().getRandom());
            helper.assertTrue(helper.getBlockState(pos).equals(state), "Ash melted at " + layers + " layers");
            int count = Block.getDrops(state, helper.getLevel(), helper.absolutePos(pos), null, player,
                    new ItemStack(Items.IRON_SHOVEL)).stream().filter(s -> s.is(CinderscapesItems.ASH_PILE)).mapToInt(ItemStack::getCount).sum();
            helper.assertTrue(count == layers, "Ash dropped " + count + " instead of " + layers);
        }
        helper.succeed();
    }

    public static void axesStripEveryStemAndHypha(GameTestHelper helper) {
        Block[][] pairs = {{CinderscapesBlocks.SCORCHED_STEM,CinderscapesBlocks.STRIPPED_SCORCHED_STEM},
                {CinderscapesBlocks.SCORCHED_HYPHAE,CinderscapesBlocks.STRIPPED_SCORCHED_HYPHAE},
                {CinderscapesBlocks.UMBRAL_STEM,CinderscapesBlocks.STRIPPED_UMBRAL_STEM},
                {CinderscapesBlocks.UMBRAL_HYPHAE,CinderscapesBlocks.STRIPPED_UMBRAL_HYPHAE}};
        var player = helper.makeMockPlayer(GameType.CREATIVE);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.DIAMOND_AXE));
        for (int i=0;i<pairs.length;i++) {
            BlockPos pos = new BlockPos(1+i,2,1); helper.setBlock(pos,pairs[i][0]);
            BlockPos abs=helper.absolutePos(pos);
            player.getMainHandItem().useOn(new UseOnContext(player, InteractionHand.MAIN_HAND, hit(abs)));
            helper.assertTrue(helper.getBlockState(pos).is(pairs[i][1]), pairs[i][0]+" did not strip");
        }
        helper.succeed();
    }

    public static void ectoplasmAndPolypiteSupportRulesWork(GameTestHelper helper) {
        GhastlyEctoplasmBlock ecto=(GhastlyEctoplasmBlock)CinderscapesBlocks.GHASTLY_ECTOPLASM;
        BlockPos p=new BlockPos(3,5,3); helper.setBlock(p.above(),Blocks.NETHERRACK); helper.setBlock(p,ecto);
        helper.setBlock(p.below(),ecto); helper.setBlock(p.below(2),ecto);
        helper.assertTrue(ecto.evaluateState(ecto.defaultBlockState(),helper.absolutePos(p),helper.getLevel()).getValue(GhastlyEctoplasmBlock.TYPE)== GhastlyEctoplasmBlock.Type.TOP,"Wrong ectoplasm top");
        helper.assertTrue(ecto.evaluateState(ecto.defaultBlockState(),helper.absolutePos(p.below()),helper.getLevel()).getValue(GhastlyEctoplasmBlock.TYPE)== GhastlyEctoplasmBlock.Type.MIDDLE,"Wrong ectoplasm middle");
        helper.assertTrue(ecto.evaluateState(ecto.defaultBlockState(),helper.absolutePos(p.below(2)),helper.getLevel()).getValue(GhastlyEctoplasmBlock.TYPE)== GhastlyEctoplasmBlock.Type.BOTTOM,"Wrong ectoplasm bottom");
        for (PolypiteQuartzBlock b : new PolypiteQuartzBlock[]{(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_QUARTZ,(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_ROSE_QUARTZ,(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_SMOKY_QUARTZ,(PolypiteQuartzBlock)CinderscapesBlocks.POLYPITE_SULFUR_QUARTZ})
            for(Direction d:Direction.values()) { BlockState s=b.stateOf(d); helper.assertTrue(!s.getShape(helper.getLevel(),helper.absolutePos(p)).isEmpty(),"Missing shape "+d); }
        helper.succeed();
    }

    public static void netherrackBonemealCopiesUmbralNylium(GameTestHelper helper) {
        BlockPos pos=new BlockPos(2,2,2); helper.setBlock(pos,Blocks.NETHERRACK); helper.setBlock(pos.east(),CinderscapesBlocks.UMBRAL_NYLIUM);
        ((BonemealableBlock)Blocks.NETHERRACK).performBonemeal(helper.getLevel(),helper.getLevel().getRandom(),helper.absolutePos(pos),Blocks.NETHERRACK.defaultBlockState());
        helper.assertBlockPresent(CinderscapesBlocks.UMBRAL_NYLIUM,pos); helper.succeed();
    }

    public static void everyCompostableHasItsExactChance(GameTestHelper helper) {
        assertCompost(helper,CinderscapesItems.BRAMBLE_BERRIES,0.3F);
        for(Item i:new Item[]{CinderscapesItems.SCORCHED_SHRUB,CinderscapesItems.SCORCHED_SPROUTS,CinderscapesItems.SCORCHED_TENDRILS,CinderscapesItems.TWILIGHT_FESCUES,CinderscapesItems.TWILIGHT_TENDRILS}) assertCompost(helper,i,0.3F);
        for(Item i:new Item[]{CinderscapesItems.CRYSTINIUM,CinderscapesItems.GHASTLY_ECTOPLASM,CinderscapesItems.LUMINOUS_POD,CinderscapesItems.PHOTOFERN,CinderscapesItems.PYRACINTH,CinderscapesItems.TALL_PHOTOFERN,CinderscapesItems.UMBRAL_FUNGUS}) assertCompost(helper,i,0.65F);
        for(Item i:new Item[]{CinderscapesItems.TWILIGHT_VINE_BLOCK,CinderscapesItems.UMBRAL_FLESH_BLOCK,CinderscapesItems.UMBRAL_WART_BLOCK}) assertCompost(helper,i,0.85F);
        helper.succeed();
    }

    public static void masonTradesAndPiglinBarterExposeAllQuartz(GameTestHelper helper) {
        var trades = new it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap<List<net.minecraft.world.entity.npc.villager.VillagerTrades.ItemListing>>();
        trades.put(4, new ArrayList<>());
        trades.put(5, new ArrayList<>());
        CinderscapesTrades.addMasonTrades(trades);
        helper.assertTrue(trades.get(4).size() == 3, "Mason level-four trade count wrong: " + trades.get(4).size());
        helper.assertTrue(trades.get(5).size() == 6, "Mason level-five trade count wrong: " + trades.get(5).size());
        Piglin trader=helper.spawn(EntityType.PIGLIN,new BlockPos(2,2,2));
        Set<Item> tradeInputs=new HashSet<>(), tradeOutputs=new HashSet<>();
        for (var level : List.of(trades.get(4), trades.get(5))) for (var listing : level) {
            var offer=listing.getOffer(helper.getLevel(),trader,helper.getLevel().getRandom());
            helper.assertTrue(offer != null,"Mason listing produced no offer");
            tradeInputs.add(offer.getBaseCostA().getItem());
            tradeOutputs.add(offer.getResult().getItem());
        }
        helper.assertTrue(tradeInputs.containsAll(Set.of(CinderscapesItems.ROSE_QUARTZ,CinderscapesItems.SMOKY_QUARTZ,CinderscapesItems.SULFUR_QUARTZ,Items.EMERALD)),"Mason trade inputs incomplete: "+tradeInputs);
        helper.assertTrue(tradeOutputs.containsAll(Set.of(Items.EMERALD,CinderscapesItems.ROSE_QUARTZ_PILLAR,CinderscapesItems.ROSE_QUARTZ_BLOCK,CinderscapesItems.SULFUR_QUARTZ_PILLAR,CinderscapesItems.SULFUR_QUARTZ_BLOCK,CinderscapesItems.SMOKY_QUARTZ_PILLAR,CinderscapesItems.SMOKY_QUARTZ_BLOCK)),"Mason trade outputs incomplete: "+tradeOutputs);
        LootTable table=helper.getLevel().getServer().reloadableRegistries().getLootTable(BuiltInLootTables.PIGLIN_BARTERING);
        Piglin piglin=helper.spawn(EntityType.PIGLIN,new BlockPos(2,2,2)); Set<Item> found=new HashSet<>();
        LootParams params=new LootParams.Builder(helper.getLevel()).withParameter(LootContextParams.THIS_ENTITY,piglin).create(LootContextParamSets.PIGLIN_BARTER);
        for(int i=0;i<2000&&found.size()<3;i++) table.getRandomItems(params).stream().map(ItemStack::getItem)
                .filter(x->x==CinderscapesItems.ROSE_QUARTZ||x==CinderscapesItems.SMOKY_QUARTZ||x==CinderscapesItems.SULFUR_QUARTZ).forEach(found::add);
        helper.assertTrue(found.size()==3,"Missing quartz barter variants: "+found); helper.succeed();
    }

    public static void lightAndTransparentBlockPropertiesAreCorrect(GameTestHelper helper) {
        helper.assertTrue(CinderscapesBlocks.UMBRAL_FLESH_BLOCK.defaultBlockState().getLightEmission()==15,"Flesh light wrong");
        helper.assertTrue(CinderscapesBlocks.LUMINOUS_POD.defaultBlockState().getLightEmission()==15,"Pod light wrong");
        helper.assertTrue(CinderscapesBlocks.TWILIGHT_TENDRILS.defaultBlockState().getLightEmission()==15,"Tendril light wrong");
        helper.assertTrue(!CinderscapesBlocks.CRYSTALLINE_QUARTZ.defaultBlockState().isSuffocating(helper.getLevel(),helper.absolutePos(new BlockPos(1,2,1))),"Crystal suffocates");
        helper.succeed();
    }

    public static void blockEntitiesCreativeTabAndConfigAreWired(GameTestHelper helper) {
        for (Block block : new Block[]{CinderscapesBlocks.SCORCHED_SIGN,CinderscapesBlocks.SCORCHED_WALL_SIGN,CinderscapesBlocks.UMBRAL_SIGN,CinderscapesBlocks.UMBRAL_WALL_SIGN})
            helper.assertTrue(net.minecraft.world.level.block.entity.BlockEntityType.SIGN.isValid(block.defaultBlockState()),"Sign block entity rejects "+block);
        for (Block block : new Block[]{CinderscapesBlocks.SCORCHED_HANGING_SIGN,CinderscapesBlocks.SCORCHED_WALL_HANGING_SIGN,CinderscapesBlocks.UMBRAL_HANGING_SIGN,CinderscapesBlocks.UMBRAL_WALL_HANGING_SIGN})
            helper.assertTrue(net.minecraft.world.level.block.entity.BlockEntityType.HANGING_SIGN.isValid(block.defaultBlockState()),"Hanging sign block entity rejects "+block);
        for (Block block : new Block[]{CinderscapesBlocks.SCORCHED_SHELF,CinderscapesBlocks.UMBRAL_SHELF})
            helper.assertTrue(net.minecraft.world.level.block.entity.BlockEntityType.SHELF.isValid(block.defaultBlockState()),"Shelf block entity rejects "+block);
        helper.assertTrue(BuiltInRegistries.CREATIVE_MODE_TAB.containsKey(id("items")),"Cinderscapes creative tab missing");
        helper.assertTrue(CinderscapesConfig.INSTANCE.polypiteLuminance >= 0 && CinderscapesConfig.INSTANCE.polypiteLuminance <= 15,"Polypite luminance outside config bounds");
        helper.assertTrue(CinderscapesBlocks.POLYPITE_QUARTZ.defaultBlockState().getLightEmission()==CinderscapesConfig.INSTANCE.polypiteLuminance,"Polypite ignores configured luminance");
        helper.assertTrue(((BrambleBerryBushBlock)CinderscapesBlocks.BRAMBLE_BERRY_BUSH).getCloneItemStack(helper.getLevel(),helper.absolutePos(new BlockPos(1,2,1)),CinderscapesBlocks.BRAMBLE_BERRY_BUSH.defaultBlockState(),false).is(CinderscapesItems.BRAMBLE_BERRIES),"Bramble clone item wrong");
        helper.succeed();
    }

    public static void everyCustomFeatureHasConfiguredData(GameTestHelper helper) {
        Registry<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?,?>> configured=helper.getLevel().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        Set<net.minecraft.world.level.levelgen.feature.Feature<?>> used=new HashSet<>();
        for (var entry:configured.entrySet()) if(entry.getKey().identifier().getNamespace().equals("cinderscapes")) used.add(entry.getValue().feature());
        for (Field field:CinderscapesFeatures.class.getFields()) try {
            if(Modifier.isStatic(field.getModifiers())&&net.minecraft.world.level.levelgen.feature.Feature.class.isAssignableFrom(field.getType())) {
                var feature=(net.minecraft.world.level.levelgen.feature.Feature<?>)field.get(null);
                if (field.getName().equals("BLACKSTONE_WEEPING_VINES")) continue; // Legacy registered type; current data intentionally uses vanilla weeping_vines.
                helper.assertTrue(used.contains(feature),"Feature has no configured data: "+field.getName());
            }
        } catch (IllegalAccessException exception) { throw new RuntimeException(exception); }
        helper.assertTrue(used.size()>=10,"Too few custom feature types represented: "+used.size());
        helper.succeed();
    }

    private static BlockHitResult hit(BlockPos p){return new BlockHitResult(Vec3.atCenterOf(p),Direction.UP,p,false);}
    private static void assertCompost(GameTestHelper h,Item item,float chance){var data=item.builtInRegistryHolder().getData(NeoForgeDataMaps.COMPOSTABLES);h.assertTrue(data!=null&&data.chance()==chance,item+" compost chance wrong: "+data);}
    @SuppressWarnings({"rawtypes","unchecked"}) private static void assertRegisteredFields(GameTestHelper h,Class<?> owner,Class<?> type,Registry<?> registry)throws Exception{for(Field f:owner.getFields()){if(!Modifier.isStatic(f.getModifiers())||!type.isAssignableFrom(f.getType()))continue;Object v=f.get(null);h.assertTrue(v!=null,owner.getSimpleName()+"."+f.getName()+" is null");h.assertTrue(((Registry)registry).getKey(v).getNamespace().equals("cinderscapes"),f.getName()+" not registered");}}
    @SuppressWarnings({"rawtypes","unchecked"}) private static void assertDynamicKeys(GameTestHelper h,Class<?> owner,ResourceKey<? extends Registry<?>> registryKey)throws Exception{Registry registry=h.getLevel().registryAccess().lookupOrThrow((ResourceKey)registryKey);for(Field f:owner.getDeclaredFields()){if(!Modifier.isStatic(f.getModifiers())||!ResourceKey.class.isAssignableFrom(f.getType()))continue;f.setAccessible(true);ResourceKey key=(ResourceKey)f.get(null);if(key!=null&&key.registry().equals(registryKey.identifier()))h.assertTrue(registry.containsKey(key.identifier()),"Missing "+key.identifier());}}
}
