package io.github.miniplaceholders.expansion.vault.paper.placeholder;

import io.github.miniplaceholders.api.utils.Components;
import io.github.miniplaceholders.expansion.vault.paper.VaultHook;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class PlayerHasPermission extends VaultPlaceholder {
    public PlayerHasPermission(VaultHook hook) {
        super(hook);
    }

    @Override
    public Tag tag(
            final @NotNull Audience audience,
            final @NotNull ArgumentQueue queue,
            final @NotNull Context ctx
    ) {
        final Player player = (Player) audience;
        final String permission = queue.popOr("You need to provide a permission to check").value();
        final boolean hasPermission = vaultHook().hasPermission(player, permission);
        return Tag.selfClosingInserting(hasPermission ? Components.TRUE_COMPONENT : Components.FALSE_COMPONENT);
    }
}
