package net.kaupenjoe.mccourse.event;

import net.kaupenjoe.mccourse.util.IEntityDataSaver;

import net.minecraft.server.network.ServerPlayerEntity;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class PlayerCopyHandler implements ServerPlayerEvents.CopyFrom {
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        ((IEntityDataSaver) newPlayer).mccourse$getPersistentData().putIntArray("homepos",
                ((IEntityDataSaver) oldPlayer).mccourse$getPersistentData().getIntArray("homepos"));
    }
}
