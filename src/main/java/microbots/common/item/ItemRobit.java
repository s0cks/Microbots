package microbots.common.item;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.ModularRobitModel;
import microbots.common.entity.EntityRobit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

public class ItemRobit
extends Item{
  public ItemRobit(){
    this.setHasSubtypes(true);
    this.setMaxDamage(0);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return "item.microbots.robit." + DefaultRobitModels.ALL_NAMES[stack.getItemDamage()];
  }

  @Override
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for(int i = 0; i < DefaultRobitModels.ALL_NAMES.length; i++){
      ItemStack stack = new ItemStack(itemIn, 1, i);
      ModularRobitModel model = DefaultRobitModels.ALL[i];
      NBTTagCompound compound = new NBTTagCompound();
      model.writeToNBT(compound);
      stack.setTagCompound(compound);
      subItems.add(stack);
    }
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if(!worldIn.isRemote){
      playerIn.addChatComponentMessage(new TextComponentString("Spawned Robit"));
      EntityRobit robit = new EntityRobit(worldIn);
      robit.initialize(DefaultRobitModels.ALL[stack.getItemDamage()]);
      robit.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 1.0F, 1.0F);
      worldIn.spawnEntityInWorld(robit);
    }
    return EnumActionResult.PASS;
  }
}