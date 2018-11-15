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
public class KsappControl {
	final Logger logger = LoggerFactory.getLogger(this.getClass());			
	@PostMapping("/Ksapp1")	
    public String helloworld2(WxHttpRequst wxHttpRequst,@RequestBody String ReqBody) {			
		long startTime = System.currentTimeMillis();  		
		String sEchoStr = null; //需要返回的明文				
		logger.info("接收到远程调用");		
		return "调用成功" ;		 				
	}	 
	@GetMapping("/Ksapp2")	
    public String helloworld2(WxHttpRequst wxHttpRequst) {			
		long startTime = System.currentTimeMillis();  		
		String sEchoStr = null; //需要返回的明文				
		logger.info("接收到远程调用");		
		return "调用成功" ;		 				
	}
}
