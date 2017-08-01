package ftp;


import ftp.util.FetchData;
import ftp.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FileFtp {

	private static final Logger logger  = LoggerFactory.getLogger(FetchData.class);
	
	private  static  FileFtp fileFtp;
	private static  Integer lock = new Integer(0);
	private ExecutorService pool; 
	private static List<String> listServices;
	
	
	private FileFtp(){
		pool=Executors.newCachedThreadPool();
	}
	
	public  static  FileFtp  getInstance(){
		if(fileFtp==null){
			synchronized (lock) {
				if(fileFtp==null){
					fileFtp= new FileFtp();
				}
			}
		}
		return fileFtp;
	}
	
	static{
		String services = ResourceUtil.getServices();
		String[] service = services.split("&&");
//		logger.info("--service---"+service.length);
		listServices = new ArrayList<String>();
		for(int i=0;i<service.length;i++){
//			logger.info("111111111111");
			listServices.add(service[i]);
		}
	}
	
	
	/**
	 * 
	 * @param url本地地址  排除了add/ 没有前头的路径
	 */
	public void upload(String url){
//		logger.info("------upload------");
		String[] scs;
		for(String sc:listServices){
			scs = sc.split(",");
//			logger.info("scs"+scs.length+"--"+scs[0]+"--"+scs[1]+"---"+scs[2]);
			FtpUtils fu=new FtpUtils(scs[0],scs[1],scs[2],21,url,url,"",1);
			pool.execute(fu);
		}
	}
	/**
	 * 
	 * @param url 本地路径
	 */
	public void delete(String url){
		String[] scs;
		for(String sc:listServices){
			scs = sc.split(",");
			FtpUtils fu=new FtpUtils(scs[0],scs[1],scs[2],21,"","",url,2);
			pool.execute(fu);
		}
	}

}
