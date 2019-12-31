package io.github.russia9.ChatSponge.listeners;

import com.google.common.collect.ImmutableMap;
import io.github.russia9.ChatSponge.ChatSponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.advancement.AdvancementEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import static io.github.russia9.ChatSponge.lib.Helper.getNearbyPlayers;
import static io.github.russia9.ChatSponge.lib.Helper.getNick;

/**
 * Listen Chat events
 *
 * @author Russia9
 */
public class ChatListener {
    private final ChatSponge plugin;
    private TextTemplate message;
    private Text nick;

    public ChatListener(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @Root Player player) {
        if (!player.hasPlayedBefore()) {
            if (plugin.getChatSpongeConfig().firstJoinMessageEnabled) {
                nick = getNick(player);
                message = plugin.getChatSpongeConfig().firstJoinMessage;
                event.setMessage(message.apply(ImmutableMap.of("nick", nick)));
            } else {
                event.setMessageCancelled(true);
            }
        } else if (plugin.getChatSpongeConfig().joinMessageEnabled) {
            if (player.hasPermission("chatsponge.joinbroadcast")) {
                nick = getNick(player);
                message = plugin.getChatSpongeConfig().joinMessage;
                event.setMessage(message.apply(ImmutableMap.of("nick", nick)));
            } else {
                event.setMessageCancelled(true);
            }
        } else {
            event.setMessageCancelled(true);
        }
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event, @Root Player player) {
        if (plugin.getChatSpongeConfig().quitMessageEnabled) {
            if (player.hasPermission("chatsponge.quitbroadcast")) {
                nick = getNick(player);
                message = plugin.getChatSpongeConfig().quitMessage;
                event.setMessage(message.apply(ImmutableMap.of("nick", nick)));
            } else {
                event.setMessageCancelled(true);
            }
        } else {
            event.setMessageCancelled(true);
        }
    }

    @Listener
    public void onChatMessage(MessageChannelEvent.Chat event, @Root Player player) {
        if (player.hasPermission("chatsponge.allowchat")) {
            Text rawMessage = event.getRawMessage();
            String stringMessage = TextSerializers.PLAIN.serialize(rawMessage);
            if (player.hasPermission("chatsponge.chatformat")) stringMessage = stringMessage.replace("&", "§");
            nick = getNick(player);
            if (plugin.getChatSpongeConfig().rangedMode) {
                if (stringMessage.startsWith("!")) { // Global chat
                    message = plugin.getChatSpongeConfig().globalMessage;
                    stringMessage = stringMessage.substring(1);
                    event.setMessage(message.apply(ImmutableMap.of(
                            "nick", nick
                            , "message", Text.of(stringMessage))));
                } else { // Local chat
                    rawMessage = TextSerializers.PLAIN.deserialize(stringMessage);
                    message = plugin.getChatSpongeConfig().message;
                    event.setChannel(MessageChannel.combined(
                            MessageChannel.fixed(getNearbyPlayers(player, plugin.getChatSpongeConfig().chatRange)),
                            MessageChannel.TO_CONSOLE)
                    );
                    Text eventMessage = message.apply(ImmutableMap.of("nick", nick, "message", rawMessage)).build();
                    event.setMessage(eventMessage);
                }
            } else {
                message = plugin.getChatSpongeConfig().message;
                event.setMessage(message.apply(ImmutableMap.of("nick", nick, "message", rawMessage)));
            }
        } else {
            player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(plugin.getChatSpongeConfig().noAllowChat));
            event.setMessageCancelled(true);
        }
    }

    @Listener
    public void onAdvancementGrant(AdvancementEvent.Grant event, @Root Player player) {
        if (plugin.getChatSpongeConfig().rangedMode) {
            event.setChannel(MessageChannel.combined(
                    MessageChannel.fixed(getNearbyPlayers(player, plugin.getChatSpongeConfig().chatRange)),
                    MessageChannel.TO_CONSOLE)
            );
        }
    }
}
