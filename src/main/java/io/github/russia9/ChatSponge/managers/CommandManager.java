package io.github.russia9.ChatSponge.managers;

import io.github.russia9.ChatSponge.ChatSponge;
import io.github.russia9.ChatSponge.commands.MeCommand;
import io.github.russia9.ChatSponge.commands.MsgCommand;
import io.github.russia9.ChatSponge.commands.SpyCommand;
import io.github.russia9.ChatSponge.commands.chat.ClearCommand;
import io.github.russia9.ChatSponge.commands.chat.guard.OffCommand;
import io.github.russia9.ChatSponge.commands.chat.guard.OnCommand;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * Class to manage io.github.russia9.ChatSponge.commands
 *
 * @author Russia9
 */
public class CommandManager {
    private CommandSpec me;
    private CommandSpec chat;
    private CommandSpec msg;
    private CommandSpec spy;

    public CommandManager(ChatSponge plugin) {
        initCommands(plugin);
        registerCommands(plugin);
    }

    /**
     * Initialises commands
     *
     * @param plugin ChatSponge plugin instance
     */
    private void initCommands(ChatSponge plugin) {
        /* /chat subcommands */
        CommandSpec on = CommandSpec.builder()
                .description(Text.of("Turns CG on."))
                .permission("chatsponge.command.chat.guard.on")
                .executor(new OnCommand(plugin))
                .build();
        CommandSpec off = CommandSpec.builder()
                .description(Text.of("Turns CG off."))
                .permission("chatsponge.command.chat.guard.off")
                .executor(new OffCommand(plugin))
                .build();
        CommandSpec add = CommandSpec.builder()
                .description(Text.of("Adds <word> to the banned list."))
                .permission("chatsponge.command.chat.guard.add")
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("word"))))
                .executor(new OffCommand(plugin))
                .build();
        CommandSpec remove = CommandSpec.builder()
                .description(Text.of("Removes <word> from the banned list."))
                .permission("chatsponge.command.chat.guard.remove")
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("word"))))
                .executor(new OffCommand(plugin))
                .build();
        CommandSpec list = CommandSpec.builder()
                .description(Text.of("Shows list of banned words."))
                .permission("chatsponge.command.chat.guard.list")
                .executor(new OffCommand(plugin))
                .build();
        CommandSpec guard = CommandSpec.builder()
                .description(Text.of("Команда управления ChatGuard."))
                .permission("chatsponge.command.chat.guard")
                .child(on, "on")
                .child(off, "off")
                .build();

        CommandSpec clear = CommandSpec.builder()
                .description(Text.of("Очищает чат."))
                .permission("chatsponge.command.chat.clear")
                .arguments(
                        GenericArguments.optional(GenericArguments.player(Text.of("player")))
                )
                .executor(new ClearCommand(plugin))
                .build();

        chat = CommandSpec.builder()
                .description(Text.of("Команда управления чатом."))
                .permission("chatsponge.command.chat.command")
                .child(clear, "clear", "c", "cl")
                .child(guard, "guard", "cg")
                .build();

        /* separate commands */
        me = CommandSpec.builder()
                .description(Text.of("Сообщает об исполнителе команды от третьего лица."))
                .permission("chatsponge.command.me")
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.text(Text.of("text"), TextSerializers.FORMATTING_CODE, true))
                )
                .executor(new MeCommand(plugin))
                .build();
        msg = CommandSpec.builder()
                .description(Text.of("Отправка личного сообщения игроку player."))
                .permission("chatsponge.command.msg")
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.player(Text.of("receiver"))),
                        GenericArguments.onlyOne(GenericArguments.remainingRawJoinedStrings(Text.of("text")))
                )
                .executor(new MsgCommand(plugin))
                .build();
        spy = CommandSpec.builder()
                .description(Text.of("Переключение трансляции личных сообщений всех игроков себе"))
                .permission("chatsponge.command.spy")
                .executor(new SpyCommand())
                .build();
    }

    /**
     * Registers commands
     *
     * @param plugin ChatSponge plugin instance
     */
    private void registerCommands(ChatSponge plugin) {
        Sponge.getCommandManager().register(plugin, me, "me", "action", "describe");
        Sponge.getCommandManager().register(plugin, msg, "m", "msg", "tell", "t", "whisper", "pm");
        Sponge.getCommandManager().register(plugin, spy, "socialspy", "spy");
        Sponge.getCommandManager().register(plugin, chat, "chat");
    }
}
