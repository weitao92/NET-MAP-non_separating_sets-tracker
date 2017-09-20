import java.util.ArrayList;
import java.util.Arrays;


public class disjointedUnion {
	
	static class equivalence
	{
		int indexOfRoot;
		non_separating_set root;
		int num;
		ArrayList<non_separating_set> sets;
		
		public equivalence(non_separating_set root, int indexOfRoot)
		{
			this.indexOfRoot = indexOfRoot;
			this.root = root;
			num = 1;
			sets = new ArrayList<non_separating_set>();
			sets.add(root);
		}
	}

	int[] union;
	int size;
	mapping_6 map;
	ArrayList<non_separating_set> bag;
	ArrayList<equivalence> distinct;
	
	public disjointedUnion(ArrayList<non_separating_set> bag)
	{
		distinct = new ArrayList<equivalence>();
		this.bag = bag;
		map = new mapping_6();
		size = bag.size();
		//sets = new non_separating_set[size];
		union = new int[size];
		Arrays.fill(union, -1);
	}
	
	public void generate()
	{
		while(true)
		{
			boolean finished = true;
			for(int i = 0; i < size; i++)
			{
				non_separating_set s = map.generateSingle(i, bag.get(i), union, bag);
				if(s != null)
				{
					finished = false;
					int index = bag.indexOf(s);
					union[index] = i;
				}
			}
			
			if(finished)
			{
				break;
			}
		}
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i = 0; i < size; i++)
		{
			int root = rootOf(i);
			if(!temp.contains(root))
			{
				temp.add(root);
				distinct.add(new equivalence(bag.get(root), root));
			}
			else
			{
				for(equivalence e : distinct)
				{
					if(e.indexOfRoot == root)
					{
						e.num++;
						e.sets.add(bag.get(i));
					}
				}
			}
		}
	}
	
	private int rootOf(int index)
	{
		while(union[index] != -1)
		{
			index = union[index];
		}
		
		return index;
	}

}
