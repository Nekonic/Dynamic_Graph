package nekonic;

import nekonic.utils.Create_GUI_Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicGraphPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.YELLOW + "GraphPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.YELLOW + "GraphPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("graph")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
                return true;
            }

            Player player = (Player) sender;
            openGraphGUI(player);
            return true;
        }
        return false;
    }

    private void openGraphGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "§f궯샮");

        int model_data[] = {101,102,111,112,113,114,115,121,122,123,124,125};
        int init_pos[] = {
                0, 1, 2, 3, 4, 5, 6,
                9,10,11,12,13,14,15,
                18,19,20,21,22,23,24,
                27,28,29,30,31,32,33
        };
        for(int i=0; i<12; i++){
            // Create a custom item to represent the graph
            ItemStack graphItem = Create_GUI_Item.createItem(model_data[i]);

            // Place the graph item in the center of the inventory
            inventory.setItem(init_pos[i], graphItem);
        }

        player.openInventory(inventory);
    }
}
