package net.mabako.minecraft.Cocoa;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Cocoa plugin base - registers events & sets the command handler
 * 
 * @author mabako (mabako@gmail.com)
 * @version 201107142356
 */
public class CocoaPlugin extends JavaPlugin
{
	/** Our blocklistener for events */
	private BlockListener blockListener;

	/** Our playerlistener for events */
	private PlayerListener playerListener;

	/**
	 * Custom enabling event, registers event and prints a message
	 */
	@Override
	public void onEnable( )
	{
		// Create the block listener
		blockListener = new CocoaBlockListener( );

		// Create the player listener
		playerListener = new CocoaPlayerListener( );

		// Register all relevant events
		PluginManager pm = getServer( ).getPluginManager( );

		pm.registerEvent( Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this );
		//pm.registerEvent( Event.Type.PLAYER_DROP_ITEM, playerListener, Priority.Normal, this );
		pm.registerEvent( Event.Type.PLAYER_PICKUP_ITEM, playerListener, Priority.Normal, this );
		pm.registerEvent( Event.Type.BLOCK_PLACE, blockListener, Priority.Normal, this );

		// Set the command handlers
		getCommand( "cocoa" ).setExecutor( new CocoaCommand( ) );

		// Output a message
		PluginDescriptionFile pdfFile = this.getDescription( );
		System.out.println( pdfFile.getName( ) + " version " + pdfFile.getVersion( ) );
	}

	/**
	 * Unused onDisable-method
	 */
	@Override
	public void onDisable( )
	{
	}
}
