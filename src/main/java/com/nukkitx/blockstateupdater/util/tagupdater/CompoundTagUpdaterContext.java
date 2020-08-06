package com.nukkitx.blockstateupdater.util.tagupdater;

import com.nukkitx.blockstateupdater.util.TagUtils;
import com.nukkitx.nbt.NbtMap;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class CompoundTagUpdaterContext {

    private final PriorityQueue<CompoundTagUpdater> updaters = new PriorityQueue<>(Comparator.reverseOrder());

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
        CompoundTagUpdater prevUpdater = this.updaters.peek();

        int updaterVersion;
        if (prevUpdater == null || baseVersion(prevUpdater.getVersion()) != version) {
            updaterVersion = 0;
        } else {
            updaterVersion = updaterVersion(prevUpdater.getVersion()) + 1;
        }
        version = mergeVersions(version, updaterVersion);

        CompoundTagUpdater updater = new CompoundTagUpdater(version);
        this.updaters.offer(updater);
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
        CompoundTagUpdater updater = this.updaters.peek();
        return updater == null ? 0 : updater.getVersion();
    }
}
