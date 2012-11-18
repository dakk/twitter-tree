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

public abstract class AbstractTreeFactory 
{
	final int TREE_FACTORY_VERSION = 1;

	/** Restituisce il nodo radice dell'albero. L'argomento specifica il nome/parametri necessari alla creazione dell'albero, e sono documentati nel metodo getDocumentation() */
	public static Node getTree(String treeAddress) 
	{ 
		throw new RuntimeException("Not Implemented");
	}

	/** Restituisce una stringa che specifica il funzionamento dell'albero e cosa deve contenere l'argomento del metodo getTree() */
	public static String getDocumentation() 
	{
		throw new RuntimeException("Not Implemented");
	}
}
