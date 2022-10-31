package org.cloudburstmc.blockstateupdater;

import org.cloudburstmc.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;

public interface BlockStateUpdater {

    void registerUpdaters(CompoundTagUpdaterContext context);

}
