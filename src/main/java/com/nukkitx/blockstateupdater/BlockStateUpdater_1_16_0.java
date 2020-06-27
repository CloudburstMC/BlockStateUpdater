package com.nukkitx.blockstateupdater;

import com.nukkitx.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockStateUpdater_1_16_0 implements BlockStateUpdater {
    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_16_0();

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext context) {
        context.addUpdater(1, 16, 0)
                .match("name", "jigsaw")
                .visit("states")
                .tryAddInt("rotation", 0);

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:blue_fire")
                .edit("name", helper -> {
                    helper.replaceWith("name", "soul_fire");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:blue_nether_wart_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "warped_wart_block");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:shroomlight_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:shroomlight");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:weeping_vines_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:weeping_vines");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:basalt_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:basalt");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:polished_basalt_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:polished_basalt");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:soul_soil_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:soul_soil");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:target_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:target");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:crimson_trap_door")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:crimsom_trapdoor");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:lodestone_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:lodestone");
                });

        context.addUpdater(1, 16, 0)
                .match("name", "minecraft:twisted_vines_block")
                .edit("name", helper -> {
                    helper.replaceWith("name", "minecraft:twisted_vines");
                });

        this.addWallUpdaters(context, "minecraft:blackstone_wall");
        this.addWallUpdaters(context, "minecraft:polished_blackstone_brick_wall");
        this.addWallUpdaters(context, "minecraft:polished_blackstone_wall");
    }

    private void addWallUpdaters(CompoundTagUpdaterContext context, String name) {
        context.addUpdater(1, 16, 0)
                .match("name", name)
                .visit("states")
                .remove("wall_block_type");
    }
}
