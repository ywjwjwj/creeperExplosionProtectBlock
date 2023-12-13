package io.yangood.goodale.entity;

import java.util.Collection;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

/**
 * @ClassName: MofeeCreeper
 * @Description:
 * @Author: Yang WeiJie
 * @Date: 2023/12/13
 * @Version: 1.0
 */
public class MofeeCreeper extends Creeper {

    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(Creeper.class, EntityDataSerializers.BOOLEAN);

    public MofeeCreeper(EntityType<? extends Creeper> type, Level world) {
        super(type, world);
    }

    public MofeeCreeper(Location location) {
        this(EntityType.CREEPER, ((CraftWorld) location.getWorld()).getHandle());
        //更改牛生成的位置
        this.setPos(location.getX(), location.getY(), location.getZ());
        this.setRot(location.getYaw(), location.getPitch());
    }

    @Override
    public void explodeCreeper() {
        if (!this.level().isClientSide) {
            float f = this.isPowered() ? 2.0F : 1.0F;

            // CraftBukkit start
            ExplosionPrimeEvent event = CraftEventFactory.callExplosionPrimeEvent(this, this.explosionRadius * f, false);
            if (!event.isCancelled()) {
                // CraftBukkit end
                this.dead = true;
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), event.getRadius(), event.getFire(), Level.ExplosionInteraction.MOB); // CraftBukkit
                this.discard();
                spawnLingeringCloud(this);
                // CraftBukkit start
            } else {
                this.swell = 0;
                this.entityData.set(DATA_IS_IGNITED, Boolean.valueOf(false)); // Paper
            }
            // CraftBukkit end
        }
    }

    //public MofeeCreeper(CraftServer server, Creeper entity) {
    //    super(server, entity);
    //    this.setPose(Pose.DYING);
    //    this.setAI(false);
    //    this.setCustomName("马飞怕");
    //    this.setCustomNameVisible(true);
    //}
    //
    //@Override
    //public void explode() {
    //    Creeper handle = getHandle();
    //    explodeCreeper(handle);
    //}

    public void explodeCreeper(Creeper creeper) {
        if (!creeper.level().isClientSide) {
            float f = this.isPowered() ? 2.0F : 1.0F;

            // CraftBukkit start
            ExplosionPrimeEvent event = CraftEventFactory.callExplosionPrimeEvent(creeper, creeper.explosionRadius * f, false);
            if (!event.isCancelled()) {
                // CraftBukkit end
                creeper.die(creeper.damageSources().genericKill());
                creeper.level().explode(creeper, creeper.getX(), creeper.getY(), creeper.getZ(), event.getRadius(), event.getFire(), Level.ExplosionInteraction.MOB); // CraftBukkit
                creeper.discard();
                spawnLingeringCloud(creeper);
                // CraftBukkit start
            } else {
                creeper.swell = 0;
                creeper.getEntityData().set(DATA_IS_IGNITED, Boolean.FALSE); // Paper
            }
            // CraftBukkit end
        }
        creeper.die(creeper.damageSources().genericKill());
        Level level = creeper.level();
        level.explode(creeper, creeper.getX(), creeper.getY(), creeper.getZ(), creeper.explosionRadius, false, ExplosionInteraction.NONE); // CraftBukkit
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
