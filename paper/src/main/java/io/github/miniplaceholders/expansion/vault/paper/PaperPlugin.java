package io.github.miniplaceholders.expansion.vault.paper;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.expansion.vault.paper.placeholder.EcoBalancePlaceholder;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperPlugin extends JavaPlugin {

    @Override
    public void onEnable(){
        this.getSLF4JLogger().info("Starting Example Expansion for Paper");
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
            .build()
            .register();
    }
}
