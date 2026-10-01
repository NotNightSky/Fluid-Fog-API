package org.notnightsky.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.notnightsky.FluidFogTesting;

import java.util.function.Function;

public class ModBlocks {
	public static final ResourceKey<Block> TEST_FLUID_KEY = ResourceKey.create(Registries.BLOCK, FluidFogTesting.id("test_fluid"));

	public static final Block TEST_FLUID = register(
			TEST_FLUID_KEY,
			TestFluidBlock::forTestFluid,
			BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
	);

	private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		Block block = blockFactory.apply(properties.setId(id));
		return Registry.register(BuiltInRegistries.BLOCK, id, block);
	}

	public static void initialize() {
	}
}
