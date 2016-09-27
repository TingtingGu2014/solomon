/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solomon.finance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Tao Zhao
 */
public class Main {
    public static void main(String[] args){
    ApplicationContext context = new ClassPathXmlApplicationContext("financeContext.xml");
        Inplay bean = (Inplay)context.getBean("inplay");
        String response = bean.getInplayDoc("http://finance.yahoo.com/news/inplay-briefing-com-055139997.html?bypass=true#");
        bean.saveResponse(response, "/Users/zhao0677/Projects/solomon/finance/output/09-26-2016.txt");
    }
}
