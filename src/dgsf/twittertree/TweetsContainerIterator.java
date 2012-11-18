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

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/** classe che rappresenta un iteratore per scorrere una lista di tweet */
public class TweetsContainerIterator implements Iterator<Node> 
{
	private Twitter twitter;  /* oggetto che contiene i dati relativi all'applicazione */
	private ResponseList<Status> statusList; /* rappresentera' la lista di tweet */
	private int index; 
	private String fullKey;
	private Node root; /* nodo radice dell'albero */ 
	
	/** costruttore della classe {@link TweetsContainerIterator}
	 * @param twitter oggetto per accedere alle API di Twitter
	 * @param statusList lista di tweets
	 * @param fullKey chiave completa 
	 * @param root nodo radice
	 *  **/
	public TweetsContainerIterator(Twitter twitter, ResponseList<Status> statusList, String fullKey, Node root)
	{
		this.twitter = twitter;
		this.statusList = statusList;
		this.index = 0;
		this.fullKey = fullKey;
		this.root = root;
	}
	
	/** override del metodo hasNext() di Iterator **/
	@Override
	public boolean hasNext() 
	{
		if(this.index < this.statusList.size())
			return true;
		else
			return false;
	}

	/** override del metodo next() di Iterator **/
	@Override
	public Node next() 
	{
		Status status;

		status = this.statusList.get(this.index); /* recupera il tweet da restituire */
		this.index++;
		
		/* restituisce il TweetNode del tweet recuperato */
		if(status != null)
			return new TweetNode(this.fullKey, new Long(status.getId()).toString(), this.root);
		else
			return null; /* creazione fallita */

	}

	/** override del metodo remove() di Iterator **/
	@Override
	public void remove() 
	{
		this.index++;
	}

}
