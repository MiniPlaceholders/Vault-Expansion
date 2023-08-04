package io.github.miniplaceholders.expansion.vault.paper.placeholder;

import io.github.miniplaceholders.expansion.vault.paper.VaultHook;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class EcoBalancePlaceholder extends VaultPlaceholder {
    public EcoBalancePlaceholder(final VaultHook hook) {
        super(hook);
    }

    @Override
    public Tag tag(
            final @NotNull Audience audience,
            final @NotNull ArgumentQueue queue,
            final @NotNull Context ctx
    ) {
        final Player player = (Player) audience;
        final double balance = vaultHook().balance(player);
        return Tag.preProcessParsed(Double.toString(balance));
    }
}
