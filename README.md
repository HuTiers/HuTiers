# HuTiers

It's like my mod [HuTiersTagger](https://modrinth.com/mod/hutierstagger) but for servers so they can put tiers in the player's suffix with their plugin 

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

Building:
```bash
git clone https://github.com/JGJ52/HuTiers.git
cd HuTiers
mvn package
```
The jar will be in the target directory