package com.jarreminder;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemContainer;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Jar Reminder",
	description = "Block catching butterflies unless you have an empty butterfly jar",
	tags = {"hunter", "butterfly", "jar"}
)
public class JarReminderPlugin extends Plugin
{
	static final long OVERHEAD_DURATION_MS = 3000;

	static boolean hasBlockedMessage(JarReminderConfig config)
	{
		final String message = config.blockedMessage();
		return message != null && !message.trim().isEmpty();
	}
	private static final int[] BUTTERFLY_NET_IDS = {
		ItemID.HUNTING_BUTTERFLY_NET,
		ItemID.II_MAGIC_BUTTERFLY_NET
	};

	@Inject
	private Client client;

	@Inject
	private JarReminderConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private JarReminderOverlay overlay;

	@Inject
	private JarReminderPanel reminderOverlay;

	private long overheadUntilMs;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
		overlayManager.add(reminderOverlay);
		overheadUntilMs = 0;
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		overlayManager.remove(reminderOverlay);
		overheadUntilMs = 0;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() != GameState.LOGGED_IN)
		{
			overheadUntilMs = 0;
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		final MenuEntry entry = event.getMenuEntry();
		if (entry == null || !"Catch".equals(entry.getOption()))
		{
			return;
		}

		final NPC npc = entry.getNpc();
		if (npc == null)
		{
			return;
		}

		final ButterflyType type = ButterflyType.fromId(npc.getId());
		if (type == null || !type.isEnabled(config))
		{
			return;
		}

		if (hasButterflyJar())
		{
			return;
		}

		event.consume();
		if (hasBlockedMessage(config))
		{
			overheadUntilMs = System.currentTimeMillis() + OVERHEAD_DURATION_MS;
		}
	}

	boolean isOverheadVisible()
	{
		return System.currentTimeMillis() < overheadUntilMs;
	}

	boolean isNetReminderVisible()
	{
		return config.netJarReminder() && isWearingButterflyNet() && !hasButterflyJar();
	}

	private boolean hasButterflyJar()
	{
		final ItemContainer inventory = client.getItemContainer(InventoryID.INV);
		return inventory != null && inventory.contains(ItemID.BUTTERFLY_JAR);
	}

	private boolean isWearingButterflyNet()
	{
		final ItemContainer worn = client.getItemContainer(InventoryID.WORN);
		if (worn == null)
		{
			return false;
		}

		for (int netId : BUTTERFLY_NET_IDS)
		{
			if (worn.contains(netId))
			{
				return true;
			}
		}
		return false;
	}

	@Provides
	JarReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(JarReminderConfig.class);
	}
}
