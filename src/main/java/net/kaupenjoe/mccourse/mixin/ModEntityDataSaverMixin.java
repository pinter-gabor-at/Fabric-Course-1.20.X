package net.kaupenjoe.mccourse.mixin;

import net.kaupenjoe.mccourse.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {
	@Unique
	private NbtCompound persistentData;

	public NbtCompound mccourse$getPersistentData() {
		if (this.persistentData == null) {
			this.persistentData = new NbtCompound();
		}
		return persistentData;
	}

	@Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
	private void injectWriteMethod(NbtCompound nbt, CallbackInfo cbi) {
		if (this.persistentData != null) {
			nbt.put("mccourse.custom_data", persistentData);
		}
	}

	@Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
	private void injectReadMethod(NbtCompound nbt, CallbackInfo cbi) {
		if (nbt.contains("mccourse.custom_data", NbtElement.COMPOUND_TYPE)) {
			this.persistentData = nbt.getCompound("mccourse.custom_data");
		}
	}
}
