package dgsf.twittertree;

import java.util.ArrayList;
import java.util.Iterator;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

/** classe che rappresenta un contenitore di nodi tweets **/
public class TweetsContainerNode extends AbstractNode
{
	private ResponseList<Status> tweetList; /* rappresentera' la lista dei tweet del contenitore */
	
	/** costruttore della classe {@link TweetsContainerNode}
	 * @param fatherFullKey chiave completa del nodo padre
	 * @param key chiave del nodo attuale
	 * @param father puntatore al nodo genitore
	 * **/
    public TweetsContainerNode(String fatherFullKey, String key, Node father)
    {
		super(fatherFullKey, key, father); /* richiama il costruttore di AbstractNode */
		
		try 
		{
	        User user = twitter.showUser(father.getKey()); /* recupera l'utente padre a cui appartengono i tweet del contenitore*/
	        
			tweetList = twitter.getUserTimeline(father.getKey(), new Paging(1, user.getStatusesCount())); /* recupera lista dei suoi tweet */		
		} 
		catch (TwitterException e) 
		{
			this.tweetList = null; /* recupero fallito */
		}
	}

	/** Genera un iteratore sui sottonodi (nodi figli del nodo corrente) */
	public Iterator<Node> getSubnodes() 
	{
		/* restituisce un iteratore di tweet da noi implementato */
		return new TweetsContainerIterator(this.twitter, this.tweetList, this.fullKey, this); 
	}

	/** metodo che restituisce l'i-esimo nodo figlio
	 * @param index indice relativo all'i-esimo figlio */
	public Node getSubnode(int index) 
	{
		try
		{
			/* recupera il tweet dell'utente in base all'indice */
			Status tmpStatus = tweetList.get(index-1); 
			
			/* restituisce un nodo di tipo tweet */
			if(tmpStatus != null)
				return new TweetNode(this.fullKey, new Long(tmpStatus.getId()).toString(), this); 
			else
				return null; /* creazione del nodo fallita*/
		}
		catch(Exception e)
		{
			return null; /* recupero fallito */
		}
	}

	/** Restituisce un codice XML contenente il numero di tweet */
	public String getContent() 
	{
		if(tweetList != null)
			return "<size>"+tweetList.size()+"</size>";
		else
			return null;
	}

	/** metodo non implementato
	 *  @throws UnsupportedOperationException **/
	public Node getSubnode(String key) throws UnsupportedOperationException
	{
		/* metodo non implementato in quanto non e' necessario ottenere tweet tramite chiave alfanumerica */
		throw (new UnsupportedOperationException());
	}
}
