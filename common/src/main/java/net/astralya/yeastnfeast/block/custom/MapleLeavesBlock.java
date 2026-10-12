package net.astralya.yeastnfeast.block.custom;

import net.astralya.yeastnfeast.particle.ModParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MapleLeavesBlock extends LeavesBlock {
    public MapleLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            BlockPos blockPos = pos.below();
            BlockState blockState = level.getBlockState(blockPos);
            if (!Block.isFaceFull(blockState.getCollisionShape(level, blockPos), Direction.UP)) {
                level.addParticle(ModParticleType.MAPLE_LEAVES.get(), pos.getX() + random.nextDouble(), pos.getY(), pos.getZ()
                        + random.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }
}
