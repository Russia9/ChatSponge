package io.github.russia9.ChatSponge.lib;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Library class.
 *
 * @author Russia9
 */
public class Helper {
    public static Text getNick(Player player) {
        return Text.builder()
                .append(TextSerializers.FORMATTING_CODE.deserialize(getPrefix(player) + player.getName()))
                .onHover(TextActions.showText(TextSerializers.FORMATTING_CODE.deserialize(
                        getPrefix(player) + player.getName() + "\n"
                                + "&a Профиль&7 (ЛКМ)"
                )))
                .onClick(TextActions.runCommand("/profile " + player.getName()))
                .build();
    }

    public static String getPrefix(Player player) {
        return "&7[&f" + player.getOption("prefix").orElse("Игрок").trim() + "&7]&f ";
    }

    public static Set<Player> getNearbyPlayers(Player p, int rangeSquared) {
        return p.getWorld().getPlayers().stream()
                .filter(target -> target.getPosition().distanceSquared(p.getPosition()) <= rangeSquared)
                .collect(Collectors.toSet());
    }
}
