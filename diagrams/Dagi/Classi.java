abstract class AbstractNode implements Node 
{
    protected String key;
    protected String fullKey;
    
    /** costruttore */
    public AbstractNode(String fatherFullKey, String key)
    {
        this.fullKey = fatherFullKey;
        this.key = fatherFullKey+"/"+key;
    }
    
    /** Restituisce la chiave assoluta (percorso completo) del nodo corrente (indirizzo, e chiavi dei sottonodi) */
	String getFullKey();
    
    /** Restituisce la chiave del nodo corrente (vuota per la radice) */
	String getKey();
}

class ContactNode extends AbstractNode
{
    /** indica se l'utente è pubblico  */
    private boolean accessPublicFlag;
    
	/** restituisce il nodo figlio a cui e' associata la chiave key */
	Node getSubnode(String key);

	/** Restituisce un codice html (senza i tag html/head/body) che rappresenta il contenuto del nodo corrente */
	String getContent();
}

class TweetNode extends AbstractNode
{
	/** Restituisce un codice html (senza i tag html/head/body) che rappresenta il contenuto del nodo corrente */
	String getContent();
}

class ContactsContainerNode extends AbstractNode
{
    /** Genera un iteratore sui sottonodi (nodi figli del nodo corrente) */
	Iterator<Node> getSubnodes();

	/** restituisce l'i-esimo nodo figlio, dove i e' il parametro int del metodo */
	Node getSubnode(int index);

	/** restituisce il nodo figlio a cui e' associata la chiave key */
	Node getSubnode(String key);

	/** Restituisce un codice XML contenente il numero dei contatti */
	String getContent();
}

class TweetsContainerNode extends AbstractNode
{
    /** Genera un iteratore sui sottonodi (nodi figli del nodo corrente) */
	Iterator<Node> getSubnodes();

	/** restituisce l'i-esimo nodo figlio, dove i e' il parametro int del metodo */
	Node getSubnode(int index);

	/** Restituisce un codice XML contenente il numero di tweet */
	String getContent();
}

class RootNode extends AbstractNode
{
	/** restituisce il nodo figlio a cui e' associata la chiave key */
	Node getSubnode(String key);
}
