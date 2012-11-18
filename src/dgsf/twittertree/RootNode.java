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
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/** classe che rappresenta il nodo radice dell'albero **/
public class RootNode extends AbstractNode
{
  private boolean isAuthenticate;
  private RequestToken requestToken;  /* rappresenta il token che twitter fornisce in fase di registrazione dell'applicazione */
  private AccessToken accessToken;
	
    /** costruttore della classe {@link RootNode}
     */
	public RootNode()
	{
		super("","",null);  /* richiama il costruttore di AbstractNode */
		
		/* setta i codici dell'applicazione */
		twitter.setOAuthConsumer("","");
		
		try 
		{
      /* richiede  a twitter  il token per fare l'autenticazione */
			this.requestToken = twitter.getOAuthRequestToken();
		} 
		catch (TwitterException e) 
		{
			this.requestToken = null; /* richiesta fallita */
		}
	}

	/** restituisce il nodo figlio a cui e' associata la chiave key */
	public Node getSubnode(String key) 
	{
		try
		{
			this.accessToken = twitter.getOAuthAccessToken(this.requestToken, key); /* effettua l'autenticazione con i codici forniti */
			this.isAuthenticate = true;
		}
		// Autenticazione non riuscita 
		catch(TwitterException e) 
		{
			this.isAuthenticate = false;
		}
		return new SessionNode(fullKey, key, this); /* crea e restituisce la sessione */

	}
	
	/* restituisce il link per effettuare l'autorizzazione dell'applicazione */
	public String getContent() throws UnsupportedOperationException 
	{
		return "<authorizationurl>"+this.requestToken.getAuthorizationURL()+"</authorizationurl>";
	}

	public Iterator<Node> getSubnodes() throws UnsupportedOperationException
	{
    /* metodo non implementato in quanto non e' necessario avere un iteratore di sottonodi in un nodo radice */
		throw (new UnsupportedOperationException());
	}

	public Node getSubnode(int index) throws UnsupportedOperationException
	{
    /* metodo non implementato in quanto non e' necessario recuperare un sottonodo tramite indice numerico in un nodo radice */
		throw (new UnsupportedOperationException());
	}
}
