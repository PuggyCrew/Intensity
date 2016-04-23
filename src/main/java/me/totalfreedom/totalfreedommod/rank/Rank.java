package me.totalfreedom.totalfreedommod.rank;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Rank implements RankBase
{

    IMPOSTOR(Type.PLAYER, "an", "Impostor", ChatColor.YELLOW),
    NON_OP(Type.PLAYER, "a", "", ChatColor.GREEN),
    OP(Type.PLAYER, "an", "OP", ChatColor.RED),
    SUPER_ADMIN(Type.ADMIN, "a", "Super Admin", ChatColor.AQUA),
    TELNET_ADMIN(Type.ADMIN, "a", "Telnet Admin", ChatColor.DARK_GREEN),
    SENIOR_ADMIN(Type.ADMIN, "a", "Senior Admin", ChatColor.LIGHT_PURPLE),
    MFM_DEVELOPER(Type.ADMIN, "a", "MFM Developer", ChatColor.DARK_PURPLE),
    LEAD_DEVELOPER(Type.ADMIN, "a", "Lead Developer", ChatColor.DARK_PURPLE),
    TRIAL_DEVELOPER(Type.ADMIN, "a", "Trial Developer", ChatColor.DARK_PURPLE),
    EXECUTIVE(Type.ADMIN, "an", "Executive", ChatColor.DARK_BLUE),
    TELNET_CONSOLE(),
    SENIOR_CONSOLE();
    //
    @Getter
    private final Type type;
    @Getter
    private final String name;
    private final String determiner;
    @Getter
    private final String tag;
    @Getter
    private final ChatColor color;

    private Rank()
    {
        this("Console", Type.ADMIN_CONSOLE, "the", "Console", ChatColor.DARK_PURPLE);
    }

    private Rank(Type type, String determiner, String tag, ChatColor color)
    {
        this.type = type;

        // Name
        final String[] nameParts = name().toLowerCase().split("_");
        String tempName = "";
        for (String part : nameParts)
        {
            tempName += Character.toUpperCase(part.charAt(0)) + part.substring(1) + " ";
        }
        name = tempName.trim();

        this.determiner = determiner;
        this.tag = tag.length() > 0 ? ChatColor.DARK_GRAY + "[" + color + tag + ChatColor.DARK_GRAY + "]" : "";

        // Colors
        this.color = color;
    }

    private Rank(String name, Type type, String determiner, String tag, ChatColor color)
    {
        this.type = type;
        this.name = name;
        this.determiner = determiner;
        this.tag = ChatColor.DARK_GRAY + "[" + color + tag + ChatColor.DARK_GRAY + "]";
        this.color = color;
    }

    @Override
    public String getColoredName()
    {
        return getColor() + getName();
    }

    @Override
    public String getColoredTag()
    {
        return getColor() + getTag();
    }

    @Override
    public String getColoredLoginMessage()
    {
        return determiner + " " + getColoredName();
    }

    public boolean isConsole()
    {
        return getType() == Type.ADMIN_CONSOLE;
    }

    @Override
    public int getLevel()
    {
        return ordinal();
    }

    @Override
    public boolean isAtLeast(RankBase rank)
    {
        return getLevel() >= rank.getLevel();
    }

    public boolean isAdmin()
    {
        return getType() == Type.ADMIN || getType() == Type.ADMIN_CONSOLE;
    }

    public boolean hasConsole()
    {
        return getConsoleVariant() != null;
    }

    public Rank getConsoleVariant()
    {
        switch (this)
        {
            case TELNET_ADMIN:
            case TELNET_CONSOLE:
                return TELNET_CONSOLE;
            case SENIOR_ADMIN:
            case SENIOR_CONSOLE:
                return SENIOR_CONSOLE;
            default:
                return null;
        }
    }

    public Rank getPlayerVariant()
    {
        switch (this)
        {
            case TELNET_ADMIN:
            case TELNET_CONSOLE:
                return TELNET_ADMIN;
            case SENIOR_ADMIN:
            case SENIOR_CONSOLE:
                return SENIOR_ADMIN;
            default:
                return null;
        }
    }

    public static Rank findRank(String string)
    {
        try
        {
            return Rank.valueOf(string.toUpperCase());
        }
        catch (Exception ignored)
        {
        }

        return Rank.NON_OP;
    }

    public static enum Type
    {

        PLAYER,
        ADMIN,
        ADMIN_CONSOLE;

        public boolean isAdmin()
        {
            return this != PLAYER;
        }
    }

}
