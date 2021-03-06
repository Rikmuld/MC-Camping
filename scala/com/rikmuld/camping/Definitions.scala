package com.rikmuld.camping

import com.rikmuld.camping.CampingMod.OBJ.{fur, tab}
import com.rikmuld.camping.Library._
import com.rikmuld.camping.features.blocks.campfire.cook.{BlockCampfireCook, TileEntityCampfireCook}
import com.rikmuld.camping.features.blocks.campfire.wood.{BlockCampfireWoodOff, BlockCampfireWoodOn, TileEntityCampfireWoodOff, TileEntityCampfireWoodOn}
import com.rikmuld.camping.features.blocks.hemp.BlockHemp
import com.rikmuld.camping.features.blocks.lantern.{BlockLantern, ItemLantern, TileEntityLantern}
import com.rikmuld.camping.features.blocks.logseat.{BlockLogSeat, TileEntityLogSeat}
import com.rikmuld.camping.features.blocks.sleeping_bag.BlockSleepingBag
import com.rikmuld.camping.features.blocks.tent.{BlockTent, TileEntityTent}
import com.rikmuld.camping.features.blocks.trap.{BlockTrap, TileEntityTrap}
import com.rikmuld.camping.features.inventory_camping.TileEntityLight
import com.rikmuld.camping.features.items.kit.ItemKit
import com.rikmuld.camping.features.items.marshmallows.ItemMarshmallow
import com.rikmuld.corerm.objs.Properties.{Ticker, _}
import com.rikmuld.corerm.objs.StateProperty.{DirectionType, PropBool, PropDirection, PropInt}
import com.rikmuld.corerm.objs.blocks.bounds.BlockBounds
import com.rikmuld.corerm.objs.{ObjDefinition, States}
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.util.BlockRenderLayer

object Definitions {
  final val LANTERN_STATES = new States(
    PropBool(Lantern.STATE_LIT),
    PropBool(Lantern.STATE_HANGING)
  )

  final val LANTERN = new ObjDefinition(
    Tab(tab),
    Name("lantern"),
    PropMaterial(Material.GLASS),
    ItemMetaData("off", "on"),
    MaxStackSize(1),
    Hardness(Hardness.DIRT),
    BlockStates(LANTERN_STATES),
    Unstable,
    NonCube,
    RenderType(BlockRenderLayer.CUTOUT),
    ItemClass(classOf[ItemLantern]),
    BlockClass(classOf[BlockLantern]),
    TileEntityClass(classOf[TileEntityLantern])
  )

  object Lantern {
    final val OFF = 0
    final val ON = 1

    final val STATE_LIT = "lit"
    final val STATE_HANGING = "hanging"
  }

  final val HEMP_STATES = new States(
    PropInt(Hemp.STATE_AGE, 0, 6)
  )

  final val HEMP = new ObjDefinition(
    Tab(tab),
    Name("hemp"),
    PropMaterial(Material.PLANTS),
    BlockStates(HEMP_STATES),
    Unstable,
    NonCube,
    NoCollision,
    Ticker,
    RenderType(BlockRenderLayer.CUTOUT),
    BlockClass(classOf[BlockHemp])
  )

  object Hemp {
    final val STATE_AGE = "age"

    final val STATE_AGE_GROWN_BOTTOM = 4
    final val STATE_AGE_GROWN_TOP = 5
    final val STATE_AGE_READY = 3
  }

  final val LOGSEAT_STATES = new States(
    PropDirection(DirectionType.Horizontal),
    PropBool(LogSeat.STATE_RIGHT_LONG),
    PropBool(LogSeat.STATE_LEFT_LONG)
  )

  final val LOGSEAT = new ObjDefinition(
    Tab(tab),
    Name("logseat"),
    PropMaterial(Material.WOOD),
    StepType(SoundType.WOOD),
    Hardness(Hardness.WOOD),
    HarvestLevel(0, "axe"),
    NonCube,
    BlockStates(LOGSEAT_STATES),
    TileEntityClass(classOf[TileEntityLogSeat]),
    BlockClass(classOf[BlockLogSeat])
  )

  object LogSeat {
    final val STATE_RIGHT_LONG = "right_long"
    final val STATE_LEFT_LONG = "left_long"
  }

  final val LIGHT = new ObjDefinition(
    Name("light"),
    PropMaterial(Material.AIR),
    LightLevel(1.0f),
    Air,
    TileEntityClass(classOf[TileEntityLight])
  )

