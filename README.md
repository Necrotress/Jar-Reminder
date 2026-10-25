# Jar Reminder

RuneLite plugin that blocks catching enabled butterflies unless an empty butterfly jar is in your inventory.

Catching an enabled butterfly with no empty jar is cancelled. The blocked message appears above your head for 3 seconds. With **Net jar reminder** on, that same message stays in a movable box above the chat while a butterfly net or magic butterfly net is worn and no empty jar is in the inventory. **Flash the Reminder Box** alternates that box between **Color** and **Flash Color**.

## Run

```
.\gradlew.bat test
.\gradlew.bat runClient
.\gradlew.bat installSideloadedPlugin
```

`runClient` opens a dev RuneLite with Jar Reminder loaded. `installSideloadedPlugin` copies `jar-reminder.jar` to `%USERPROFILE%\.runelite\sideloaded-plugins`. Fully close RuneLite, then launch it again and enable the sideloaded jar.
