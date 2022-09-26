package space.moontalk.mc.cpspeed.command;

import java.util.Set;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import space.moontalk.mc.commands.route.RouteCall;
import space.moontalk.mc.commands.route.RouteHandler;

import space.moontalk.mc.cpspeed.teleport.TeleportManager;
import space.moontalk.mc.cpspeed.teleport.TeleportManagerHolder;

// tpa list (got | in)

@Getter
@AllArgsConstructor
public class TpaListGotSelfHandler implements RouteHandler,
                                              TeleportManagerHolder {
    private final @NotNull TeleportManager teleportManager;

    @Override
    public void onRoute(@NotNull RouteCall call) throws Exception {
        final Player player          = call.getCommandSender();
        val          requests        = teleportManager.getGotRequests(player);
        val          messageProvider = teleportManager.getMessageProvider();
        
        if (requests.isEmpty()) {
            val message = messageProvider.makeYouHaveNoGotRequestsMessage(player);
            player.sendMessage(message);
            return;
        }

        var message = messageProvider.makeYourGotRequestsHeaderMessage(player, requests);
        player.sendMessage(message);

        for (val request : requests) {
            message = messageProvider.makeGotRequestsItemMessage(request, player);
            player.sendMessage(message);
        }
    }

    @Override
    public @NotNull Set<Class<?>> getClasses() {
        return Set.of(Player.class);
    }

    @Override
    public @NotNull String getPermission() {
        return "cpspeed.tpa.list.got.self";
    }
}
