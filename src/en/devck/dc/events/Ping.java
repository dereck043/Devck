package en.devck.dc.events;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import en.devck.dc.Devck;
import en.devck.dc.commands.CommandsRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter{
	
	GuildMessageReceivedEvent event;
	private CommandsRepository cmr = new CommandsRepository();
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if (event.getAuthor().isBot()) return;
		
		this.event = event;
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].startsWith(Devck.prefix)) {
			
			args[0] = args[0].substring(1);
			
			EmbedBuilder emb = new EmbedBuilder();
			switch (args[0].toLowerCase()) {
			//
			case "info":
				typing();
				emb.setColor(0xffae3d);
				emb.setTitle("\\⛩ Welcome to Devck! \\⛩");
				emb.setDescription("Bot Prefix: " + Devck.prefix + "\nHope you enjoy it\nIf you need more help just type "+Devck.prefix+"help / support");
				Member dev = event.getGuild().getMemberById("620375879071170570");
				emb.setFooter("Bot Created By "+dev.getNickname(),"https://cdn.discordapp.com/avatars/620375879071170570/d2441a0dd75d861c50f2603f8f424545.png");
				event.getChannel().sendMessageEmbeds(emb.build()).queue();
				emb.clear();
				break;
			//
			case "greetings":
				typing();
				if (event.getMessage().getMentionedMembers().toArray().length==1) {
					Member member = event.getGuild().getMemberById(event.getMessage().getMentionedMembers().get(0).getId());
					send(event.getAuthor().getName()+" said hello to "+member.getAsMention());
				}else {
					reply("You need to specify only 1 member.");
				}
				
				break;
			//
			case "help":
				//defined in PrivateSupport event
				send(event.getAuthor().getName()+" has been supported by DM :smirk_cat: ");
				break;
			//
			case "hi":
				typing();
				AllGreetsResponse();
				break;
			//
			case "hey":
				typing();
				AllGreetsResponse();
				break;
			//
			case "hello":
				typing();
				AllGreetsResponse();
				break;
			//
			case "yell":
				typing();
				emb.setColor(0xf24970);
				emb.setTitle(event.getAuthor().getName()+" says");
				String body ="";
				args[0]="";
				for (String bd : args) {
					body+=bd+" ";
				}
				emb.setDescription(body.toUpperCase());
				event.getChannel().sendMessageEmbeds(emb.build()).queue();
				emb.clear();
				break;
			//
			case "start":
				typing();
				reply("Not built yet");
				break;
			//not listed command
			case "si":
				typing();
				reply("Don't you dare "+event.getAuthor().getName()+" >:(");
				break;
			//
			case "sum":
				typing();
				Integer sum=0;
				args[0]="0";
				for (String num : args) {
					if(Pattern.matches("-?[0-9]+", num)) {
						sum+= Integer.parseInt(num);
					}else {
						reply("One of the params in not a number :( ");
						sum=null;
						break;
					}
				}
				if(sum!=null)
					reply("The sum is: "+sum);
				break;
			//
			case "commands":
				typing();
				emb.setColor(0xffae3d);
				emb.setTitle("OUR CURRENT COMMANDS:");
				emb.setDescription(cmr.toString());
				event.getChannel().sendMessageEmbeds(emb.build()).queue();
				emb.clear();
				break;
				//
			case "support":
				//defined in PrivateSupport event
				send(event.getAuthor().getName()+" has been supported by DM :smirk_cat: ");
				break;
			case "ping":
				typing();
				if(args.length==2) {
					args[1]=(args[1].equals("v2"))?"https://www5.shocklogic.com/v2/":args[1];
					args[1]=(args[1].equals("asp"))?"https://www5.shocklogic.com/scripts/jmevent/Registration.asp":args[1];
					args[1]=(args[1].equals("php"))?"https://www5.shocklogic.com/scripts/JMEvent/ParticipantDisplay_2.asp?Client_Id=%27SHOCKLOGIC%27&Project_Id=%270100%27&System_Id=2&Person_Id=5340971":args[1];
					try {
						getStatus(args[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					send("`This command needs 1 url, at least`");
				}
				break;
			default:
				typing();
				send("I did not understand your command... but I'm learning!");
				break;
			}
			
		}
		
	}
	public String getStatus(String url) throws IOException {
		 
		String result = "";
		int code = 200;
		try {
			if (!url.contains("www")) {
				url = "www."+url;
			}
			if (!url.contains("https://")) {
				url = "https://"+url;
			}
			
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();
 
			code = connection.getResponseCode();
			if (code == 200) {
				result = " :green_circle: \n" + "`Code: " + code;
				;
			} else {
				result = " :yellow_circle: \n" + "`Code: " + code;
			}
		} catch (Exception e) {
			result = " :red_circle: \n" + "`Wrong domain - Exception: " + e.getMessage();
 
		}
		send("`"+url+ "`\nStatus:" + result+"`");
		return result;
	}
	
	private void typing() {
		event.getChannel().sendTyping().queue();
	}
	
	private void AllGreetsResponse() {
		List<String> responses = new ArrayList<String>();
		
		responses.add("Hey! Go ahead and Type `[help]` to start using me!");
		responses.add("Hi there! If you want to start using me just go ahead and Type `[help]` ");
		responses.add("Hello! Do you want to start using me? just go ahead and Type `[help]` ");
		
		int min = 0;
		int max = responses.size()-1;
		int random_res = (int)Math.floor(Math.random()*(max-min+1)+min);
		String response = responses.get(random_res).replace("[help]",Devck.prefix+"help");
		
		reply(response);
		
	}
	
	private void send(String msg) {
		event.getChannel().sendMessage(msg).queue();
	} 
	private void reply(String msg) {
		event.getMessage().reply(msg).queue();
	} 

}
