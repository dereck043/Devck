package en.devck.dc.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandsRepository {
	
	private List<String> commandsList = new ArrayList<String>();
	
	public CommandsRepository(){
		commandsList.add("info");//0
		commandsList.add("greetings");//1
		commandsList.add("help");//2
		commandsList.add("hi / hey / hello");//3
		commandsList.add("yell");//4
		commandsList.add("start");//5
		commandsList.add("sum");//6
		commandsList.add("commands");//7
		commandsList.add("support");//8
		commandsList.add("ping");//9
	}

	public List<String> getCommandsList() {
		return commandsList;
	}
	
	public String toString() {
		String cmds = "";
		for (String str : commandsList) {
			cmds+="- `"+str+"`\n";
		}
		return cmds;
	}
	

}
