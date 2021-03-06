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

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dgsf.twittertree.Node;
import dgsf.twittertree.TwitterTreeFactory;

public class TwitterTreeInsertPath extends JFrame implements ActionListener
{
	private JButton buttonOk;
	private ImageComponent logoImage;
	private JTextArea inputBox;
	private JLabel label;
	
	public TwitterTreeInsertPath()
	{	
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new GridLayout(3,1));
		
		this.buttonOk = new JButton("Invia");
		this.buttonOk.setPreferredSize(new Dimension(120,40));
		
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
		
		this.inputBox = new JTextArea(1,20);
		this.label = new JLabel("Path: ");
		JPanel subContainer2 = new JPanel();
		subContainer2.setLayout(new FlowLayout());
		
		subContainer2.add(this.label);
		subContainer2.add(this.inputBox);
		mainContainer.add(subContainer2);
		
		JPanel subContainer3 = new JPanel();
		subContainer3.setLayout(new FlowLayout());
		mainContainer.add(subContainer3);
		
		subContainer3.add(this.buttonOk);
		this.buttonOk.addActionListener(this);
		
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("TwitterTree");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/* azione collegata al bottone */
	public void actionPerformed(ActionEvent event ) 
	{
		Node pathNode = TwitterTreeFactory.getTree("/."+this.inputBox.getText());
		String content[];
		
		if(pathNode != null)
			content = pathNode.getContent().split("\\<.*?>");
		else
			return;
		
		String totContent = "";
		for(int i=0; i<content.length; i++)
		{
			totContent+=content[i];
		}
		JOptionPane.showMessageDialog(this, totContent);
	}
}
