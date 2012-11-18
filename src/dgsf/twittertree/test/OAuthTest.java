/*
 * TwitterTree
 * Copyright (C) 2012,  Davide Gessa, Gianmarco Cherchi, 
 * Sara Casti, Alessandro Carcangiu, Fabrizio corda
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
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
