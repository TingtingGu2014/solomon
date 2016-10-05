/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solomon.aws.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Tao Zhao
 */
public class AwsUtil {
    
    public static final String DEVELOPER_PROFILE_NAME = "default";
    public static final Regions DEVELOPER_REGION = Regions.US_WEST_2;
    
    public static AWSCredentials getAwsCredentials(){
        try {
            AWSCredentials credentials = new ProfileCredentialsProvider(DEVELOPER_PROFILE_NAME).getCredentials();
            return credentials;
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location, and is in valid format.",
                    e);
        }        
    }
    
    public static AmazonSimpleEmailServiceClient getAwsEmailServClient(AWSCredentials credentials){
        return new AmazonSimpleEmailServiceClient(credentials);
    }
    
    public static AwsMailServ getAwsMailServ(){
        ApplicationContext context = new ClassPathXmlApplicationContext("awsServiceContext.xml");
        return (AwsMailServ)context.getBean("awsMailServ");
    }
}
