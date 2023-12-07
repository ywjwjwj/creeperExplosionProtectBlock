package io.yangood.goodale.listen;

import java.util.Collection;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
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
        // 如果是苦力怕爆炸
        if (entity instanceof CraftCreeper creeper) {
            // 取消原始事件
            event.setCancelled(true);

            // 调用自定义爆炸事件
            Creeper handle = creeper.getHandle();
            explodeCreeper(event, handle);
        }
    }

    public void explodeCreeper(ExplosionPrimeEvent event, Creeper creeper) {
        creeper.die(creeper.damageSources().genericKill());
        Level level = creeper.level();
        level.explode(creeper, creeper.getX(), creeper.getY(), creeper.getZ(), event.getRadius(), event.getFire(),
            ExplosionInteraction.NONE); // CraftBukkit
        creeper.discard();
        spawnLingeringCloud(creeper);
    }

    private void spawnLingeringCloud(Creeper creeper) {
        Collection<MobEffectInstance> collection = creeper.getActiveEffects();

        Level level = creeper.level();
        if (!collection.isEmpty() && !level.paperConfig().entities.behavior.disableCreeperLingeringEffect) { // Paper
            AreaEffectCloud entityareaeffectcloud = new AreaEffectCloud(creeper.level(), creeper.getX(), creeper.getY(),
                creeper.getZ());

            entityareaeffectcloud.setOwner(creeper); // CraftBukkit
            entityareaeffectcloud.setRadius(2.5F);
            entityareaeffectcloud.setRadiusOnUse(-0.5F);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float) entityareaeffectcloud.getDuration());

            for (MobEffectInstance mobEffect : collection) {
                entityareaeffectcloud.addEffect(new MobEffectInstance(mobEffect));
            }

            level.addFreshEntity(entityareaeffectcloud, CreatureSpawnEvent.SpawnReason.EXPLOSION); // CraftBukkit
        }
    }

}
