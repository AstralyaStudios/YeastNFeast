package net.astralya.yeastnfeast.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MapleLeavesParticle extends TextureSheetParticle {
    private float rotationSpeed;
    private final float fallingCurveAngleFactor;
    private final float rotationAcceleration;

    protected MapleLeavesParticle(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteProvider) {
        super(world, x, y, z);
        this.setSprite(spriteProvider.get(this.random.nextInt(12), 12));
        this.rotationSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.fallingCurveAngleFactor = this.random.nextFloat();
        this.rotationAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.lifetime = 300;
        this.gravity = 7.5E-4F;
        float f = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.quadSize = f;
        this.setSize(f, f);
        this.friction = 1.0F;
        this.xd = velocityX;
        this.yd = velocityY;
        this.zd = velocityZ;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float f = (float)(300 - this.lifetime);
            float g = Math.min(f / 300.0F, 1.0F);
            double d = Math.cos(Math.toRadians(this.fallingCurveAngleFactor * 60.0F)) * 2.0 * Math.pow((double)g, 1.25);
            double e = Math.sin(Math.toRadians(this.fallingCurveAngleFactor * 60.0F)) * 2.0 * Math.pow((double)g, 1.25);
            this.xd += d * 0.0025F;
            this.zd += e * 0.0025F;
            this.yd -= this.gravity;
            this.rotationSpeed += this.rotationAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll += this.rotationSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || (this.lifetime < 299 && (this.xd == 0.0 || this.zd == 0.0))) {
                this.remove();
            }

            if (!this.removed) {
                this.xd *= this.friction;
                this.yd *= this.friction;
                this.zd *= this.friction;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new MapleLeavesParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }
}
