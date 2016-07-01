package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.ChatManager;
import me.totalfreedom.totalfreedommod.config.ConfigEntry;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.EXECUTIVE, source = SourceType.BOTH)
@CommandParameters(
        description = "Remotelly control admin options.",
        usage = "/<command> <adminchatcolor|cakelyrics|shitchestlyrics> <value>")
public class Command_admin extends FreedomCommand
{
    @Override
    protected boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if(args.length < 1)
        {
            return false;
        }
        if(args[0].equalsIgnoreCase("adminchatcolor"))
        {
            if(args.length != 2)
            {
                return false;
            }
            if(args[1].contains("&k") || args[1].contains("&0"))
            {
                msg(ChatColor.RED + "What are you trying to do?");
                return false;
            }
            ChatManager.adminchatcolor = args[1];
            msg("Successfully set adminchat color to " + FUtil.colorize(args[1]) + args[1]);
            
        }
        if(args[0].equalsIgnoreCase("cakelyrics"))
        {
            if(args.length < 2)
            {
                return false;
            }
            if(args[1].equalsIgnoreCase("reset"))
            {
                Command_cake.CAKE_LYRICS = "But there's no sense crying over every mistake. You just keep on trying till you run out of cake.";
                msg("Successfully reset cake lyrics");
            }
            Command_cake.CAKE_LYRICS = StringUtils.join(args," ");
            msg("Successfully set cake lyrics to: " + ChatColor.YELLOW + Command_cake.CAKE_LYRICS);
        }
        if(args[0].equalsIgnoreCase("shitchestlyrics"))
        {
            if(args.length < 2)
            {
                return false;
            }
            if(args[1].equalsIgnoreCase("reset"))
            {
                Command_shitchest.SHITCHEST_LYRICS = "Heres a chest to store your Shit in!";
                msg("Successfully reset cake lyrics");
            }
            Command_shitchest.SHITCHEST_LYRICS = StringUtils.join(args," ");
            msg("Successfully set cake lyrics to: " + ChatColor.YELLOW + Command_shitchest.SHITCHEST_LYRICS);
        }
        return true;
    }
}
