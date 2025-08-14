package io.github.miniplaceholders.expansion.vault.paper.placeholder;

import io.github.miniplaceholders.expansion.vault.paper.VaultHook;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class PlayerGroupsPlaceholder extends VaultPlaceholder {
    public PlayerGroupsPlaceholder(VaultHook hook) {
        super(hook);
    }

    @Override
    public Tag tag(
            final @NotNull Player player,
            final @NotNull ArgumentQueue queue,
            final @NotNull Context ctx
    ) {
        final String group = vaultHook().groups(player);
        return Tag.preProcessParsed(group);
    }
}
