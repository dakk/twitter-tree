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
package dgsf.twitterTreeSwing;
import dgsf.twittertree.*;

import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TwitterTreeAutenticate extends JFrame implements ActionListener
{
	private JButton buttonAutenticate;
	private ImageComponent logoImage;
	
	public TwitterTreeAutenticate()
	{
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new GridLayout(2,1));
		
		this.buttonAutenticate = new JButton("Autentica");
		this.buttonAutenticate.setPreferredSize(new Dimension(120,40));
		
		JPanel subContainer1 = new JPanel();
		subContainer1.setLayout(new FlowLayout());
		mainContainer.add(subContainer1);
		
		try
		{
			this.logoImage = new ImageComponent("resources/twitter_logo.png");
			subContainer1.add(this.logoImage);
			this.logoImage.setPreferredSize(new Dimension(150,150));
		}
		catch(IOException ex)
		{
		}
		
		JPanel subContainer2 = new JPanel();
		subContainer2.setLayout(new FlowLayout());
		mainContainer.add(subContainer2);
		
		subContainer2.add(this.buttonAutenticate);
		this.buttonAutenticate.addActionListener(this);
		
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("TwitterTree");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/* azione collegata al bottone OK */
	public void actionPerformed(ActionEvent event ) 
	{
		Node rootNode = TwitterTreeFactory.getTree("/");
		String url = rootNode.getContent().replaceAll("\\<.*?>","");
		
		System.out.println("Apertura browser all'url: "+url);
		
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		java.net.URI uri;
		try {
			uri = new java.net.URI(url);
			desktop.browse( uri );
		} 
		catch (URISyntaxException ex) 
		{
			System.out.println("Impossibile completare la richiesta");
		} 
		catch (IOException ex) 
		{
			System.out.println("Impossibile completare la richiesta");
		}
		
		this.dispose();
		TwitterTreeInsertCode insertCodeWindow = new TwitterTreeInsertCode();
		
	}

}
