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
