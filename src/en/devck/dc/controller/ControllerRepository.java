package en.devck.dc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ControllerRepository {

	//Not used tool yet, but already built
	public void gimmeResponse() throws IOException {
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		
		URL url = new URL ("https://jsonplaceholder.typicode.com/albums");
		connection = (HttpURLConnection) url.openConnection();
		// Request setup
		connection.setRequestMethod ("GET");
		connection.setConnectTimeout (5000);
		connection.setReadTimeout (5000);
		int status = connection.getResponseCode(); 
		System.out.print(status);
		if (status > 299) {
		    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
		    
		   while ( (line = reader.readLine()) != null) {
		       responseContent.append(line);
		   		}
		   reader.close();
		 } else {
		    reader = new BufferedReader (new InputStreamReader(connection.getInputStream()));
		   while ( (line = reader.readLine()) != null) {
		       responseContent.append(line);
		     }
		   reader.close();
		   System.out.println(responseContent.toString());
		 }
	}
}
