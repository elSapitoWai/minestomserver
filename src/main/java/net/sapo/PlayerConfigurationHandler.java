package net.sapo;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public class PlayerConfigurationHandler {

    public static void onPlayerConfiguration(AsyncPlayerConfigurationEvent event) {
        final Player player = event.getPlayer();
        event.setSpawningInstance(Main.getInstanceContainer());
        player.setRespawnPoint(new Pos(0, 42, 0));

        player.setPermissionLevel(4);
    }
}
