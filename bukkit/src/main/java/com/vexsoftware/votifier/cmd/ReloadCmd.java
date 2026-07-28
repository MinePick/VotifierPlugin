package com.vexsoftware.votifier.cmd;

import com.vexsoftware.votifier.VotifierPluginBukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCmd implements CommandExecutor {

    private final VotifierPluginBukkit plugin;

    public ReloadCmd(VotifierPluginBukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender.hasPermission("votifierplugin.reload")) {
            sender.sendMessage(ChatColor.GRAY + "Reloading VotifierPlugin...");
            if (plugin.reload()) {
                sender.sendMessage(ChatColor.DARK_GREEN + "VotifierPlugin has been reloaded!");
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Looks like there was a problem reloading VotifierPlugin, check the console!");
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do this!");
        }
        return true;
    }
}
