package org.notnightsky.client.api;

import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FogType;
import org.jspecify.annotations.NonNull;

/**
 * This abstract class extends {@link FogEnvironment} to provide the required {@code isApplicable()} method.
 *
 * <p>Modders should extend this class to create their custom fog environments.
 * This child of {@code FogEnvironment} should then be registered to a fluid via the registry.
 *
 * <p><b>Example usage:</b>
 * <pre>{@code
 * public class AcidFluidFogEnvironment extends FabricFogEnvironment {
 *     // Override relevant FogEnvironment methods to define fog color, distance, shape, etc.
 * }
 * }</pre>
 *
 * @see FluidFogRegistry
 */
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
