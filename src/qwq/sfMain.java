package qwq;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class sfMain extends JavaPlugin implements Listener{
    public static String prefix= ChatColor.translateAlternateColorCodes('&', "&d[Slimefun修复] &r");
    boolean debug = false;
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("插件加载.");
    }
    @Override
    public void onDisable() {
        getLogger().info("插件卸载.");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            getLogger().info(prefix + "你必须是玩家!");
            return true;
        }
        Player p = ((Player) sender).getPlayer();
        if (cmd.getName().equalsIgnoreCase("renew")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("i")) {
                String string = p.getInventory().getItemInMainHand().getItemMeta().toString();
                String string2 = string.replace('§', '&');
                p.sendMessage(string2);
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("debug")) {
                if (debug) debug = false; else debug = true;
                getLogger().info(prefix + "切换成功: " + debug);
                return true;
            }
            if (p.getInventory().getItemInMainHand() == null) return false;
            ItemStack iS = p.getInventory().getItemInMainHand();
            String s = iS.getItemMeta().getDisplayName().toString();
            if (!(s.contains("§e小背包") || s.contains("§e普通背包") || s.contains("§e大背包") || s.contains("§e编织背包") || s.contains("§e镀金背包") || s.contains("§c灵魂绑定背包") || s.contains("§b小冰柜") || s.contains("§9多功能工具 §7- §eI") || s.contains("§9多功能工具 §7- §eII") || s.contains("§9多功能工具 §7- §eIII") || s.contains("§9多功能工具 §7- §eIV") || s.contains("§9多功能工具 §7- §eV") || s.contains("§9多功能工具 §7- §eVI") || s.contains("§9多功能工具 §7- §eVII"))) {
                p.sendMessage(prefix + "你的物品不支持更新.");
                return true;
            }
            if (s.contains("§e小背包")) {
                itemUpdate("§e手提包", p);
            } else if (s.contains("§e普通背包")) {
                itemUpdate("§e小背包", p);
            } else if (s.contains("§e大背包")) {
                itemUpdate("§e单肩包", p);
            } else if (s.contains("§e编织背包")) {
                itemUpdate("§e双肩包", p);
            } else if (s.contains("§e镀金背包")) {
                itemUpdate("§e旅行包", p);
            } else if (s.contains("§c灵魂绑定背包")) {
                itemUpdate("§c系魂包", p);
            } else if (s.contains("§b小冰柜")) {
                itemUpdate("§b便携冰袋", p);
            } else if (s.contains("§9多功能工具 §7- §eI")) {
                itemUpdate("§9瑞士军刀 §7- §eI", p);
            } else if (s.contains("§9多功能工具 §7- §eII")) {
                itemUpdate("§9瑞士军刀 §7- §eII", p);
            } else if (s.contains("§9多功能工具 §7- §eIII")) {
                itemUpdate("§9瑞士军刀 §7- §eIII", p);
            } else if (s.contains("§9多功能工具 §7- §eIV")) {
                itemUpdate("§9瑞士军刀 §7- §eIV", p);;
            } else if (s.contains("§9多功能工具 §7- §eV")) {
                itemUpdate("§9瑞士军刀 §7- §eV", p);
            } else if (s.contains("§9多功能工具 §7- §eVI")) {
                itemUpdate("§9瑞士军刀 §7- §eVI", p);
            } else if (s.contains("§9多功能工具 §7- §eVII")) {
                itemUpdate("§9瑞士军刀 §7- §eVII", p);
            }
            return true;
        }
        return true;
    }
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (e.getItemInHand() == null || e.getItemInHand().getItemMeta() == null || e.getItemInHand().getItemMeta().getDisplayName() == null) return;
        ItemStack iS = e.getItemInHand();
        String s = iS.getItemMeta().getDisplayName().toString();
        if (debug) getLogger().info(s);
        if (s.contains("§e小背包") || s.contains("§e普通背包") || s.contains("§e大背包") || s.contains("§e编织背包") || s.contains("§e镀金背包") || s.contains("§c灵魂绑定背包") || s.contains("§b小冰柜")) {
            p.sendMessage(prefix + "请使用 /renew 更新你的物品.");
            e.setCancelled(true);
        }
    }
    public void itemUpdate(String s, Player p) {
        ItemStack iS = p.getInventory().getItemInMainHand();
        ItemMeta iM = p.getInventory().getItemInMainHand().getItemMeta();
        iM.setDisplayName(s);
        iS.setItemMeta(iM);
        p.getInventory().setItemInMainHand(iS);
        p.sendMessage(prefix + "物品更新成功.");
    }
}
