package com.zplus.adsl;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  

import com.zplus.utils.net.IPUtility;

/**
 * ============================
 * 创建ADSL连接
 * Date：2017-6-17 18:15:40
 * Author zhangdashun
 * ============================
 * */
public class ADSL {  
	
	private static String connectName = Config.getConfig("adslName");
	private static String account = Config.getConfig("adslAccount");
	private static String password = Config.getConfig("adslPassword");
	
	private static View view = new View();
	
	/** 
	 * ============================
	 * 执行CMD命令,并返回String字符串 
	 * Date：2017-6-17 18:25:02
	 * @param cmd 执行的命令字符串
	 * @return 执行cmd命令返回的结果
	 * ============================
	 * */  
	public static String executeCmd(String cmd) throws Exception {  
		Process process = Runtime.getRuntime().exec("cmd /c " + cmd);  
		StringBuilder stringBulider = new StringBuilder();  
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), "GB2312"));  
		String line;  

		while ((line = bufferedReader.readLine()) != null) {  
			stringBulider.append(line + "\n");  
		}  

		return stringBulider.toString();  
	}  

	/** 
	 * ===========================
	 * 连接ADSL
	 * Date：2017-6-17 18:18:58
	 * @param connectName 连接名字，如宽带连接
	 * @return 操作结果
	 * ===========================
	 * */  
	public static String connAdsl(String connectName, String adslAccount, String adslPassword) throws Exception {  
		getView().getLogTextField().setText(getView().getLogTextField().getText() + "正在建立连接...\r\n");
		String cmd = "rasdial " + connectName + " " + adslAccount + " " + adslPassword;  
		String result = executeCmd(cmd);  

		// 判断是否连接成功  
		if (result.indexOf("已连接") > 0) {  
			getView().getLogTextField().setText(getView().getLogTextField().getText() + "已成功建立连接...\r\n");
			getView().getInnerIPTextField().setText(IPUtility.getLocalAddress());
			getView().getOuterIPTextField().setText(IPUtility.getV4IP());
			return "已成功建立连接";  

		} else {  
			System.err.println(result);  
			getView().getLogTextField().setText(getView().getLogTextField().getText() + "建立连接失败...\r\n");
			return result;  
		}  
	}  

	/** 
	 * ===========================
	 * 断开ADSL 
	 * Date：2017-6-17 18:23:17
	 * @param connectName 连接名，如“宽带连接”
	 * @return 是否断开成功
	 * ===========================
	 * */  
	public static String cutAdsl(String connectName) throws Exception {  
		String cmd = "rasdial " + connectName + " /disconnect";  
		String result = executeCmd(cmd);  

		if (result.indexOf("没有连接") != -1) {  
			System.err.println(connectName + "连接不存在!");  
		}
		
		getView().getLogTextField().setText(getView().getLogTextField().getText() + "连接已断开...");
		getView().getInnerIPTextField().setText(IPUtility.getLocalAddress());
		getView().getOuterIPTextField().setText(IPUtility.getV4IP());
		return "已断开";  
	}  
	
	/** 
	 * ===========================
	 * 测试网络是否连接
	 * Date：2017-6-17 18:24:44
	 * @return 是否已经联网
	 * ===========================
	 * */  
	public static boolean isConnect(){  
		boolean connect = false;  
		Runtime runtime = Runtime.getRuntime();  
		Process process;  

		try {  
			process = runtime.exec("ping " + "www.baidu.com");  
			InputStream is = process.getInputStream();   
			InputStreamReader isr = new InputStreamReader(is);   
			BufferedReader br = new BufferedReader(isr);   
			String line = null;   
			StringBuffer sb = new StringBuffer();   

			while ((line = br.readLine()) != null) {   
				sb.append(line);   
			}   

			System.out.println("返回值为:"+sb);    
			is.close();   
			isr.close();   
			br.close();   

			if (null != sb && !sb.toString().equals("")) {   
				if (sb.toString().indexOf("TTL") > 0) {   
					// 网络畅通    
					connect = true;  

				} else {   
					// 网络不畅通    
					connect = false;  
				}   
			}   

		} catch (IOException e) {  
			e.printStackTrace();  
		}   

		return connect;  
	}  

	//测试代码  
	public static void main(String[] args) throws InterruptedException, Exception { 
		getView().createFrame();
		getView().getConnectNameTextField().setText(getConnectName());
		getView().getAccountTextField().setText(getAccount());
		getView().getPasswordTextField().setText(getPassword());
		getView().getLogTextField().setText(getView().getLogTextField().getText() + "启动完成...\r\n");
		getView().getInnerIPTextField().setText(IPUtility.getLocalAddress());
		getView().getOuterIPTextField().setText(IPUtility.getV4IP());
		//启动socket服务
		Server.start();
	}
	
	public static String getConnectName() {
		return ADSL.connectName;
	}

	public static void setConnectName(String connectName) {
		ADSL.connectName = connectName;
	}

	public static String getAccount() {
		return ADSL.account;
	}

	public static void setAccount(String account) {
		ADSL.account = account;
	}

	public static String getPassword() {
		return ADSL.password;
	}

	public static void setPassword(String password) {
		ADSL.password = password;
	}

	public static View getView() {
		return view;
	}

	public static void setView(View view) {
		ADSL.view = view;
	}  
}  