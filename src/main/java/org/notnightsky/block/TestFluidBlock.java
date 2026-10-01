package org.notnightsky.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import org.notnightsky.fluid.ModFluids;

public class TestFluidBlock extends net.minecraft.world.level.block.LiquidBlock {
	public TestFluidBlock(FlowingFluid fluid, BlockBehaviour.Properties properties) {
		super(fluid, properties);
	}

	public static TestFluidBlock forTestFluid(BlockBehaviour.Properties properties) {
		return new TestFluidBlock(ModFluids.TEST_FLUID_STILL, properties);
	}
}
