package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

public class BlockStateUpdater_1_20_30 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_20_30();

    public static final String[] COLORS = {
            "magenta",
            "pink",
            "green",
            "lime",
            "yellow",
            "black",
            "light_blue",
            "brown",
            "cyan",
            "orange",
            "red",
            "gray",
            "white",
            "blue",
            "purple",
            "silver"
    };

    public static final String[] DIRECTIONS = {
            "east",
            "south",
            "north",
            "west",
            "up",
            "down"
    };

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext ctx) {
        for (String color : COLORS) {
            if (color.equals("silver")) {
                this.addTypeUpdater(ctx, "minecraft:stained_glass", "color", color, "minecraft:light_gray_stained_glass");
                this.addTypeUpdater(ctx, "minecraft:stained_glass_pane", "color", color, "minecraft:light_gray_stained_glass_pane");
                this.addTypeUpdater(ctx, "minecraft:concrete_powder", "color", color, "minecraft:light_gray_concrete_powder");
                this.addTypeUpdater(ctx, "minecraft:stained_hardened_clay", "color", color, "minecraft:light_gray_terracotta");
            } else {
                this.addTypeUpdater(ctx, "minecraft:stained_glass", "color", color, "minecraft:" + color + "_stained_glass");
                this.addTypeUpdater(ctx, "minecraft:stained_glass_pane", "color", color, "minecraft:" + color + "_stained_glass_pane");
                this.addTypeUpdater(ctx, "minecraft:concrete_powder", "color", color, "minecraft:" + color + "_concrete_powder");
                this.addTypeUpdater(ctx, "minecraft:stained_hardened_clay", "color", color, "minecraft:" + color + "_terracotta");
            }
        }

        this.addDirectionUpdater(ctx, "minecraft:amethyst_cluster", "facing_direction", "minecraft:block_face");
        this.addDirectionUpdater(ctx, "minecraft:anvil", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:big_dripleaf", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:blast_furnace", "facing_direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:calibrated_sculk_sensor", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:campfire", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:end_portal_frame", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:furnace", "facing_direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:large_amethyst_bud", "facing_direction", "minecraft:block_face");
        this.addDirectionUpdater(ctx, "minecraft:lectern", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:lit_blast_furnace", "facing_direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:lit_furnace", "facing_direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:lit_smoker", "facing_direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:medium_amethyst_bud", "facing_direction", "minecraft:block_face");
        this.addDirectionUpdater(ctx, "minecraft:pink_petals", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:powered_comparator", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:powered_repeater", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:small_amethyst_bud", "facing_direction", "minecraft:block_face");
        this.addDirectionUpdater(ctx, "minecraft:small_dripleaf_block", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:smoker", "facing_direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:soul_campfire", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:unpowered_comparator", "direction", "minecraft:cardinal_direction", 3);
        this.addDirectionUpdater(ctx, "minecraft:unpowered_repeater", "direction", "minecraft:cardinal_direction", 3);

        ctx.addUpdater(1, 20, 30)
                .regex("name", "minecraft:.+slab(?:[2-4])?\\b")
                .visit("states")
                .edit("top_slot_bit", helper -> {
                    boolean value;
                    if (helper.getTag() instanceof Byte) {
                        value = (byte) helper.getTag() == 1;
                    } else {
                        value = (boolean) helper.getTag();
                    }

                    if (value) {
                        helper.replaceWith("minecraft:vertical_half", "top");
                    } else {
                        helper.replaceWith("minecraft:vertical_half", "bottom");
                    }
                });

        // TODO: Mojang added 51 updaters, I managed to do the same with less. Maybe I missed something? Need to check later.
    }

    private void addTypeUpdater(CompoundTagUpdaterContext context, String identifier, String typeState, String type, String newIdentifier) {
        context.addUpdater(1, 20, 30)
                .match("name", identifier)
                .visit("states")
                .match(typeState, type)
                .edit(typeState, helper -> helper.getRootTag().put("name", newIdentifier))
                .remove(typeState);
    }

    private void addDirectionUpdater(CompoundTagUpdaterContext ctx, String identifier, String oldProperty, String newProperty) {
        this.addDirectionUpdater(ctx, identifier, oldProperty, newProperty, 5);
    }

    private void addDirectionUpdater(CompoundTagUpdaterContext ctx, String identifier, String oldProperty, String newProperty, int maxMeta) {
        ctx.addUpdater(1, 20, 30)
                .match("name", identifier)
                .visit("states")
                .edit(oldProperty, helper -> {
                    int value = (int) helper.getTag();
                    helper.replaceWith(newProperty, DIRECTIONS[value > maxMeta ? 0 : value]);
                });
    }

    private void addSlabUpdater(CompoundTagUpdaterContext ctx, String identifier) {
        ctx.addUpdater(1, 20, 30)
                .match("name", identifier)
                .visit("states")
                .edit("top_slot_bit", helper -> {
                    boolean value;
                    if (helper.getTag() instanceof Byte) {
                        value = (byte) helper.getTag() == 1;
                    } else {
                        value = (boolean) helper.getTag();
                    }

                    if (value) {
                        helper.replaceWith("minecraft:vertical_half", "top");
                    } else {
                        helper.replaceWith("minecraft:vertical_half", "bottom");
                    }
                });
    }
}
