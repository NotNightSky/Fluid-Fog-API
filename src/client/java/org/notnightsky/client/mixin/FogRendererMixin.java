package org.notnightsky.client.mixin;

import java.util.Arrays;

import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import org.notnightsky.client.FluidFogTestingClient;
import org.notnightsky.client.render.fog.environment.TestFluidEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;newArrayList([Ljava/lang/Object;)Ljava/util/ArrayList;", remap = false), index = 0)
    private static Object[] appendCustomFogEnvironment(Object[] originalArray) {
        FogEnvironment[] newArray = Arrays.copyOf(originalArray, originalArray.length + 1, FogEnvironment[].class);

        newArray[originalArray.length] = new TestFluidEnvironment();

        return newArray;
    }
}

