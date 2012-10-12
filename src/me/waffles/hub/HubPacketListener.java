package me.waffles.hub;

import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.PacketEvent;

public class HubPacketListener extends MonitorListener
{

	public HubPacketListener(HubMain plugin, ConnectionSide side) 
	{
		super(plugin, side);
	}
	
    @Override
    public void onPacketReceiving(PacketEvent event) 
    {
        // TODO
    	if(getPlugin().sendDebug)
    		System.out.println("[HUB] Packet received: " + event.getPacketID());
    }
}
