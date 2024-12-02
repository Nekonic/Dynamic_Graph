package nekonic;

import nekonic.utils.Create_GUI_Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(
                Component.text("GraphPlugin has been enabled!", NamedTextColor.DARK_AQUA)
        );
    }

    @Override
    public void onDisable() {
        for (BukkitTask task : updateTasks.values()) {
            task.cancel();
        }
        getServer().getConsoleSender().sendMessage(
                Component.text("GraphPlugin has been disabled!", NamedTextColor.DARK_AQUA)
        );
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
        if (event.getView().getTitle().equals("§f궯샮")) {
            Player player = (Player) event.getPlayer();
            BukkitTask task = updateTasks.remove(player);
            if (task != null) {
                task.cancel();
            }
        }
    }

    private void updateGraph(Player player, Inventory inventory) {
        if (inventory == null) return;

        int[] modelDataList = {
                101, 102, 103, 104, 105, 106, 107, 108, 109, 110
        };

        int[] initPos = {
                0, 1, 2, 3, 4, 5, 6,
                9,10,11,12,13,14,15,
                18,19,20,21,22,23,24,
                27,28,29,30,31,32,33,
                36,37,38,39,40,41,42
        };

        for (int i : initPos) {
            // 랜덤하게 모델 데이터 선택
            int randomModelData = modelDataList[(int)(Math.random() * modelDataList.length)];

            boolean isIncrease = Math.random() > 0.5; // 예시로 랜덤하게 상승/하락을 결정

            // 그래프 아이템 생성
            ItemStack graphItem = Create_GUI_Item.createItem(randomModelData, isIncrease);
            inventory.setItem(i, graphItem);
        }
    }
}
