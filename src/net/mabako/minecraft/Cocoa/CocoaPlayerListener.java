package net.mabako.minecraft.Cocoa;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

/**
 * Player Listener for Cocoa that enables its world-editing properties
 * 
 * @author mabako (mabako@gmail.com)
 */
public class CocoaPlayerListener extends PlayerListener
{
	private static final int VIEW_DISTANCE = 600;
	/** Plugin instance */
	private CocoaPlugin instance;

	/**
	 * Constructor with the plugin instance
	 * 
	 * @param instance
	 *            plugin instance
	 */
	public CocoaPlayerListener( CocoaPlugin instance )
	{
		this.instance = instance;
	}

	/**
	 * Allows us to process world clicks
	 * 
	 * Left Click - replace block with Air
	 * Right Click - give you the block's material
	 */
	@Override
	public void onPlayerInteract( PlayerInteractEvent event )
	{
		Block block = event.getClickedBlock( );
		Player player = event.getPlayer( );
		if( instance.usesCocoa( player ) )
		{
			Action action = event.getAction( );

			if( action == Action.LEFT_CLICK_AIR )
			{
				block = player.getTargetBlock( null, VIEW_DISTANCE );
				action = Action.LEFT_CLICK_BLOCK;
			}
			else if( action == Action.RIGHT_CLICK_AIR )
			{
				block = player.getTargetBlock( null, VIEW_DISTANCE );
				action = Action.RIGHT_CLICK_BLOCK;
			}

			// Can't modify air or bedrock
			if( block == null || block.getType( ) == Material.AIR
					|| block.getType( ) == Material.BEDROCK )
				return;

			if( action == Action.LEFT_CLICK_BLOCK )
			{
				block.setType( Material.AIR );
				event.setCancelled( true );
			}
			else if( action == Action.RIGHT_CLICK_BLOCK )
			{
				event.setCancelled( true );
				instance.giveItem( player, block );
			}
		}
	}

	/**
	 * Stops the player from dropping the item
	 */
	@Override
	public void onPlayerDropItem( PlayerDropItemEvent event )
	{
		Player player = event.getPlayer( );
		if( instance.hasCocoa( player ) )
		{
			// Remove all of the player's items with this type
			player.getInventory( ).remove( event.getItemDrop( ).getItemStack( ).getType( ) );

			// Remove the dropped item
			// event.getItemDrop( ).remove( );
		}
	}
}
