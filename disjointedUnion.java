import java.util.ArrayList;
import java.util.Arrays;

/**
 * this is the main classification class.
 * If you want to see the classification of nonseparating_sets in certain groups. You need to change
 * the type of map variable in this class. For example if you want to check z4*z8, then you need to change
 * map to be type of mapping_48.
 * 
 * The logic is first generate all the automorphism inside group, then use automorphism and translate by an
 * element of order 2 to put them into disjointedUnions(Hurwitz class).
 * 
 * @author weitao92
 *
 */
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
		}
	}

	int[] union;
	int size;
	mapping_48 map;
	ArrayList<non_separating_set> bag;
	ArrayList<equivalence> distinct;
	
	public disjointedUnion(ArrayList<non_separating_set> bag)
	{
		distinct = new ArrayList<equivalence>();
		this.bag = bag;
		map = new mapping_48();
		size = bag.size();
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
					int root1 = rootOf(i);
					int root2 = rootOf(index);
					union[root2] = root1;
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
				equivalence e = new equivalence(bag.get(root), root); 
				distinct.add(e);
				e.sets.add(bag.get(i));
				
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
