package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

import java.util.function.Function;

public class BlockStateUpdater_1_21_0 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_21_0();

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext ctx) {
        ctx.addUpdater(1, 21, 0)
                .match("name", "minecraft:coral_block")
                .edit("states", helper -> {
                    String type = (String) helper.getCompoundTag().remove("coral_color");
                    Object bit = helper.getCompoundTag().remove("dead_bit");
                    boolean dead = bit instanceof Byte && (byte) bit == 1 || bit instanceof Boolean && (boolean) bit;

                    String newName;
                    switch (type) {
                        case "blue":
                            newName = "minecraft:" + (dead ? "dead_" : "") + "tube_coral_block";
                            break;
                        case "pink":
                            newName = "minecraft:" + (dead ? "dead_" : "") + "brain_coral_block";
                            break;
                        case "purple":
                            newName = "minecraft:" + (dead ? "dead_" : "") + "bubble_coral_block";
                            break;
                        case "yellow":
                            newName = "minecraft:" + (dead ? "dead_" : "") + "horn_coral_block";
                            break;
                        case "red":
                        default:
                            newName = "minecraft:" + (dead ? "dead_" : "") + "fire_coral_block";
                            break;
                    }
                    helper.getRootTag().put("name", newName);
                });

        this.addTypeUpdater(ctx, "minecraft:double_plant", "double_plant_type", type -> {
            switch (type) {
                case "syringa":
                    return "minecraft:lilac";
                case "grass":
                    return "minecraft:tall_grass";
                case "fern":
                    return "minecraft:large_fern";
                case "rose":
                    return "minecraft:rose_bush";
                case "paeonia":
                    return "minecraft:peony";
                case "sunflower":
                default:
                    return "minecraft:sunflower";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:stone_block_slab", "stone_slab_type", type -> {
            switch (type) {
                case "quartz":
                    return "minecraft:quartz_slab";
                case "wood":
                    return "minecraft:petrified_oak_slab";
                case "stone_brick":
                    return "minecraft:stone_brick_slab";
                case "brick":
                    return "minecraft:brick_slab";
                case "smooth_stone":
                    return "minecraft:smooth_stone_slab";
                case "sandstone":
                    return "minecraft:sandstone_slab";
                case "nether_brick":
                    return "minecraft:nether_brick_slab";
                case "cobblestone":
                default:
                    return "minecraft:cobblestone_slab";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:tallgrass", "tall_grass_type", type -> {
            switch (type) {
                case "fern":
                    return "minecraft:fern";
                case "default":
                default:
                    return "minecraft:short_grass";
            }
        });

        // These are not official updaters

        ctx.addUpdater(1, 21, 0, false, false)
                .match("name", "minecraft:trial_spawner")
                .visit("states")
                .tryAdd("ominous", (byte) 0);

        ctx.addUpdater(1, 21, 0, false, false)
                .match("name", "minecraft:vault")
                .visit("states")
                .tryAdd("ominous", (byte) 0);
    }

    private void addTypeUpdater(CompoundTagUpdaterContext context, String identifier, String typeState, Function<String, String> rename) {
        context.addUpdater(1, 21, 0)
                .match("name", identifier)
                .visit("states")
                .edit(typeState, helper -> helper.getRootTag().put("name", rename.apply((String) helper.getTag())))
                .remove(typeState);

    }
}
