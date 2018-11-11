package com.ipacs.qywx.pls.test;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class testParse {
	
	
	public static void main(String[] args) {
		String msg ="<xml><ToUserName><![CDATA[wwad1f9f2028c52c5f]]></ToUserName><FromUserName><![CDATA[ShiDaQing]]></FromUserName><CreateTime>1541856112</CreateTime><MsgType><![CDATA[event]]></MsgType><AgentID>1000003</AgentID><Event><![CDATA[click]]></Event><EventKey><![CDATA[button3]]></EventKey></xml>" ;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			
			StringReader sr = new StringReader(msg);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("EventKey");
			String Content = nodelist1.item(0).getTextContent();
			System.out.println("Content：" + Content);
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
