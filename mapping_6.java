import java.util.ArrayList;

public class mapping_6 {
	
	class pair
	{
		int a, b;
		pair(int a, int b)
		{
			this.a = a;
			this.b = b;
		}
	}
	
	element[] orderTwos;
	ArrayList<matrix> phase1;
	//ArrayList<matrix> phase2;
	ArrayList<non_separating_set> set;
	matrix[] basic;
	
	public mapping_6()
	{
		set = new ArrayList<non_separating_set>();
		//phase2 = new ArrayList<matrix>();
		orderTwos = new element[4];
		orderTwos[0] = new element(6,6,0,0);
		orderTwos[1] = new element(6,6,0,3);
		orderTwos[2] = new element(6,6,3,0);
		orderTwos[3] = new element(6,6,3,3);
		phase1 = new ArrayList<matrix>();	
		basic = new matrix[6];
		basic[0] = new matrix(1,0,0,1);
		basic[1] = new matrix(1,1,0,1);
		basic[2] = new matrix(0,1,1,1);
		basic[3] = new matrix(0,1,1,0);
		basic[4] = new matrix(1,1,1,0);
		basic[5] = new matrix(1,0,1,1);
		generate_phase_1();
	}
	
	private void generate_phase_1()
	{
		group g = new group(3,3);
		ArrayList<element> list;
		int a, b, c, d;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(i == 0 && j == 0)
				{
					continue;
				}
				else
				{
					a = i;
					c = j;
					element generator = new element(3,3,a,c);
					list = g.reduce(generator);
					for(element e : list)
					{
						b = e.value1;
						d = e.value2;
						phase1.add(new matrix(a,b,c,d));
					}
				}
			}
		}
	}

	public void generate(non_separating_set s)
	{
		for(matrix t : basic)
		{
			for(matrix m : phase1)
			{
				for(element e : orderTwos)
				{
					non_separating_set result = auto(t, m, e, s);
					if(!set.contains(result))
					{
						set.add(result);
					}
				}
			}
		}
	}
	
	public non_separating_set auto(matrix basic, matrix phase1, element orderTwo, 
			non_separating_set s)
			{
				element[] set = new element[4];
				for(int i = 0; i < 4; i++)
				{
					element e = s.set[i];
					int v1 = e.value1 % 2;
					int v2 = e.value1 % 3;
					int v3 = e.value2 % 2;
					int v4 = e.value2 % 3;
					
					int a = (basic.basic[0][0] * v1 + basic.basic[0][1] * v3) % 2;
					int b = (basic.basic[1][0] * v1 + basic.basic[1][1] * v3) % 2;
					int c = (phase1.basic[0][0] * v2 + phase1.basic[0][1] * v4) % 3;
					int d = (phase1.basic[1][0] * v2 + phase1.basic[1][1] * v4) % 3;
					
					pair p1 = new pair(a,c);
					pair p2 = new pair(b,d);
					element newOne = new element(6,6,backtrack(p1),backtrack(p2));
					newOne.operationThis(newOne, orderTwo);
					set[i] = newOne;
				}
				non_separating_set newSet = new non_separating_set(set);
				return newSet;
			}
	
	private int backtrack(pair p)
	{
		if(p.a == 1 && p.b == 1)
		{
			return 1;
		}
		else if(p.a == 0 && p.b == 2)
		{
			return 2;
		}
		else if(p.a == 1 && p.b == 0)
		{
			return 3;
		}
		else if(p.a == 0 && p.b == 1)
		{
			return 4;
		}
		else if(p.a == 1 && p.b == 2)
		{
			return 5;
		}
		else
		{
			return 0;
		}
	}
	
	
	public non_separating_set generateSingle(int index, non_separating_set s, int[] union, 
			ArrayList<non_separating_set> bag)
	{
		non_separating_set result = null;
		int root1 = rootOf(index, union);
		for(matrix t : basic)
		{
			for(matrix m : phase1)
			{
				for(element e : orderTwos)
				{
					result = auto(t, m, e, s);
					if(!result.equals(s))
					{
						int index1 = bag.indexOf(result);
						int root2 = rootOf(index1, union);
						if(root1 != root2)
						{
							return result;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	private int rootOf(int index, int[] union)
	{
		while(union[index] != -1)
		{
			index = union[index];
		}
		
		return index;
	}
	
	public boolean contains(non_separating_set s)
	{
		return set.contains(s);
	}
	
	public void display()
	{
		for(non_separating_set s : set)
		{
			System.out.println(s);
		}
	}

}
