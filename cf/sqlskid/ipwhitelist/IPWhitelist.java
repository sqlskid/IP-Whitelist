package cf.sqlskid.ipwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;


public class IPWhitelist extends JavaPlugin {

    public static IPWhitelist instance;
    public ArrayList<String> IPAdresy = new ArrayList<>();

    private File dataFile = new File("main.dat");

    @Override
    public void onEnable(){
        instance = this;

        if(!dataFile.exists())
        {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            read(dataFile.getName());
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[IP-Whitelist] Nastala chyba!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage("[IP-Whitelist] Nastala chyba! Zkusim vytvorit soubor.");
        }
        Bukkit.getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getCommand("ipwhitelist").setExecutor(new IPWhitelistCommand());

        Bukkit.getConsoleSender().sendMessage("[IP-Whitelist] Plugin byl zapnut!");
    }


    @Override
    public void onDisable(){
        try {
            save(dataFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String fileName) throws IOException {
        FileOutputStream fout= new FileOutputStream (fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(IPAdresy);
        fout.close();
    }

    public void read(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin= new FileInputStream (fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        IPAdresy = (ArrayList<String>)ois.readObject();
        fin.close();
    }



}
