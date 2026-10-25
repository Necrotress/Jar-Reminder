package com.jarreminder;

import java.util.Arrays;
import net.runelite.api.gameval.NpcID;

enum ButterflyType
{
	RUBY_HARVEST(NpcID.BUTTERFLY_RUBY),
	SAPPHIRE_GLACIALIS(NpcID.BUTTERFLY_GLACIALIS),
	SNOWY_KNIGHT(NpcID.BUTTERFLY_SNOWY),
	BLACK_WARLOCK(NpcID.BUTTERFLY_WARLOCK),
	SUNLIGHT_MOTH(NpcID.MOTH_SUNLIGHT),
	MOONLIGHT_MOTH(NpcID.MOTH_MOONLIGHT, NpcID.MOTH_MOONLIGHT_LOW_WANDER);

	private final int[] npcIds;

	ButterflyType(int... npcIds)
	{
		this.npcIds = npcIds;
	}

	boolean matches(int npcId)
	{
		return Arrays.stream(npcIds).anyMatch(id -> id == npcId);
	}

	boolean isEnabled(JarReminderConfig config)
	{
		switch (this)
		{
			case RUBY_HARVEST:
				return config.rubyHarvest();
			case SAPPHIRE_GLACIALIS:
				return config.sapphireGlacialis();
			case SNOWY_KNIGHT:
				return config.snowyKnight();
			case BLACK_WARLOCK:
				return config.blackWarlock();
			case SUNLIGHT_MOTH:
				return config.sunlightMoth();
			case MOONLIGHT_MOTH:
				return config.moonlightMoth();
			default:
				return false;
		}
	}

	static ButterflyType fromId(int npcId)
	{
		for (ButterflyType type : values())
		{
			if (type.matches(npcId))
			{
				return type;
			}
		}
		return null;
	}
}
