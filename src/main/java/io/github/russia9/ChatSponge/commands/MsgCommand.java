package io.github.russia9.ChatSponge.commands;

import com.google.common.collect.ImmutableMap;
import io.github.russia9.ChatSponge.ChatSponge;
import io.github.russia9.ChatSponge.lib.Helper;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * /msg command
 * Command to send private messages.
 * Aliases: /m /msg /tell /t /whisper /pm
 */
public class MsgCommand implements CommandExecutor {
    private final ChatSponge plugin;

    public MsgCommand(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        Text message = TextSerializers.FORMATTING_CODE.deserialize(args.<String>getOne(Text.of("text")).get());
        if (src instanceof Player) {
            Player sender = (Player) src;
            Player receiver = args.<Player>getOne(Text.of("receiver")).get();
            MessageChannel spy = MessageChannel.combined(() -> Sponge.getServer().getOnlinePlayers().stream()
                    .filter(player -> (player.getOption("socialspy").filter(Predicate.isEqual("true")).isPresent()) && !player.equals(sender) && !player.equals(receiver))
                    .collect(Collectors.toList()), MessageChannel.TO_CONSOLE);
            if (!sender.equals(receiver)) {
                Text senderMessage = getMessage(receiver, message, true);
                Text receiverMessage = getMessage(sender, message, false);
                Text spyMessage = getMessage(sender, receiver, message);
                sender.sendMessage(senderMessage);
                receiver.sendMessage(receiverMessage);
                spy.send(spyMessage);
            } else {
                sender.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(plugin.getChatSpongeConfig().prefix + "&cНельзя отправить сообщение самому себе."));
            }
        } else {
            Player receiver = args.<Player>getOne(Text.of("receiver")).get();
            MessageChannel spy = MessageChannel.combined(() -> Sponge.getServer().getOnlinePlayers().stream()
                    .filter(player -> (player.getOption("socialspy").filter(Predicate.isEqual("true")).isPresent()) && !player.equals(receiver))
                    .collect(Collectors.toList()), MessageChannel.TO_CONSOLE);
            Text receiverMessage = getMessage(plugin.getChatSpongeConfig().console, message);
            Text spyMessage = getMessage(plugin.getChatSpongeConfig().console, receiver, message);
            receiver.sendMessage(receiverMessage);
            spy.send(spyMessage);
        }
        return CommandResult.success();
    }

    private Text getMessage(Player player, Text message, boolean sender) {
        TextTemplate privateMessage;
        if (sender) {
            privateMessage = plugin.getChatSpongeConfig().privateMessageSender;
        } else {
            privateMessage = plugin.getChatSpongeConfig().privateMessageReceiver;
        }
        return privateMessage.apply(ImmutableMap.of(
                "nick", Text.builder()
                        .append(TextSerializers.FORMATTING_CODE.deserialize(Helper.getPrefix(player)))
                        .append(TextSerializers.FORMATTING_CODE.deserialize("&c" + player.getName()))
                        .onHover(TextActions.showText(TextSerializers.FORMATTING_CODE.deserialize(
                                Helper.getPrefix(player) + player.getName() + "\n"
                                        + "&a Профиль&7 (ЛКМ)"
                        )))
                        .onClick(TextActions.runCommand("/profile " + player.getName()))
                        .build(),
                "message", message
        )).build();
    }

    private Text getMessage(Player sender, Player receiver, Text message) {
        return plugin.getChatSpongeConfig().privateMessageSpy.apply(ImmutableMap.of(
                "sender", Helper.getNick(sender),
                "receiver", Helper.getNick(receiver),
                "message", message
        )).build();
    }

    private Text getMessage(String sender, Player receiver, Text message) {
        return plugin.getChatSpongeConfig().privateMessageSpy.apply(ImmutableMap.of(
                "sender", TextSerializers.FORMATTING_CODE.deserialize(sender),
                "receiver", Helper.getNick(receiver),
                "message", message
        )).build();
    }

    private Text getMessage(String sender, Text message) {
        return plugin.getChatSpongeConfig().privateMessageReceiver.apply(ImmutableMap.of(
                "nick", TextSerializers.FORMATTING_CODE.deserialize(sender),
                "message", message
        )).build();
    }
}
