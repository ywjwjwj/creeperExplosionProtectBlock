package io.yangood.goodale;

import io.yangood.goodale.listen.CustomListen;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public final class Goodale extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().sendMessage(Component.text("&4[Goodale]&6Hello, 插件已装载!"));
        getServer().getPluginManager().registerEvents(CustomListen.CREEPER_LISTEN, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
