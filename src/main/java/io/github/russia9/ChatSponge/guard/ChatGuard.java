package io.github.russia9.ChatSponge.guard;

import io.github.russia9.ChatSponge.ChatSponge;

/**
 * ChatGuard base class
 *
 * @author Russia9
 */
public class ChatGuard {
    private final ChatSponge plugin;

    public ChatGuard(ChatSponge plugin) {
        this.plugin = plugin;
    }

    public boolean check(String text) {
        switch (plugin.getChatSpongeConfig().chatGuardLevel) {
            case 1:
                // TODO: CG 1 level
                break;
            case 2:
                // TODO: CG 2 level
                break;
            case 3:
                // TODO: CG 3 level
                break;
            default:
                break;
        }
        return true;
    }
}
