package org.notnightsky.client.api;

import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;
import org.jspecify.annotations.NonNull;

public abstract class FabricFogEnvironment extends FogEnvironment {
    @Override
    public final boolean isApplicable(FogType type, @NonNull Entity entity) {
        return type != null && "FABRIC_API_FLUID_FOG".equals(type.name());
    }

}
