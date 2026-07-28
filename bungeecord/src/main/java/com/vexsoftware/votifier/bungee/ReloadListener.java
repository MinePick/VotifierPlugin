package com.vexsoftware.votifier.bungee;

import net.md_5.bungee.api.event.ProxyReloadEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ReloadListener implements Listener {
    private final VotifierPlugin votifierPlugin;

    public ReloadListener(VotifierPlugin votifierPlugin) {
        this.votifierPlugin = votifierPlugin;
    }

    @EventHandler
    public void onProxyReload(ProxyReloadEvent event) {
        this.votifierPlugin.reload();
    }
}
