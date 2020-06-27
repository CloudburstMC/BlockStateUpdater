package com.nukkitx.blockstateupdater.util.tagupdater;

import com.nukkitx.blockstateupdater.util.TagUtils;
import com.nukkitx.nbt.NbtMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompoundTagUpdaterContext {

    private static final int LATEST_VERSION = makeVersion(1, 15, 0);
    private final List<CompoundTagUpdater> updaters = new ArrayList<>();
    private int updaterVersion = 0;

    private static int mergeVersions(int baseVersion, int updaterVersion) {
        return updaterVersion | baseVersion;
    }

    private static int baseVersion(int version) {
        return version & 0xFFFFFF00;
    }

    public static void serializeCommon(Map<String, Object> builder, String id) {
        builder.put("version", LATEST_VERSION);
        builder.put("name", id);
    }

    public static int makeVersion(int major, int minor, int patch) {
        return (patch << 8) | (minor << 16) | (major << 26);
    }

    public CompoundTagUpdater.Builder addUpdater(int major, int minor, int patch) {
        int version = makeVersion(major, minor, patch);
        if (this.updaters.isEmpty() || this.updaters.get(this.updaters.size() - 1).getVersion() == version) {
            this.updaterVersion++;
        } else {
            this.updaterVersion = 0;
        }
        version = mergeVersions(version, this.updaterVersion);

        int baseVersion = baseVersion(version);
        if (!this.updaters.isEmpty()) {
            baseVersion = baseVersion(this.updaters.get(this.updaters.size() - 1).getVersion());
        }

        CompoundTagUpdater updater = new CompoundTagUpdater(baseVersion);
        this.updaters.add(updater);
        this.updaters.sort(null);
        return updater.builder();
    }

    public NbtMap update(NbtMap tag, int version) {
        Map<String, Object> mutableTag = (Map<String, Object>) TagUtils.toMutable(tag);

        for (CompoundTagUpdater updater : updaters) {
            if (updater.getVersion() < version) {
                continue;
            }
            updater.update(mutableTag);
        }
        return (NbtMap) TagUtils.toImmutable(mutableTag);
    }

    public int getLatestVersion() {
        if (this.updaters.isEmpty()) {
            return 0;
        }
        return this.updaters.get(updaters.size() - 1).getVersion();
    }
}
