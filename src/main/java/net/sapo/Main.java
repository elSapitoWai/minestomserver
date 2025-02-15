package net.sapo;

import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.extras.lan.OpenToLAN;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import net.sapo.Commands.GamemodeCommand;

public class Main {
    private static InstanceContainer instanceContainer;

    public static InstanceContainer getInstanceContainer() {
        return instanceContainer;
    }

    public static void main(String[] args) {
        // Initialize the server
        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();


        instanceContainer = instanceManager.createInstanceContainer();
        instanceContainer.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 40, Block.STONE);
        });

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, PlayerConfigurationHandler::onPlayerConfiguration);

        CommandManager commandManager = MinecraftServer.getCommandManager();
        commandManager.register(new GamemodeCommand());

        // Start the server
        OpenToLAN.open();
        minecraftServer.start("0.0.0.0", 25564);
    }
}