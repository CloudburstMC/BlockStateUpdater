package com.nukkitx.blockstateupdater.util.tagupdater;

import com.nukkitx.blockstateupdater.util.TagUtils;
import com.nukkitx.nbt.NbtMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompoundTagUpdaterContext {

    private final List<CompoundTagUpdater> updaters = new ArrayList<>();

    private static int mergeVersions(int baseVersion, int updaterVersion) {
        return updaterVersion | baseVersion;
    }

    private static int baseVersion(int version) {
        return version & 0xFFFFFF00;
    }

    public static int updaterVersion(int version) {
        return version & 0x000000FF;
    }

    public static int makeVersion(int major, int minor, int patch) {
        return (patch << 8) | (minor << 16) | (major << 24);
    }

    public CompoundTagUpdater.Builder addUpdater(int major, int minor, int patch) {
        int version = makeVersion(major, minor, patch);
        CompoundTagUpdater prevUpdater = this.getLatestUpdater();

        int updaterVersion;
        if (prevUpdater == null || baseVersion(prevUpdater.getVersion()) != version) {
            updaterVersion = 0;
        } else {
            updaterVersion = updaterVersion(prevUpdater.getVersion()) + 1;
        }
        version = mergeVersions(version, updaterVersion);

        CompoundTagUpdater updater = new CompoundTagUpdater(version);
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
        mutableTag.put("version", this.getLatestVersion());
        return (NbtMap) TagUtils.toImmutable(mutableTag);
    }

    private CompoundTagUpdater getLatestUpdater() {
        return this.updaters.isEmpty() ? null : this.updaters.get(this.updaters.size() - 1);
    }

    public int getLatestVersion() {
        CompoundTagUpdater updater = this.getLatestUpdater();
        return updater == null ? 0 : updater.getVersion();
    }
}
