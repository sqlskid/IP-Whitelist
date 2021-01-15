package cf.sqlskid.ipwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IPWhitelistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            commandSender.sendMessage(ChatColor.RED + "Tenhle prikaz muze pouzit jen konzole!");
            return false;
        } else {
            if (strings.length == 2) {
                if (strings[0].equalsIgnoreCase("add")) {
                    if(IPWhitelist.instance.IPAdresy.contains(strings[1]))
                        return false;
                    IPWhitelist.instance.IPAdresy.add(strings[1]);
                    commandSender.sendMessage(ChatColor.GREEN + "IP Adresa " + strings[1] + " byla pridana na whitelist!");
                    return true;
                } else if (strings[0].equalsIgnoreCase("remove")) {
                    if(!IPWhitelist.instance.IPAdresy.contains(strings[1]))
                        return false;
                    IPWhitelist.instance.IPAdresy.remove(strings[1]);
                    commandSender.sendMessage(ChatColor.GREEN + "IP Adresa " + strings[1] + " byla odebrana z whitelistu!");
                    return true;
                }
                else {
                    commandSender.sendMessage(ChatColor.RED + "Pouzij: /ipwhitelist <add, remove> <IP>");
                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "Pouzij: /ipwhitelist <add, remove> <IP>");
            }
        }
        return false;
    }
}
