package me.waffles.hub;

import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ConnectionSide;

public class HubMain extends JavaPlugin
{
	private ProtocolManager protocolManager;

	public void onEnable()
	{
		protocolManager = ProtocolLibrary.getProtocolManager();
		protocolManager.addPacketListener(new HubPacketListener(this, ConnectionSide.SERVER_SIDE, 0x3E));
	}
}
