package net.kaupenjoe.mccourse.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kaupenjoe.mccourse.util.IEntityDataSaver;

import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class SetHomeCommand {
	@SuppressWarnings("unused")
	public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher,
		CommandRegistryAccess commandRegistryAccess,
		CommandManager.RegistrationEnvironment registrationEnvironment) {
		commandDispatcher
			.register(CommandManager.literal("home")
				.then(CommandManager.literal("set")
					.executes(SetHomeCommand::run)));
	}

	/**
	 * Execute "home set" command
	 * @return 1 on success
	 */
	public static int run(CommandContext<ServerCommandSource> context) {
		ServerPlayerEntity player = context.getSource().getPlayer();
		if (player != null) {
			IEntityDataSaver ds = (IEntityDataSaver) player;
			BlockPos playerPos = player.getBlockPos();
			String positionString = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";
			ds.mccourse$getPersistentData().putIntArray("homepos",
				new int[]{playerPos.getX(), playerPos.getY(), playerPos.getZ()});
			context.getSource().sendFeedback(() ->
				Text.literal("Set Home at " + positionString), true);
			return 1;
		}
		return 0;
	}
}
