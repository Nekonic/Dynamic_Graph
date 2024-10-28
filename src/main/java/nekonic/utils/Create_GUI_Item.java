package nekonic.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Create_GUI_Item {

    public static ItemStack createItem(int index) {
        ItemStack GUI_Item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = GUI_Item.getItemMeta();


        meta.setHideTooltip(true);

        // 커스텀 모델 데이터 추가
        meta.setCustomModelData(index); // 커스텀 모델 데이터 설정
        GUI_Item.setItemMeta(meta);

        return GUI_Item;
    }
}
