package me.waffles.hub;

import java.util.HashMap;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ConnectionSide;


public class HubMain extends JavaPlugin implements Listener 
{

	public HashMap<String, String> playerServerMap = new HashMap<String, String>();
	public boolean sendDebug;
	
	private ProtocolManager protocolManager;
	private Server server;

	@Override
	public void onEnable() 
	{
		server = this.getServer();
		protocolManager = ProtocolLibrary.getProtocolManager();
		protocolManager.addPacketListener(new HubPacketListener(this, ConnectionSide.CLIENT_SIDE));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		try
		{
			//Change everything to lower case
			for(String a : args)
				a = a.toLowerCase();
			//If the command is hub
			if(cmd.getName().equals("hub"))
			{
				switch(args[0])
				{
				case "setserver":
					//Try to match the name with online players
					if(server.getPlayer(args[1]) != null)
						args[1] = server.getPlayer(args[1]).getName().toLowerCase();
					//Add to HashMap
					playerServerMap.put(args[1], args[2]);
					System.out.println("[HUB] " + args[1] + " joined " + args[2]);
					break;
				case "debug":
					sendDebug = true;
					break;
				}

			}
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
}