package net.kaupenjoe.mccourse.mixin;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

@Mixin(TrunkPlacerType.class)
public interface TrunkPlacerTypeInvoker {
	@Invoker
	static <P extends TrunkPlacer> TrunkPlacerType<P> callRegister(String id, Codec<P> codec) {
		throw new IllegalStateException();
	}
}
