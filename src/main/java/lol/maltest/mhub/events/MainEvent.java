package lol.maltest.mhub.events;

import de.ancash.actionbar.ActionBarAPI;
import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import lol.maltest.mhub.Main;
import lol.maltest.mhub.util.ChatUtil;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.List;

public class MainEvent implements Listener {

    FileConfiguration config = Main.plugin.getConfig();


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        List<String> lines = config.getStringList("scoreboard.lines");

        if(config.getString("data.spawn.world").equals("notSet")) {
            player.sendMessage(ChatUtil.clr(config.getString("strings.errors.nospawnset")));
        } else {
            World world = Bukkit.getWorld(config.getString("data.spawn.world"));
            Double x = config.getDouble("data.spawn.x");
            Double y = config.getDouble("data.spawn.y");
            Double z = config.getDouble("data.spawn.z");
            Location spawn = new Location(world, x,y,z);
            player.teleport(spawn);
        }

        // scoreboard

        BPlayerBoard board = Netherboard.instance().createBoard(player, ChatUtil.clr(config.getString("scoreboard.title")));
        int i = 0;
        for(String line : lines) {
            board.set(ChatUtil.clr(line), i);
            i++;
        }

        if(config.getBoolean("actionbar.enabled")) {

            ActionBarAPI.sendActionBar(player,ChatUtil.clr("actionbar.text"));

        }

//        if(config.getBoolean("actionbar.enabled")) {
//            ActionBarAPI.sendActionBar(player,ChatUtil.clr(config.getString("actionbar.text")));
//        }
    }
}
