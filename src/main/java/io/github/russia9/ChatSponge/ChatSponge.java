package io.github.russia9.ChatSponge;

import io.github.russia9.ChatSponge.chatguard.ChatGuard;
import io.github.russia9.ChatSponge.commands.CommandManager;
import io.github.russia9.ChatSponge.config.ChatSpongeConfig;
import io.github.russia9.ChatSponge.config.ConfigLoader;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.sql.SqlService;

import javax.inject.Inject;
import java.io.File;

/**
 * Main class of plugin
 *
 * @author Russia9
 */

@Plugin(
        id = "chatsponge",
        name = "ChatSponge",
        version = "0.5-indev",
        description = "Amazing EpicCraft chat plugin",
        authors = "Russia9")
public class ChatSponge {
    private final File configDir;
    private final GuiceObjectMapperFactory factory;
    @Inject
    private Logger logger;
    @Inject
    private Game game;
    private SqlService sqlService;
    private ConfigLoader configLoader;
    private CommandManager commandManager;
    private ChatSpongeConfig chatSpongeConfig;
    private ChatGuard chatGuard;

    @Inject
    public ChatSponge(@ConfigDir(sharedRoot = false) File configDir, GuiceObjectMapperFactory factory) {
        this.configDir = configDir;
        this.factory = factory;
    }

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        configLoader = new ConfigLoader(this);
        if (configLoader.loadConfig()) chatSpongeConfig = configLoader.getChatSpongeConfig();
        logger.info("§aChatSponge§e pre-init");
    }

    @Listener
    public void onGameInit(GameInitializationEvent event) {
        chatGuard = new ChatGuard(this, chatSpongeConfig.enableCG);
        Sponge.getEventManager().registerListeners(this, new ChatListener(this));
        sqlService = Sponge.getServiceManager().provideUnchecked(SqlService.class);
        commandManager = new CommandManager(this);
        logger.info("§aChatSponge§e init");
    }

    @Listener
    public void onPostInit(GamePostInitializationEvent event) {
        logger.info("§aChatSponge§e post-init");
    }

    @Listener
    public void onGameStopping(GameStoppingEvent event) {
        logger.info("§aChatSponge§c stopping");
    }

    public Logger getLogger() {
        return logger;
    }

    public File getConfigDir() {
        return configDir;
    }

    public GuiceObjectMapperFactory getFactory() {
        return factory;
    }

    public ChatSpongeConfig getChatSpongeConfig() {
        return chatSpongeConfig;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ConfigLoader getConfigLoader() {
        return configLoader;
    }

    public Game getGame() {
        return game;
    }

    public ChatGuard getChatGuard() {
        return chatGuard;
    }

    public SqlService getSqlService() {
        return sqlService;
    }
}
