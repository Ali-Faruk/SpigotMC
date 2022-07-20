package de.codeali.discordwebhookplugin.commands;

import de.codeali.discordwebhookplugin.DiscordWebhookPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class dcsendCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!DiscordWebhookPlugin.getInstance().getConfig().getString("WebHookURL").isEmpty()) {
            if (sender.hasPermission(DiscordWebhookPlugin.getInstance().getConfig().getString("PERMISSION"))) {
                if (args.length >= 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        sb.append(args[i]);
                        if (i < args.length) {
                            sb.append(" ");
                        }
                    }
                    sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("MESSAGES.SUCCESS").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX").replace("&", "§")));
                    DiscordWebhookPlugin.getInstance().sendToDiscord("**" + sender.getName() + "**: " + sb.toString());
                    return true;
                } else {
                    sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("MESSAGES.ERROR").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX").replace("&", "§")));
                }
                return true;
            } else {
                sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("PERMISSION MESSAGE").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX").replace("&", "§")));
            }
        }else {
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4No WebHook URL set!");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
        }
        return true;
    }
}
