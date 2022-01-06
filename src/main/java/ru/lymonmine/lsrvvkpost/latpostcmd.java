package ru.lymonmine.lsrvvkpost;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class latpostcmd implements CommandExecutor {

    private main plugin;

    public latpostcmd(main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("lsrvvkpost.lat")) {
            String s = plugin.getConfig().getString("lang.noperm");
            s = s.replace("&", "\u00a7");
            sender.sendMessage(s);
            return true;

        }
        if (longpollevent.url == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.instance.getConfig().getString("lang.last-post.nolast")));
            return true;
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.instance.getConfig().getString("lang.last-post.last")));
            sender.sendMessage(longpollevent.url);
            return true;
        }
    }
}





