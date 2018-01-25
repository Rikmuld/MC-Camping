package com.rikmuld.camping.objs.registers

import com.rikmuld.camping.CampingMod._
import com.rikmuld.camping.objs.tile._
import com.rikmuld.corerm.misc.ModRegister
import net.minecraftforge.fml.common.registry.GameRegistry

object ModTiles extends ModRegister {
    override def register {
      GameRegistry.registerTileEntity(classOf[TileLantern], MOD_ID + "_tileLantern")
      GameRegistry.registerTileEntity(classOf[TileLogseat], MOD_ID + "_tileLogseat")
      GameRegistry.registerTileEntity(classOf[TileLight], MOD_ID + "_tileLight")
      GameRegistry.registerTileEntity(classOf[TileTrap], MOD_ID + "_tileTrap")
      GameRegistry.registerTileEntity(classOf[TileCampfire], MOD_ID + "_tileCampfire")
      GameRegistry.registerTileEntity(classOf[TileCampfireCook], MOD_ID + "_tileCampfireCook")
      GameRegistry.registerTileEntity(classOf[TileCampfireWood], MOD_ID + "_tileCampfireWood")
      GameRegistry.registerTileEntity(classOf[TileTent], MOD_ID + "_tileTent")
    }
  }