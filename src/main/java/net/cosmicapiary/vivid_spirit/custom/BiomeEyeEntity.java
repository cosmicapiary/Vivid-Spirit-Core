package net.cosmicapiary.vivid_spirit.custom;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BiomeEyeEntity extends Entity implements FlyingItemEntity {
	private static final TrackedData<ItemStack> ITEM;
	private double targetX;
	private double targetY;
	private double targetZ;
	private int lifespan;
	private boolean dropsItem;

	public BiomeEyeEntity(EntityType<? extends BiomeEyeEntity> entityType, World world) {
		super(entityType, world);
	}

	public void setItem(ItemStack stack) {
		this.getDataTracker().set(ITEM, stack.copyWithCount(1));
	}

	private ItemStack getTrackedItem() {
		return this.getDataTracker().get(ITEM);
	}

	public ItemStack getStack() {
		ItemStack itemStack = this.getTrackedItem();
		return itemStack.isEmpty() ? new ItemStack(Items.SNOWBALL) : itemStack;
	}

	protected void initDataTracker() {
		this.getDataTracker().startTracking(ITEM, ItemStack.EMPTY);
	}

	public boolean shouldRender(double distance) {
		double d = this.getBoundingBox().getAverageSideLength() * 4.0;
		if (Double.isNaN(d)) {
			d = 4.0;
		}

		d *= 64.0;
		return distance < d * d;
	}

	public void initTargetPos(BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		double deltaX = x - this.getX();
		double deltaZ = z - this.getZ();
		double dist = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
		if (dist > 12.0) {
			this.targetX = this.getX() + deltaX / dist * 12.0;
			this.targetZ = this.getZ() + deltaZ / dist * 12.0;
			this.targetY = this.getY() + 8.0;
		} else {
			this.targetX = x;
			this.targetY = y;
			this.targetZ = z;
		}

		this.lifespan = 0;
		this.dropsItem = false;
	}

	public void setVelocityClient(double x, double y, double z) {
		this.setVelocity(x, y, z);
		if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
			double velocity = Math.sqrt(x * x + z * z);
			this.setYaw((float)(MathHelper.atan2(x, z) * 57.2957763671875));
			this.setPitch((float)(MathHelper.atan2(y, velocity) * 57.2957763671875));
			this.prevYaw = this.getYaw();
			this.prevPitch = this.getPitch();
		}

	}

	public void tick() {
		super.tick();
		Vec3d velocity = this.getVelocity();
		double newX = this.getX() + velocity.x;
		double newY = this.getY() + velocity.y;
		double newZ = this.getZ() + velocity.z;
		double horizontalVelocity = velocity.horizontalLength();
		this.setPitch(updateRotation(this.prevPitch, (float)(MathHelper.atan2(velocity.y, horizontalVelocity) * 57.2957763671875)));
		this.setYaw(updateRotation(this.prevYaw, (float)(MathHelper.atan2(velocity.x, velocity.z) * 57.2957763671875)));
		if (!this.getWorld().isClient) {
			double deltaX = this.targetX - newX;
			double deltaZ = this.targetZ - newZ;
			float distance = (float)Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
			float angle = (float)MathHelper.atan2(deltaZ, deltaX);
			double xzVelocity = MathHelper.lerp(0.0025, horizontalVelocity, distance);
			double yVelocity = velocity.y;
			if (distance < 1.0F) {
				xzVelocity *= 0.8;
				yVelocity *= 0.8;
			}

			int rising = this.getY() < this.targetY ? 1 : -1;
			velocity = new Vec3d(Math.cos(angle) * xzVelocity, yVelocity + ((double)rising - yVelocity) * 0.014999999664723873, Math.sin(angle) * xzVelocity);
			this.setVelocity(velocity);
		}

		if (this.isTouchingWater()) {
			for(int i = 0; i < 4; ++i) {
				this.getWorld().addParticle(ParticleTypes.BUBBLE, newX - velocity.x * 0.25, newY - velocity.y * 0.25, newZ - velocity.z * 0.25, velocity.x, velocity.y, velocity.z);
			}
		} else {
			this.getWorld().addParticle(ParticleTypes.POOF, newX - velocity.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3, newY - velocity.y * 0.25 - 0.5, newZ - velocity.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3, velocity.x, velocity.y, velocity.z);
		}

		if (!this.getWorld().isClient) {
			this.setPosition(newX, newY, newZ);
			++this.lifespan;
			if (this.lifespan > 80) {
				if (!this.getWorld().isClient) {
					this.playSound(SoundEvents.ENTITY_ENDER_EYE_DEATH, 1.0F, 1.0F);
					this.discard();
					if (this.dropsItem) {
						this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.getStack()));
					} else {
						//this.getWorld().syncWorldEvent(2003, this.getBlockPos(), Item.getRawId(getTrackedItem().getItem()));
						double blockX = getBlockPos().getX() + 0.5;
						double blockY = getBlockPos().getY();
						double blockZ = getBlockPos().getZ() + 0.5;

						for (int i = 0; i < 8; ++i) {
							this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), blockX, blockY, blockZ, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15);
						}

						for (double angle = 0.0; angle < 6.283185307179586; angle += 0.15707963267948966) {
							this.getWorld().addParticle(ParticleTypes.POOF, blockX + Math.cos(angle) * 5.0, blockY - 0.4, blockZ + Math.sin(angle) * 5.0, Math.cos(angle) * -5.0, 0.0, Math.sin(angle) * -5.0);
							this.getWorld().addParticle(ParticleTypes.POOF, blockX + Math.cos(angle) * 5.0, blockY - 0.4, blockZ + Math.sin(angle) * 5.0, Math.cos(angle) * -7.0, 0.0, Math.sin(angle) * -7.0);
						}
					}
				}
				if (!this.dropsItem) {
					double blockX = getBlockPos().getX() + 0.5;
					double blockY = getBlockPos().getY();
					double blockZ = getBlockPos().getZ() + 0.5;

					for (int i = 0; i < 8; ++i) {
						this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), blockX, blockY, blockZ, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15);
					}

					for (double angle = 0.0; angle < 6.283185307179586; angle += 0.15707963267948966) {
						this.getWorld().addParticle(ParticleTypes.POOF, blockX + Math.cos(angle) * 5.0, blockY - 0.4, blockZ + Math.sin(angle) * 5.0, Math.cos(angle) * -5.0, 0.0, Math.sin(angle) * -5.0);
						this.getWorld().addParticle(ParticleTypes.POOF, blockX + Math.cos(angle) * 5.0, blockY - 0.4, blockZ + Math.sin(angle) * 5.0, Math.cos(angle) * -7.0, 0.0, Math.sin(angle) * -7.0);
					}
				}
			}
		} else {
			this.setPos(newX, newY, newZ);
		}

	}



	protected static float updateRotation(float prevRot, float newRot) {
		while(newRot - prevRot < -180.0F) {
			prevRot -= 360.0F;
		}

		while(newRot - prevRot >= 180.0F) {
			prevRot += 360.0F;
		}

		return MathHelper.lerp(0.2F, prevRot, newRot);
	}

	public void writeCustomDataToNbt(NbtCompound nbt) {
		ItemStack itemStack = this.getTrackedItem();
		if (!itemStack.isEmpty()) {
			nbt.put("Item", itemStack.writeNbt(new NbtCompound()));
		}

	}

	public void readCustomDataFromNbt(NbtCompound nbt) {
		ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("Item"));
		this.setItem(itemStack);
	}

	public boolean isAttackable() {
		return false;
	}

	static {
		ITEM = DataTracker.registerData(BiomeEyeEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
	}
}
