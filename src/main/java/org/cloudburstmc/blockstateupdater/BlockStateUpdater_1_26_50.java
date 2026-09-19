package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

public class BlockStateUpdater_1_26_50 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_26_50();

    public static final String[] STAIRS = {
            "acacia_stairs",
            "andesite_stairs",
            "bamboo_mosaic_stairs",
            "bamboo_stairs",
            "birch_stairs",
            "black_wool_stairs",
            "blackstone_stairs",
            "blue_wool_stairs",
            "brick_stairs",
            "brown_wool_stairs",
            "cherry_stairs",
            "cinnabar_brick_stairs",
            "cinnabar_stairs",
            "cobbled_deepslate_stairs",
            "crimson_stairs",
            "cut_copper_stairs",
            "cyan_wool_stairs",
            "dark_oak_stairs",
            "dark_prismarine_stairs",
            "deepslate_brick_stairs",
            "deepslate_tile_stairs",
            "diorite_stairs",
            "end_brick_stairs",
            "exposed_cut_copper_stairs",
            "granite_stairs",
            "gray_wool_stairs",
            "green_wool_stairs",
            "jungle_stairs",
            "light_blue_wool_stairs",
            "light_gray_wool_stairs",
            "lime_wool_stairs",
            "magenta_wool_stairs",
            "mangrove_stairs",
            "mossy_cobblestone_stairs",
            "mossy_stone_brick_stairs",
            "mud_brick_stairs",
            "nether_brick_stairs",
            "normal_stone_stairs",
            "oak_stairs",
            "orange_wool_stairs",
            "oxidized_cut_copper_stairs",
            "pale_oak_stairs",
            "pink_wool_stairs",
            "polished_andesite_stairs",
            "polished_blackstone_brick_stairs",
            "polished_blackstone_stairs",
            "polished_cinnabar_stairs",
            "polished_deepslate_stairs",
            "polished_diorite_stairs",
            "polished_granite_stairs",
            "polished_sulfur_stairs",
            "polished_tuff_stairs",
            "poplar_stairs",
            "prismarine_bricks_stairs",
            "prismarine_stairs",
            "purple_wool_stairs",
            "purpur_stairs",
            "quartz_stairs",
            "red_nether_brick_stairs",
            "red_sandstone_stairs",
            "red_wool_stairs",
            "resin_brick_stairs",
            "sandstone_stairs",
            "smooth_quartz_stairs",
            "smooth_red_sandstone_stairs",
            "smooth_sandstone_stairs",
            "spruce_stairs",
            "stone_brick_stairs",
            "stone_stairs",
            "sulfur_brick_stairs",
            "sulfur_stairs",
            "tuff_brick_stairs",
            "tuff_stairs",
            "warped_stairs",
            "waxed_cut_copper_stairs",
            "waxed_exposed_cut_copper_stairs",
            "waxed_oxidized_cut_copper_stairs",
            "waxed_weathered_cut_copper_stairs",
            "weathered_cut_copper_stairs",
            "white_wool_stairs",
            "yellow_wool_stairs"
    };

    public static final String[] CONNECTABLES = {
            "acacia_fence",
            "bamboo_fence",
            "birch_fence",
            "black_stained_glass_pane",
            "blue_stained_glass_pane",
            "brown_stained_glass_pane",
            "cherry_fence",
            "copper_bars",
            "crimson_fence",
            "cyan_stained_glass_pane",
            "dark_oak_fence",
            "exposed_copper_bars",
            "glass_pane",
            "gray_stained_glass_pane",
            "green_stained_glass_pane",
            "hard_black_stained_glass_pane",
            "hard_blue_stained_glass_pane",
            "hard_brown_stained_glass_pane",
            "hard_cyan_stained_glass_pane",
            "hard_glass_pane",
            "hard_gray_stained_glass_pane",
            "hard_green_stained_glass_pane",
            "hard_light_blue_stained_glass_pane",
            "hard_light_gray_stained_glass_pane",
            "hard_lime_stained_glass_pane",
            "hard_magenta_stained_glass_pane",
            "hard_orange_stained_glass_pane",
            "hard_pink_stained_glass_pane",
            "hard_purple_stained_glass_pane",
            "hard_red_stained_glass_pane",
            "hard_white_stained_glass_pane",
            "hard_yellow_stained_glass_pane",
            "iron_bars",
            "jungle_fence",
            "light_blue_stained_glass_pane",
            "light_gray_stained_glass_pane",
            "lime_stained_glass_pane",
            "magenta_stained_glass_pane",
            "mangrove_fence",
            "nether_brick_fence",
            "oak_fence",
            "orange_stained_glass_pane",
            "oxidized_copper_bars",
            "pale_oak_fence",
            "pink_stained_glass_pane",
            "poplar_fence",
            "purple_stained_glass_pane",
            "red_stained_glass_pane",
            "spruce_fence",
            "trip_wire",
            "warped_fence",
            "waxed_copper_bars",
            "waxed_exposed_copper_bars",
            "waxed_oxidized_copper_bars",
            "waxed_weathered_copper_bars",
            "weathered_copper_bars",
            "white_stained_glass_pane",
            "yellow_stained_glass_pane"
    };

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext ctx) {
        // 1.26.50 started storing stair corners and horizontal connections as states
        // instead of inferring them from neighbours at runtime; old states get the defaults.
        for (String stairs : STAIRS) {
            ctx.addUpdater(1, 26, 50, false, false)
                    .match("name", "minecraft:" + stairs)
                    .visit("states")
                    .tryAdd("minecraft:corner", "none");
        }

        for (String connectable : CONNECTABLES) {
            ctx.addUpdater(1, 26, 50, false, false)
                    .match("name", "minecraft:" + connectable)
                    .visit("states")
                    .tryAdd("minecraft:connection_north", (byte) 0)
                    .tryAdd("minecraft:connection_east", (byte) 0)
                    .tryAdd("minecraft:connection_south", (byte) 0)
                    .tryAdd("minecraft:connection_west", (byte) 0);
        }
    }
}
