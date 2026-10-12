package net.astralya.yeastnfeast.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class MapleLeavesParticle extends SpriteBillboardParticle {
    private float rotationSpeed;
    private final float fallingCurveAngleFactor;
    private final float rotationAcceleration;

    protected MapleLeavesParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.setSprite(spriteProvider.getSprite(this.random.nextInt(12), 12));
        this.rotationSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.fallingCurveAngleFactor = this.random.nextFloat();
        this.rotationAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.maxAge = 300;
        this.gravityStrength = 7.5E-4F;
        float f = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.scale = f;
        this.setBoundingBoxSpacing(f, f);
        this.velocityMultiplier = 1.0F;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.maxAge-- <= 0) {
            this.markDead();
        }

        if (!this.dead) {
            float f = (float)(300 - this.maxAge);
            float g = Math.min(f / 300.0F, 1.0F);
            double d = Math.cos(Math.toRadians(this.fallingCurveAngleFactor * 60.0F)) * 2.0 * Math.pow((double)g, 1.25);
            double e = Math.sin(Math.toRadians(this.fallingCurveAngleFactor * 60.0F)) * 2.0 * Math.pow((double)g, 1.25);
            this.velocityX += d * 0.0025F;
            this.velocityZ += e * 0.0025F;
            this.velocityY -= this.gravityStrength;
            this.rotationSpeed += this.rotationAcceleration / 20.0F;
            this.prevAngle = this.angle;
            this.angle += this.rotationSpeed / 20.0F;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (this.onGround || (this.maxAge < 299 && (this.velocityX == 0.0 || this.velocityZ == 0.0))) {
                this.markDead();
            }

            if (!this.dead) {
                this.velocityX *= this.velocityMultiplier;
                this.velocityY *= this.velocityMultiplier;
                this.velocityZ *= this.velocityMultiplier;
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new MapleLeavesParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }
}
