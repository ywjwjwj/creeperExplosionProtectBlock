package io.yangood.goodale.listen;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CreeperListen
 * @Description:
 * @Author: Yang WeiJie
 * @Date: 2023/12/06
 * @Version: 1.0
 */
public class CreeperListen implements Listener {

    private static final Logger logger = LoggerFactory.getLogger(CreeperListen.class);

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBoom(@NotNull ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        // if creeper boom!!
        if (entity instanceof CraftCreeper creeper) {
            // cancel original event
            event.setCancelled(true);
            // call a custom explosion event
            Creeper handle = creeper.getHandle();
            explodeCreeper(event, handle);
        }
    }

    public void explodeCreeper(ExplosionPrimeEvent event, Creeper creeper) {
        creeper.die(creeper.damageSources().genericKill());
        Level level = creeper.level();
        level.explode(creeper, creeper.getX(), creeper.getY(), creeper.getZ(), event.getRadius(), event.getFire(), ExplosionInteraction.NONE); // CraftBukkit
        creeper.discard();
        //EventUtils.spawnLingeringCloud(creeper);
    }

}
