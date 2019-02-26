package io.github.russia9.ChatSponge.chatguard;

import io.github.russia9.ChatSponge.ChatSponge;

public class ChatGuard {
    private ChatSponge plugin;

    public ChatGuard(ChatSponge plugin) {
        this.plugin = plugin;
    }

    public String protect(String message) {
        return message;
    }
}
