import fs from "node:fs";
import path from "node:path";

const root = path.resolve("src/main/generated/data/cinderscapes");
const sourceRoot = path.resolve("../Cinderscapes-26.2/src/main/generated/data/cinderscapes");
const configuredRoot = path.join(root, "worldgen", "configured_feature");
const featureRoot = path.join(root, "worldgen", "feature");

if (!root.endsWith(path.join("src", "main", "generated", "data", "cinderscapes"))) {
    throw new Error(`Refusing to replace unexpected target: ${root}`);
}
fs.rmSync(path.join(root, "worldgen"), { recursive: true, force: true });
fs.cpSync(path.join(sourceRoot, "worldgen"), path.join(root, "worldgen"), { recursive: true });
fs.cpSync(path.join(sourceRoot, "trim_material"), path.join(root, "trim_material"), { recursive: true, force: true });

function walk(directory) {
    return fs.readdirSync(directory, { withFileTypes: true }).flatMap((entry) => {
        const target = path.join(directory, entry.name);
        return entry.isDirectory() ? walk(target) : [target];
    });
}

function blockStates(value) {
    if (Array.isArray(value)) return value.map(blockStates);
    if (value === null || typeof value !== "object") return value;

    if (typeof value.Name === "string") {
        if (value.Properties && Object.keys(value.Properties).length > 0) {
            return { id: value.Name, properties: blockStates(value.Properties) };
        }
        return value.Name;
    }

    const converted = {};
    for (const [key, child] of Object.entries(value)) converted[key] = blockStates(child);
    return converted;
}

for (const file of walk(configuredRoot).filter((name) => name.endsWith(".json"))) {
    const relative = path.relative(configuredRoot, file);
    const original = JSON.parse(fs.readFileSync(file, "utf8"));
    let migrated = { type: original.type, ...(original.config ?? {}) };
    migrated = blockStates(migrated);

    if (migrated.to_place?.type === "minecraft:simple_state_provider") {
        migrated.to_place = migrated.to_place.state;
    }
    if (migrated.vegetation_state?.type === "minecraft:weighted_state_provider") {
        migrated.vegetation_state.type = "minecraft:weighted";
    }

    const destination = path.join(featureRoot, relative);
    fs.mkdirSync(path.dirname(destination), { recursive: true });
    fs.writeFileSync(destination, `${JSON.stringify(migrated, null, 2)}\n`);
}
fs.rmSync(configuredRoot, { recursive: true });

for (const file of walk(path.join(root, "worldgen", "placed_feature")).filter((name) => name.endsWith(".json"))) {
    let migrated = blockStates(JSON.parse(fs.readFileSync(file, "utf8")));
    migrated.placement = migrated.placement.map((modifier) => {
        if (modifier.type !== "minecraft:random_offset") return modifier;
        const { xz_spread, y_spread, ...rest } = modifier;
        return { ...rest, type: "minecraft:offset", x: xz_spread, y: y_spread, z: xz_spread };
    });
    fs.writeFileSync(file, `${JSON.stringify(migrated, null, 2)}\n`);
}

for (const file of walk(path.join(root, "trim_material")).filter((name) => name.endsWith(".json"))) {
    const migrated = JSON.parse(fs.readFileSync(file, "utf8"));
    if (migrated.asset_name) {
        migrated.palette_id = `cinderscapes:trim/${migrated.asset_name}`;
        delete migrated.asset_name;
    }
    fs.writeFileSync(file, `${JSON.stringify(migrated, null, 2)}\n`);
}

for (const file of walk(path.join(root, "advancement", "recipes")).filter((name) => name.endsWith(".json"))) {
    const migrated = JSON.parse(fs.readFileSync(file, "utf8"));
    for (const criterion of Object.values(migrated.criteria ?? {})) {
        if (criterion.trigger === "minecraft:recipe_unlocked" && criterion.conditions?.recipe) {
            criterion.conditions.recipes = criterion.conditions.recipe;
            delete criterion.conditions.recipe;
        }
    }
    fs.writeFileSync(file, `${JSON.stringify(migrated, null, 2)}\n`);
}

for (const file of walk(path.join(root, "recipe")).filter((name) => name.endsWith(".json"))) {
    const migrated = JSON.parse(fs.readFileSync(file, "utf8"));
    if (["minecraft:smelting", "minecraft:blasting"].includes(migrated.type) && migrated.cookingtime === undefined) {
        migrated.cookingtime = 200;
    }
    fs.writeFileSync(file, `${JSON.stringify(migrated, null, 2)}\n`);
}

function lootCondition(condition) {
    if (condition.condition === "minecraft:match_tool" && condition.predicate?.predicates?.["minecraft:enchantments"]?.some(
        (entry) => entry.enchantments === "minecraft:silk_touch"
    )) return "minecraft:tool/can_silk_touch";

    if (condition.condition === "minecraft:block_state_property") {
        return { type: "minecraft:match_block", blocks: condition.block, state: condition.properties };
    }

    const migrated = {};
    for (const [key, value] of Object.entries(condition)) {
        if (key === "condition") migrated.type = value;
        else if (key === "term") migrated.term = lootCondition(value);
        else migrated[key] = lootNode(value);
    }
    return migrated;
}

function lootModifier(modifier) {
    const migrated = {};
    for (const [key, value] of Object.entries(modifier)) {
        migrated[key === "function" ? "type" : key] = lootNode(value);
    }
    return migrated;
}

function lootNode(value) {
    if (Array.isArray(value)) return value.map(lootNode);
    if (value === null || typeof value !== "object") return value;
    const migrated = {};
    for (const [key, child] of Object.entries(value)) {
        if (key === "conditions") {
            if (child.length !== 1) throw new Error("Unexpected multi-condition loot entry");
            migrated.condition = lootCondition(child[0]);
        } else if (key === "functions") {
            const modifiers = child.map(lootModifier);
            migrated.modifier = modifiers.length === 1 ? modifiers[0] : modifiers;
        } else {
            migrated[key] = lootNode(child);
        }
    }
    return migrated;
}

for (const file of walk(path.join(root, "loot_table")).filter((name) => name.endsWith(".json"))) {
    const migrated = lootNode(JSON.parse(fs.readFileSync(file, "utf8")));
    fs.writeFileSync(file, `${JSON.stringify(migrated, null, 2)}\n`);
}
