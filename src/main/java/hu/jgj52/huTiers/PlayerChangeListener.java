package hu.jgj52.huTiers;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface PlayerChangeListener {
    void onChange(Player player, HuTiersPlayer huTiersPlayer);
}
