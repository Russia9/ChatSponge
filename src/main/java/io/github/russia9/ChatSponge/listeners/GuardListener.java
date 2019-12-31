package io.github.russia9.ChatSponge.listeners;


import io.github.russia9.ChatSponge.ChatSponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Listen ChatGuard events
 *
 * @author Russia9
 */
public class GuardListener {
    private final ChatSponge plugin;

    public GuardListener(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Listener
    public void onChatMessage(MessageChannelEvent.Chat event, @Root Player player) {
        if(!player.hasPermission("chatsponge.guard.bypass")) {
            if (!plugin.getChatGuard().check(event.getRawMessage().toString())) {
                player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(plugin.getChatSpongeConfig().noBadWords));
                event.setCancelled(true);
            }
        }
    }
}
