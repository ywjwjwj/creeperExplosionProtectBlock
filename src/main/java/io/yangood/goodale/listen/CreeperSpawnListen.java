package io.yangood.goodale.listen;

import io.yangood.goodale.entity.MofeeCreeper;
import java.lang.reflect.Field;
import net.minecraft.world.entity.monster.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CreeperSpawnListen
 * @Description: 苦力怕生成监听
 * @Author: Yang WeiJie
 * @Date: 2023/12/13
 * @Version: 1.0
 */
public class CreeperSpawnListen implements Listener {

    private static final Logger logger = LoggerFactory.getLogger(CreeperSpawnListen.class);

    @EventHandler(priority = EventPriority.LOWEST)
    public void spawn(@NotNull EntitySpawnEvent event) {
        spawnMofeeCreeper(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void spawn(@NotNull SpawnerSpawnEvent event) {
        spawnMofeeCreeper(event);
    }

    private static void spawnMofeeCreeper(@NotNull EntityEvent event) {
        Entity entity = event.getEntity();
        // 如果是苦力怕生成 则改为自己的苦力怕
        if (entity instanceof Creeper creeper) {
            MofeeCreeper mofeeCreeper = new MofeeCreeper(creeper.getBukkitEntity().getLocation());
            try {
                Field field = event.getClass().getDeclaredField("entity");
                field.setAccessible(true);
                field.set(event, mofeeCreeper);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
