package org.notnightsky.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import org.notnightsky.fluid.ModFluids;

public class FluidFogTestingClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FluidRenderingRegistry.register(
				ModFluids.TEST_FLUID_STILL,
				ModFluids.TEST_FLUID_FLOWING,
				new FluidModel.Unbaked(
						new Material(Identifier.withDefaultNamespace("block/water_still")),
						new Material(Identifier.withDefaultNamespace("block/water_flow")),
						new Material(Identifier.withDefaultNamespace("block/water_overlay")),
						BlockTintSources.constant(ARGB.opaque(0x075800))
				)
		);
	}
}
