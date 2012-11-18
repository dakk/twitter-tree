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
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dgsf.twittertree.Node;
import dgsf.twittertree.TwitterTreeFactory;

public class TwitterTreeInsertCode extends JFrame implements ActionListener
{
	private JButton buttonAutenticate;
	private ImageComponent logoImage;
	private JTextArea inputBox;
	private JLabel label;
	
	public TwitterTreeInsertCode()
	{
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new GridLayout(3,1));
		
		this.buttonAutenticate = new JButton("OK");
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
		
		this.inputBox = new JTextArea(1, 12);
		this.label = new JLabel("Inserisci il codice di autenticazione: ");
		JPanel subContainer2 = new JPanel();
		subContainer2.setLayout(new FlowLayout());
		
		subContainer2.add(this.label);
		subContainer2.add(this.inputBox);
		mainContainer.add(subContainer2);
		
		JPanel subContainer3 = new JPanel();
		subContainer3.setLayout(new FlowLayout());
		mainContainer.add(subContainer3);
		
		subContainer3.add(this.buttonAutenticate);
		this.buttonAutenticate.addActionListener(this);
		
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("TwitterTree");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/* azione collegata al bottone */
	public void actionPerformed(ActionEvent event ) 
	{
		String code = "/"+this.inputBox.getText();
		Node firstNode = TwitterTreeFactory.getTree(code); /* autenticazione */
		this.dispose();
		TwitterTreeInsertPath insertPathWindow = new TwitterTreeInsertPath();
	}

}
