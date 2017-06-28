package com.zplus.adsl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * =============================<br/>
 * 获取配置文件信息<br/>
 * Date：2017-6-17 18:11:23<br/>
 * Author zhangdashun<br/>
 * =============================<br/>
 * */
@SuppressWarnings("unused")
public class Config {

	//static final ResourceBundle config = ResourceBundle.getBundle(System.getProperty("user.dir") + "/config.properties");
	static final File file = new File(System.getProperty("user.dir") + "/config.properties");

	public static String getConfig (String key) {
		//return getKey(config, key);
		return getValueOuterProperties(key);
	}

	/**
	 * =============================<br/>
	 * 获取配置文件中相应的返回信息<br/>
	 * @param bundle 要访问的配置文件名
	 * @param key 配置文件中的key
	 * @return 在配置文件中获取到的值<br/>
	 * =============================<br/>
	 * */
	public static String getKey(ResourceBundle bundle, String key){
		String value = "";

		try {
			value = bundle.getString(key);

		}catch(java.util.MissingResourceException e){
			value = "";
			System.err.println("没找到 " + key + " 值,返回空值.");
		}

		return value;
	}

	/**
	 * ==============================<br/>
	 * 从外部文件读取配置，并根据key返回value<br/>
	 * Date：2017-6-18 13:58:03<br/>
	 * @param key key名字
	 * @return key对应的value<br/>
	 * ==============================<br/>
	 * */
	public static String getValueOuterProperties (String key) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				if (-1 < tempString.indexOf(key)) {
					return tempString.split("=")[1].trim();
				}
			}

			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
}
