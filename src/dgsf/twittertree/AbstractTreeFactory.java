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
