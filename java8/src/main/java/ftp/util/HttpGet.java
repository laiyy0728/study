package ftp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGet {

	private static final Logger log = LoggerFactory.getLogger(FetchData.class);

     public  static String SendGet(String url){
		
		try {
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer stt = new StringBuffer();
			while((line=in.readLine())!=null){
				stt.append(line);
			}
			in.close();
			connection.disconnect();
			return stt.toString();
		} catch (MalformedURLException e) {
			log.info("refer the get!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("refruse the get!");
			e.printStackTrace();
		}
		return null;
		
	}

}
