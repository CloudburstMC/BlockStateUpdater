package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

import java.util.function.Function;

public class BlockStateUpdater_1_20_80 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_20_80();

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext ctx) {
        this.addTypeUpdater(ctx, "minecraft:sapling", "sapling_type", type -> "minecraft:" + type + "_sapling");
        this.addTypeUpdater(ctx, "minecraft:red_flower", "flower_type", type -> {
            switch (type) {
                case "tulip_orange":
                    return "minecraft:orange_tulip";
                case "tulip_pink":
                    return "minecraft:pink_tulip";
                case "tulip_white":
                    return "minecraft:white_tulip";
                case "oxeye":
                    return "minecraft:oxeye_daisy";
                case "orchid":
                    return "minecraft:blue_orchid";
                case "houstonia":
                    return "minecraft:azure_bluet";
                case "tulip_red":
                default:
                    return "minecraft:red_tulip";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:coral_fan", "coral_color", type -> {
            switch (type) {
                case "blue":
                    return "minecraft:tube_coral_fan";
                case "pink":
                    return "minecraft:brain_coral_fan";
                case "purple":
                    return "minecraft:bubble_coral_fan";
                case "yellow":
                    return "minecraft:horn_coral_fan";
                case "red":
                default:
                    return "minecraft:fire_coral_fan";
            }
        });

        this.addTypeUpdater(ctx, "minecraft:coral_fan_dead", "coral_color", type -> {
            switch (type) {
                case "blue":
                    return "minecraft:dead_tube_coral_fan";
                case "pink":
                    return "minecraft:dead_brain_coral_fan";
                case "purple":
                    return "minecraft:dead_bubble_coral_fan";
                case "yellow":
                    return "minecraft:dead_horn_coral_fan";
                case "red":
                default:
                    return "minecraft:dead_fire_coral_fan";
            }
        });


        // This is not official updater, but they correctly removed sapling_type
        ctx.addUpdater(1, 20, 80, false, false)
                .match("name", "minecraft:bamboo_sapling")
                .visit("states")
                .remove("sapling_type");
    }

    private void addTypeUpdater(CompoundTagUpdaterContext context, String identifier, String typeState, Function<String, String> rename) {
        context.addUpdater(1, 20, 80)
                .match("name", identifier)
                .visit("states")
                .edit(typeState, helper -> helper.getRootTag().put("name", rename.apply((String) helper.getTag())))
                .remove(typeState);

    }
}
