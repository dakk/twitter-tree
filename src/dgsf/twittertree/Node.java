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
