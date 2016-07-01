package me.totalfreedom.totalfreedommod.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "For the shit that is still alive.", usage = "/<command>")
public class Command_shitchest extends FreedomCommand
{
    private final Random random = new Random();
    public static String SHITCHEST_LYRICS = "Heres a chest to store your Shit in!";
    
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        final StringBuilder output = new StringBuilder();
        final ItemStack heldItem = new ItemStack(Material.CHEST);
        final ItemMeta heldItemMeta = heldItem.getItemMeta();
        final String[] words = SHITCHEST_LYRICS.split("");
        for (final String word : words)
        {
            output.append(ChatColor.COLOR_CHAR).append(Integer.toHexString(1 + random.nextInt(14))).append(word);
        }
        FUtil.bcastMsg(output.toString());
        heldItemMeta.setDisplayName(ChatColor.DARK_GRAY + "SHIT CHEST");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Have Fun with your Shit Chest!");
        lore.add(ChatColor.GRAY + "Copyright " + sender.getName() + " 2016");
        heldItemMeta.setLore(lore);
        heldItem.setItemMeta(heldItemMeta);

        for (final Player player : server.getOnlinePlayers())
        {
            final int firstEmpty = player.getInventory().firstEmpty();
            if (firstEmpty >= 0)
            {
                player.getInventory().setItem(firstEmpty, heldItem);
            }

        }
        return true;
    }
}
