package io.github.russia9.ChatSponge.commands;

import io.github.russia9.ChatSponge.ChatSponge;
import io.github.russia9.ChatSponge.commands.chat.ClearCommand;
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
        CommandSpec clear = CommandSpec.builder()
                .description(Text.of("Очищает чат."))
                .permission("chatsponge.command.chat.clear")
                .arguments(
                        GenericArguments.optional(GenericArguments.player(Text.of("player")))
                )
                .executor(new ClearCommand(plugin))
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

        chat = CommandSpec.builder()
                .description(Text.of("Команда управления чатом."))
                .permission("chatsponge.command.chat.command")
                .child(clear, "clear", "c", "cl")
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
