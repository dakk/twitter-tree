package dgsf.twittertree;

import java.util.Iterator;

public interface Node 
{
	final int NODE_VERSION = 1;

	/** Genera un iteratore sui sottonodi (nodi figli del nodo corrente) */
	public Iterator<Node> getSubnodes();

	/** restituisce l'i-esimo nodo figlio, dove i e' il parametro int del metodo */
	public Node getSubnode(int index);

	/** restituisce il nodo figlio a cui e' associata la chiave key */
	public Node getSubnode(String key);

	/** Restituisce la chiave del nodo corrente (vuota per la radice) */
	public String getKey();

	/** Restituisce la chiave assoluta (percorso completo) del nodo corrente (indirizzo, e chiavi dei sottonodi) */
	public String getFullKey();

	/** Restituisce un codice XML (senza i tag html/head/body) che rappresenta il contenuto del nodo corrente */
	public String getContent();
}
