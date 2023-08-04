package io.github.miniplaceholders.expansion.vault.paper;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.expansion.vault.paper.placeholder.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class PaperPlugin extends JavaPlugin {

    @Override
    public void onEnable(){
        this.getSLF4JLogger().info("Starting Vault Expansion for Paper");
        final ServicesManager registrationManager = getServer().getServicesManager();
        final RegisteredServiceProvider<Economy> economyProvider = registrationManager.getRegistration(Economy.class);
        final RegisteredServiceProvider<Chat> chatProvider = registrationManager.getRegistration(Chat.class);
        final RegisteredServiceProvider<Permission> permissionProvider = registrationManager.getRegistration(Permission.class);
        final VaultHook hook = new VaultHook(
                permissionProvider == null ? null : permissionProvider.getProvider(),
                economyProvider == null ? null : economyProvider.getProvider(),
                chatProvider == null ? null : chatProvider.getProvider()
        );

        Expansion.builder("vault")
            .filter(Player.class)
            .audiencePlaceholder("eco_balance", new EcoBalancePlaceholder(hook))
            .audiencePlaceholder("group", new PlayerGroupPlaceholder(hook))
            .audiencePlaceholder("groups", new PlayerGroupsPlaceholder(hook))
            .audiencePlaceholder("group_prefix", new PlayerGroupPrefixPlaceholder(hook))
            .audiencePlaceholder("group_suffix", new PlayerGroupSuffixPlaceholder(hook))
            .audiencePlaceholder("has_permission", new PlayerHasPermission(hook))
            .audiencePlaceholder("in_group", new PlayerInGroupPlaceholder(hook))
            .audiencePlaceholder("in_primary_group", new PlayerInPrimaryGroupPlaceholder(hook))
            .audiencePlaceholder("prefix", new PlayerPrefixPlaceholder(hook))
            .audiencePlaceholder("suffix", new PlayerPrefixPlaceholder(hook))
            .build()
            .register();
    }
}
