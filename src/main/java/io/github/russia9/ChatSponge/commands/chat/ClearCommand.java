package io.github.russia9.ChatSponge.commands.chat;

import io.github.russia9.ChatSponge.ChatSponge;
import io.github.russia9.ChatSponge.lib.Helper;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Optional;

/**
 * /chat clear command
 * Clears chat.
 * Aliases: /chat clear /chat cl /chat c
 */
public class ClearCommand implements CommandExecutor {
    private final ChatSponge plugin;

    public ClearCommand(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Optional<Player> player = args.getOne("player");
        if (player.isPresent()) {
            player.get().sendMessage(Text.of("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
            player.get().sendMessage(TextSerializers.FORMATTING_CODE.deserialize(plugin.getChatSpongeConfig().prefix + "&fВаш чат был&c очищен."));
            MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize(TextSerializers.FORMATTING_CODE.serialize(Helper.getNick(player.get())) + " &fchat cleared"));
        } else {
            MessageChannel.TO_PLAYERS.send(Text.of("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
            MessageChannel.TO_PLAYERS.send(TextSerializers.FORMATTING_CODE.deserialize(plugin.getChatSpongeConfig().prefix + "&fЧат был&c очищен."));
            MessageChannel.TO_CONSOLE.send(TextSerializers.FORMATTING_CODE.deserialize("&cAll &fchat cleared"));
        }
        return CommandResult.success();
    }
}
