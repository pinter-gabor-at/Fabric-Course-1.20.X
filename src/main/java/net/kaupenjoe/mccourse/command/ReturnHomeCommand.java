package net.kaupenjoe.mccourse.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kaupenjoe.mccourse.util.IEntityDataSaver;

import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ReturnHomeCommand {
	@SuppressWarnings("unused")
	public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher,
		CommandRegistryAccess commandRegistryAccess,
		CommandManager.RegistrationEnvironment registrationEnvironment) {
		commandDispatcher
			.register(CommandManager.literal("home")
				.then(CommandManager.literal("return")
					.executes(ReturnHomeCommand::run)));
	}

	/**
	 * Execute "home return" command
	 * @return 1 on success
	 */
	private static int run(CommandContext<ServerCommandSource> context) {
		ServerPlayerEntity player = context.getSource().getPlayer();
		if (player != null) {
			IEntityDataSaver ds = (IEntityDataSaver) player;
			int[] homepos = ds.mccourse$getPersistentData().getIntArray("homepos");
			if (homepos.length == 3) {
				context.getSource().getPlayer().requestTeleport(homepos[0], homepos[1], homepos[2]);
				context.getSource().sendFeedback(() ->
					Text.literal("Player returned Home!"), false);
				return 1;
			}
			context.getSource().sendFeedback(() ->
				Text.literal("No Home Position has been Set!"), false);
		}
		return 0;
	}
}
