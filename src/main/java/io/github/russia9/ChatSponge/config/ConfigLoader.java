package io.github.russia9.ChatSponge.config;

import com.google.common.reflect.TypeToken;
import io.github.russia9.ChatSponge.ChatSponge;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;

/**
 * Class to load ChatSponge config.
 */
public class ConfigLoader {
    private final ChatSponge plugin;
    private ChatSpongeConfig chatSpongeConfig;

    public ConfigLoader(ChatSponge plugin) {
        this.plugin = plugin;
        if (!plugin.getConfigDir().exists()) {
            plugin.getConfigDir().mkdirs();
        }
    }

    public boolean loadConfig() {
        try {
            File file = new File(plugin.getConfigDir(), "config.conf");
            if (!file.exists()) {
                file.createNewFile();
            }
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build();
            CommentedConfigurationNode config = loader.load(ConfigurationOptions.defaults().setObjectMapperFactory(plugin.getFactory()).setShouldCopyDefaults(true));
            chatSpongeConfig = config.getValue(TypeToken.of(ChatSpongeConfig.class), new ChatSpongeConfig());
            loader.save(config);
            return true;
        } catch (Exception e) {
            plugin.getLogger().error("Could not load config.", e);
            return false;
        }
    }

    public ChatSpongeConfig getChatSpongeConfig() {
        return chatSpongeConfig;
    }
}
