/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solomon.finance;

import org.shareok.data.documentProcessor.FileUtil;
import org.shareok.data.htmlrequest.HttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tao Zhao
 */
public class Inplay {
    public static final String LINK = "http://finance.yahoo.com/news/inplay-briefing-com-055139997.html";
    private HttpRequestHandler httpHandler;
    
    @Autowired
    public void setHttpHandler(HttpRequestHandler httpHandler){
        this.httpHandler = httpHandler;
    }
    
    public String getInplayDoc(String url){
        return httpHandler.sendGet(url);
//        WebCrower crower = new WebCrower();
//        crower.
//        StringBuffer response = 
    }
    
    public void saveResponse(String response, String path){
        FileUtil.outputStringToFile(response, path);
    }
}
