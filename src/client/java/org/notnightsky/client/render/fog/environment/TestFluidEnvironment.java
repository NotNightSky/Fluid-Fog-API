package org.notnightsky.client.render.fog.environment;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.util.ARGB;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class TestFluidEnvironment extends FogEnvironment {
    private static final Vector3fc GREEN = ARGB.vector3fFromRGB24(0x00FF00);

    @Override
    public void setupFog(final FogData fog, final Camera camera, final ClientLevel level, final float renderDistance, final DeltaTracker deltaTracker) {
        float partialTicks = deltaTracker.getGameTimeDeltaPartialTick(false);
        fog.environmentalStart = camera.attributeProbe().getValue(EnvironmentAttributes.WATER_FOG_START_DISTANCE, partialTicks);
        fog.environmentalEnd = camera.attributeProbe().getValue(EnvironmentAttributes.WATER_FOG_END_DISTANCE, partialTicks);
        if (camera.entity() instanceof LocalPlayer player) {
            fog.environmentalEnd = fog.environmentalEnd * Math.max(0.25F, player.getWaterVision());
        }

        fog.skyEnd = fog.environmentalEnd;
        fog.cloudEnd = fog.environmentalEnd;
    }

    @Override
    public boolean isApplicable(final @Nullable FogType fogType, final Entity entity) {
        return fogType != null && "FLUIDFOGTESTING_TEST_FLUID".equals(fogType.name());
    }

    @Override
    public @NonNull Vector3fc getBaseColor(final ClientLevel level, final Camera camera, final int renderDistance, final float partialTicks) {
        return GREEN;
    }
}
