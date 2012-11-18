package dgsf.twittertree;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
/** classe astratta che rappresenta un generico nodo dell'albero **/
public abstract class AbstractNode implements Node 
{
    protected String key;
    protected String fullKey;
    protected Twitter twitter;
    protected Node fatherNode;
    
    /** costruttore della classe {@link AbstractNode}
     * @param fatherFullKey chiave completa del nodo padre 
     * @param key chiave del nodo attuale
     * @param father puntatore al nodo genitore
     * **/
    public AbstractNode(String fatherFullKey, String key, Node father)
    {
        this.fullKey = fatherFullKey+"/"+key; /* crea il percorso assoluto del nodo */ 
        this.key = key; /* crea chiave del nodo */
        this.fatherNode = father; /* puntatore al nodo padre */
        
		/* Recupera l'oggetto di Twitter associato all'applicazione */
		this.twitter = new TwitterFactory().getInstance();
    }
    
    /** Restituisce la chiave assoluta (percorso completo) del nodo corrente (indirizzo, e chiavi dei sottonodi) */
	public String getFullKey()
	{
		return this.fullKey;
	}
    
    /** Restituisce la chiave del nodo corrente (vuota per la radice) */
	public String getKey()
	{
		return this.key;
	}
}
