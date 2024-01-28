package net.kaupenjoe.mccourse.networking.packet;

import net.kaupenjoe.mccourse.block.entity.GemEmpoweringStationBlockEntity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import net.fabricmc.fabric.api.networking.v1.PacketSender;

public class ItemStackSyncS2CPacket {
	@SuppressWarnings("unused")
	public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
		PacketByteBuf buf, PacketSender responseSender) {
		int size = buf.readInt();
		DefaultedList<ItemStack> list = DefaultedList.ofSize(size, ItemStack.EMPTY);
		for (int i = 0; i < size; i++) {
			list.set(i, buf.readItemStack());
		}
		BlockPos position = buf.readBlockPos();
		if (client.world != null &&
			client.world.getBlockEntity(position) instanceof GemEmpoweringStationBlockEntity blockEntity) {
			blockEntity.setInventory(list);
		}
	}
}
