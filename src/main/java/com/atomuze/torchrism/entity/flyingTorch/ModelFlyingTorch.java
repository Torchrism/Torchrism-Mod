package com.atomuze.torchrism.entity.flyingTorch;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports

public class ModelFlyingTorch extends ModelBase {
	private final ModelRenderer bb_main;

	public ModelFlyingTorch() {
		textureWidth = 16;
		textureHeight = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 4, -2.0F, -10.0F, 0.0F, 2, 10, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 0, -5.75F, -8.74F, 1.0F, 4, 4, 0, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, -0.75F, -9.0F, 1.0F, 4, 4, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}