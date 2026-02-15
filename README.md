# HuTiers

It's like my mod [HuTiersTagger](https://modrinth.com/mod/hutierstagger) but for servers so they can put tiers in the player's suffix with their plugin or whatever

This is **not** a standalone plugin, you will need to build another plugin around it for it to do anything.

Include it in your project:
```xml
<repository>
    <id>jgj52-repo</id>
    <url>https://maven.jgj52.hu/repository/maven-releases/</url>
</repository>
```

```xml
<dependency>
    <groupId>hu.jgj52</groupId>
    <artifactId>hutiers</artifactId>
    <version>1.3</version>
    <scope>provided</scope>
</dependency>
```

Get a tier by:
```java
import hu.jgj52.huTiers.Gamemode;
import hu.jgj52.huTiers.HuTiersPlayer;
import org.bukkit.Bukkit;

HuTiersPlayer player = HuTiersPlayer.of(Bukkit.getPlayer("player"));
String tier = player.getTier(Gamemode.Sword);
Boolean retired = player.getRetired(Gamemode.Sword);
```
**getTier and getRetired will return null if it hasn't fetched the player from the api yet (first time on runtime for each player)**

Event if a player gets their tier changed:

```java
import hu.jgj52.huTiers.PlayerChangeEvent;

class yourplugin {
    public void onEnable() {
        PlayerChangeEvent.register(player -> {
            // player is an OfflinePlayer
            System.out.println(player.getUniqueId() + " has been updated!");
        });
    }
}
```

You can also get from the overall list:

```java
import hu.jgj52.huTiers.HuTiers;
import hu.jgj52.huTiers.HuTiersPlayer;

Set<HuTiersPlayer> first30player = HuTiers.getPlayers(0, 30);
```

Building:
```bash
git clone https://github.com/JGJ52/HuTiers.git
cd HuTiers
mvn package
```
The jar will be in the target directory