package org.notnightsky.mixin;

import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FogType.class)
enum FogTypeMixin {
    FABRIC_API_FLUID_FOG
}
