package io.yangood.goodale;

import io.yangood.goodale.listen.CustomListen;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Goodale extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().sendMessage(
            Component.text("[Goodale]", TextColor.fromHexString("#36BE43"))
                .append(Component.text("Hello, 插件已装配!", TextColor.fromHexString("#36BE43")))
        );

        getServer().getPluginManager().registerEvents(CustomListen.CREEPER_LISTEN, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
