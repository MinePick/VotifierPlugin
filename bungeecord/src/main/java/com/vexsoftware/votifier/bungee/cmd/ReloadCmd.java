package com.vexsoftware.votifier.bungee.cmd;

import com.vexsoftware.votifier.bungee.VotifierPlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class NVReloadCmd extends Command {

    private final VotifierPlugin plugin;

    private static final BaseComponent reloadingVotifierPlugin = new TextComponent("Reloading VotifierPlugin...");
    private static final BaseComponent votifierpluginHasBeenReloaded = new TextComponent("VotifierPlugin has been reloaded!");
    private static final BaseComponent problem = new TextComponent("Looks like there was a problem reloading VotifierPlugin, check the console!");
    private static final BaseComponent permission = new TextComponent("You do not have permission to do this!");

    static {
        reloadingVotifierPlugin.setColor(ChatColor.GRAY);
        votifierpluginHasBeenReloaded.setColor(ChatColor.DARK_GREEN);
        problem.setColor(ChatColor.DARK_RED);
        permission.setColor(ChatColor.DARK_RED);
    }

    public NVReloadCmd(VotifierPlugin plugin) {
        super("pnvreload", "votifierplugin.reload");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("votifierplugin.reload")) {
            sender.sendMessage(reloadingVotifierPlugin);
            if (plugin.reload()) {
                sender.sendMessage(votifierpluginHasBeenReloaded);
            } else {
                sender.sendMessage(problem);
            }
        } else {
            sender.sendMessage(permission);
        }
    }
}
