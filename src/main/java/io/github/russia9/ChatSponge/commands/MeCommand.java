package io.github.russia9.ChatSponge.commands;

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
 * Aliases: /me /action /describe
 */
public class MeCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        Text message = args.<Text>getOne(Text.of("text")).get();
        // Get nick
        Text nick;
        if (src instanceof Player) {
            nick = Helper.getNick((Player) src);
        } else {
            nick = TextSerializers.FORMATTING_CODE.deserialize("&7[&4Epic&6Craft&7] &dConsole&r");
        }

        MessageChannel.TO_ALL.send(TextSerializers.FORMATTING_CODE.deserialize("&5 * ").concat(nick).concat(Text.of(" ")).concat(message));
        return CommandResult.success();
    }
}
