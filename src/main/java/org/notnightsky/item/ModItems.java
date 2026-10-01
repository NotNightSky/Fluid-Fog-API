package org.notnightsky.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.notnightsky.FluidFogTesting;
import org.notnightsky.fluid.ModFluids;

import java.util.function.Function;

public class ModItems {
	public static final ResourceKey<Item> TEST_FLUID_BUCKET_KEY = ResourceKey.create(Registries.ITEM, FluidFogTesting.id("test_fluid_bucket"));

	public static final Item TEST_FLUID_BUCKET = register(
			TEST_FLUID_BUCKET_KEY,
			props -> new BucketItem(ModFluids.TEST_FLUID_STILL, props),
			new Item.Properties()
					.craftRemainder(Items.BUCKET)
					.stacksTo(1)
	);

	public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
		Item item = itemFactory.apply(settings.setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);
		return item;
	}

	public static void initialize() {
	}
}
