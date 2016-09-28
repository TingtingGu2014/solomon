/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solomon.aws.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

/**
 *
 * @author Tao Zhao
 */
public class AwsMailServ {
    private String from;
    private String to;
    private String body;
    private String topic;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }

    public String getTopic() {
        return topic;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public void sendMail(){
        Destination destination = new Destination().withToAddresses(new String[]{to});
        Content subject = new Content().withData(topic);
        Content textBody = new Content().withData(body);
        Body mailBody = new Body().withText(textBody);
        
        Message message = new Message().withSubject(subject).withBody(mailBody);
        SendEmailRequest request = new SendEmailRequest().withSource(from).withDestination(destination).withMessage(message);
        
        try {
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            /*
             * The ProfileCredentialsProvider will return your [New US East (Virginia) Profile]
             * credential profile by reading from the credentials file located at
             * (/Users/zhao0677/.aws/credentials).
             *
             * TransferManager manages a pool of threads, so we create a
             * single instance and share it throughout our application.
             */
            
            try {
                AWSCredentials credentials = AwsUtil.getAwsCredentials();
                AmazonSimpleEmailServiceClient client = AwsUtil.getAwsEmailServClient(credentials);
                Region REGION = Region.getRegion(AwsUtil.DEVELOPER_REGION);
                client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            System.out.println("Email sent!");
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Cannot load the credentials from the credential profiles file. " +
                        "Please make sure that your credentials file is at the correct " +
                        "location (/Users/zhao0677/.aws/credentials), and is in valid format.",
                        e);
            }
            

        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
}
