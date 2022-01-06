package ru.lymonmine.lsrvvkpost;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class fakepostcmd implements CommandExecutor {

    private main plugin;

    public fakepostcmd(main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("lsrvvkpost.fake")) {
            String s = plugin.getConfig().getString("lang.noperm");
            s = s.replace("&", "\u00a7");
            sender.sendMessage(s);
            return true;

        }
        if (args.length >= 0) ;
        for (String s : plugin.getConfig().getStringList("alert-newpost")) {
            s = s.replace("&", "\u00a7");
            Bukkit.broadcastMessage(s);
        }
        return true;
    }
}
