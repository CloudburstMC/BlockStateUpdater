package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

public class BlockStateUpdater_1_20_10 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_20_10();

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
                this.addTypeUpdater(ctx, "minecraft:concrete", "color", color, "minecraft:light_gray_concrete");
                this.addTypeUpdater(ctx, "minecraft:shulker_box", "color", color, "minecraft:light_gray_shulker_box");
            } else {
                this.addTypeUpdater(ctx, "minecraft:concrete", "color", color, "minecraft:" + color + "_concrete");
                this.addTypeUpdater(ctx, "minecraft:shulker_box", "color", color, "minecraft:" + color + "_shulker_box");
            }
        }

        this.addFacingDirectionUpdater(ctx, "minecraft:observer");
    }

    private void addTypeUpdater(CompoundTagUpdaterContext context, String identifier, String typeState, String type, String newIdentifier) {
        context.addUpdater(1, 20, 10)
                .match("name", identifier)
                .visit("states")
                .match(typeState, type)
                .edit(typeState, helper -> helper.getRootTag().put("name", newIdentifier))
                .remove(typeState);

    }

    private void addFacingDirectionUpdater(CompoundTagUpdaterContext ctx, String identifier) {
        ctx.addUpdater(1, 20, 10)
                .match("name", identifier)
                .visit("states")
                .edit("facing_direction", helper -> {
                    int value = (int) helper.getTag();
                    helper.replaceWith("minecraft:facing_direction", DIRECTIONS[value & 5]); // Don't ask me why namespace is in vanilla state
                });
    }
}
