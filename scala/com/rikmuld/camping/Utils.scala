package com.rikmuld.camping

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayer.SleepResult
import net.minecraft.init.Biomes
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World

import scala.collection.JavaConversions._

object Utils {
  def trySleep(isOccupied: => Boolean, setOccupied: (Boolean) => Unit)(world: World, pos: BlockPos, player: EntityPlayer): Boolean = {
    if(!world.provider.canRespawnHere || world.getBiomeForCoordsBody(pos) == Biomes.HELL) {
      world.destroyBlock(pos, false)
      world.newExplosion(null, pos.getX + 0.5, pos.getY + 0.5, pos.getZ + 0.5, 5, true, true)

      return true
    }

    if (isOccupied && getPlayerInBed(world, pos).isDefined) {
      player.sendMessage(new TextComponentTranslation("tile.bed.occupied", new Object))

      return true
    }

    var occupied: Boolean =
      false

    player.trySleep(pos) match {
      case SleepResult.OK =>
        occupied = true
      case SleepResult.NOT_POSSIBLE_NOW =>
        player.sendMessage(new TextComponentTranslation("tile.bed.noSleep", new Object))
      case SleepResult.NOT_SAFE =>
        player.sendMessage(new TextComponentTranslation("tile.bed.noSafe", new Object))
      case SleepResult.TOO_FAR_AWAY =>
        player.sendMessage(new TextComponentTranslation("tile.bed.toFarAway", new Object))
      case _ =>
    }

    setOccupied(occupied)

    true
  }

  def getPlayerInBed(world: World, pos: BlockPos): Option[EntityPlayer] =
    world.playerEntities.find(player =>
      player.isPlayerSleeping && player.getPosition.equals(pos)
    )
}

object SeqUtils {
  def merge[A, B, C](b: Seq[B])(f: (A, B) => C)(a: Seq[A]): Seq[C] =
    merge(a, b)(f)

  def merge[A, B, C](a: Seq[A], b: Seq[B])(f: (A, B) => C): Seq[C] =
    a zip b map(t => f(t._1, t._2))

  def allOne(all: Int => Boolean)(one: Int => Boolean)(v: Seq[Int]): Boolean =
    (v forall all) && (v exists one)
}
