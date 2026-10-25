package com.jarreminder;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("jarreminder")
public interface JarReminderConfig extends Config
{
	@ConfigSection(
		name = "Butterflies",
		description = "Butterfly types that require an empty jar",
		position = 0,
		closedByDefault = false
	)
	String butterflySection = "butterflySection";

	@ConfigSection(
		name = "Reminders",
		description = "Head text and the movable reminder box",
		position = 1,
		closedByDefault = false
	)
	String reminderSection = "reminderSection";

	@ConfigItem(
		keyName = "rubyHarvest",
		name = "Ruby harvest",
		description = "Require an empty butterfly jar to catch ruby harvests",
		position = 0,
		section = butterflySection
	)
	default boolean rubyHarvest()
	{
		return true;
	}

	@ConfigItem(
		keyName = "sapphireGlacialis",
		name = "Sapphire glacialis",
		description = "Require an empty butterfly jar to catch sapphire glacialis",
		position = 1,
		section = butterflySection
	)
	default boolean sapphireGlacialis()
	{
		return true;
	}

	@ConfigItem(
		keyName = "snowyKnight",
		name = "Snowy knight",
		description = "Require an empty butterfly jar to catch snowy knights",
		position = 2,
		section = butterflySection
	)
	default boolean snowyKnight()
	{
		return true;
	}

	@ConfigItem(
		keyName = "blackWarlock",
		name = "Black warlock",
		description = "Require an empty butterfly jar to catch black warlocks",
		position = 3,
		section = butterflySection
	)
	default boolean blackWarlock()
	{
		return true;
	}

	@ConfigItem(
		keyName = "sunlightMoth",
		name = "Sunlight moth",
		description = "Require an empty butterfly jar to catch sunlight moths",
		position = 4,
		section = butterflySection
	)
	default boolean sunlightMoth()
	{
		return true;
	}

	@ConfigItem(
		keyName = "moonlightMoth",
		name = "Moonlight moth",
		description = "Require an empty butterfly jar to catch moonlight moths",
		position = 5,
		section = butterflySection
	)
	default boolean moonlightMoth()
	{
		return true;
	}

	@ConfigItem(
		keyName = "blockedMessage",
		name = "Blocked message",
		description = "Text shown above your head when a catch is blocked, and in the movable reminder box",
		position = 0,
		section = reminderSection
	)
	default String blockedMessage()
	{
		return "You need a butterfly jar.";
	}

	@ConfigItem(
		keyName = "netJarReminder",
		name = "Net jar reminder",
		description = "While a butterfly net or magic butterfly net is worn, show the blocked message above the chat until an empty butterfly jar is in the inventory",
		position = 1,
		section = reminderSection
	)
	default boolean netJarReminder()
	{
		return true;
	}

	@ConfigItem(
		keyName = "flashReminder",
		name = "Flash the Reminder Box",
		description = "Makes the reminder box flash between the defined colors",
		position = 2,
		section = reminderSection
	)
	default boolean flashReminder()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
		keyName = "reminderColor",
		name = "Color",
		description = "Reminder box color. One of the two colors used while flashing",
		position = 3,
		section = reminderSection
	)
	default Color reminderColor()
	{
		return new Color(70, 70, 70, 150);
	}

	@Alpha
	@ConfigItem(
		keyName = "reminderFlashColor",
		name = "Flash Color",
		description = "The other reminder box color used while flashing",
		position = 4,
		section = reminderSection
	)
	default Color reminderFlashColor()
	{
		return new Color(255, 0, 0, 150);
	}
}
