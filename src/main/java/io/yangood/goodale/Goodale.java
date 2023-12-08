package io.yangood.goodale;

import io.papermc.paper.plugin.configuration.PluginMeta;
import io.yangood.goodale.listen.CustomListen;
import io.yangood.goodale.utils.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Goodale extends JavaPlugin {

    @Override
    public void onEnable() {
        Util.init(getServer());

        PluginMeta pluginMeta = getPluginMeta();
        String pluginVersion = pluginMeta.getVersion();
        String serverVersion = getServer().getVersion();
        // Plugin startup logic
        getServer().sendMessage(
            Component.text("[Goodale]", TextColor.fromHexString("#36BE43"))
                .append(Component.text("插件bug，邮件联系 ywj@yangood.top!", TextColor.fromHexString("#be0013")))
                .append(Component.text("Hello, 插件已装配!", TextColor.fromHexString("#36BE43")))
                .append(Component.text("当前服务器版本【" + serverVersion + "】, 请确认版本与插件版本【" + pluginVersion + "】匹配"))
        );

        getServer().getPluginManager().registerEvents(CustomListen.CREEPER_LISTEN, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
