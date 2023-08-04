package io.github.miniplaceholders.expansion.vault.paper.placeholder;

import io.github.miniplaceholders.api.placeholder.AudiencePlaceholder;
import io.github.miniplaceholders.expansion.vault.paper.VaultHook;

public sealed abstract class VaultPlaceholder
        implements AudiencePlaceholder
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
