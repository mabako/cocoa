package net.mabako.minecraft.Cocoa;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CocoaCommand implements CommandExecutor
{
	/** Plugin instance */
	private CocoaPlugin instance;

	/**
	 * Constructor with the plugin instance
	 * 
	 * @param instance
	 *            plugin instance
	 */
	public CocoaCommand( CocoaPlugin instance )
	{
		this.instance = instance;
	}
	
	@Override
	public boolean onCommand( CommandSender sender, Command command, String label, String[ ] split )
	{
		if( !( sender instanceof Player ) )
		{
			return false;
		}

		Player player = (Player) sender;

		
		
		if( instance.hasCocoa( player ) )
		{
			player.sendMessage( ChatColor.GREEN + "Cocoa: " +
					ChatColor.YELLOW + "Left Click: " + ChatColor.WHITE + "Destroy Blocks. " +
					ChatColor.YELLOW + "Right Click: " + ChatColor.WHITE + "Copy Material to inventory." );
		}
		else
		{
			ItemStack item = new ItemStack( Material.INK_SACK, 1, (short) 3, (byte) 3 );
			player.getInventory( ).setItem( 0, item );
			player.sendMessage( ChatColor.GREEN + "Gave you cocoa: " +
					ChatColor.YELLOW + "Left Click: " + ChatColor.WHITE + "Destroy Blocks. " +
					ChatColor.YELLOW + "Right Click: " + ChatColor.WHITE + "Copy Material to inventory." );
		}
		return true;
	}
}
