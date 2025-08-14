package io.github.miniplaceholders.expansion.vault.paper.placeholder;

import io.github.miniplaceholders.api.resolver.AudienceTagResolver;
import io.github.miniplaceholders.expansion.vault.paper.VaultHook;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public sealed abstract class VaultPlaceholder
        implements AudienceTagResolver<@NotNull Player>
        permits EcoBalancePlaceholder,
                PlayerGroupPlaceholder,
                PlayerGroupPrefixPlaceholder,
                PlayerGroupSuffixPlaceholder,
                PlayerGroupsPlaceholder,
                PlayerHasPermission,
                PlayerInGroupPlaceholder,
                PlayerInPrimaryGroupPlaceholder,
                PlayerPrefixPlaceholder,
                PlayerSuffixPlaceholder
{
    private final VaultHook hook;

    protected VaultPlaceholder(final VaultHook hook) {
        this.hook = hook;
    }

    protected VaultHook vaultHook() {
        return this.hook;
    }
}
