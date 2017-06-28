package com.zplus.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ==============================<br/>
 * 模拟客户端http请求<br/>
 * Date：2017-6-18 09:38:45<br/>
 * Author zhangdashun<br/>
 * ==============================<br/>
 * */
public class Client {

	/*public static void main (String [] args) {
		
		
		String connect = "http://192.168.1.4:65510/connectNetwork";
		String disconnect = "http://192.168.1.4:65510/shutdownNetwork";
		
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		
		try {
			url = new URL(disconnect);
			urlConnection = (HttpURLConnection) url.openConnection();
		    in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			
		    while((read=in.readLine())!=null){
				inputLine.append(read+"\r\n");
			}
			
		    System.out.println(inputLine.toString());
		    
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("获取外网ip失败");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("获取外网ip失败");
			
		}finally{
			if(in!=null){
				try {
					in.close();
					
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("获取外网ip失败");
				}
			}
		}
	}*/
}
