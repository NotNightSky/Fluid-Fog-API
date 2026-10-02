package org.notnightsky.client.api;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import org.jspecify.annotations.Nullable;
import org.notnightsky.client.mixin.FogRendererAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class FluidFogRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger("fog-api");
    private static final Map<Fluid, FabricFogEnvironment> BY_FLUID = new ConcurrentHashMap<>();
    private static final List<FabricFogEnvironment> ENVS = new ArrayList<>();

    private FluidFogRegistry() {}

    /**
     * <p>This method is used to register the {@code FabricFogEnvironment}.
     *
     * <p>Note that environments are automatically associated with the fluid
     * by the {@code still} and {@code flowing} arguments. Therefore, no further
     * registrations are required to make the fog function.
     *
     * @param still The fluid's still type resource key.
     * @param flowing The fluid's flowing type resource key.
     * @param env The {@code FabricFogEnvironment} that is to be used for these fluid states.
     */
    public static void register(Fluid still, Fluid flowing, FabricFogEnvironment env) {
        Objects.requireNonNull(still, "still");
        Objects.requireNonNull(flowing, "flowing");
        Objects.requireNonNull(env, "env");

        for (Fluid fluid : List.of(still, flowing)) {
            for (FabricFogEnvironment existing : ENVS) {
                if (existing.equals(env)) {
                    LOGGER.warn("Fluid {} already has a fog environment ({})",
                            BuiltInRegistries.FLUID.getKey(fluid), existing.getClass().getName());
                }
            }
            BY_FLUID.put(fluid, env);
        }
        if (!ENVS.contains(env)) {
            ENVS.add(env);
            FogRendererAccessor.getFogEnvironments().add(env);
        }
    }

    /**
     * <p>This method is used to find if the fluid is assigned an environment.
     *
     * @param fluid The Fluid to be checked.
     * @return Whether the environment for the said fluid is found.
     */
    public static boolean hasEnvironment(Fluid fluid) {
        return BY_FLUID.containsKey(fluid);
    }

    /**
     * <p>This method is used to find the environment assigned to the fluid.
     *
     * @param fluid The Fluid to look up.
     * @return The environment registered for the fluid, or null if none.
     */
    public static @Nullable FabricFogEnvironment getEnvironment(Fluid fluid) {
        return BY_FLUID.get(fluid);
    }
}
