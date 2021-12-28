package com.nukkitx.blockstateupdater;

import com.nukkitx.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

public class BlockStateUpdater_1_17_30 implements BlockStateUpdater {

    public static final BlockStateUpdater INSTANCE = new BlockStateUpdater_1_17_30();

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext context) {
        context.addUpdater(1, 16, 210, true) // Palette version wasn't bumped so far
                .match("name", "minecraft:frame")
                .visit("states")
                .tryAdd("item_frame_photo_bit", (byte) 0);
    }
}
