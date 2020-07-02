import org.bukkit.plugin.java.JavaPlugin;

public class ChatItem extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("[ChatItem] is enabled.");
	}
	@Override
	public void onDisable() {
		getLogger().info("[ChatItem] is disabled.");
	}
}
