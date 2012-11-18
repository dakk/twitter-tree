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

import java.util.*;

import twitter4j.IDs;
import twitter4j.TwitterException;
import twitter4j.User;

/** classe che rappresenta un contenitore di nodi contatto **/
public class ContactsContainerNode extends AbstractNode 
{	
	private long[] contactList; /* rappresentera' la lista dei contatti presenti */
	
	/** costruttore della classe {@link ContactsContainerNode}
	 * @param fatherFullKey chiave completa del nodo padre
	 * @param key chiave del nodo attuale
	 * @param father puntatore al nodo genitore
	 * @throws TwitterException**/
    public ContactsContainerNode(String fatherFullKey, String key, Node father) throws TwitterException 
    {
		super(fatherFullKey, key, father); /* richiama il costruttore di AbstractNode */ 
		
		User user = twitter.showUser(father.getKey()); /* preleva l'utente al quale appartiene la lista */ 
		long id = user.getId(); /* recupera il suo id */ 
		
		if(key.equals("following"))
		{
			/* recuperiamo la lista dei following dell'utente scelto */
			IDs ids = twitter.getFriendsIDs(id,-1);
			contactList = ids.getIDs();
		}
		else if(key.equals("followers"))
		{
			/* recuperiamo la lista dei followers dell'utente scelto */
			IDs ids = twitter.getFollowersIDs(id, -1);
			contactList = ids.getIDs();
		}
		else 
		{
			contactList = null; /* errore nel recupero dei dati */
		}
		
	}

	/** metodo che genera un iteratore sui sottonodi (nodi figli del nodo corrente) */
	public Iterator<Node> getSubnodes() 
	{
		/* restituisce l'iteratore da noi implementato */
		return new ContactsContainerIterator(this.twitter, this.contactList, this.fullKey, this);
	}

	/** metodo che restituisce l'i-esimo nodo figlio
	 * @param index indice relativo all'i-esimo figlio
	 * */
	public Node getSubnode(int index) 
	{
		try
		{
			/* recupera l'utente figlio con indice numerico index-1 */
			User us = twitter.showUser(contactList[index-1]); 
			return new ContactNode(this.fullKey, us.getScreenName(), this); /* restituisce il nodo del contatto */
		}
		/* recupero fallito */
		catch(IndexOutOfBoundsException e) 
		{
			return null;
		}
		catch(TwitterException e)
		{
			return null;
		}
	}

	/** Restituisce il nodo figlio a cui e' associata la chiave key */
	public Node getSubnode(String key) 
	{
		/* metodo non implementato in quanto non e' necessario recuperare un utente in base alla chiave  */
		throw (new UnsupportedOperationException());
	}

	/** Restituisce un codice XML contenente il numero dei contatti */
	public String getContent() 
	{
		if(this.contactList != null)
			return "<size>"+this.contactList.length+"</size>";
		else return null;
	}
}
