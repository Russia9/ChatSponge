package io.github.russia9.ChatSponge.commands;

import io.github.russia9.ChatSponge.ChatSponge;
import io.github.russia9.ChatSponge.lib.Helper;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * /me command
 * Send message like " * Russia9 Example".
 * Aliases: /me /action /describe
 *
 * @author Russia9
 */
public class MeCommand implements CommandExecutor {
    private final ChatSponge plugin;

    public MeCommand(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        Text message = args.<Text>getOne(Text.of("text")).get();
        // Get nick
        Text nick;
        if (src instanceof Player) {
            nick = Helper.getNick((Player) src);
        } else {
            nick = TextSerializers.FORMATTING_CODE.deserialize(plugin.getChatSpongeConfig().console);
        }

        MessageChannel.TO_ALL.send(TextSerializers.FORMATTING_CODE.deserialize("&5 * ").concat(nick).concat(Text.of(" ")).concat(message));
        return CommandResult.success();
    }
}
