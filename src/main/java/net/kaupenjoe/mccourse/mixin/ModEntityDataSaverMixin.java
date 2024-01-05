package net.kaupenjoe.mccourse.mixin;

import net.kaupenjoe.mccourse.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {
	@Unique
	private NbtCompound persistentData;

	@Override
	public NbtCompound mccourse$getPersistentData() {
		if (this.persistentData == null) {
			this.persistentData = new NbtCompound();
		}
		return persistentData;
	}

	@Inject(method = "writeNbt", at = @At("HEAD"))
	private void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> info) {
		if (this.persistentData != null) {
			nbt.put("mccourse.custom_data", persistentData);
		}
	}

	@Inject(method = "readNbt", at = @At("HEAD"))
	private void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
		if (nbt.contains("mccourse.custom_data", 10)) {
			this.persistentData = nbt.getCompound("mccourse.custom_data");
		}
	}
}
