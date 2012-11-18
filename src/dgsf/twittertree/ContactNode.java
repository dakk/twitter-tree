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

import twitter4j.TwitterException;
import twitter4j.User;

/** classe che rappresenta un singolo contatto **/
public class ContactNode extends AbstractNode 
{
    private User user; /* conterra' le informazioni relative all'utente desiderato */
    
    /** costruttore della classe {@link ContactNode}
     * @param fatherFullKey chiave completa del nodo padre 
     * @param key chiave del nodo attuale
     * @param father puntatore al nodo genitore
     * @throws TwitterException 
     */    
    public ContactNode(String fatherFullKey, String key, Node father) throws TwitterException 
    {
		super(fatherFullKey, key, father); /* richiama il costruttore di AbstractNode */
	
        this.user = twitter.showUser(key); /* Recupera l'utente il cui nome e' contenuto nella chiave */
	}
    
	/** metodo che restituisce il nodo figlio a cui e' associata la chiave key */
	public Node getSubnode(String key)
	{
		try
		{
			/* Viene restituito un oggetto di tipo contenitore in base alla chiave passata come parametro  */
			if(key.equals("following")) 
			{
				return new ContactsContainerNode(this.fullKey, "following", this);
			}
			else if(key.equals("followers"))
			{
				return new ContactsContainerNode(this.fullKey, "followers", this);
			}
			else if(key.equals("tweets"))
			{
				return new TweetsContainerNode(this.fullKey, "tweets", this);
			}
			else
			{
				return null; /* chiave passata non valida */
			}	
		}
		catch (TwitterException e)
		{
			return null; /* errore nella creazione del contenitore */
		}
	}

	/** Restituisce un codice xml contenente le informazioni dell'utente scelto */
	public String getContent()
	{
		return  "<name>"+user.getName()+"</name>\n"+
				"<id>"+user.getId()+"</id>\n"+
				"<screenname>"+user.getScreenName()+"</screenname>\n"+
				"<description>"+user.getDescription()+"</description>\n"+
				"<lang>"+user.getLang()+"</lang>\n"+
				"<avatar>"+user.getProfileImageURL().toString()+"</avatar>";
	}

	/** metodo non implementato **/
	public Iterator<Node> getSubnodes() throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto non e' necessario avere un iteratore di nodi 
		 * in un singolo contatto */
		throw (new UnsupportedOperationException());
	}

	/** metodo non implementato **/
	public Node getSubnode(int index) throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto non e' necessario ottenere un figlio 
		 * individuato da un indice numerico in un nodo di tipo contatto */
		throw (new UnsupportedOperationException());
	}
}