  final val SLEEPING_BAG_STATES = new States(
    PropBool(SleepingBag.STATE_IS_HEAD),
    PropBool(SleepingBag.STATE_IS_OCCUPIED),
    PropDirection(DirectionType.Horizontal)
  )

  final val SLEEPING_BAG = new ObjDefinition(
    Tab(tab),
    Name("sleeping_bag"),
    PropMaterial(Material.CLOTH),
    StepType(SoundType.CLOTH),
    Hardness(0.1f),
    MaxStackSize(4),
    BlockStates(SLEEPING_BAG_STATES),
    BlockClass(classOf[BlockSleepingBag]),
    NoCollision,
    Unstable,
    NonCube
  )

  object SleepingBag {
    final val STATE_IS_HEAD = "is_head"
    final val STATE_IS_OCCUPIED = "is_occupied"
  }

  final val TRAP_STATES = new States(
    PropBool(Trap.STATE_OPEN, true)
  )

  final val TRAP = new ObjDefinition(
    Tab(tab),
    Name("trap"),
    PropMaterial(Material.IRON),
    Hardness(Hardness.STONE),
    HarvestLevel(0, "pickaxe"),
    GuiTrigger(GuiInfo.TRAP),
    Unstable,
    NoCollision,
    NonCube,
    BlockStates(TRAP_STATES),
    TileEntityClass(classOf[TileEntityTrap]),
    BlockClass(classOf[BlockTrap])
  )

  object Trap {
    final val STATE_OPEN = "open"
  }

  final val CAMPFIRE_WOOD_STATES = new States(
    PropInt(CampfireWood.STATE_LIGHT, 0, 16)
  )

  final val CAMPFIRE_WOOD = new ObjDefinition(
    Tab(tab),
    PropMaterial(Material.WOOD),
    Hardness(1),
    Unstable,
    NonCube,
    NoCollision,
    HarvestLevel(0, "axe"),
    BlockStates(CAMPFIRE_WOOD_STATES)
  )

  final val CAMPFIRE_WOOD_ON = CAMPFIRE_WOOD.update(
    LightLevel(LightLevel.TORCH),
    Name("campfire_wood_on"),
    Ticker,
    BlockClass(classOf[BlockCampfireWoodOn]),
    TileEntityClass(classOf[TileEntityCampfireWoodOn])
  )

  final val CAMPFIRE_WOOD_OFF = CAMPFIRE_WOOD.update(
    Name("campfire_wood_off"),
    BlockClass(classOf[BlockCampfireWoodOff]),
    TileEntityClass(classOf[TileEntityCampfireWoodOff])
  )

  object CampfireWood {
    final val STATE_LIGHT = "light"
  }

  final val CAMPFIRE_COOK_STATES = new States(
    PropBool(CampfireCook.STATE_ON)
  )

  final val CAMPFIRE_COOK = new ObjDefinition(
    Tab(tab),
    Name("campfire_cook"),
    PropMaterial(Material.ROCK),
    Hardness(Hardness.STONE),
    HarvestLevel(0, "pickaxe"),
    Unstable,
    NonCube,
    BlockStates(CAMPFIRE_COOK_STATES),
    BlockClass(classOf[BlockCampfireCook]),
    TileEntityClass(classOf[TileEntityCampfireCook]),
    GuiTrigger(GuiInfo.CAMPFIRE_COOK)
  )

  object CampfireCook {
    final val STATE_ON = "lit"
  }

  final val TENT_STATES = new States(
    PropBool(Tent.STATE_ON),
    PropDirection(DirectionType.Horizontal)
  )

  final val TENT = new ObjDefinition(
    Tab(tab),
    Name("tent"),
    PropMaterial(Material.CLOTH),
    Hardness(0.2f),
    GuiTrigger(GuiInfo.TENT),
    BlockStates(TENT_STATES),
    NonCube,
    Unstable,
    ItemMetaData(
      "black",
      "red",
      "green",
      "brown",
      "blue",
      "purple",
      "cyan",
      "gray_light",
      "gray_dark",
      "pink",
      "lime",
      "yellow",
      "blue_light",
      "magenta",
      "orange",
      "white"
    ),
    BlockClass(classOf[BlockTent]),
    TileEntityClass(classOf[TileEntityTent])
  )

  object Tent {
    final val STATE_ON = "on"

