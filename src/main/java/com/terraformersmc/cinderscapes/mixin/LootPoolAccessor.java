package com.terraformersmc.cinderscapes.mixin;

import com.terraformersmc.cinderscapes.loottables.CinderscapesLootPoolAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LootPool.class)
public abstract class LootPoolAccessor implements CinderscapesLootPoolAccessor {
    @Invoker("<init>")
    private static LootPool cinderscapes$create(List<LootPoolEntryContainer> entries,
            Optional<Holder<LootItemCondition>> condition, Optional<Holder<LootItemFunction>> modifier,
            Holder<ContextIntProvider> rolls, Holder<ContextFloatProvider> bonusRolls, Optional<String> name) {
        throw new AssertionError();
    }
    @Shadow @Final private List<LootPoolEntryContainer> entries;
    @Shadow @Final private Optional<Holder<LootItemCondition>> condition;
    @Shadow @Final private Optional<Holder<LootItemFunction>> modifier;
    @Shadow private Holder<ContextIntProvider> rolls;
    @Shadow private Holder<ContextFloatProvider> bonusRolls;
    @Shadow private String name;

    @Override
    public LootPool cinderscapes$withEntries(List<LootPoolEntryContainer> additions) {
        List<LootPoolEntryContainer> merged = new ArrayList<>(entries);
        merged.addAll(additions);
        return cinderscapes$create(merged, condition, modifier, rolls, bonusRolls, Optional.ofNullable(name));
    }
}
