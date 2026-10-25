package com.jarreminder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class JarReminderOverlay extends Overlay
{
	@Inject
	private Client client;

	@Inject
	private JarReminderPlugin plugin;

	@Inject
	private JarReminderConfig config;

	@Inject
	JarReminderOverlay()
	{
		setLayer(OverlayLayer.ABOVE_SCENE);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!plugin.isOverheadVisible() || !JarReminderPlugin.hasBlockedMessage(config))
		{
			return null;
		}

		final String text = config.blockedMessage();

		final Player localPlayer = client.getLocalPlayer();
		if (localPlayer == null)
		{
			return null;
		}

		graphics.setFont(FontManager.getRunescapeBoldFont());
		final Point point = localPlayer.getCanvasTextLocation(graphics, text, localPlayer.getLogicalHeight() + 40);
		if (point == null)
		{
			return null;
		}

		final int x = point.getX();
		final int y = point.getY();
		graphics.setColor(Color.BLACK);
		graphics.drawString(text, x + 1, y);
		graphics.drawString(text, x - 1, y);
		graphics.drawString(text, x, y + 1);
		graphics.drawString(text, x, y - 1);
		graphics.setColor(Color.WHITE);
		graphics.drawString(text, x, y);
		return null;
	}
}
