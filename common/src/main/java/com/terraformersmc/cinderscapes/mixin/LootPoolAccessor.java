package com.terraformersmc.cinderscapes.mixin;

import com.terraformersmc.cinderscapes.loottables.CinderscapesLootPoolAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LootPool.class)
public abstract class LootPoolAccessor implements CinderscapesLootPoolAccessor {
    @Invoker("<init>")
    private static LootPool cinderscapes$create(List<LootPoolEntryContainer> entries,
            List<LootItemCondition> conditions, List<LootItemFunction> functions,
            NumberProvider rolls, NumberProvider bonusRolls, Optional<String> name) {
        throw new AssertionError();
    }
    @Shadow @Final private List<LootPoolEntryContainer> entries;
    @Shadow @Final private List<LootItemCondition> conditions;
    @Shadow @Final private List<LootItemFunction> functions;
    @Shadow private NumberProvider rolls;
    @Shadow private NumberProvider bonusRolls;
    @Shadow private String name;

    @Override
    public LootPool cinderscapes$withEntries(List<LootPoolEntryContainer> additions) {
        List<LootPoolEntryContainer> merged = new ArrayList<>(entries);
        merged.addAll(additions);
        return cinderscapes$create(merged, conditions, functions, rolls, bonusRolls, Optional.ofNullable(name));
    }
}