    final val BLACK = 0
    final val RED = 1
    final val GREEN = 2
    final val BROWN = 3
    final val BLUE = 4
    final val PURPLE = 5
    final val CYAN = 6
    final val GRAY_LIGHT = 7
    final val GRAY_DARK = 8
    final val PINK = 9
    final val LIME = 10
    final val YELLOW = 11
    final val BLUE_LIGHT = 12
    final val MAGENTA = 13
    final val ORANGE = 14
    final val WHITE = 15
  }

  final val BOUNDS_TENT = BlockBounds.BOUNDS.update(
    Name("tent_bounds"),
    PropMaterial(Material.CLOTH),
    NonCube,
    Unstable
  )

  final val KNIFE = new ObjDefinition(
    Tab(tab),
    MaxDamage(200),
    MaxStackSize(1),
    Name("knife")
  )

  final val PARTS = new ObjDefinition(
    Tab(tab),
    Name("parts"),
    ItemMetaData("canvas", "stick_iron", "peg", "pan", "ash", "marshmallows", "marshmallow_stick_raw")
  )

  object Parts {
    final val CANVAS = 0
    final val STICK_IRON = 1
    final val TENT_PEG = 2
    final val PAN = 3
    final val ASH = 4
    final val MARSHMALLOW = 5
    final val MARSHMALLOW_STICK = 6
  }

  final val BACKPACK = new ObjDefinition(
    Tab(tab),
    MaxStackSize(1),
    Name("bag"),
    GuiTriggerMeta((0, GuiInfo.POUCH), (1, GuiInfo.BACKPACK), (2, GuiInfo.RUCKSACK)),
    ItemMetaData("pouch", "backpack", "rucksack")
  )

  object Backpack {
    final val POUCH = 0
    final val BACKPACK = 1
    final val RUCKSACK = 2
  }

  final val KIT = new ObjDefinition(
    Tab(tab),
    MaxStackSize(1),
    Name("kit"),
    GuiTrigger(GuiInfo.KIT),
    ItemMetaData("kit", "spit", "grill", "pan", "useless"),
    ItemClass(classOf[ItemKit])
  )

  object Kit {
    final val EMPTY = 0
    final val SPIT = 1
    final val GRILL = 2
    final val PAN = 3
    final val USELESS = 4
  }

  final val MARSHMALLOW = new ObjDefinition(
    Tab(tab),
    Name("marshmallow_stick_cooked"),
    LikedByWolfs(false),
    FoodPoints(3),
    Saturation(Saturation.Poor),
    ItemClass(classOf[ItemMarshmallow])
  )

  final val PARTS_ANIMAL = new ObjDefinition(
    Tab(tab),
    Name("parts_animal"),
    ItemMetaData("fur_white", "fur_brown")
  )

  object PartsAnimal {
    final val FUR_WHITE = 0
    final val FUR_BROWN = 1
  }

  final val FUR_BOOT = new ObjDefinition(
    Tab(tab),
    Name("armor_fur_boots"),
    ArmorType(EntityEquipmentSlot.FEET),
    PropArmorMaterial(fur),
    ArmorTexture(TextureInfo.ARMOR_FUR_MAIN)
  )

  final val FUR_LEG = new ObjDefinition(
    Tab(tab),
    Name("armor_fur_leg"),
    ArmorType(EntityEquipmentSlot.LEGS),
    PropArmorMaterial(fur),
    ArmorTexture(TextureInfo.ARMOR_FUR_LEG)
  )

  final val FUR_CHEST = new ObjDefinition(
    Tab(tab),
    Name("armor_fur_chest"),
    ArmorType(EntityEquipmentSlot.CHEST),
    PropArmorMaterial(fur),
    ArmorTexture(TextureInfo.ARMOR_FUR_MAIN)
  )

  final val FUR_HEAD = new ObjDefinition(
    Tab(tab),
    Name("armor_fur_helm"),
    ArmorType(EntityEquipmentSlot.HEAD),
    PropArmorMaterial(fur),
    ArmorTexture(TextureInfo.ARMOR_FUR_MAIN)
  )

  final val VENISON_RAW = new ObjDefinition(
    Tab(tab),
    Name("venison_raw"),
    LikedByWolfs(true),
    FoodPoints(4),
    Saturation(Saturation.Low)
  )

  final val VENISON_COOKED = new ObjDefinition(
    Tab(tab),
    Name("venison_cooked"),
    LikedByWolfs(true),
    FoodPoints(10),
    Saturation(Saturation.Good)
  )
}