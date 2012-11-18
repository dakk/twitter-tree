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

/** *classe utilizzata per stabilire la sessione con Twitter */
public class SessionNode extends AbstractNode 
{
	/** costruttore della classe {@link SessionNode}
	 * **/
	public SessionNode(String fatherFullKey, String key, Node father)
    {
		super(fatherFullKey, key, father); /* richiama il costruttore di AbstractNode */
	}
	
	/** restituisce il nodo figlio a cui e' associata la chiave key */
	public Node getSubnode(String key) 
	{
		/* Prova a ottenere il contatto selezionato. Se non ci riesce viene restituito "null" */
		try
		{
			return new ContactNode(fullKey, key, this); /* restituisce il contatto */
		}
		catch(TwitterException e)
		{
			return null; /* E' stato raggiunto il limite di richieste consentite da twitter */
		}
	}
	/** metodo non implementato 
	 *  @throws UnsupportedOperationException */
	public Iterator<Node> getSubnodes() throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto non e' necessario avere un iteratore di sottonodi in un nodo di tipo SessionNode */
		throw (new UnsupportedOperationException());
	}
	/** metodo non implementato 
	 *  @throws UnsupportedOperationException */
	public Node getSubnode(int index) throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto non e' necessario recuperare un sottonodo tramite indice numerico in un nodo di tipo SessionNode */
		throw (new UnsupportedOperationException());
	}
	/** metodo non implementato 
	 *  @throws UnsupportedOperationException */
	public String getContent() throws UnsupportedOperationException 
	{
		/* metodo non implementato in quanto non e' necessario restituire il contenuto di un nodo sessione */
		throw (new UnsupportedOperationException());
	}
}
