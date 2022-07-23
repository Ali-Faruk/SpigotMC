package de.codeali.discordwebhookplugin.commands;

import de.codeali.discordwebhookplugin.DiscordWebhookPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class discordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(DiscordWebhookPlugin.getInstance().getConfig().getString("MESSAGES.LINK").replace("<prefix>", DiscordWebhookPlugin.getInstance().getConfig().getString("PREFIX")).replace("&", "§"));
        return false;
    }
}
