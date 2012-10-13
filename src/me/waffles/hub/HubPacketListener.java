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
    	String playername = event.getPlayer().getName().toLowerCase();
    	if(getPlugin().playerServerMap.containsKey(playername))
    	{
        	if(getPlugin().sendDebug)
        		System.out.println("[HUB] Passed On: " + playername + " ---> " + getPlugin().playerServerMap.get(playername));
    		event.setCancelled(true);
    		//TODO Pass onto server
    		//sendToServer(Player p, getPlugin().playerServerMap.get(playername));
    	}
    }
}
