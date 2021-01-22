package cf.sqlskid.ipwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class IPWhitelistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            commandSender.sendMessage(ChatColor.RED + "Tenhle prikaz muze pouzit jen konzole!");
            return false;
        } else {
            if (strings.length == 2) {
                switch (strings[0].toLowerCase()) {
                    case "add":
                        if (!IPWhitelist.instance.allowedPlayers.contains(strings[1])) {
                            IPWhitelist.instance.allowedPlayers.add(strings[1]);
                            commandSender.sendMessage(ChatColor.GREEN + "Hrac " + strings[1] + " byl pridan na IP Whitelist!");
                        }
                        break;
                    case "remove":
                        UUID uuid = Bukkit.getOfflinePlayerIfCached(strings[1]).getUniqueId();
                        if (IPWhitelist.instance.IPAdresy.containsKey(uuid)) {
                            IPWhitelist.instance.IPAdresy.remove(uuid);
                        }
                        break;
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "Pouzij: /ipwhitelist <add, remove> <IP>");
                return true;
            }
        }
        return false;
    }
}
