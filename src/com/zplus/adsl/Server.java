package com.zplus.adsl;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * ======================<br/>
 * Http Server服务器<br/>
 * Author zhangdashun<br/>
 * Date：2017-6-17 21:30:28<br/>
 * ======================<br/>
 * */
public class Server {
	
	static public void start(){
		ServerSocket ss = null;
		int port = Integer.parseInt(Config.getConfig("listenPort"));
		SocketAddress address = null;
		Socket connect = null;
		byte[] buffer = new byte[512];
		int readLen = 0;

		try{
			address = new InetSocketAddress(port);
			ss = new ServerSocket();
			ss.bind(address);

			while(true){
				connect = ss.accept();
				String result = "";
				
				do{
					readLen = connect.getInputStream().read(buffer);
					String string = new String(buffer);
					
					if (-1 < string.indexOf("connectNetwork")) {
						ADSL.getView().getLogTextField().setText(ADSL.getView().getLogTextField().getText() + 
								"接收到连接ADSL指令：\r\n" + string + "\r\n");
						result = ADSL.connAdsl(ADSL.getConnectName(), ADSL.getAccount(), ADSL.getPassword());
						break;
					}
					
					if (-1 < string.indexOf("shutdownNetwork")) {
						ADSL.getView().getLogTextField().setText(ADSL.getView().getLogTextField().getText() + 
								"接收到断开ADSL指令：\r\n" + string + "\r\n");
						result = ADSL.cutAdsl(ADSL.getConnectName());
						break;
					}
					
				}while(readLen == 512);

				PrintWriter out = new PrintWriter(connect.getOutputStream(),  
                        true); 
				out.println("HTTP/1.1 200 OK");
				out.println("Content-Type:text/html;charset:UTF-8");
				out.println();
				out.println(result);
				out.close();
				connect.close();
			}
			
		}catch(Exception ex){
			System.err.println("socket 服务异常");
		}
	}
}

