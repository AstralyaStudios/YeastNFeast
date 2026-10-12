package net.astralya.yeastnfeast.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class DrippingSyrupParticle extends TextureSheetParticle {
    private final float wobbleSpeed;
    private final float wobbleAmount;

    protected DrippingSyrupParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteProvider) {
        super(world, x, y, z, vx, vy, vz);
        this.setSprite(spriteProvider.get(world.random));
        this.quadSize *= 0.4F;
        this.lifetime = (int)(40 + world.random.nextInt(10));
        this.gravity = 0.0025F; // slow gravity
        this.yd = -0.005;       // initial downward speed
        this.xd = 0;
        this.zd = 0;

        this.wobbleSpeed = 0.1F + world.random.nextFloat() * 0.1F;
        this.wobbleAmount = 0.0015F + world.random.nextFloat() * 0.0015F;

        this.setColor(0.48F, 0.3F, 0.12F); // warm syrupy brown
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
            return;
        }

        this.yd -= this.gravity;

        // Simulate sticky wobble
        double wobbleX = Mth.sin(this.age * wobbleSpeed) * wobbleAmount;
        double wobbleZ = Mth.cos(this.age * wobbleSpeed) * wobbleAmount;

        this.move(wobbleX, this.yd, wobbleZ);

        if (this.onGround) {
            this.setAlpha(0.6F); // Optional: fade slightly before removal
            this.remove();
        }
    }

        public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double vx, double vy, double vz) {
            return new DrippingSyrupParticle(world, x, y, z, vx, vy, vz, this.spriteProvider);
        }
    }
}
