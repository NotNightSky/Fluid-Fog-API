package org.notnightsky.client.api;

import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FogType;
import org.jspecify.annotations.NonNull;

public abstract class FabricFogEnvironment extends FogEnvironment {
    @Override
    public final boolean isApplicable(FogType type, Entity entity) {
        if (type == null || !"FABRIC_API_FLUID_FOG".equals(type.name())) {
            return false;
        }

        Fluid eyeFluid = entity.level().getFluidState(BlockPos.containing(entity.getEyePosition())).getType();
        return FluidFogRegistry.getEnvironment(eyeFluid) == this;
    }

}
