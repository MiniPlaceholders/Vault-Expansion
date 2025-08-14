package io.github.miniplaceholders.expansion.vault.paper;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.api.provider.ExpansionProvider;
import io.github.miniplaceholders.api.provider.LoadRequirement;
import io.github.miniplaceholders.api.types.Platform;
import io.github.miniplaceholders.expansion.vault.paper.placeholder.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

@SuppressWarnings("unused")
public final class VaultExpansionProvider implements ExpansionProvider {

    @Override
    public Expansion provideExpansion() {
        final ServicesManager registrationManager = Bukkit.getServer().getServicesManager();
        final RegisteredServiceProvider<Economy> economyProvider = registrationManager.getRegistration(Economy.class);
        final RegisteredServiceProvider<Chat> chatProvider = registrationManager.getRegistration(Chat.class);
        final RegisteredServiceProvider<Permission> permissionProvider = registrationManager.getRegistration(Permission.class);
        final VaultHook hook = new VaultHook(
                permissionProvider == null ? null : permissionProvider.getProvider(),
                economyProvider == null ? null : economyProvider.getProvider(),
                chatProvider == null ? null : chatProvider.getProvider()
        );

        return Expansion.builder("vault")
                .author("MiniPlaceholders Contributors")
                .version("2.0.0")
                .audiencePlaceholder(Player.class, "eco_balance", new EcoBalancePlaceholder(hook))
                .audiencePlaceholder(Player.class, "group", new PlayerGroupPlaceholder(hook))
                .audiencePlaceholder(Player.class, "groups", new PlayerGroupsPlaceholder(hook))
                .audiencePlaceholder(Player.class, "group_prefix", new PlayerGroupPrefixPlaceholder(hook))
                .audiencePlaceholder(Player.class, "group_suffix", new PlayerGroupSuffixPlaceholder(hook))
                .audiencePlaceholder(Player.class, "has_permission", new PlayerHasPermission(hook))
                .audiencePlaceholder(Player.class, "in_group", new PlayerInGroupPlaceholder(hook))
                .audiencePlaceholder(Player.class, "in_primary_group", new PlayerInPrimaryGroupPlaceholder(hook))
                .audiencePlaceholder(Player.class, "prefix", new PlayerPrefixPlaceholder(hook))
                .audiencePlaceholder(Player.class, "suffix", new PlayerSuffixPlaceholder(hook))
                .build();
    }

    @Override
    public LoadRequirement loadRequirement() {
        return LoadRequirement.allOf(
                LoadRequirement.platform(Platform.PAPER),
                LoadRequirement.requiredComplement("Vault")
        );
    }


}
