package hu.jgj52.huTiers;

import org.bukkit.plugin.java.JavaPlugin;

public final class HuTiers extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        WebSocketManager webSocketManager = new WebSocketManager();
        webSocketManager.conncect("wss://api.hutiers.hu/");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
