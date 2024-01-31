package net.kaupenjoe.mccourse.networking;

import net.kaupenjoe.mccourse.networking.packet.ItemStackSyncS2CPacket;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModMessages {
	public static final Identifier ITEM_SYNC = new ModIdentifier("item_sync");

	public static void registerS2CPackets() {
		ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
	}
}
