package net.cosmicapiary.vivid_spirit.custom;

import com.mojang.datafixers.util.Pair;
import net.cosmicapiary.vivid_spirit.VividSpirit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;

import java.util.function.Predicate;

public class BiomeEyeItem extends Item {
    private final Predicate<RegistryEntry<Biome>> BIOMES;
    private static final int RADIUS = 6400;
    private static final int HORIZONTAL_BLOCK_CHECK_INTERVAL = 64;
    private static final int VERTICAL_BLOCK_CHECK_INTERVAL = 128;

    public BiomeEyeItem(Item.Settings settings, RegistryKey<Biome> key) {
        this(settings, ((biome) -> biome.matchesKey(key)));
    }

    public BiomeEyeItem(Item.Settings settings, TagKey<Biome> biomes) {
        this(settings, ((biome) -> biome.isIn(biomes)));
    }

    public BiomeEyeItem(Item.Settings settings, Predicate<RegistryEntry<Biome>> biomes) {
        super(settings);
        this.BIOMES = biomes;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        if (world instanceof ServerWorld serverWorld) {
            Pair<BlockPos, RegistryEntry<Biome>> biomes = serverWorld.locateBiome(BIOMES, user.getBlockPos(), RADIUS, HORIZONTAL_BLOCK_CHECK_INTERVAL, VERTICAL_BLOCK_CHECK_INTERVAL);
            if (biomes != null) {
                BlockPos blockPos = biomes.getFirst();
                BiomeEyeEntity eyeEntity = new BiomeEyeEntity(VividSpirit.BIOME_EYE, world);
                eyeEntity.setPosition(user.getX(), user.getBodyY(0.5d), user.getZ());
                eyeEntity.setItem(itemStack);
                eyeEntity.initTargetPos(blockPos);
                world.emitGameEvent(GameEvent.PROJECTILE_SHOOT, eyeEntity.getPos(), Emitter.of(user));
                world.spawnEntity(eyeEntity);

                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                world.syncWorldEvent(null, 1003, user.getBlockPos(), 0);
                if (!user.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                user.incrementStat(Stats.USED.getOrCreateStat(this));
                user.swingHand(hand, true);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.consume(itemStack);
    }
}
