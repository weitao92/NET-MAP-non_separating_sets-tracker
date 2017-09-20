import java.util.ArrayList;


public class mapping_4 {
	
	element[] orderTwos;
	ArrayList<matrix> matrices;
	ArrayList<non_separating_set> set;
	matrix[] basic;
	
	public mapping_4()
	{
		set = new ArrayList<non_separating_set>();
		orderTwos = new element[4];
		orderTwos[0] = new element(4,4,0,0);
		orderTwos[1] = new element(4,4,0,2);
		orderTwos[2] = new element(4,4,2,0);
		orderTwos[3] = new element(4,4,2,2);
		matrices = new ArrayList<matrix>();	
		basic = new matrix[6]; //6 2*2 matrix that has non-zero determinant.
		basic[0] = new matrix(1,0,0,1);
		basic[1] = new matrix(1,1,0,1);
		basic[2] = new matrix(0,1,1,1);
		basic[3] = new matrix(0,1,1,0);
		basic[4] = new matrix(1,1,1,0);
		basic[5] = new matrix(1,0,1,1);
		
		for(matrix m : basic)
		{
			generateMatrix(m, 1, 0,0,0,0);
		}
	}
	
	private void generateMatrix(matrix m, int index, int a, int b, int c, int d)
	{
		if(index == 1)
		{
			if(m.basic[0][0] == 0)
			{
				a = 0;
				generateMatrix(m, index + 1, a, b, c, d);
				a = 2;
				generateMatrix(m, index + 1, a, b, c, d);
			}
			else
			{
				a = 1;
				generateMatrix(m, index + 1, a, b, c, d);
				a = 3;
				generateMatrix(m, index + 1, a, b, c, d);
			}
		}
		else if(index == 2)
		{
			if(m.basic[0][1] == 0)
			{
				b = 0;
				generateMatrix(m, index + 1, a, b, c, d);
				b = 2;
				generateMatrix(m, index + 1, a, b, c, d);
			}
			else
			{
				b = 1;
				generateMatrix(m, index + 1, a, b, c, d);
				b = 3;
				generateMatrix(m, index + 1, a, b, c, d);
			}
		}
		else if(index == 3)
		{
			if(m.basic[1][0] == 0)
			{
				c = 0;
				generateMatrix(m, index + 1, a, b, c, d);
				c = 2;
				generateMatrix(m, index + 1, a, b, c, d);
			}
			else
			{
				c = 1;
				generateMatrix(m, index + 1, a, b, c, d);
				c = 3;
				generateMatrix(m, index + 1, a, b, c, d);
			}
		}
		else
		{
			if(m.basic[1][1] == 0)
			{
				d = 0;
				matrix newOne = new matrix(a,b,c,d);
				matrices.add(newOne);
				d = 2;
				matrix newOne1 = new matrix(a,b,c,d);
				matrices.add(newOne1);
			}
			else
			{
				d = 1;
				matrix newOne = new matrix(a,b,c,d);
				matrices.add(newOne);
				d = 3;
				matrix newOne1 = new matrix(a,b,c,d);
				matrices.add(newOne1);
			}

		}
	}
	
	
	public void generate(non_separating_set s)
	{
		for(matrix m : matrices)
		{
			for(element e : orderTwos)
			{
				non_separating_set result = m.multi(s, e);
				if(!set.contains(result))
				{
					set.add(result);
				}
			}
		}
	}
	
	public non_separating_set generateSingle(int index, non_separating_set s, int[] union, 
			ArrayList<non_separating_set> bag)
	{
		non_separating_set result = null;
		int root1 = rootOf(index, union);
		for(matrix m : matrices)
		{
			for(element e : orderTwos)
			{
				result = m.multi(s, e);
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
