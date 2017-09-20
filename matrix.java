

public class matrix {
	
	int[][] basic;
	
	public matrix(int a, int b, int c, int d)
	{
		basic = new int[2][2];
		basic[0][0] = a;
		basic[0][1] = b;
		basic[1][0] = c;
		basic[1][1] = d;
	}
	
	public boolean equals(Object obj)
	{
		matrix another = (matrix) obj;
		
		return basic[0][0] == another.basic[0][0]
				&& basic[0][1] == another.basic[0][1]
						&& basic[1][0] == another.basic[1][0]
								&& basic[1][1] == another.basic[1][1];
	}
	
	public non_separating_set multi(non_separating_set s, element orderTwo)
	{
		element[] bag = s.set;
		element[] newSet = new element[4];
		for(int i = 0; i < 4; i++)
		{
			element e = bag[i];
			int value1 = (basic[0][0] * e.value1 + basic[0][1] * e.value2) % e.module1;
			int value2 = (basic[1][0] * e.value1 + basic[1][1] * e.value2) % e.module2;
			element newOne = new element(e.module1, e.module1, value1, value2);
			newOne.operationThis(newOne, orderTwo);
			newSet[i] = newOne;
		}
		
		return new non_separating_set(newSet);
	}
	
	public matrix MatrixMul(matrix another, int base)
	{
		int a = (basic[0][0] * another.basic[0][0] + basic[0][1] * another.basic[1][0]) % base;
		int b = (basic[0][0] * another.basic[0][1] + basic[0][1] * another.basic[1][1]) % base;
		int c = (basic[1][0] * another.basic[0][0] + basic[1][1] * another.basic[1][0]) % base;
		int d = (basic[1][0] * another.basic[0][1] + basic[1][1] * another.basic[1][1]) % base;
		matrix newOne = new matrix(a,b,c,d);
		return newOne;
	}

}
