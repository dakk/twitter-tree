package dgsf.twittertree.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class OAuthTest 
{
	public static void main(String args[])
	{
		// The factory instance is re-useable and thread safe.
	    Twitter twitter = new TwitterFactory().getInstance();
	    twitter.setOAuthConsumer("kKdACxN1YFUnfaZRZtqsKg", "VRDNQgveNFzSrI2gCZh6AAwMtp6V7GjmF1xJwQ");
	    
	    RequestToken requestToken;
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			return;
		}
		System.out.println("requestToken:"+requestToken);
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      String pin;
		try {
			pin = br.readLine();
		} catch (IOException e) 
		{
			return;
		}
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	         }else
	         {
	           accessToken = twitter.getOAuthAccessToken();
	         }
	      } 
	      catch (TwitterException te) 
	      {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	      System.out.println("accessToken:"+accessToken);
	    }
	    
	    Status status;
		try {
			status = twitter.updateStatus("test");
		} catch (TwitterException e) {
			return;
		}
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
}
