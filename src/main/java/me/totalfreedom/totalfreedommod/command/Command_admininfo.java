package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.config.ConfigEntry;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(
        description = "Display admin info.",
        usage = "/<command>",
        aliases = "ai")
public class Command_admininfo extends FreedomCommand
{
    @Override
    protected boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        String[] AI = ConfigEntry.SERVER_ADMIN_INFO.getString().split("|");
        for(final String message : AI)
        {
            msg(message);
        }
        return true;
    }
}