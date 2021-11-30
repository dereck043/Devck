package en.devck.dc.events;

import en.devck.dc.Devck;
import en.devck.dc.commands.CommandsRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PrivateSupport extends ListenerAdapter{
	
	CommandsRepository cmr = new CommandsRepository();
	
		public void onMessageReceived(MessageReceivedEvent event) {
		   if (event.getMessage().getContentRaw().equalsIgnoreCase(Devck.prefix+"support") || event.getMessage().getContentRaw().equalsIgnoreCase(Devck.prefix+"help")) {
		      Member member = event.getMember();
		      if (member != null) {
		    	 EmbedBuilder emb = new EmbedBuilder();
					
		         member.getUser().openPrivateChannel().queue(privateChannel -> {
		        	 privateChannel.sendTyping().queue();
		        	 emb.setColor(0xffae3d);
					 emb.setTitle(" Hi! I am Devck! ");
					 emb.setDescription("a Tool-Bot in development, "
			        	 		+ "created to help you, "
							    +"our prefix is: `"+Devck.prefix+"`, "
			        	 		+ "and our current commands are the following: \n"
			        	 		+cmr.toString());
					 Member dev = event.getGuild().getMemberById("620375879071170570");
					 emb.setFooter("Bot Created By "+dev.getNickname(),"https://cdn.discordapp.com/avatars/620375879071170570/d2441a0dd75d861c50f2603f8f424545.png");
					
		        	 privateChannel.sendMessageEmbeds(emb.build()).queue();
		         });
		         emb.clear();
		      }
		   }
		}
}
		         