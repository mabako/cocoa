package net.mabako.minecraft.Cocoa;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handles the /cocoa-Command
 * @author mabako
 */
public class CocoaCommand implements CommandExecutor
{
	/**
	 * Command Handler
	 */
	@Override
	public boolean onCommand( CommandSender sender, Command command, String label, String[ ] split )
	{
		// We need a player
		if( !( sender instanceof Player ) )
			return false;

		// Check if the user has the Cocoa item already
		Player player = (Player) sender;
		if( Util.hasCocoa( player ) )
		{
			// Message on how to use it
			player.sendMessage( ChatColor.GREEN + "Cocoa: " +
					ChatColor.YELLOW + "Left Click: " + ChatColor.WHITE + "Destroy Blocks. " +
					ChatColor.YELLOW + "Right Click: " + ChatColor.WHITE + "Copy Material to inventory." );
		}
		else
		{
			// Give the player Cocoa
			ItemStack item = new ItemStack( Material.INK_SACK, 1, (short) 3, (byte) 3 );
			player.getInventory( ).setItem( 0, item );
			
			// Message on how to use it
			player.sendMessage( ChatColor.GREEN + "Gave you cocoa: " +
					ChatColor.YELLOW + "Left Click: " + ChatColor.WHITE + "Destroy Blocks. " +
					ChatColor.YELLOW + "Right Click: " + ChatColor.WHITE + "Copy Material to inventory." );
		}
		return true;
	}
}
