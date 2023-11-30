package io.yangood.goodale;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Goodale extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void teleportPlayer(Player player) {
        Location to = getConfig().getLocation("target_location");
        player.teleport(to);
    }
}
