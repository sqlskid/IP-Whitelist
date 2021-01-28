package cf.sqlskid.ipwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class JoinLeaveListener implements Listener {


    @EventHandler
    public void onJoin(PlayerLoginEvent e){
        if(IPWhitelist.instance.allowedPlayers.contains(e.getPlayer().getName())){
            IPWhitelist.instance.IPAdresy.put(e.getPlayer().getUniqueId(), e.getAddress().getHostAddress());
            IPWhitelist.instance.allowedPlayers.remove(e.getPlayer().getName());
        }else{
            if(IPWhitelist.instance.IPAdresy.containsKey(e.getPlayer().getUniqueId()) && IPWhitelist.instance.IPAdresy.containsValue(e.getAddress().getHostAddress())){
                e.getPlayer().sendMessage(ChatColor.GREEN + "Byl jsi verifikovan.");
            }else{
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Nejsi na whitelistu!\n Prosim kontaktuj admina.");
            }
        }
    }

}
