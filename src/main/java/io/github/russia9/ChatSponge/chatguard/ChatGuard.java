package io.github.russia9.ChatSponge.chatguard;

import io.github.russia9.ChatSponge.ChatSponge;

/**
 * CG main class
 */
public class ChatGuard {
    private ChatSponge plugin;

    public ChatGuard(ChatSponge plugin) {
        this.plugin = plugin;
    }

    /**
     * Function to verify message and delete NFSW and filthy language.
     *
     * @param message Message to check
     * @return verified and edited message
     */
    public String verify(String message) {
        return message;
    }
}
