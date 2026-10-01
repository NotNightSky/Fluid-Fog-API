package org.notnightsky.fluid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import org.notnightsky.FluidFogTesting;

public class ModFluids {
	public static final ResourceKey<Fluid> TEST_FLUID_STILL_KEY = create("test_fluid");
	public static final ResourceKey<Fluid> TEST_FLUID_FLOWING_KEY = create("flowing_test_fluid");

	public static final FlowingFluid TEST_FLUID_STILL = register(TEST_FLUID_STILL_KEY, new TestFluid.Source());
	public static final FlowingFluid TEST_FLUID_FLOWING = register(TEST_FLUID_FLOWING_KEY, new TestFluid.Flowing());

	public static ResourceKey<Fluid> create(String name) {
		return ResourceKey.create(Registries.FLUID, FluidFogTesting.id(name));
	}

	private static FlowingFluid register(ResourceKey<Fluid> key, FlowingFluid fluid) {
		return Registry.register(BuiltInRegistries.FLUID, key, fluid);
	}

	public static void initialize() {
	}
}
