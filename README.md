# HuTiers

Get a tier by:

```java
import hu.jgj52.huTiers.Gamemode;
import hu.jgj52.huTiers.HuTiersPlayer;
import org.bukkit.Bukkit;

HuTiersPlayer player = HuTiersPlayer.of(Bukkit.getPlayer("player"));
String tier = player.getTier(Gamemode.Sword);
Boolean retired = player.getRetired(Gamemode.Sword);
```
**getTier and getRetired will return null if it hasn't fetched the player from the api yet (first time on runtime/player)**