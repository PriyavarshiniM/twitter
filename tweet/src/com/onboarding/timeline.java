package com.onboarding;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class timeline {
    public void gettimeline(){
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
                System.out.println("File not found");
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

        try {
            List<Status> statuses;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Twitter Screen Name: ");
            String user = sc.nextLine();

            statuses = twitter.getUserTimeline(user);
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }

    }
}
