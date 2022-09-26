package space.moontalk.mc.cpspeed.command;

import java.util.Set;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;
import space.moontalk.mc.cpspeed.teleport.TeleportManagerHolder;

// tpa to? %p

@Getter
@AllArgsConstructor
public class TpaToHandler implements RouteHandler, 
                                     TeleportManagerHolder {
    private final @NotNull TeleportManager teleportManager;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final Player to   = call.getPlaceholdedAt(0); 
        final Player from = call.getCommandSender();
        teleportManager.sendRequest(from, to);
    }

    @Override
    public @NotNull Set<Class<?>> getClasses() {
        return Set.of(Player.class);
    }

    @Override
    public @NotNull String getPermission() {
        return "cpspeed.tpa.to";
    }
}
