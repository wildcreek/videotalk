package com.wildcreek.videotalk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 第三方校验
 * @author lenovo
 *
 */
public class SoupClient {
//  private String url = "http://88.88.10.93:33500/ACS/services/VASService";
//	private String url = "http://192.168.1.133:8080/HvccTestServer/servlet/TestSoupServlet";
	//中兴认证接口
//	private String url="http://223.99.189.32:8090/EPGAuthIn";    http://10.0.3.13:33500/ACS/services/VASService
	//山东认证接口
	private String url="http://88.88.10.93:33500/ACS/services/VASService";  
	private static Logger log = LoggerFactory.getLogger(SoupClient.class);
	
	public static void main(String[] args) {
		SoupClient sc = new SoupClient();
		sc.postByUrlConnection("hxXAV7fyrjyhad3pIdH6X2X443188965");
	}
	
	public void postByUrlConnection() {
		String info="<bean:UserToken>hxXAV7fyrjyhad3pIdH6X2X443188965</bean:UserToken>";
		try {
			URL url = new URL("http://223.99.189.32:8090/EPGAuthIn");
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Accept-Encoding", "gzip,deflate");
			con.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			con.setRequestProperty("SOAPAction", "");
			con.setRequestProperty("User-Agent", "Jakarta Commons-HttpClient/3.1");
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(new String(info.getBytes("UTF-8")));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			System.out.println("【【【【【【【");
			for (line = br.readLine(); line != null; line = br.readLine()) {
//				log.debug("postByUrlConnection", line);
				System.out.println(line);
			}
			System.out.println("】】】】】】】");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String postByUrlConnection(String info) {
		//info="<bean:UserToken>hxXAV7fyrjyhad3pIdH6X2X443188965</bean:UserToken>";
		info="<bean:UserToken>"+info+"</bean:UserToken>";
		String resultValue = null;
		try {
			URL url = new URL("http://223.99.189.32:8090/EPGAuthIn");
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Accept-Encoding", "gzip,deflate");
			con.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			con.setRequestProperty("SOAPAction", "");
			con.setRequestProperty("User-Agent", "Jakarta Commons-HttpClient/3.1");
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(new String(info.getBytes("UTF-8")));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			System.out.println("【【【【【【【");
			for (line = br.readLine(); line != null; line = br.readLine()) {
				System.out.println("-----"+line);
				if(line.indexOf("<Result>")!=-1){
					resultValue = line.substring(line.indexOf(">")+1,line.indexOf("</Result>"));
				}
			}
			System.out.println("中兴认证接口返回值resultValue:"+resultValue);
			System.out.println("】】】】】】】");
			log.debug("中兴认证接口返回值resultValue:"+resultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultValue;
	}
	
	private String getZetInfo(){
		StringBuilder sb = new StringBuilder();
		sb.append("<bean:UserToken>hxXAV7fyrjyhad3pIdH6X2X443188965</bean:UserToken>");
		return sb.toString();
		
	}
	private String getSDInfo(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.vas.huawei.com\" xmlns:bean=\"http://bean.vas.huawei.com\">");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<ser:serviceAuthReq>");
		sb.append("<bean:SPID></bean:SPID>");
		sb.append("<bean:UserID></bean:UserID>");
		sb.append("<bean:UserToken>trhq5w9i90ph-Stbt31y8cy</bean:UserToken>");
		sb.append("<bean:ExpiredTime></bean:ExpiredTime>");
		sb.append("</ser:serviceAuthReq>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		return sb.toString();
	}

	private String getXmlInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.vas.huawei.com\" xmlns:bean=\"http://bean.vas.huawei.com\">");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<ser:serviceAuthReq>");
		sb.append("<bean:IP></bean:IP>");
		sb.append("<bean:MAC></bean:MAC>");
		sb.append("<bean:SPID></bean:SPID>");
		sb.append("<bean:contentID>10000100000000010000000000000429</bean:contentID>");
		sb.append("<bean:productID>zwvasanci5</bean:productID>");
		sb.append("<bean:serviceID></bean:serviceID>");
		sb.append("<bean:timeStamp>1111</bean:timeStamp>");
		sb.append("<bean:transactionID></bean:transactionID>");
		sb.append("<bean:userID></bean:userID>");
		sb.append("<bean:userToken>0F4E192737683330347CC878FD327C34</bean:userToken>");
		sb.append("</ser:serviceAuthReq>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		
		

		return sb.toString();
	}

	private String inputStreamToString(InputStream is) {
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		try {
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		} catch (IOException e) {
		}
		return total.toString();
	}
}
