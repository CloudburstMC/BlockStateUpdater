package com.nukkitx.blockstateupdater;

import com.nukkitx.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

public interface BlockStateUpdater {

    void registerUpdaters(CompoundTagUpdaterContext context);

}
