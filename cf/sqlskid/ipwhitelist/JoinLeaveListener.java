package cf.sqlskid.ipwhitelist;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class JoinLeaveListener implements Listener {


    @EventHandler
    public void onJoin(PlayerLoginEvent e){
        if(!IPWhitelist.instance.IPAdresy.contains(e.getAddress().getHostAddress())){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Tvoje IP adresa neni na whitelistu. Prosim kontaktuj spravce serveru.");
        }
    }

}
