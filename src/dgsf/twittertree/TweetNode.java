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
package dgsf.twittertree;

import java.util.Iterator;

import twitter4j.Status;
import twitter4j.TwitterException;

/** classe che rappresneta un nodo di tipo Tweet **/
public class TweetNode extends AbstractNode 
{
	private Status status; /* rappresentera' un tweet */
	
	/** costruttore della classe {@link TweetNode}
     * @param fatherFullKey chiave completa del nodo padre 
     * @param key chiave del nodo attuale
     * @param father puntatore al nodo genitore
	 */
	public TweetNode(String fatherFullKey, String key, Node father) 
	{
		super(fatherFullKey, key, father); /* richiama il costruttore di AbstractNode */
		
		try 
		{
			this.status = twitter.showStatus(Long.parseLong(key)); /* recupera un tweet in base alla chiave passata */
		} 
		/* recupero del tweet fallito */
		catch (NumberFormatException e) 
		{
			this.status = null;
		} 
		catch (TwitterException e) 
		{
			this.status = null;
		}
	}

	/** Restituisce un codice xml (senza i tag html/head/body) che rappresenta il contenuto del nodo corrente */
	public String getContent() 
	{
		if(this.status != null)
		{
			return "<tweet>\n"+
						"<id>"+this.status.getId()+"</id>\n"+
						"<text>"+this.status.getText()+"</text>\n"+
						"<retweetcount>"+this.status.getRetweetCount()+"</retweetcount>\n"+
					"</tweet>";
		}
		else
			return null;
	}
	/** metodo non implementato 
	 *  @throws UnsupportedOperationException */
	public Iterator<Node> getSubnodes() throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto i nodi tweet non hanno sottonodi */
		throw (new UnsupportedOperationException());
	}
	/** metodo non implementato 
	 *  @throws UnsupportedOperationException */
	public Node getSubnode(int index) throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto tweet non hanno sottonodi */
		throw (new UnsupportedOperationException());
	}
	/** metodo non implementato 
	 *  @throws UnsupportedOperationException */
	public Node getSubnode(String key) throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto tweet non hanno sottonodi */
		throw (new UnsupportedOperationException());
	}
}
