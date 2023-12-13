package io.yangood.goodale.utils;

import java.util.Collection;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * @ClassName: EventUtils
 * @Description:
 * @Author: Yang WeiJie
 * @Date: 2023/12/13
 * @Version: 1.0
 */
public class EventUtils {

    public static void spawnLingeringCloud(Creeper creeper) {
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
