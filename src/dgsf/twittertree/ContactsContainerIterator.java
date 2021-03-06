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
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;


/** classe che rappresenta un iteratore per scorrere una lista di contatti */
public class ContactsContainerIterator implements Iterator<Node>
{
	private Twitter twitter; /* oggetto che contiene i dati relativi all'applicazione */ 
	private long[] ids; /* lista degli ids utente presenti nel contenitore */ 
	private int index;
	private String fullKey;
	private Node root; /* nodo radice dell'albero */ 
	
	/** costruttore della classe {@link ContactsContainerIterator}
	 * @param twitter oggetto per accedere alle API di Twitter
	 * @param ids lista contenente gli id degli utenti 
	 * @param fullKey chiave completa 
	 * @param root nodo radice
	 *  **/
	public ContactsContainerIterator(Twitter twitter, long[] ids, String fullKey, Node root)
	{
		this.twitter = twitter;
		this.ids = ids;
		this.index = 0;
		this.fullKey = fullKey;
		this.root = root;
	}
	
	/** override del metodo hasNext() di Iterator **/
	@Override
	public boolean hasNext() 
	{
		if(this.index < ids.length)
			return true;
		else
			return false;
	}

	/** override del metodo next() di Iterator **/
	@Override
	public Node next() 
	{
		User us;
		try 
		{
			us = this.twitter.showUser(ids[this.index]); /* recupera l'utente da restituire  */
		} 
		catch (TwitterException e)  /* errore nel recupero dell'utente */
		{
			this.index++;
			return null;
		}
		this.index++;
		
		try 
		{
			return new ContactNode(this.fullKey, us.getScreenName(), this.root); /* restituisce il contatto dell'utente recuperato */
		} 
		catch (TwitterException e) /* errore nella restituzione del contatto */
		{
			return null;
		}
	}

	/** override del metodo remove() di Iterator **/
	@Override
	public void remove() 
	{		
		this.index++; /* salta all'elemento successivo */
	}

}
