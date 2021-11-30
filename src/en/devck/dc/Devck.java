package en.devck.dc;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import en.devck.dc.events.Ping;
import en.devck.dc.events.PrivateSupport;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Activity.ActivityType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Devck {
	public static String prefix = "*";
	
	// Main method
	public static void main(String[] args) throws LoginException, IOException {
		String token="OTE0ODIzMDgyNDI0Nzk5Mjkz.";
		token+="YaSpYw.BWi5inS5o-Fl6STSsIm0orCl6To";
		JDABuilder jda = JDABuilder.createDefault(token);
		jda.setStatus(OnlineStatus.IDLE);
		Activity act = Activity.of(ActivityType.WATCHING, "Cowboy Bebop");
		jda.setActivity(act);
		jda.addEventListeners(new Ping(), new PrivateSupport());
		jda.setChunkingFilter(ChunkingFilter.ALL);
		jda.setMemberCachePolicy(MemberCachePolicy.ALL);
		jda.enableIntents(GatewayIntent.GUILD_MEMBERS);
		jda.build();
		
	}
	        
	
}
