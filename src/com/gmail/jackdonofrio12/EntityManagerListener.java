package com.gmail.jackdonofrio12;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityManagerListener implements Listener
{
  private JavaPlugin plugin;

  public EntityManagerListener(JavaPlugin plugin)
  {
    this.plugin = plugin;
  }

  @EventHandler
  public void onEntitySpawn(CreatureSpawnEvent event)
  {
    EntityType entityType = event.getEntity().getType();
    String name = entityType.toString();
    if (!plugin.getConfig().getBoolean(name))
    {
      event.getEntity().remove();
    }
  }

}
