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
