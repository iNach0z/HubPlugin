package me.waffles.hub;

import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class HubPacketListener extends PacketAdapter
{

	public HubPacketListener(Plugin plugin, ConnectionSide connectionSide, int i) 
	{
		super(plugin, connectionSide, i);
	}
	
	 @Override
     public void onPacketSending(PacketEvent event) 
	 {
         // Item packets
         switch (event.getPacketID()) 
         {
         case 0x3E: // Sound effect
             event.setCancelled(true);
             break;
         }
     }


}
