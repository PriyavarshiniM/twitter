package com.onboarding;

import twitter4j.TwitterException;

import java.util.Scanner;


public class Main {

    public static void main(String []args) {
        posttweet p = new posttweet();
        timeline t = new timeline();

        System.out.println("1) post tweet 2)get home timeline");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        if(i==1) {
            try {
                p.tweet();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        else if(i==2)
            t.gettimeline();

        else
            System.out.println("enter either 1 or 2");
    }

}
