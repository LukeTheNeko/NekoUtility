package nekoutility.commands

import nekoutility.Main
import nekoutility.Main.Companion.send
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

class enchant : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }
        val p = sender
        if (!p.hasPermission("nekoutility.enchant")) {
            send(p, Main.configcu!!.getConfig().getString("no-permission"))
            return true
        }
        when (args.size) {
            2 -> {
                val itemInHand = p.itemInHand
                if (itemInHand == null || itemInHand.type == Material.AIR) {
                    send(p, Main.configcu!!.getConfig().getString("cannot-enchant"))
                    return true
                }
                val enchantmentName = args[0].lowercase(Locale.getDefault())
                val level = args[1].toInt()
                val enchantment = enchantment(enchantmentName)
                if (enchantment != null) {
                    p.itemInHand = enchantItem(itemInHand, enchantment, level)
                    send(p, "item-enchanted")
                    return true
                } else send(p, "cannot-enchant")
            }

            else -> p.sendMessage(Arrays.stream(Enchantment.values()).toArray().contentToString())
        }
        return false
    }

    private fun enchantItem(item: ItemStack, enchantment: Enchantment, level: Int): ItemStack {
        val meta = item.itemMeta
        meta.addEnchant(enchantment, level, true)
        item.setItemMeta(meta)
        return item
    }

    private fun enchantment(ec: String): Enchantment? {
        return when (ec) {
            "protection" -> Enchantment.PROTECTION_ENVIRONMENTAL
            "efficiency", "eff" -> Enchantment.DIG_SPEED
            "fireprotection" -> Enchantment.PROTECTION_FIRE
            "featherfalling" -> Enchantment.PROTECTION_FALL
            "blast" -> Enchantment.PROTECTION_EXPLOSIONS
            "projectile" -> Enchantment.PROTECTION_PROJECTILE
            "respiration" -> Enchantment.OXYGEN
            "aquainfinity" -> Enchantment.WATER_WORKER
            "thorns" -> Enchantment.THORNS
            "depthstrider" -> Enchantment.DEPTH_STRIDER
            "sharpness", "sharp" -> Enchantment.DAMAGE_ALL
            "smite" -> Enchantment.DAMAGE_UNDEAD
            "arthropods" -> Enchantment.DAMAGE_ARTHROPODS
            "knockback", "kb" -> Enchantment.KNOCKBACK
            "fireaspect" -> Enchantment.FIRE_ASPECT
            "looting" -> Enchantment.LOOT_BONUS_MOBS
            "silktouch", "silk" -> Enchantment.SILK_TOUCH
            "unbreaking", "unb" -> Enchantment.DURABILITY
            "fortune", "fort" -> Enchantment.LOOT_BONUS_BLOCKS
            "power" -> Enchantment.ARROW_DAMAGE
            "punch" -> Enchantment.ARROW_KNOCKBACK
            "fire" -> Enchantment.ARROW_FIRE
            "infinite" -> Enchantment.ARROW_INFINITE
            "luck" -> Enchantment.LUCK
            "lure" -> Enchantment.LURE
            else -> null
        }
    }
}