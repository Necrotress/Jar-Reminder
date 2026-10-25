package com.jarreminder;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.LineComponent;

public class JarReminderPanel extends OverlayPanel
{
	private static final int FLASH_PERIOD_CYCLES = 40;

	@Inject
	private Client client;

	@Inject
	private JarReminderPlugin plugin;

	@Inject
	private JarReminderConfig config;

	@Inject
	JarReminderPanel()
	{
		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!plugin.isNetReminderVisible() || !JarReminderPlugin.hasBlockedMessage(config))
		{
			return null;
		}

		final String text = config.blockedMessage();

		panelComponent.getChildren().clear();
		panelComponent.getChildren().add(LineComponent.builder()
			.left(text)
			.build());
		if (config.flashReminder())
		{
			panelComponent.setBackgroundColor(client.getGameCycle() % FLASH_PERIOD_CYCLES >= FLASH_PERIOD_CYCLES / 2
				? config.reminderFlashColor()
				: config.reminderColor());
		}
		else
		{
			panelComponent.setBackgroundColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
		}
		return super.render(graphics);
	}
}
