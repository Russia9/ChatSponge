package io.github.russia9.ChatSponge.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

import static org.spongepowered.api.text.TextTemplate.arg;
import static org.spongepowered.api.text.TextTemplate.of;

@ConfigSerializable
public class ChatSpongeConfig {
    @Setting("Ranged mode")
    public final boolean rangedMode = true;
    @Setting("Chat range")
    public final int chatRange = 100;
    @Setting("First Join")
    public final boolean firstJoinMessageEnabled = true;
    @Setting("Join")
    public final boolean joinMessageEnabled = true;
    @Setting("Quit")
    public final boolean quitMessageEnabled = true;
    @Setting("First Join Message")
    public final TextTemplate firstJoinMessage = of(
            arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&e впервые зашел на сервер!"));
    @Setting("Join Message")
    public final TextTemplate joinMessage = of(
            arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&e присоединился!"));
    @Setting("Quit Message")
    public final TextTemplate quitMessage = of(
            arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&e вышел!"));
    @Setting("Local message template")
    public final TextTemplate message = of(
            TextSerializers.FORMATTING_CODE.deserialize(" &7[L] &r"), arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&r: "), arg("message"));
    @Setting("Global message template")
    public final TextTemplate globalMessage = of(
            TextSerializers.FORMATTING_CODE.deserialize(" &7[&2G&7] &r"), arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&r: "), arg("message"));
    public final TextTemplate privateMessageSender = of(
            TextColors.GOLD, TextSerializers.FORMATTING_CODE.deserialize(" &6[&cЯ &6-> "), arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&6] "), TextColors.RESET, arg("message"));
    @Setting("No allow chat message")
    public final String noAllowChat = "[&4Epic&6Craft&7] &cИзвините, но у вас нет прав на написание сообщений здесь";
    @Setting("Enabled")
    public boolean enable = true;

    public String prefix = "&7[&4Epic&6Craft&7] ";

    public TextTemplate privateMessageReceiver = of(
            TextColors.GOLD, TextSerializers.FORMATTING_CODE.deserialize(" &6["), arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&6 -> &cЯ&6] "), TextColors.RESET, arg("message"));

    public TextTemplate privateMessageSpy = of(
            TextColors.GOLD, TextSerializers.FORMATTING_CODE.deserialize(" &6[&eSS&6]"), arg("sender"), TextSerializers.FORMATTING_CODE.deserialize("&6 -> "), arg("receiver"), TextSerializers.FORMATTING_CODE.deserialize("&6] "), TextColors.RESET, arg("message"));

    public String console = "&7[&4Epic&6Craft&7] &cConsole&r";
}
