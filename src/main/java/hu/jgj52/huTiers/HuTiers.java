package hu.jgj52.huTiers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public final class HuTiers extends JavaPlugin {

    private static final HttpClient client = HttpClient.newHttpClient();

    @Override
    public void onEnable() {
        // Plugin startup logic
        WebSocketManager webSocketManager = new WebSocketManager();
        webSocketManager.conncect("wss://api.hutiers.hu/");
    }

    public static Set<HuTiersPlayer> getPlayers(Integer from, Integer to) {
        Set<HuTiersPlayer> players = new HashSet<>();
        new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://api.hutiers.hu/v2/overall/ " + from + "/" + to)).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonArray arr = new Gson().fromJson(response.body(), JsonArray.class);
                for (JsonElement element : arr.asList()) {
                    JsonObject player = element.getAsJsonObject();
                    HuTiersPlayer huTiersPlayer = HuTiersPlayer.of(UUID.fromString(player.get("uuid").getAsString()));
                    players.add(huTiersPlayer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return players;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
