package dgsf.twittertree;

/** classe concreta che implementa la factory che gestisce la creazione del nodo radice **/
public class TwitterTreeFactory extends AbstractTreeFactory
{
	private static Node rootNode = new RootNode();
	
	/** Restituisce il nodo radice dell'albero. L'argomento specifica il nome/parametri 
	 * necessari alla creazione dell'albero, e sono documentati nel metodo getDocumentation() 
	 * @param path completo inserito dall'utilizzatore della libreria 
	 * */
	public static Node getTree(String treeAddress) 
	{
		String[] splittedTree = treeAddress.split("/");
		Node tmpNode = rootNode;
		Integer tmpIndex;
		
		for(int i = 1 ; i < splittedTree.length; i++)
		{				
			// Se abbiamo gia' effettuato l'accesso, utilizziamo la notazione /./path per non
			// dover rimettere il codice
			if(i == 1 && splittedTree[1] != ".")
			{
				tmpNode = tmpNode.getSubnode(splittedTree[1]);
			}
			else
			{
				try
				{
					tmpIndex = Integer.valueOf(splittedTree[i]);
				}
				catch(NumberFormatException exc)
				{
					tmpIndex = null;
				}
				

				if(tmpIndex == null)
				{
					tmpNode = tmpNode.getSubnode(splittedTree[i]);
				}
				else
				{
					tmpNode = tmpNode.getSubnode(tmpIndex);
				}
			}	
			
			if(tmpNode == null)
				return null;
		}
		
		return tmpNode;
	}

	/** Restituisce una stringa che specifica il funzionamento dell'albero e cosa deve contenere 
	 * l'argomento del metodo getTree() */
	public static String getDocumentation() 
	{
		return "passaggio1: per accedere alle informazioni fornite da twitter occorre effettuare l'autenticazione" +
				"dell'applicazione tramite il codice fornito da Twitter. L'URL di accesso si trova nel contenuto della radice\n" +
				"passaggio2: si accede inserendo (solo la prima volta) il codice ricevuto in precedenza, come primo nodo del path\n" +
				"passaggio3: dopo il primo accesso e' sufficiente inserire come primo nodo del path il carattere '.' al " +
				"posto del codice di autenticazione\n" +
				"esempio: primo accesso: /8571128/utente/tweets/....../\n" +
				"esempio: accessi successivi al primo: /./utente/............./" +
				"NB: il path ha sempre come carattere finale uno '/'.";		
	}
}
