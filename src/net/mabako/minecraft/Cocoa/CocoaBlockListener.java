package net.mabako.minecraft.Cocoa;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class CocoaBlockListener extends BlockListener
{
	/** Plugin instance */
	private CocoaPlugin instance;

	/**
	 * Constructor with the plugin instance
	 * 
	 * @param instance
	 *            plugin instance
	 */
	public CocoaBlockListener( CocoaPlugin instance )
	{
		this.instance = instance;
	}
	
	@Override
	public void onBlockPlace( BlockPlaceEvent event )
	{
		Player player = event.getPlayer( );
		if( instance.hasCocoa( player ) )
		{
			Block block = event.getBlock( );
			Material material = instance.findItem( block.getType( ) );
			player.setItemInHand( new ItemStack( material, material.getMaxStackSize( ), block.getData( ), block.getData( ) ) );
		}
	}
}
