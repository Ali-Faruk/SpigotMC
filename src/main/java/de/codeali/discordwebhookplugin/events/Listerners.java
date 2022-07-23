package de.codeali.discordwebhookplugin.events;

import de.codeali.discordwebhookplugin.DiscordWebhookPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listerners implements Listener {

    public static String prefix;
    public static String join;
    public static String leave;
    public static String death;
    public static boolean joinenabled;
    public static boolean leaveenabled;
    public static boolean deathenabled;

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(!DiscordWebhookPlugin.getInstance().getConfig().getString("WebHookURL").isEmpty()) {
            if (joinenabled) {
                String join = DiscordWebhookPlugin.getInstance().getConfig().getString("JOIN.MESSAGE").replace("<player>", event.getPlayer().getName());
                DiscordWebhookPlugin.getInstance().sendToDiscord(">>> " + join);
            }
        }else {
            if(event.getPlayer().isOp()){
                event.getPlayer().sendMessage("§4---------------------------");
                event.getPlayer().sendMessage("");
                event.getPlayer().sendMessage("§4§lNo WebHook URL set!");
                event.getPlayer().sendMessage("");
                event.getPlayer().sendMessage("§4---------------------------");
            }
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4No WebHook URL set!");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
        }
    }
    @EventHandler
    public void onDissconect(PlayerQuitEvent event){
        if(!DiscordWebhookPlugin.getInstance().getConfig().getString("WebHookURL").isEmpty()) {
            if (leaveenabled) {
                String leave = DiscordWebhookPlugin.getInstance().getConfig().getString("LEAVE.MESSAGE").replace("<player>", event.getPlayer().getName());
                DiscordWebhookPlugin.getInstance().sendToDiscord(">>> " + leave);
            }
        }else {
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4No WebHook URL set!");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        if(!DiscordWebhookPlugin.getInstance().getConfig().getString("WebHookURL").isEmpty()) {
            if (deathenabled) {
                String death = DiscordWebhookPlugin.getInstance().getConfig().getString("DEATH.MESSAGE").replace("<player>", "**" + event.getEntity().getPlayer().getName() + "**");
                DiscordWebhookPlugin.getInstance().sendToDiscord(death);
            }
        }else {
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4No WebHook URL set!");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
        }
    }
}
