package io.github.russia9.ChatSponge.commands.chat.guard;

import io.github.russia9.ChatSponge.ChatSponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

public class ListCommand implements CommandExecutor {
    private final ChatSponge plugin;

    public ListCommand(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(Text.of("INDEV"));
        return CommandResult.success();
    }
}
