package de.codeali.discordwebhookplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class discordwebhookpluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage("&8&l------------------------".replace("&", "§"));
            sender.sendMessage("&e".replace("&", "§"));
            sender.sendMessage("&e/dcsend <TEXT> #Send the Message to the Discord Server.".replace("&", "§"));
            sender.sendMessage("&e".replace("&", "§"));
            sender.sendMessage("&cPlugin developed by &b&lCODEALI".replace("&", "§"));
            sender.sendMessage("&4&lYouTube: &4https://www.youtube.com/channel/UC48749SRiQHSDp04zh97QoA".replace("&", "§"));
            sender.sendMessage("&e".replace("&", "§"));
            sender.sendMessage("&8&l------------------------".replace("&", "§"));
        }
        return false;
    }
}
