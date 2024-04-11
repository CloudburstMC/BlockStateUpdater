package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;
import org.cloudburstmc.nbt.NbtMap;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UtilityClass
public class BlockStateUpdaters {

    private static final CompoundTagUpdaterContext CONTEXT;
    private static final int LATEST_VERSION;

    static {
        List<BlockStateUpdater> updaters = new ArrayList<>();
        updaters.add(BlockStateUpdaterBase.INSTANCE);
        updaters.add(BlockStateUpdater_1_10_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_12_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_13_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_14_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_15_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_16_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_16_210.INSTANCE);
        updaters.add(BlockStateUpdater_1_17_30.INSTANCE);
        updaters.add(BlockStateUpdater_1_17_40.INSTANCE);
        updaters.add(BlockStateUpdater_1_18_10.INSTANCE);
        updaters.add(BlockStateUpdater_1_18_30.INSTANCE);
        updaters.add(BlockStateUpdater_1_19_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_19_20.INSTANCE);
        updaters.add(BlockStateUpdater_1_19_70.INSTANCE);
        updaters.add(BlockStateUpdater_1_19_80.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_0.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_10.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_30.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_40.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_50.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_60.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_70.INSTANCE);
        updaters.add(BlockStateUpdater_1_20_80.INSTANCE);

        CompoundTagUpdaterContext context = new CompoundTagUpdaterContext();
        updaters.forEach(updater -> updater.registerUpdaters(context));
        CONTEXT = context;
        LATEST_VERSION = context.getLatestVersion();
    }

    public static NbtMap updateBlockState(NbtMap tag, int version) {
        return CONTEXT.update(tag, version);
    }

    public static void serializeCommon(Map<String, Object> builder, String id) {
        builder.put("version", LATEST_VERSION);
        builder.put("name", id);
    }

    public static int getLatestVersion() {
        return LATEST_VERSION;
    }
}
