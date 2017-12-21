/**
 * represent a nonseparating_set. Used for classification.
 * 
 * @author weitao92
 *
 */
public class non_separating_set {
	
	element[] set;
	
	public non_separating_set(element[] set)
	{
		this.set = set;
	}
	
	public boolean equals(Object obj)
	{
		non_separating_set another = (non_separating_set) obj;
		
		element[] anotherSet = another.set;
		
		for(int i = 0; i < 4; i++)
		{
			boolean contains = false;
			for(int j = 0; j < 4; j++)
			{
				if(anotherSet[j].equals(set[i]) || anotherSet[j].equals(set[i].inverse()))
				{
					contains = true;
					break;
				}
			}
			
			if(!contains)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public String toString()
	{
		 String result = "[";
		 for(element e : set)
		 {
			 result += e.toString();
		 }
		 result += "]";
		 return result;
	}

}
