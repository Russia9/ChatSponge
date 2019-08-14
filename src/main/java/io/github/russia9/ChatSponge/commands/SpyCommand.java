package io.github.russia9.ChatSponge.commands;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.permission.SubjectData;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * /spy command
 * Command to spy messages sended with /m command.
 * Aliases: /socialspy /spy
 *
 * @author Russia9
 */
public class SpyCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        if (src instanceof Player) {
            Player player = (Player) src;
            if (!player.getOption("socialspy").isPresent() || player.getOption("socialspy").get().equals("true")) {
                player.getSubjectData().setOption(SubjectData.GLOBAL_CONTEXT, "socialspy", "false");
                player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(" &6[&eSS&6]&c disabled!"));
            } else {
                player.getSubjectData().setOption(SubjectData.GLOBAL_CONTEXT, "socialspy", "true");
                player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(" &6[&eSS&6]&a enabled!"));
            }
        } else {
            src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize("&cЭту команду может использовать только игрок."));
        }

        return CommandResult.success();
    }
}
