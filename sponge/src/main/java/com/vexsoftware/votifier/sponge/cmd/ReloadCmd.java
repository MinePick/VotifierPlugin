package com.vexsoftware.votifier.sponge.cmd;

import com.vexsoftware.votifier.sponge.VotifierPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ReloadCmd implements CommandExecutor {

    private final VotifierPlugin plugin;

    public ReloadCmd(VotifierPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, @NonNull CommandContext args) throws CommandException {
        src.sendMessage(Text.builder("Reloading VotifierPlugin...").color(TextColors.GRAY).build());
        if (plugin.reload())
            return CommandResult.success();
        else
            return CommandResult.empty();
    }
}
