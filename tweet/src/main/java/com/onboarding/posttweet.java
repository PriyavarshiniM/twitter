package com.onboarding;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class posttweet {
    public void tweet() throws TwitterException{
        String consumerKey = null;
        String consumerSecret =null;
        String accessToken=null;
        String accessTokenSecret=null;

        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "auth.properties";
            input = timeline.class.getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            prop.load(input);

            consumerKey=prop.getProperty("consumerKey");
            consumerSecret=prop.getProperty("consumerSecret");
            accessToken=prop.getProperty("accessToken");
            accessTokenSecret=prop.getProperty("accessTokenSecret");



        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        try
        {
            System.out.println("your tweet...");
            Scanner sc= new Scanner(System.in);
            String s= sc.nextLine();
            System.out.println(twitter.getScreenName());
            Status status = twitter.updateStatus(s);
            System.out.println("Successfully updated the status...");
        }catch (TwitterException te) {
            te.printStackTrace();
            System.exit(-1);
        }
        System.err.println("Error occurred while updating the status!");
    }
}


