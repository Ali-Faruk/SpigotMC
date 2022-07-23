package de.codeali.discordwebhookplugin;

import de.codeali.discordwebhookplugin.commands.dcsendCommand;
import de.codeali.discordwebhookplugin.commands.discordCommand;
import de.codeali.discordwebhookplugin.commands.discordwebhookpluginCommand;
import de.codeali.discordwebhookplugin.events.Listerners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class DiscordWebhookPlugin extends JavaPlugin {
    private static DiscordWebhookPlugin instance;
    private URL url;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("discord").setExecutor(new discordCommand());
        getCommand("dcsend").setExecutor(new dcsendCommand());
        getCommand("discordwebhookplugin").setExecutor(new discordwebhookpluginCommand());
        Listerners.prefix = getConfig().getString("PREFIX");
        Listerners.join = getConfig().getString("JOIN.MESSAGE");
        Listerners.leave = getConfig().getString("LEAVE.MESSAGE");
        Listerners.joinenabled = getConfig().getBoolean("JOIN.ENABLED");
        Listerners.leaveenabled = getConfig().getBoolean("LEAVE.ENABLED");
        Listerners.deathenabled = getConfig().getBoolean("DEATH.ENABLED");
        Listerners.death = getConfig().getString("LEAVE.MESSAGE");
        Bukkit.getPluginManager().registerEvents(new Listerners(), this);
        instance = this;

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§l§l----------------------------------");
        Bukkit.getConsoleSender().sendMessage(Listerners.prefix.replace("&", "§") +" §aPlugin wurde geladen");
        Bukkit.getConsoleSender().sendMessage(Listerners.prefix.replace("&", "§") +" §cPlugin entwickelt von §bCODEALI");
        Bukkit.getConsoleSender().sendMessage(Listerners.prefix.replace("&", "§") +" §4YouTube: §chttps://www.youtube.com/channel/UC48749SRiQHSDp04zh97QoA");
        Bukkit.getConsoleSender().sendMessage("§l§l----------------------------------");
        Bukkit.getConsoleSender().sendMessage("");

        if(!getConfig().getString("WebHookURL").isEmpty()) {
            try {
                url = new URL(getConfig().getString("WebHookURL"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }else {
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4No WebHook URL set!");
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage("§4---------------------------");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§l§l----------------------------------");
        Bukkit.getConsoleSender().sendMessage(Listerners.prefix.replace("&", "§") +" §aPlugin wurde entladen");
        Bukkit.getConsoleSender().sendMessage(Listerners.prefix.replace("&", "§") +" §cPlugin entwickelt von §bCODEALI");
        Bukkit.getConsoleSender().sendMessage(Listerners.prefix.replace("&", "§") +" §4YouTube: §chttps://www.youtube.com/channel/UC48749SRiQHSDp04zh97QoA");
        Bukkit.getConsoleSender().sendMessage("§l§l----------------------------------");
        Bukkit.getConsoleSender().sendMessage("");
    }

    public static DiscordWebhookPlugin getInstance(){
        return instance;
    }

    public void sendToDiscord(String content){
        if(!getConfig().getString("WebHookURL").isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("content", content);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.addRequestProperty("Content-Type", "application/json");
                connection.addRequestProperty("User-Agent", "Java-DiscordWebhook");
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                OutputStream stream = connection.getOutputStream();
                stream.write(jsonObject.toJSONString().getBytes());
                stream.flush();
                stream.close();

                connection.getInputStream().close();
                connection.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
