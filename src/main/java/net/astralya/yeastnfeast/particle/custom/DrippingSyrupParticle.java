package net.astralya.yeastnfeast.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrippingSyrupParticle extends TextureSheetParticle {

    protected DrippingSyrupParticle(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.setSprite(spriteProvider.get(world.random));
        this.quadSize *= 0.5F;
        this.lifetime = (int) (64.0F / (Mth.randomBetween(world.random, 0.3F, 1.0F) * 0.5F));
        this.gravity = 0.01F;
        this.xd = 0.0;
        this.yd = -0.01;
        this.zd = 0.0;
        this.setColor(0.8F * 0.6F, 0.5F * 0.6F, 0.2F * 0.6F);
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
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround) {
                this.remove();
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
            return new DrippingSyrupParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }
}