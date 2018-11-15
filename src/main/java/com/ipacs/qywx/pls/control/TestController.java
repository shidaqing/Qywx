package com.ipacs.qywx.pls.control;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ipacs.qywx.pls.model.WxHttpRequst;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@RestController
public class TestController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());	
	String sToken = "hGAi4miB6IBPNEb9wo7WnvR" ;
	String sEncodingAESKey = "eCc18xVLMw40OuMJRoLYNgGUCw4KI4PWiKm5Uh1gD3d" ;
	String sCorpID = "wwad1f9f2028c52c5f" ;	
	WXBizMsgCrypt wxcpt = null ;
	String repMsg ;
	
	
	@PostMapping("/hello")	
    public String helloworld2(WxHttpRequst wxHttpRequst,@RequestBody String ReqBody) {	
		
		long startTime = System.currentTimeMillis();  
		
		String sEchoStr = null; //需要返回的明文		
		//System.out.println("wxHttpRequst:"+ wxHttpRequst.msg_signature+" " +wxHttpRequst.echostr) ;    
		logger.info("=================进入POST方法====================="+"/n");
		logger.info("msg_signature: "+ wxHttpRequst.msg_signature+"/n");
		logger.info("msg_signature: "+ wxHttpRequst.echostr+"/n");
		logger.info("timestamp: "+ wxHttpRequst.timestamp+"/n");
		logger.info("nonce: "+ wxHttpRequst.nonce+"/n");	
		logger.info("ReqBody : "+ ReqBody+"/n");			
		try {
			 wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);			
			 String sMsg = wxcpt.DecryptMsg(wxHttpRequst.msg_signature, wxHttpRequst.timestamp, wxHttpRequst.nonce, ReqBody);
			 logger.info(":解析出的明文是： " + sMsg+"/n");
			 
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db;
				
				db = dbf.newDocumentBuilder();
				
				StringReader sr = new StringReader(sMsg);
				InputSource is = new InputSource(sr);
				Document document = db.parse(is);

				Element root = document.getDocumentElement();
				NodeList MsgType = root.getElementsByTagName("MsgType");
				String MsgTypeContent = MsgType.item(0).getTextContent();
				if (MsgTypeContent.equals("text")) {
					
					NodeList Content = root.getElementsByTagName("Content");
					 repMsg = "后台接收到信息："+ Content.item(0).getTextContent();
					
				}else if(MsgTypeContent.equals("event")) {
					
					NodeList Content = root.getElementsByTagName("EventKey");
					repMsg = "后台接收到点击事件：" + Content.item(0).getTextContent();
					
				}							 			 
			 String sRespData = "<xml><ToUserName><![CDATA[ShiDaQing]]></ToUserName><FromUserName><![CDATA[wwad1f9f2028c52c5f]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+repMsg+"]]></Content><MsgId>1234567890123456</MsgId><AgentID>1000003</AgentID></xml>";
			 logger.info("返回的明文是： " + sRespData+"/n");			 
			  sEchoStr = wxcpt.EncryptMsg(sRespData, wxHttpRequst.timestamp, wxHttpRequst.nonce);
			 logger.info("返回的密文是： " + sEchoStr+"/n");			 			 			 
		} catch (AesException | ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block			 
			logger.error(e.getMessage());	
			return e.getMessage() ;
		}		
		long endTime = System.currentTimeMillis();  		
		float seconds = (endTime - startTime) ;  				
		logger.info("==================请求处理时长 : "+ seconds+"ms====================="+"/n");			
		return sEchoStr ;		 				
	}	
	
	
	
	
    @GetMapping("/hello")
    public String helloworld(WxHttpRequst wxHttpRequst) {
		String sEchoStr = null; //需要返回的明文			
		logger.info("=================进入GET方法=====================");
		//System.out.println("wxHttpRequst:"+ wxHttpRequst.msg_signature+" " +wxHttpRequst.echostr) ;       
		logger.info("msg_signature: "+ wxHttpRequst.msg_signature);
		logger.info("msg_signature: "+ wxHttpRequst.echostr);
		logger.info("timestamp: "+ wxHttpRequst.timestamp);
		logger.info("nonce: "+ wxHttpRequst.nonce);				
		try {
			 wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);			
			 String sVerifyMsgSig = wxHttpRequst.msg_signature;
			 String sVerifyTimeStamp = wxHttpRequst.timestamp;
			 String sVerifyNonce = wxHttpRequst.nonce;
			 String sVerifyEchoStr = wxHttpRequst.echostr ;
			 sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
						sVerifyNonce, sVerifyEchoStr);
			 logger.info("verifyurl echostr: " + sEchoStr);
		} catch (AesException e) {
			// TODO Auto-generated catch block			 
			logger.error(e.getMessage());	
			return e.getMessage() ;
		}
		return sEchoStr ;		 		
    }
    
    
}
