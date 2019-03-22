package io.github.russia9.ChatSponge.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.HashMap;
import java.util.Map;

import static org.spongepowered.api.text.TextTemplate.arg;
import static org.spongepowered.api.text.TextTemplate.of;

/**
 * ChatSponge plugin configuration.
 */
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
    public boolean enableCG = true;

    public String prefix = "&7[&4Epic&6Craft&7] ";

    Map<String, String[]> dict = new HashMap<String, String[]>() {{
        put("а", new String[]{"а", "a", "@"});
        put("б", new String[]{"б", "6", "b"});
        put("в", new String[]{"в", "b", "v"});
        put("г", new String[]{"г", "r", "g"});
        put("д", new String[]{"д", "d", "g"});
        put("е", new String[]{"е", "e"});
        put("ё", new String[]{"ё", "е", "e"});
        put("ж", new String[]{"ж", "zh", "*"});
        put("з", new String[]{"з", "3", "z"});
        put("и", new String[]{"и", "u", "i"});
        put("й", new String[]{"й", "u", "y", "i"});
        put("к", new String[]{"к", "k", "i{", "|{"});
        put("л", new String[]{"л", "l", "ji"});
        put("м", new String[]{"м", "m"});
        put("н", new String[]{"н", "h", "n"});
        put("о", new String[]{"о", "o", "0"});
        put("п", new String[]{"п", "n", "p"});
        put("р", new String[]{"р", "r", "p"});
        put("с", new String[]{"с", "c", "s"});
        put("т", new String[]{"т", "m", "t"});
        put("у", new String[]{"у", "y", "u"});
        put("ф", new String[]{"ф", "f"});
        put("х", new String[]{"х", "x", "h", "к", "k", "}{"});
        put("ц", new String[]{"ц", "c", "u,"});
        put("ч", new String[]{"ч", "ch"});
        put("ш", new String[]{"ш", "sh"});
        put("щ", new String[]{"щ", "sch"});
        put("ь", new String[]{"ь", "b"});
        put("ы", new String[]{"ы", "bi", "bl"});
        put("ъ", new String[]{"ъ"});
        put("э", new String[]{"э", "е", "e"});
        put("ю", new String[]{"ю", "io"});
        put("я", new String[]{"я", "ya"});
        put("пи", new String[]{"3,14", "314", "π"});
    }};

    public TextTemplate privateMessageReceiver = of(
            TextColors.GOLD, TextSerializers.FORMATTING_CODE.deserialize(" &6["), arg("nick"), TextSerializers.FORMATTING_CODE.deserialize("&6 -> &cЯ&6] "), TextColors.RESET, arg("message"));

    public TextTemplate privateMessageSpy = of(
            TextColors.GOLD, TextSerializers.FORMATTING_CODE.deserialize(" &6[&eSS&6]"), arg("sender"), TextSerializers.FORMATTING_CODE.deserialize("&6 -> "), arg("receiver"), TextSerializers.FORMATTING_CODE.deserialize("&6] "), TextColors.RESET, arg("message"));

    public String console = "&7[&4Epic&6Craft&7] &cConsole&r";
}
