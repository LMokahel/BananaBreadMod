package millllionwith4ls.bananabreadmod.core;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;



public class BananaNuke extends Block{
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public BananaNuke(BlockBehaviour.Properties p_57422_) {
        super(p_57422_);
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, Boolean.valueOf(false)));
    }

    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }

    public void onPlace(BlockState p_57466_, Level p_57467_, BlockPos p_57468_, BlockState p_57469_, boolean p_57470_) {
        if (!p_57469_.is(p_57466_.getBlock())) {
            if (p_57467_.hasNeighborSignal(p_57468_)) {
                onCaughtFire(p_57466_, p_57467_, p_57468_, null, null);
                p_57467_.removeBlock(p_57468_, false);
            }

        }
    }
    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    public static void explode(Level pLevel, BlockPos pPos) {
        explode(pLevel, pPos, (LivingEntity)null);
    }

    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    private static void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity) {

        System.out.println("REACHED");
        if (!pLevel.isClientSide) {
            BananaNukePrimed bananaNukePrimed = new BananaNukePrimed(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, pEntity);
            pLevel.addFreshEntity(bananaNukePrimed);
            pLevel.playSound((Player)null, bananaNukePrimed.getX(), bananaNukePrimed.getY(), bananaNukePrimed.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent(pEntity, GameEvent.PRIME_FUSE, pPos);
        }
    }


    public void neighborChanged(BlockState p_57457_, Level p_57458_, BlockPos p_57459_, Block p_57460_, BlockPos p_57461_, boolean p_57462_) {
        if (p_57458_.hasNeighborSignal(p_57459_)) {
            onCaughtFire(p_57457_, p_57458_, p_57459_, null, null);
            p_57458_.removeBlock(p_57459_, false);
        }

    }

    public void playerWillDestroy(Level p_57445_, BlockPos p_57446_, BlockState p_57447_, Player p_57448_) {
        if (!p_57445_.isClientSide() && !p_57448_.isCreative() && p_57447_.getValue(UNSTABLE)) {
            onCaughtFire(p_57447_, p_57445_, p_57446_, null, null);
        }

        super.playerWillDestroy(p_57445_, p_57446_, p_57447_, p_57448_);
    }

    public void wasExploded(Level p_57441_, BlockPos p_57442_, Explosion p_57443_) {
        if (!p_57441_.isClientSide) {
            BananaNukePrimed PrimedBananaNuke = new BananaNukePrimed(p_57441_, (double)p_57442_.getX() + 1D, (double)p_57442_.getY(), (double)p_57442_.getZ() + 1D, p_57443_.getSourceMob());
            int i = PrimedBananaNuke.getFuse();
            PrimedBananaNuke.setFuse((short)(p_57441_.random.nextInt(i) + i));
            p_57441_.addFreshEntity(PrimedBananaNuke);
        }
    }



    public InteractionResult use(BlockState p_57450_, Level p_57451_, BlockPos p_57452_, Player p_57453_, InteractionHand p_57454_, BlockHitResult p_57455_) {
        ItemStack itemstack = p_57453_.getItemInHand(p_57454_);
        if (!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE)) {
            return super.use(p_57450_, p_57451_, p_57452_, p_57453_, p_57454_, p_57455_);
        } else {
            onCaughtFire(p_57450_, p_57451_, p_57452_, p_57455_.getDirection(), p_57453_);
            p_57451_.setBlock(p_57452_, Blocks.AIR.defaultBlockState(), 11);
            Item item = itemstack.getItem();
            if (!p_57453_.isCreative()) {
                if (itemstack.is(Items.FLINT_AND_STEEL)) {
                    itemstack.hurtAndBreak(1, p_57453_, (p_57425_) -> {
                        p_57425_.broadcastBreakEvent(p_57454_);
                    });
                } else {
                    itemstack.shrink(1);
                }
            }

            p_57453_.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(p_57451_.isClientSide);
        }
    }

    public void onProjectileHit(Level p_57429_, BlockState p_57430_, BlockHitResult p_57431_, Projectile p_57432_) {
        if (!p_57429_.isClientSide) {
            BlockPos blockpos = p_57431_.getBlockPos();
            Entity entity = p_57432_.getOwner();
            if (p_57432_.isOnFire() && p_57432_.mayInteract(p_57429_, blockpos)) {
                onCaughtFire(p_57430_, p_57429_, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                p_57429_.removeBlock(blockpos, false);
            }
        }

    }

    public boolean dropFromExplosion(Explosion p_57427_) {
        return false;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57464_) {
        p_57464_.add(UNSTABLE);
    }
}

