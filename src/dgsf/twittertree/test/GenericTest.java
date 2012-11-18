package dgsf.twittertree.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dgsf.twittertree.Node;
import dgsf.twittertree.TwitterTreeFactory;

public class GenericTest 
{
	public static void main(String args[])
	{	
		String inputPath;
		
		Node rootNode = TwitterTreeFactory.getTree("/");
		System.out.println(rootNode.getContent());
		
		while(true)
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try 
			{
				inputPath = in.readLine();
			} 
			catch (IOException e) 
			{
				return;
			}
			
			Node testNode = TwitterTreeFactory.getTree(inputPath);
			
			if(testNode != null)
			{
				System.out.println(testNode.getContent());
			}
			else
				System.out.println("Null");
		}
	}

}
