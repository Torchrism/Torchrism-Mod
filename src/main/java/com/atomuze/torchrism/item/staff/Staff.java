package com.atomuze.torchrism.item.staff;

import com.atomuze.torchrism.item.ItemBase;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Staff extends ItemBase {

	private final float attackDamage;
    private final Item.ToolMaterial material;
	
	public Staff(String name,Item.ToolMaterial material) {
		super(name);
		
		this.material = material;
        this.setMaxDamage(material.getMaxUses());
        this.attackDamage = 3.0F + material.getAttackDamage();
	}
	
	

}
