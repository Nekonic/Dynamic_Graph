package nekonic;

import nekonic.utils.Create_GUI_Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DynamicGraphPlugin extends JavaPlugin implements Listener {

    private Map<Player, BukkitTask> updateTasks = new HashMap<>();
    private Random random = new Random();

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.YELLOW + "GraphPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        for (BukkitTask task : updateTasks.values()) {
            task.cancel();
        }
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
        player.openInventory(inventory);
        // 인벤토리 업데이트 작업 시작
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getOpenInventory().getTitle().equals("§f궯샮")) {
                    updateGraph(player,inventory);
                } else {
                    this.cancel(); // 인벤토리가 닫혔거나 다른 인벤토리가 열렸다면 작업 중지
                    updateTasks.remove(player);
                }
            }
        }.runTaskTimer(this, 0L, 20L); // 1초마다 실행

        updateTasks.put(player, task);

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("Dynamic Graph")) {
            Player player = (Player) event.getPlayer();
            BukkitTask task = updateTasks.remove(player);
            if (task != null) {
                task.cancel();
            }
        }
    }

    private void updateGraph(Player player, Inventory inventory) {
        if (inventory == null) return;

        int[] modelData = {101,102,103,104,105,106,
                111,112,113,114,115,116,117,118,119,120,
                201,202,203,204,205,206,207,208,209,210};
        int[] initPos = {
                0, 1, 2, 3, 4, 5, 6,
                9,10,11,12,13,14,15,
                18,19,20,21,22,23,24,
                27,28,29,30,31,32,33,
                34,35,36,37,38,39,40,
        };

        for (int i = 0; i < modelData.length; i++) {
            int randomModelData = modelData[random.nextInt(modelData.length)];
            ItemStack graphItem = Create_GUI_Item.createItem(randomModelData);
            inventory.setItem(initPos[i], graphItem);
        }
    }
}
