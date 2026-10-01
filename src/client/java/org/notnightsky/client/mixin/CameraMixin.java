package org.notnightsky.client.mixin;

import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.notnightsky.fluid.TestFluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class CameraMixin {
	@Shadow
	private boolean initialized;

	@Shadow
	private Level level;

	@Shadow
	private Vec3 position;

	@Final
	@Shadow
	private BlockPos.MutableBlockPos blockPosition;

	@Inject(method = "getFluidInCamera", at = @At("HEAD"), cancellable = true)
	private void fluidfogtesting$getTestFluidFogType(CallbackInfoReturnable<FogType> cir) {
		if (!initialized) {
			return;
		}

		FluidState fluidState = level.getFluidState(blockPosition);
		if (!(fluidState.getType() instanceof TestFluid)) {
			return;
		}

		float height = fluidState.getHeightForCamera(level, blockPosition);
		if (position.y < blockPosition.getY() + height) {
			try {
				cir.setReturnValue(FogType.valueOf("FLUIDFOGTESTING_TEST_FLUID"));
			} catch (IllegalArgumentException ignored) {
			}
		}
	}
}
