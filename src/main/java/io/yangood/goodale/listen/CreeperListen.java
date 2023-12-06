package io.yangood.goodale.listen;

import cn.hutool.core.util.NumberUtil;
import io.yangood.goodale.utils.Util;
import java.util.List;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @ClassName: CreeperListen
 * @Description:
 * @Author: Yang WeiJie
 * @Date: 2023/12/06
 * @Version: 1.0
 */
public class CreeperListen implements Listener {

    @EventHandler
    public void onBoom(ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        // 如果是苦力怕爆炸
        if (entity instanceof Creeper creeper) {
            // 取消爆炸事件
            event.setCancelled(true);

            // 如果苦力怕已死亡
            if (creeper.isDead()) {
                List<Entity> nearbyEntities = creeper.getNearbyEntities(event.getRadius(), event.getRadius(), event.getRadius());
                for (Entity nearbyEntity : nearbyEntities) {
                    if (nearbyEntity instanceof Player player) {
                        // 计算距离
                        double distance = Util.calcTowEntityDistance(player, creeper);
                        // 计算伤害
                        double damage = NumberUtil.round(NumberUtil.mul(6, NumberUtil.div(distance, 3)), 2).doubleValue();

                        // 苦力怕造成伤害
                        player.damage(damage, creeper);
                        // 玩家被击退
                        player.knockback(damage, creeper.getX(), creeper.getZ());
                    }
                }

            }
        }
    }

}
