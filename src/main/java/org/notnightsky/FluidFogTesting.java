package org.notnightsky;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.notnightsky.block.ModBlocks;
import org.notnightsky.fluid.ModFluids;
import org.notnightsky.item.ModItems;

public class FluidFogTesting implements ModInitializer {
	public static final String MOD_ID = "fluidfogtesting";

	@Override
	public void onInitialize() {
		ModFluids.initialize();
		ModBlocks.initialize();
		ModItems.initialize();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
