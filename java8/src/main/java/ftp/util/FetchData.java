package ftp.util;


import ftp.FileFtp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FetchData implements Runnable {
	
	private static final Logger logger  = LoggerFactory.getLogger(FetchData.class);
	
	private static final String baseurl = ResourceUtil.getBaseurl();

	@Override
	public void run() {
		begin();
	}


	private void begin() {
		String fetchurl=HttpGet.SendGet(baseurl);
		logger.info("url:"+fetchurl);
		if((fetchurl!=null)&&(!fetchurl.startsWith("HTTP"))&&(fetchurl.length()>5)){
			
			if(fetchurl.startsWith("add")){
//				logger.info("add");
				String addUrl=fetchurl.substring(4);
				FileFtp.getInstance().upload(addUrl);
			}
			if(fetchurl.startsWith("del")){
				logger.info("del");
				String delurl=fetchurl.substring(4);
				FileFtp.getInstance().delete(delurl);
			}
		}
		
	}

}
