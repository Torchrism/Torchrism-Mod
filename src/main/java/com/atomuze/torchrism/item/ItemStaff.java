package com.atomuze.torchrism.item;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.block.BlockWaterTorch;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.network.PacketAltarCraftingParticle;
import com.atomuze.torchrism.network.ModNetworks;
import com.atomuze.torchrism.network.PacketModParticle;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStaff extends net.minecraft.item.ItemTool {
	
	private String name;
	Random random = new Random();

	public static final Item.ToolMaterial TORCH_STAFF_MATERIAL = EnumHelper.addToolMaterial("TORCH_STAFF", 3, 65535, 0, 0, 0);
	private java.util.Map<String, Integer> toolClasses = new java.util.HashMap<String, Integer>();
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);
	NBTTagCompound nbt;
	private int torchCount;
    
	public ItemStaff(String name) {
		super(0, 0, TORCH_STAFF_MATERIAL, EFFECTIVE_ON);
		setRegistryName(name);
		setUnlocalizedName(Torchrism.MODID + "." + name);
		setCreativeTab(Torchrism.creativeTab);
		setMaxStackSize(1);
		setHarvestLevel("pickaxe", 3);
		this.name = name;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
		setNbt(itemstack);
		
		if (raytraceresult == null) {
			if (playerIn.isSneaking()) {
				if (!worldIn.isRemote) {
					for (int i = 0; i < 36; i++) {
						if (playerIn.inventory.getStackInSlot(i).getItem().equals(new ItemStack(Blocks.TORCH).getItem())) {
							addNbtCount("count", playerIn.inventory.getStackInSlot(i).getCount());
							playerIn.inventory.getStackInSlot(i).shrink(playerIn.inventory.getStackInSlot(i).getCount());
							return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
						}
					}
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.ENTITY) {

			BlockPos blockpos = raytraceresult.getBlockPos();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);
			Material material = iblockstate.getMaterial();
			
			if (material == Material.WATER && ((Integer) iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0 && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.AIR) {
				worldIn.setBlockState(blockpos.up(), ModBlocks.waterTorch.getDefaultState(), 11);
				playerIn.addStat(StatList.getObjectUseStats(this));
				playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
				if (!worldIn.isRemote) {
					playerIn.getHeldItem(handIn).damageItem(-1, playerIn);
					addNbtCount("count", 1);
				}
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			} else {
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			}
		} else {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		Block block = new Block(Material.CIRCUITS);
		IBlockState state = worldIn.getBlockState(pos);
		setNbt(itemstack);
		if(player.isSneaking() && worldIn.getBlockState(pos).getBlock() == Blocks.TORCH) {
			if (!worldIn.isRemote) {
				if(nbt.getInteger("count") > 0) {
					addNbtCount("count", -1);
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, new ItemStack(Blocks.TORCH)));
					return EnumActionResult.SUCCESS;
				}
			}
			return EnumActionResult.PASS;
		}else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.waterTorch || worldIn.getBlockState(pos).getBlock() == Blocks.TORCH || worldIn.getBlockState(pos).getBlock() == ModBlocks.compactedTorch || worldIn.getBlockState(pos).getBlock() == ModBlocks.doublecompactedTorch) {
			if (!worldIn.isRemote) {
				if (worldIn.getBlockState(pos).getBlock() == ModBlocks.waterTorch) {
					addNbtCount("count", 1);
				} else if (worldIn.getBlockState(pos).getBlock() == Blocks.TORCH) {
					addNbtCount("count", 1);
				} else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.compactedTorch) {
					addNbtCount("count", 8);
				} else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.doublecompactedTorch) {
					addNbtCount("count", 64);
				}
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
			ModNetworks.network.sendToDimension(new PacketModParticle(EnumParticleTypes.SMOKE_LARGE.getParticleID(), pos.getX(), pos.getY(), pos.getZ()), player.dimension);
			return EnumActionResult.SUCCESS;
		} else if (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && block.canPlaceTorchOnTop(state, worldIn, pos.down()) && nbt.getInteger("count") > 0) {
				worldIn.setBlockState(pos, Blocks.TORCH.getDefaultState());
				addNbtCount("count", 1);
				return EnumActionResult.SUCCESS;
		} else if (block.canPlaceTorchOnTop(state, worldIn, pos) && facing != EnumFacing.DOWN && nbt.getInteger("count") > 0) {
			addNbtCount("count", -1);
			player.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
			Material m = worldIn.getBlockState(posOffset(pos ,facing)).getMaterial();
			if((m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER){
				worldIn.setBlockState(posOffset(pos ,facing), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
			}else if(m == Material.WATER) {
				worldIn.setBlockState(posOffset(pos ,facing), ModBlocks.waterTorch.getDefaultState().withProperty(BlockTorch.FACING, facing));
			}
			return EnumActionResult.SUCCESS;
		} else if (nbt.getInteger("count") <= 0) {
			if (worldIn.isRemote) player.sendMessage(new TextComponentString(I18n.format(Torchrism.MODID + "." + "torch_staff.no_enough_item")));		
		}
		return EnumActionResult.PASS;
	}

	private void addNbtCount(String NbtName, int addNum) {
		int a = nbt.getInteger(NbtName);
		nbt.setInteger(NbtName, a + addNum);
	}
	
	private void setNbt(ItemStack itemstack) {
		nbt = itemstack.hasTagCompound() ? itemstack.getTagCompound() : new NBTTagCompound();
		if(!nbt.hasKey("count")) {
		    itemstack.setTagCompound(nbt);
			nbt.setInteger("count", 0);
		}
	}
	
	private BlockPos posOffset(BlockPos pos ,EnumFacing facing) {
		if (facing == EnumFacing.NORTH) {
			return pos.north();
		} else if (facing == EnumFacing.SOUTH) {
			return pos.south();
		} else if (facing == EnumFacing.EAST) {
			return pos.east();
		} else if (facing == EnumFacing.WEST) {
			return pos.west();
		} else if (facing == EnumFacing.UP) {
			return pos.up();
		} else {
			return pos;
		}
	}
	
	public void registerItemModel(Item item) {
		Torchrism.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		if(stack.hasTagCompound()) {
			if(nbt.hasKey("count")) {
				tooltip.add(I18n.format(Torchrism.MODID + "." + "torch_staff.torch_count") + nbt.getInteger("count"));
			}
		}
		
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}
}