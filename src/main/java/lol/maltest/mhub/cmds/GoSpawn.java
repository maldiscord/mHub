package lol.maltest.mhub.cmds;

import lol.maltest.mhub.Main;
import lol.maltest.mhub.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GoSpawn implements CommandExecutor {

    FileConfiguration config = Main.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            if(config.getString("data.spawn.world").equals("notSet")) {
                player.sendMessage(ChatUtil.clr(config.getString("strings.errors.nospawnset")));
            } else {
                World world = Bukkit.getWorld(config.getString("data.spawn.world"));
                Double x = config.getDouble("data.spawn.x");
                Double y = config.getDouble("data.spawn.y");
                Double z = config.getDouble("data.spawn.z");
                Location spawn = new Location(world, x,y,z);
                player.teleport(spawn);
                player.sendMessage(ChatUtil.clr(config.getString("strings.teleportedspawn")));
            }
        }
        return false;
    }
}
