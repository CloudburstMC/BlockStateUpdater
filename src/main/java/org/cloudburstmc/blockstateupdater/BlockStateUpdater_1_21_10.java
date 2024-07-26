package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

import java.util.function.Function;

public class BlockStateUpdater_1_21_10 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_21_10();

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext ctx) {
        this.addTypeUpdater(ctx, "minecraft:stone_block_slab2", "stone_slab_type_2", type -> {
            switch (type) {
                case "prismarine_rough":
                    return "minecraft:prismarine_slab";
                case "prismarine_dark":
                    return "minecraft:dark_prismarine_slab";
                case "smooth_sandstone":
                    return "minecraft:smooth_sandstone_slab";
                case "purpur":
                    return "minecraft:purpur_slab";
                case "red_nether_brick":
                    return "minecraft:red_nether_brick_slab";
                case "prismarine_brick":
                    return "minecraft:prismarine_brick_slab";
                case "mossy_cobblestone":
                    return "minecraft:mossy_cobblestone_slab";
                case "red_sandstone":
                default:
                    return "minecraft:red_sandstone_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:stone_block_slab3", "stone_slab_type_3", type -> {
            switch (type) {
                case "smooth_red_sandstone":
                    return "minecraft:smooth_red_sandstone_slab";
                case "polished_granite":
                    return "minecraft:polished_granite_slab";
                case "granite":
                    return "minecraft:granite_slab";
                case "polished_diorite":
                    return "minecraft:polished_diorite_slab";
                case "andesite":
                    return "minecraft:andesite_slab";
                case "polished_andesite":
                    return "minecraft:polished_andesite_slab";
                case "diorite":
                    return "minecraft:diorite_slab";
                case "end_stone_brick":
                default:
                    return "minecraft:end_stone_brick_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:stone_block_slab4", "stone_slab_type_4", type -> {
            switch (type) {
                case "smooth_quartz":
                    return "minecraft:smooth_quartz_slab";
                case "cut_sandstone":
                    return "minecraft:cut_sandstone_slab";
                case "cut_red_sandstone":
                    return "minecraft:cut_red_sandstone_slab";
                case "stone":
                    return "minecraft:normal_stone_slab";
                case "mossy_stone_brick":
                default:
                    return "minecraft:mossy_stone_brick_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:double_stone_block_slab", "stone_slab_type", type -> {
            switch (type) {
                case "quartz":
                    return "minecraft:quartz_double_slab";
                case "wood":
                    return "minecraft:petrified_oak_double_slab";
                case "stone_brick":
                    return "minecraft:stone_brick_double_slab";
                case "brick":
                    return "minecraft:brick_double_slab";
                case "sandstone":
                    return "minecraft:sandstone_double_slab";
                case "nether_brick":
                    return "minecraft:nether_brick_double_slab";
                case "cobblestone":
                    return "minecraft:cobblestone_double_slab";
                case "smooth_stone":
                default:
                    return "minecraft:smooth_stone_double_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:double_stone_block_slab2", "stone_slab_type_2", type -> {
            switch (type) {
                case "prismarine_rough":
                    return "minecraft:prismarine_double_slab";
                case "prismarine_dark":
                    return "minecraft:dark_prismarine_double_slab";
                case "smooth_sandstone":
                    return "minecraft:smooth_sandstone_double_slab";
                case "purpur":
                    return "minecraft:purpur_double_slab";
                case "red_nether_brick":
                    return "minecraft:red_nether_brick_double_slab";
                case "prismarine_brick":
                    return "minecraft:prismarine_brick_double_slab";
                case "mossy_cobblestone":
                    return "minecraft:mossy_cobblestone_double_slab";
                case "red_sandstone":
                default:
                    return "minecraft:red_sandstone_double_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:double_stone_block_slab3", "stone_slab_type_3", type -> {
            switch (type) {
                case "smooth_red_sandstone":
                    return "minecraft:smooth_red_sandstone_double_slab";
                case "polished_granite":
                    return "minecraft:polished_granite_double_slab";
                case "granite":
                    return "minecraft:granite_double_slab";
                case "polished_diorite":
                    return "minecraft:polished_diorite_double_slab";
                case "andesite":
                    return "minecraft:andesite_double_slab";
                case "polished_andesite":
                    return "minecraft:polished_andesite_double_slab";
                case "diorite":
                    return "minecraft:diorite_double_slab";
                case "end_stone_brick":
                default:
                    return "minecraft:end_stone_brick_double_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:double_stone_block_slab4", "stone_slab_type_4", type -> {
            switch (type) {
                case "smooth_quartz":
                    return "minecraft:smooth_quartz_double_slab";
                case "cut_sandstone":
                    return "minecraft:cut_sandstone_double_slab";
                case "cut_red_sandstone":
                    return "minecraft:cut_red_sandstone_double_slab";
                case "stone":
                    return "minecraft:normal_stone_double_slab";
                case "mossy_stone_brick":
                default:
                    return "minecraft:mossy_stone_brick_double_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:prismarine", "prismarine_block_type", type -> {
            switch (type) {
                case "bricks":
                    return "minecraft:prismarine_bricks";
                case "dark":
                    return "minecraft:dark_prismarine";
                case "default":
                default:
                    return "minecraft:prismarine";
            }
        });

        this.addCoralUpdater(ctx, "minecraft:coral_fan_hang", "tube_coral_wall_fan", "brain_coral_wall_fan");
        this.addCoralUpdater(ctx, "minecraft:coral_fan_hang2", "bubble_coral_wall_fan", "fire_coral_wall_fan");
        this.addCoralUpdater(ctx, "minecraft:coral_fan_hang3", "horn_coral_wall_fan", null);

        this.addTypeUpdater(ctx, "minecraft:monster_egg", "monster_egg_stone_type", type -> {
            switch (type) {
                case "cobblestone":
                    return "minecraft:infested_cobblestone";
                case "stone_brick":
                    return "minecraft:infested_stone_bricks";
                case "mossy_stone_brick":
                    return "minecraft:infested_mossy_stone_bricks";
                case "cracked_stone_brick":
                    return "minecraft:infested_cracked_stone_bricks";
                case "chiseled_stone_brick":
                    return "minecraft:infested_chiseled_stone_bricks";
                case "stone":
                default:
                    return "minecraft:infested_stone";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:stonebrick", "stone_brick_type", type -> {
            switch (type) {
                case "mossy":
                    return "minecraft:mossy_stone_bricks";
                case "cracked":
                    return "minecraft:cracked_stone_bricks";
                case "chiseled":
                    return "minecraft:chiseled_stone_bricks";
                case "smooth":
                    // return "minecraft:smooth_stone_bricks"; // TODO: does not seem to exists anymore
                case "default":
                default:
                    return "minecraft:stone_bricks";
            }
        });
    }

    private void addTypeUpdater(CompoundTagUpdaterContext context, String identifier, String typeState, Function<String, String> rename) {
        context.addUpdater(1, 21, 10)
                .match("name", identifier)
                .visit("states")
                .edit(typeState, helper -> helper.getRootTag().put("name", rename.apply((String) helper.getTag())))
                .remove(typeState);

    }

    private void addCoralUpdater(CompoundTagUpdaterContext context, String identifier, String type1, String type2) {
        context.addUpdater(1, 21, 10)
                .match("name", identifier)
                .edit("states", helper -> {
                    Object deadBit = helper.getCompoundTag().remove("dead_bit");
                    boolean dead = deadBit instanceof Byte && (byte) deadBit == 1 || deadBit instanceof Boolean && (boolean) deadBit;

                    Object typeBit = helper.getCompoundTag().remove("coral_hang_type_bit"); // always remove

                    String type;
                    if (type2 == null) {
                        type = type1;
                    } else {
                        type = (typeBit instanceof Byte && (byte) typeBit == 1 || typeBit instanceof Boolean && (boolean) typeBit) ? type2 : type1;
                    }
                    helper.getRootTag().put("name", dead ? "minecraft:dead_" + type : "minecraft:" + type);
                });

    }
}
