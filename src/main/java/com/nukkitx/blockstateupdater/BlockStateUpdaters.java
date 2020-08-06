package com.nukkitx.blockstateupdater;

import com.nukkitx.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;
import com.nukkitx.nbt.NbtMap;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@UtilityClass
public class BlockStateUpdaters {

    public static final int LATEST_VERSION = CompoundTagUpdaterContext.makeVersion(1, 16, 0);

    private static final Set<BlockStateUpdater> UPDATERS = new HashSet<>();
    private static final CompoundTagUpdaterContext CONTEXT = new CompoundTagUpdaterContext();

    static {
        UPDATERS.add(BlockStateUpdaterBase.INSTANCE);
        UPDATERS.add(BlockStateUpdater_1_10_0.INSTANCE);
        UPDATERS.add(BlockStateUpdater_1_12_0.INSTANCE);
        UPDATERS.add(BlockStateUpdater_1_13_0.INSTANCE);
        UPDATERS.add(BlockStateUpdater_1_14_0.INSTANCE);
        UPDATERS.add(BlockStateUpdater_1_15_0.INSTANCE);
        UPDATERS.add(BlockStateUpdater_1_16_0.INSTANCE);

        UPDATERS.forEach(updater -> updater.registerUpdaters(CONTEXT));
    }

    public static NbtMap updateBlockState(NbtMap tag, int version) {
        return CONTEXT.update(tag, version);
    }

    public static void serializeCommon(Map<String, Object> builder, String id) {
        builder.put("version", LATEST_VERSION);
        builder.put("name", id);
    }
}
