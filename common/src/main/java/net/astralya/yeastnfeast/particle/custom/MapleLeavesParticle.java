package net.astralya.yeastnfeast.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class MapleLeavesParticle extends TextureSheetParticle {
    private final float fallingDriftAngle;

    protected MapleLeavesParticle(ClientLevel world, double x, double y, double z,
                                  double velocityX, double velocityY, double velocityZ,
                                  SpriteSet spriteSet) {
        super(world, x, y, z);
        this.setSprite(spriteSet.get(this.random.nextInt(12), 12));
        this.lifetime = 100;
        this.gravity = 0.0003F;
        this.friction = 0.98F;
        this.quadSize = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.setSize(this.quadSize, this.quadSize);
        this.fallingDriftAngle = this.random.nextFloat() * ((float) Math.PI * 2F);

        this.xd = velocityX;
        this.yd = -0.01D;
        this.zd = velocityZ;

        this.oRoll = this.roll = 0;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.lifetime-- <= 0) {
            this.remove();
            return;
        }

        float progress = 1.0F - ((float) this.lifetime / 300.0F);
        double driftStrength = Math.sin(progress * Math.PI) * 0.0025F;
        this.xd += Math.cos(this.fallingDriftAngle) * driftStrength;
        this.zd += Math.sin(this.fallingDriftAngle) * driftStrength;

        this.yd -= this.gravity;

        this.move(this.xd, this.yd, this.zd);

        if (this.onGround || (this.lifetime < 299 && (this.xd == 0.0 && this.zd == 0.0))) {
            this.remove();
        } else {
            this.xd *= this.friction;
            this.yd *= this.friction;
            this.zd *= this.friction;
        }
    }

        public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new MapleLeavesParticle(level, x, y, z, vx, vy, vz, this.spriteSet);
        }
    }
}

