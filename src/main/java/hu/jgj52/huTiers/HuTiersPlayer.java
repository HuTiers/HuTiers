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
import org.bukkit.entity.Player;

public class HuTiersPlayer {
    private static Map<Player, HuTiersPlayer> players = new HashMap<>();

    public static HuTiersPlayer of(Player player) {
        if (players.get(player) != null)
            return players.get(player);
        HuTiersPlayer pl = new HuTiersPlayer(player);
        players.put(player, pl);
        return pl;
    }

    private static final HttpClient client = HttpClient.newHttpClient();

    private JsonObject tiers;

    private JsonObject retired;

    private HuTiersPlayer(Player player) {
        new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://api.hutiers.hu/v3/player/" + String.valueOf(player.getUniqueId()))).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonArray arr = (JsonArray)new Gson().fromJson(response.body(), JsonArray.class);
                if (!arr.isEmpty()) {
                    this.tiers = arr.get(0).getAsJsonObject();
                    this.retired = arr.get(1).getAsJsonObject();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getTier(Gamemode gamemode) {
        if (this.tiers != null)
            return this.tiers.get(gamemode.name()).getAsString();
        return null;
    }

    public Boolean getRetired(Gamemode gamemode) {
        if (this.retired != null)
            return this.retired.get(gamemode.name()).getAsBoolean();
        return null;
    }
}
