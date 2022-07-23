package de.codeali.discordwebhookplugin.commands;

import de.codeali.discordwebhookplugin.DiscordWebhookPlugin;
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
            sender.sendMessage("&e/discord or /dc #This message send you the Discord invite link.".replace("&", "§"));
            sender.sendMessage("&e".replace("&", "§"));
            sender.sendMessage("&cPlugin developed by &b&lCODEALI".replace("&", "§"));
            sender.sendMessage("&4&lYouTube: &4https://www.youtube.com/channel/UC48749SRiQHSDp04zh97QoA".replace("&", "§"));
            sender.sendMessage("&e".replace("&", "§"));
            sender.sendMessage("&8&l------------------------".replace("&", "§"));
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("reload")){
                if(sender.hasPermission(DiscordWebhookPlugin.getInstance().getConfig().getString("PERMISSION.RELOAD"))) {
                    DiscordWebhookPlugin.getInstance().saveDefaultConfig();
                    DiscordWebhookPlugin.getInstance().reloadConfig();
                    sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("MESSAGES.RELOAD").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX")).replace("&", "§"));
                }else{
                    sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("PERMISSION_MESSAGE").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX").replace("&", "§")));
                }
            }else{
                if(args[0].equalsIgnoreCase("rl")){
                    if(sender.hasPermission(DiscordWebhookPlugin.getInstance().getConfig().getString("PERMISSION.RELOAD"))) {
                        DiscordWebhookPlugin.getInstance().reloadConfig();
                        sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("MESSAGES.RELOAD").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX")));
                    }else{
                        sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("PERMISSION_MESSAGE").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX").replace("&", "§")));
                    }
                }
            }
        }
        return false;
    }
}
