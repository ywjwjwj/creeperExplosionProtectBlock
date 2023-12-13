package io.yangood.goodale.utils;

import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * @ClassName: Util
 * @Description:
 * @Author: Yang WeiJie
 * @Date: 2023/12/06
 * @Version: 1.0
 */
public class Util {

    private static final String CRAFTBUKKIT_PACKAGE = Bukkit.getServer().getClass().getPackage().getName();
    private static Server server;
    private static PluginMeta pluginMeta;


    public static void init(Server server, @NotNull PluginMeta pluginMeta) {
        Util.server = server;
        Util.pluginMeta = pluginMeta;
    }

    public static Server getServer() {
        return server;
    }

    public static PluginMeta getPluginMeta() {
        return pluginMeta;
    }

    /**
     * 2个单位间的距离
     * @param entity1 实体1
     * @param entity2 实体2
     * @return 实体距离
     */
    public static double calcTowEntityDistance(Entity entity1, Entity entity2) {
        return Math.sqrt(
            (entity1.getX() - entity2.getX()) * (entity1.getX() - entity2.getX())
                + (entity1.getY() - entity2.getY()) * (entity1.getY() - entity2.getY())
                + (entity1.getZ() - entity2.getZ()) * (entity1.getZ() - entity2.getZ())
        );
    }

    public static String cbClass(String clazz) {
        return CRAFTBUKKIT_PACKAGE + "." + clazz;
    }

}
