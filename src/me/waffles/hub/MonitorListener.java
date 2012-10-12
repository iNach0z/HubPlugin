package me.waffles.hub;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.ListeningWhitelist;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.reflect.FieldAccessException;

/**
 * Represents a listener that is notified of every sent and received packet.
 * 
 * @author Kristian
 */
public abstract class MonitorListener implements PacketListener {

    private HubMain plugin;
    private ListeningWhitelist sending = ListeningWhitelist.EMPTY_WHITELIST;
    private ListeningWhitelist receiving = ListeningWhitelist.EMPTY_WHITELIST;

    public MonitorListener(HubMain plugin, ConnectionSide side) {
        this(plugin, side, plugin.getLogger());
    }
    
    public MonitorListener(HubMain plugin, ConnectionSide side, Logger logger) {
        super();
        this.plugin = plugin;

        // Recover in case something goes wrong
        try {
            if (side.isForServer())
                this.sending = new ListeningWhitelist(ListenerPriority.MONITOR, Packets.Server.getSupported());
            if (side.isForClient())
                this.receiving = new ListeningWhitelist(ListenerPriority.MONITOR, Packets.Client.getSupported());
        } catch (FieldAccessException e) {
            if (side.isForServer())
                this.sending = new ListeningWhitelist(ListenerPriority.MONITOR, Packets.Server.getRegistry().values());
            if (side.isForClient())
                this.receiving = new ListeningWhitelist(ListenerPriority.MONITOR, Packets.Client.getRegistry().values());
            logger.log(Level.WARNING, "Defaulting to 1.3 packets.", e);
        }
    }
    
    @Override
    public void onPacketReceiving(PacketEvent event) {
        // Empty for now
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        // Empty for now
    }
    
    @Override
    public ListeningWhitelist getSendingWhitelist() {
        return sending;
    }
    
    @Override
    public ListeningWhitelist getReceivingWhitelist() {
        return receiving;
    }
    
    @Override
    public HubMain getPlugin() {
        return plugin;
    }
}