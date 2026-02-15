package hu.jgj52.huTiers;

import org.bukkit.OfflinePlayer;

@FunctionalInterface
public interface PlayerChangeListener {
    void onChange(OfflinePlayer player, HuTiersPlayer huTiersPlayer);
}
