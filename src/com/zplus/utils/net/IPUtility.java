package com.zplus.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * =============================<br/>
 * 获取本机ip地址：内网ip、外网ip<br/>
 * Date：2017-6-18 08:50:21<br/>
 * Author：zhangdashun<br/>
 * =============================<br/>
 * */
public class IPUtility {

	/**
	 * ===========================<br/>
	 * 获取本机内网ip<br/>
	 * Date：2017-6-18 08:56:08<br/>
	 * @return 获取到的本机ip，如果没有获取到则返回空字符串<br/>
	 * ===========================<br/>
	 * */
	public static String getLocalAddress(){
		String ip = "";
		
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			
		} catch (UnknownHostException e) {
			System.err.println("catch ip failure.");
		}
		
		return ip;
	}
	
	/**
	 * ===================================<br/>
	 * 获取本机的外网ip<br/>
	 * Date：2017-6-18 08:59:36<br/>
	 * @return 本机的外网ip<br/>
	 * ===================================<br/>
	 * */
	public static String getV4IP(){
		String ip = "";
		String chinaz = "http://ip.chinaz.com";
		
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
		    in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			
		    while((read=in.readLine())!=null){
				inputLine.append(read+"\r\n");
			}
			
		} catch (MalformedURLException e) {
			System.err.println("获取外网ip失败");
			
		} catch (IOException e) {
			System.err.println("获取外网ip失败");
			
		}finally{
			if(in!=null){
				try {
					in.close();
					
				} catch (IOException e) {
					System.err.println("获取外网ip失败");
				}
			}
		}
		
		
		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		
		if(m.find()){
			String ipstr = m.group(1);
			ip = ipstr;
		}
		
		return ip;
	}
}
