package io.github.russia9.ChatSponge.commands.chat.guard;

import io.github.russia9.ChatSponge.ChatSponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * /chat guard on command
 * Command to turn CG on
 * Aliases: /chat guard on
 */
public class OnCommand implements CommandExecutor {
    private final ChatSponge plugin;

    public OnCommand(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&eChatGuard &aon"));
            plugin.getChatGuard().setEnable(true);
        }
        MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize("&eChatGuard &aon"));
        return CommandResult.success();
    }
}
