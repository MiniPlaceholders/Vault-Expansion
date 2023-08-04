package io.github.miniplaceholders.expansion.vault.paper;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;

public record VaultHook(
        @Nullable Permission permission,
        @Nullable Economy economy,
        @Nullable Chat chat
) {
    public double balance(final @NotNull Player player) {
        requireNonNull(economy, "there is no valid economic hook");
        return economy.getBalance(player);
    }

    public boolean hasPermission(final @NotNull Player player, final @NotNull String permission) {
        requireNonNull(this.permission, "there is no valid permission hook");
        return this.permission.has(player, permission);
    }

    public String primaryGroup(final @NotNull Player player) {
        requireNonNull(this.permission, "there is no valid permission hook");
        return this.permission.getPrimaryGroup(player);
    }

    public String groups(final @NotNull Player player) {
        requireNonNull(this.permission, "there is no valid permission hook");
        return String.join(", ", this.permission.getPlayerGroups(player));
    }

    public boolean inGroup(final @NotNull Player player, String group) {
        requireNonNull(this.permission, "there is no valid permission hook");
        return this.permission.playerInGroup(player, group);
    }

    public boolean inPrimaryGroup(final @NotNull Player player, final @NotNull String group) {
        requireNonNull(this.permission, "there is no valid permission hook");
        final String primaryGroup = this.permission.getPrimaryGroup(player);
        return group.equalsIgnoreCase(primaryGroup);
    }

    public String groupPrefix(final @NotNull Player player, @Nullable String group) {
        requireNonNull(this.chat, "there is no valid chat hook");
        if (group == null) {
           group = this.primaryGroup(player);
        }
        return this.chat.getGroupPrefix(player.getWorld().getName(), group);
    }

    public String groupSuffix(final @NotNull Player player, @Nullable String group) {
        requireNonNull(this.chat, "there is no valid chat hook");
        if (group == null) {
            group = this.primaryGroup(player);
        }
        return this.chat.getGroupSuffix(player.getWorld().getName(), group);
    }

    public String prefix(final @NotNull Player player) {
        requireNonNull(this.chat, "there is no valid chat hook");
        return this.chat.getPlayerPrefix(player);
    }

    public String suffix(final @NotNull Player player) {
        requireNonNull(this.chat, "there is no valid chat hook");
        return this.chat.getPlayerSuffix(player);
    }
}
