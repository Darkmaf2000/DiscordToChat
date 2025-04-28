package com.tuonome.discordrelay;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import javax.security.auth.login.LoginException;

public class DiscordRelayPlugin extends JavaPlugin {

    private JDA jda;
    private long channelId;
    private String format;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        String token = getConfig().getString("token", "").trim();
        channelId   = getConfig().getLong("channel-id", 0L);
        format      = getConfig().getString("format", "[Discord] <%AUTHOR%>: %MESSAGE%");

        if (token.isEmpty() || channelId == 0L) {
            getLogger().severe("Token Discord o channel-id non configurati correttamente!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        try {
            jda = JDABuilder.createDefault(token)
                    .addEventListeners(new ListenerAdapter() {
                        @Override
                        public void onMessageReceived(MessageReceivedEvent event) {
                            // Ignora bot e canali diversi
                            if (event.getAuthor().isBot()) return;
                            if (!event.getChannel().getIdLong() == channelId) return;

                            String author  = event.getAuthor().getName();
                            String message = event.getMessage().getContentDisplay();

                            String output = format
                                    .replace("%AUTHOR%", author)
                                    .replace("%MESSAGE%", message);

                            // Esegui il broadcast sul thread principale di Bukkit
                            Bukkit.getScheduler().runTask(DiscordRelayPlugin.this, () ->
                                Bukkit.broadcastMessage(output)
                            );
                        }
                    })
                    .build();
        } catch (LoginException e) {
            getLogger().severe("Errore login Discord: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (jda != null) {
            jda.shutdown();
        }
    }
}
