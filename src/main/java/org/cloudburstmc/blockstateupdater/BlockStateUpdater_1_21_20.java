package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

import java.util.function.Function;

public class BlockStateUpdater_1_21_20 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_21_20();

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext ctx) {
        ctx.addUpdater(1, 21, 20)
                .match("name", "minecraft:light_block")
                .visit("states")
                .edit("block_light_level", helper -> helper.getRootTag().put("name", "minecraft:light_block_" + helper.getTag()))
                .remove("block_light_level");


        this.addTypeUpdater(ctx, "minecraft:sandstone", "sand_stone_type", type -> {
            switch (type) {
                case "cut":
                    return "minecraft:cut_sandstone";
                case "heiroglyphs":
                    return "minecraft:chiseled_sandstone";
                case "smooth":
                    return "minecraft:smooth_sandstone";
                case "default":
                default:
                    return "minecraft:sandstone";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:quartz_block", "chisel_type", type -> {
            switch (type) {
                case "chiseled":
                    return "minecraft:chiseled_quartz_block";
                case "lines":
                    return "minecraft:quartz_pillar";
                case "smooth":
                    return "minecraft:smooth_quartz";
                case "default":
                default:
                    return "minecraft:quartz_block";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:red_sandstone", "sand_stone_type", type -> {
            switch (type) {
                case "cut":
                    return "minecraft:cut_red_sandstone";
                case "heiroglyphs":
                    return "minecraft:chiseled_red_sandstone";
                case "smooth":
                    return "minecraft:smooth_red_sandstone";
                case "default":
                default:
                    return "minecraft:red_sandstone";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:sand", "sand_type", type -> {
            switch (type) {
                case "red":
                    return "minecraft:red_sand";
                case "normal":
                default:
                    return "minecraft:sand";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:dirt", "dirt_type", type -> {
            switch (type) {
                case "coarse":
                    return "minecraft:coarse_dirt";
                case "normal":
                default:
                    return "minecraft:dirt";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:anvil", "damage", type -> {
            switch (type) {
                case "broken":
                    return "minecraft:damaged_anvil";
                case "slightly_damaged":
                    return "minecraft:chipped_anvil";
                case "very_damaged":
                    return "minecraft:deprecated_anvil";
                case "undamaged":
                default:
                    return "minecraft:anvil";
            }
        });

        // Vanilla does not use updater for this block for some reason
        ctx.addUpdater(1, 21, 20, false, false)
                .match("name", "minecraft:yellow_flower")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:dandelion");
                });
    }

    private void addTypeUpdater(CompoundTagUpdaterContext context, String identifier, String typeState, Function<String, String> rename) {
        context.addUpdater(1, 21, 20)
                .match("name", identifier)
                .visit("states")
                .edit(typeState, helper -> helper.getRootTag().put("name", rename.apply((String) helper.getTag())))
                .remove(typeState);

    }
}
