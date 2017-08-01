package ftp.util;

import java.util.ResourceBundle;

public class ResourceUtil {
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

	public ResourceUtil() {
		super();
	}
	
	/**
	 * 获取httpsqs的URL连接
	 * @return
	 */
	public static final String getBaseurl(){
		return bundle.getString("baseurl");
	}
	/**
	 * ftp上传服务器的信息：ip、用户名、密码
	 * 每个服务器用&&分割，一个是没有哦&&
	 * @return
	 */
	public static final String getServices(){
		return bundle.getString("services");
	}
	
	public static final String getHeadReplace(){
		return bundle.getString("headReaplace");
	}
	
	public static final String getHeadAdd(){
		return bundle.getString("HeadAdd");
	}

}
