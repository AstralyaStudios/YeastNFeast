package net.astralya.yeastnfeast.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class FermentationBubbleParticle extends TextureSheetParticle {
    protected FermentationBubbleParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z);
        this.pickSprite(sprites);
        this.quadSize = 0.04F + this.random.nextFloat() * 0.02F;
        this.lifetime = 30 + this.random.nextInt(15);
        this.hasPhysics = false;
        this.gravity = 0;
        this.friction = 1.0F;
        this.xd = (this.random.nextDouble() - 0.5) * 0.003;
        this.yd = 0.015;
        this.zd = (this.random.nextDouble() - 0.5) * 0.003;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new FermentationBubbleParticle(level, x, y, z, this.sprites);
        }
    }
}
