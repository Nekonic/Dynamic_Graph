package nekonic;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;

public class Dynamic_Graph extends JavaPlugin implements Listener {

    private Inventory graphInventory;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Dynamic Graph Plugin has been enabled.");
        createGraphGUI();
        startGraphUpdateTask();
    }

    private void createGraphGUI() {
        graphInventory = Bukkit.createInventory(null, 27, "Dynamic Graph");

        // Initialize the graph with empty values (for example, glass panes)
        for (int i = 0; i < graphInventory.getSize(); i++) {
            graphInventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }

    // To open the GUI for a player
    public void openGraphGUI(Player player) {
        player.openInventory(graphInventory);
    }

    // Update the graph in a loop or based on an event
    private void startGraphUpdateTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Generate dynamic data and update the graph
                updateGraph();
            }
        }.runTaskTimer(this, 0L, 20L);  // Updates every second
    }

    // Example graph update logic
    private void updateGraph() {
        for (int i = 0; i < 9; i++) {
            int value = (int) (Math.random() * 10);  // Example dynamic value between 0 and 9

            // Create a visual representation for the "bar" in the graph
            ItemStack barItem = new ItemStack(Material.BLUE_STAINED_GLASS, value + 1);
            graphInventory.setItem(i, barItem);
        }
    }

    // Prevent players from modifying the graph inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(graphInventory)) {
            event.setCancelled(true);  // Disable item dragging in the graph GUI
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("graph") && sender instanceof Player) {
            Player player = (Player) sender;
            openGraphGUI(player);
            return true;
        }
        return false;
    }
}
