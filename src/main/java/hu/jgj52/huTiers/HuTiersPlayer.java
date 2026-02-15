package hu.jgj52.huTiers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class HuTiersPlayer {
    private static final Map<UUID, HuTiersPlayer> players = new HashMap<>();

    public static HuTiersPlayer of(OfflinePlayer player) {
        if (players.containsKey(player.getUniqueId()))
            return players.get(player.getUniqueId());
        HuTiersPlayer pl = new HuTiersPlayer(player);
        players.put(player.getUniqueId(), pl);
        return pl;
    }

    public static HuTiersPlayer of(Player player) {
        return of(Bukkit.getOfflinePlayer(player.getUniqueId()));
    }

    public static HuTiersPlayer of(UUID uuid) {
        return of(Bukkit.getOfflinePlayer(uuid));
    }

    private static final HttpClient client = HttpClient.newHttpClient();

    static void removePlayer(OfflinePlayer player) {
        players.remove(player.getUniqueId());
    }

    private JsonObject tiers;

    private JsonObject retired;

    private HuTiersPlayer(OfflinePlayer player) {
        new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://api.hutiers.hu/v3/player/" + player.getUniqueId())).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonArray arr = new Gson().fromJson(response.body(), JsonArray.class);
                if (!arr.isEmpty()) {
                    this.tiers = arr.get(0).getAsJsonObject();
                    this.retired = arr.get(1).getAsJsonObject();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Nullable
    public String getTier(Gamemode gamemode) {
        if (this.tiers != null)
            return this.tiers.get(gamemode.name()).getAsString();
        return null;
    }

    @Nullable
    public Boolean getRetired(Gamemode gamemode) {
        if (this.retired != null)
            return this.retired.get(gamemode.name()).getAsBoolean();
        return null;
    }
}
