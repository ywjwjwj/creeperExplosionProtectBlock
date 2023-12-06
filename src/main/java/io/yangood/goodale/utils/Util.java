package io.yangood.goodale.utils;

import org.bukkit.entity.Entity;

/**
 * @ClassName: Util
 * @Description:
 * @Author: Yang WeiJie
 * @Date: 2023/12/06
 * @Version: 1.0
 */
public class Util {

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

}
