package com.gmail.jackdonofrio12;

import org.bukkit.plugin.java.JavaPlugin;

public class EntityManager extends JavaPlugin
{

  @Override
  public void onEnable()
  {
    this.saveDefaultConfig();
    this.getCommand("disable").setExecutor(new DisableCommand(this));
    this.getCommand("enable").setExecutor(new EnableCommand(this));
    getServer().getPluginManager()
      .registerEvents(new EntityManagerListener(this), this);
  }

  @Override
  public void onDisable()
  {
  }

}
