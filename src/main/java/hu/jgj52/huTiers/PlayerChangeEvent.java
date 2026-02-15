package hu.jgj52.huTiers;

import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerChangeEvent {
    private static final List<PlayerChangeListener> listeners =
            new CopyOnWriteArrayList<>();

    public static void register(PlayerChangeListener listener) {
        listeners.add(listener);
    }

    static void fire(OfflinePlayer player, HuTiersPlayer huTiersPlayer) {
        for (PlayerChangeListener listener : listeners) {
            listener.onChange(player, huTiersPlayer);
        }
    }
}
