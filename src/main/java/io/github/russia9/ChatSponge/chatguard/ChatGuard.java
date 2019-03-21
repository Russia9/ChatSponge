package io.github.russia9.ChatSponge.chatguard;

import io.github.russia9.ChatSponge.ChatSponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * CG main class
 */
public class ChatGuard {
    private ChatSponge plugin;
    private boolean enable;

    public ChatGuard(ChatSponge plugin, boolean enable) {
        this.plugin = plugin;
        this.enable = enable;
    }

    /**
     * Function to verify message and delete NFSW and filthy language.
     *
     * @param message Message to check
     * @return verified and edited message
     */
    public String verify(String message, Player player) {
        String verified = message;
        String[] filter = {"хуй", "хух", "хах"};
        boolean check = true;
        if (verified.contains("хуй")) check = false;
        if (check && verified.contains("хух")) check = false;
        if (check && verified.contains("хах")) check = false;
        if (check) {
            for (int i = 0; i < 3; i++) {
                verified = verified.replace(filter[i], "###");
            }
        }
        return verified;
    }

    public Text verify(Text message, Player player) {
        String verifed = TextSerializers.PLAIN.serialize(message);
        return TextSerializers.PLAIN.deserialize(verify(verifed, player));
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
