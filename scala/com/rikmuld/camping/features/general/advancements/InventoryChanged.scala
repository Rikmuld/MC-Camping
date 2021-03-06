package com.rikmuld.camping.features.general.advancements

import com.google.gson.{JsonDeserializationContext, JsonObject}
import com.rikmuld.camping.Library.AdvancementInfo._
import com.rikmuld.camping.features.inventory_camping.InventoryCamping
import com.rikmuld.corerm.advancements.triggers.TriggerSimple
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.ResourceLocation

object InventoryChanged {
  class Trigger extends TriggerSimple.Trigger[InventoryCamping, Instance] {
    protected val id: ResourceLocation =
      INVENTORY_CHANGED

    override def deserializeInstance(json: JsonObject, context: JsonDeserializationContext): Instance = {
      val items = Option(json.get("items")) map ItemPredicate.deserializeArray map(_.toSeq)
      val isFull = Option(json.get("full")).map(_.getAsBoolean)
      val isEmpty = Option(json.get("empty")).map(_.getAsBoolean)

      new Instance(items, isFull, isEmpty)
    }
  }

  protected class Instance(items: Option[Seq[ItemPredicate]],
                           full: Option[Boolean],
                           empty: Option[Boolean]) extends TriggerSimple.Instance[InventoryCamping](INVENTORY_CHANGED) {

    def test(player: EntityPlayerMP, inventory: InventoryCamping): Boolean =
      items.fold(true)(_.forall(testItem => inventory.getInventory.exists(item => testItem.test(item)))) &&
        full.fold(true)(_ == inventory.isFull) &&
        empty.fold(true)(_ == inventory.isEmpty)
  }
}