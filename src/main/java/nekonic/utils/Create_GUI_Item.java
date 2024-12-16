package nekonic.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class Create_GUI_Item {

    public static ItemStack createGUIItem(int index, boolean isIncrease) {
        ItemStack GUI_Item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) GUI_Item.getItemMeta();
        meta.setHideTooltip(true);

        // 커스텀 모델 데이터 설정
        meta.setCustomModelData(index);

        // 색상 설정 (상승이면 빨간색, 하락이면 파란색)
        if (isIncrease) {
            meta.setColor(Color.RED);
        } else {
            meta.setColor(Color.BLUE);
        }

        GUI_Item.setItemMeta(meta);
        return GUI_Item;
    }

    public static ItemStack initGUIItem(){
        ItemStack GUI_Item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = GUI_Item.getItemMeta();

        meta.displayName(Component.text("메인화면으로 돌아가기"+NamedTextColor.BLUE));

        return GUI_Item;
    }
}
