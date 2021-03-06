

/**
 * basic element of a group finite abelian group generated by 2 elements.
 * @author weitao92
 *
 */
public class element implements Comparable<element>
{
	
	int module1;
	int module2;
	int value1;
	int value2;
	String rep;
	
	public element(int module1, int module2, int value1, int value2)
	{
		this.module1 = module1;
		this.module2 = module2;
		this.value1 = value1;
		this.value2 = value2;
		rep = toString();
	}
	
	/**
	 * group operation.
	 * @param another
	 */
	public element operation(element another, int iteration)
	{
		element newOne = new element(module1, module2, value1, value2);
		for(int i = 0; i < iteration; i++)
		{
			newOne.value1 = (newOne.value1 + another.value1) % module1;
			newOne.value2 = (newOne.value2 + another.value2) % module2;
		}
		return newOne;
	}
	
	/**
	 * a version without generate a new element.
	 * @param result
	 * @param another
	 */
	public void operationThis(element result, element another)
	{
		result.value1 = (this.value1 + another.value1) % module1;
		result.value2 = (this.value2 + another.value2) % module2;
	}
	
	/**
	 * compute the order of this element in current group.
	 * @return
	 */
	public int order()
	{
		int order1 = module1 / GCD(module1, value1);
		int order2 = module2 / GCD(module2, value2);
		return LCM(order1, order2);
	}
	
	/**
	 * compute the inverse of current element.
	 * @return
	 */
	public element inverse()
	{
		int x;
		if(value1 == 0)
		{
			x = 0;
		}
		else
		{
			x = module1 - value1;
		}
		int y;
		if(value2 == 0)
		{
			y = 0;
		}
		else
		{
			y = module2 - value2;
		}
		
		return new element(module1, module2, x, y);
	}
	
	/**
	 * Computer the GCD of 2 integers.
	 * @param a
	 * @param b
	 * @return
	 */
	static int GCD(int a, int b)
	{
		while(b > 0)
		{
			int temp = b;
			b = a % b;
			a = temp;
		}
		
		return a;
	}
	
	/**
	 * computer the LCM of 2 integers.
	 * @param a
	 * @param b
	 * @return
	 */
	int LCM(int a, int b)
	{
		if(a >= b)
		{
			return (a*b) / GCD(a, b);
		}
		else
		{
			return (a*b) / GCD(b, a);
		}
	}
	
	/**
	 * implement the toString function.
	 */
	public String toString()
	{
		return "(" + value1 + "," + value2 + ")";
	}
	
	/**
	 * implement the equals method.
	 * @param another
	 * @return
	 */
	public boolean equals(Object obj)
	{
		element another = (element) obj;
		return 
				value1 == another.value1 && value2 == another.value2;
	}
	
	/**
	 * implemented compareTo function.
	 * @param another
	 * @return
	 */
	public int compareTo(element another)
	{
		if(this.value1 < another.value1 || 
				(this.value1 == another.value1 && this.value2 < another.value2))
		{
			return -1;
		}
		else if(this.value1 == another.value1 && this.value2 == another.value2)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	/**
	 * generate hashcode for element.
	 */
	public int hashCode()
	{
		return value1 * 300 + 1 * value2;
	}

}
