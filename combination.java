import java.util.ArrayList;

/**
 * Main algorithm of program is contained here. first generate all the possible candidate
 * then use brute-force with hashing to check whether or not they are nonseparating set or
 * complete unobstructed set.
 * 
 * @author weitao92
 *
 */
public class combination {

	static int[] insert;
	long num;
	
	public combination()
	{
		insert = new int[4];
		num = 0;
	}
	
	/**
	 * generate a bag consists of 4 random element from g and their inverse.
	 * @param g
	 * @return
	 */
	public void generate(group g, checker c, int mode)
	{
		ArrayList<element> exist = g.list;
		
		eliminate(exist, g.list.get(0).module1, g.list.get(0).module2);
		element[] myList = new element[exist.size()];
		for(int i = 0; i < exist.size(); i++)
		{
			myList[i] = exist.get(i);
		}
		System.out.println("Phase 1 is done.");
		
		if(mode == 1)
		{
			findNSS(myList,c);
		}
		else
		{
			findCUS(myList, c);
		}
	}
	
	private void findNSS(element arr[], checker c)
	{
		element[] sublist = new element[4];
		for(int x = 0; x <= arr.length - 4; x++)
		{
			sublist[0] = arr[x];
			for(int y = x + 1; y <= arr.length - 3; y++)
			{
				sublist[1] = arr[y];
				for(int z = y + 1; z <= arr.length - 2; z++)
				{
					sublist[2] = arr[z];
					for(int l = z + 1; l <= arr.length - 1; l++)
					{
						sublist[3] = arr[l];
							//num++;
							
							element h1 = sublist[0];
							element ih1 = h1.inverse();
							element h2 = sublist[1];
							element ih2 = h2.inverse();
							element h3 = sublist[2];
							element ih3 = h3.inverse();
							element h4 = sublist[3];
							element ih4 = h4.inverse();
							boolean success = true;
							int outSize = c.quotients.size();
							
							outMost: for(int i = 0; i < outSize; i++)
							{
								quotientGroup q = c.quotients.get(i);
								ArrayList<quotientGroup> grouplist = q.cyclicgroups;
								int innerSize = grouplist.size();
								
								for(int j = 0; j < innerSize; j++)
								{
									quotientGroup quotient = grouplist.get(j);
									int c1 = -1;
									boolean b1 = false;
									int c2 = -2;
									boolean b2 = false;
									int c3 = -3;
									boolean b3 = false;
									int c4 = -4;
									boolean b4 = false;
									
									int length = q.order/2;

									for(int o = 0; o <= length; o++)
									{
										coset C = quotient.list.get(o);
										if(!b1 && (C.contains(h1) || C.contains(ih1)))
										{
											c1 = o;
											b1 = true;
										}
										
										if(!b2 && (C.contains(h2) || C.contains(ih2)))
										{
											c2 = o;
											b2 = true;
										}
										
										if(!b3 && (C.contains(h3) || C.contains(ih3)))
										{
											c3 = o;
											b3 = true;
										}
										
										if(!b4 && (C.contains(h4) || C.contains(ih4)))
										{
											c4 = o;
											b4 = true;
										}
										
										if(b1 && b2 && b3 && b4)
										{
											break;
										}
									}
									
									
									insert[0] = c1;
									insert[1] = c2;
									insert[2] = c3;
									insert[3] = c4;
									
									doInsertionSort(insert);
									
									if(insert[2] != insert[1])
									{
										success = false;
										break outMost;
									}
								}
							}
							
							if(success)
							{
								element[] newList = new element[4];
								for(int i = 0; i < 4; i++)
								{
									newList[i] = sublist[i];
								}
								non_separating_set set = new non_separating_set(newList);
								c.successful.add(set);
								c.NSS++;
							}	
					}
				}
			}
		}
	}
	
	private void findCUS(element arr[], checker c)
	{
		
		element[] sublist = new element[4];
		for(int x = 0; x <= arr.length - 4; x++)
		{
			sublist[0] = arr[x];
			for(int y = x + 1; y <= arr.length - 3; y++)
			{
				sublist[1] = arr[y];
				for(int z = y + 1; z <= arr.length - 2; z++)
				{
					sublist[2] = arr[z];
					for(int l = z + 1; l <= arr.length - 1; l++)
					{
						sublist[3] = arr[l];
							//num++;
							
							element h1 = sublist[0];
							element ih1 = h1.inverse();
							element h2 = sublist[1];
							element ih2 = h2.inverse();
							element h3 = sublist[2];
							element ih3 = h3.inverse();
							element h4 = sublist[3];
							element ih4 = h4.inverse();
							boolean success = true;
							int outSize = c.quotients.size();

							outMost: for(int i = 0; i < outSize; i++)
							{
								quotientGroup q = c.quotients.get(i);
								ArrayList<quotientGroup> grouplist = q.cyclicgroups;
								int innerSize = grouplist.size();
								double d = q.H.order / 2.0;
								
								for(int j = 0; j < innerSize; j++)
								{
									quotientGroup quotient = grouplist.get(j);
									int c1 = -1;
									boolean b1 = false;
									int c2 = -2;
									boolean b2 = false;
									int c3 = -3;
									boolean b3 = false;
									int c4 = -4;
									boolean b4 = false;
									
									int length = q.order/2;

									for(int o = 0; o <= length; o++)
									{
										coset C = quotient.list.get(o);
										if(!b1 && (C.contains(h1) || C.contains(ih1)))
										{
											c1 = o;
											b1 = true;
										}
										
										if(!b2 && (C.contains(h2) || C.contains(ih2)))
										{
											c2 = o;
											b2 = true;
										}
										
										if(!b3 && (C.contains(h3) || C.contains(ih3)))
										{
											c3 = o;
											b3 = true;
										}
										
										if(!b4 && (C.contains(h4) || C.contains(ih4)))
										{
											c4 = o;
											b4 = true;
										}
										
										if(b1 && b2 && b3 && b4)
										{
											break;
										}
									}
									
									
									insert[0] = c1;
									insert[1] = c2;
									insert[2] = c3;
									insert[3] = c4;
									
									doInsertionSort(insert);

									if((insert[2] - insert[1])/d >= 1.0)
									{
										success = false;
										break outMost;
									}
								}
							}
							
							if(success)
							{
								element[] newList = new element[4];
								for(int i = 0; i < 4; i++)
								{
									newList[i] = sublist[i];
								}
								non_separating_set set = new non_separating_set(newList);
								c.successful.add(set);
								c.NSS++;
							}
						
					}
				}
			}
		}
	}
	
	public static void doInsertionSort(int[] input){
        
        for (int i = 1; i < input.length; i++) {
        	int j = i;
        	int temp = input[i];
            while(j > 0 && temp < input[j-1])
            {
            	input[j] = input[j-1];
            	j--;
            }
            
            input[j] = temp;
        }
    }
	
	@SuppressWarnings("unused")
	private boolean invalid(element list[], int start, int index)
	{
		element pivot = list[index];
		element inverse = pivot.inverse();
		for(int i = start; i < index; i++)
		{
			
				if(list[i].equals(inverse))
				{
					return true;
				}

		}
		
		return false;
	}
	
	private void eliminate(ArrayList<element> list, int module1, int module2)
	{
		ArrayList<element> bad = new ArrayList<element>();
		for(int i = 1; i < list.size(); i++)
		{
			element target = list.get(i);
			if(target.order() == 2)
			{
				continue;
			}
			for(int j = 0; j < i; j++)
			{
				if(target.equals(list.get(j).inverse()))
				{
					bad.add(target);
				}
			}
		}
		
		list.removeAll(bad);
	}

}
