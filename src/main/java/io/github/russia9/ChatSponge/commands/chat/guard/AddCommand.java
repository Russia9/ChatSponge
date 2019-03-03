package io.github.russia9.ChatSponge.commands.chat.guard;

import io.github.russia9.ChatSponge.ChatSponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

/**
 * /chat guard add [word] command
 * Command to add [word] to the banned list
 * Aliases: /chat guard add [word]
 */
public class AddCommand implements CommandExecutor {
    private final ChatSponge plugin;

    public AddCommand(ChatSponge plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String word = args.<String>getOne(Text.of("word")).get();
        return CommandResult.success();
    }
}
