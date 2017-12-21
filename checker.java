import java.util.ArrayList;

/**
 * resposible to coordinate several parts of program. Also if you want to check all the coset numbers
 * of certain candidate set, see the comments in run method.
 * 
 * @author weitao92
 *
 */
public class checker {
	
	ArrayList<non_separating_set> successful;
	ArrayList<quotientGroup> quotients;
	int NSS;
	int SS;
	
	public checker()
	{
		successful = new ArrayList<non_separating_set>();
		quotients = new ArrayList<quotientGroup> ();
		NSS = 0;
		SS = 0;
	}
	
	public void run(int x, int y, int mode)
	{
		group A = new group(x,y);
		group twoA = A.operation(2);
		quotientGroup ATWOA = new quotientGroup(A, twoA);
		
		if(!ATWOA.isomorphic())
		{
			System.out.println("A/2A is not isomorphic to Z2 * Z2.");
			return;
		}
		else
		{
			System.out.println("A/2A is isomorphic to Z2 * Z2, we can continue.");
			
			ArrayList<group> B = new ArrayList<group>();
			
			
			for(element b : A.list)
			{
				//if(b.order() == 8)
				{
					group gb = A.generate(b);
								
					if(!B.contains(gb))
					{
						B.add(gb);
					}
				}
			}
			
			for(group b : B)
			{				
				quotientGroup AB = new quotientGroup(A, b);
				
				if(!AB.cyclic())
				{
					continue;
				}
				else
				{					
					AB.update();
					
					if(!quotients.contains(AB))
					{
						quotients.add(AB);
						AB.cyclicgroups = AB.generate();
					}
				}
			}
			combination combo = new combination();
			combo.generate(A, this, mode);
			
			//this part of code is for print coset number for certain candidate. uncomment this part
			//if you try to test it.
			/**
			System.out.println("for H: (0,1),(2,1),(4,1),(6,1).");
			element[] sublist = new element[4];
			sublist[0] = new element(8,4,0,1);
			sublist[1] = new element(8,4,2,1);
			sublist[2] = new element(8,4,4,1);
			sublist[3] = new element(8,4,6,1);
			
			element h1 = sublist[0];
			element ih1 = h1.inverse();
			element h2 = sublist[1];
			element ih2 = h2.inverse();
			element h3 = sublist[2];
			element ih3 = h3.inverse();
			element h4 = sublist[3];
			element ih4 = h4.inverse();
			boolean success = true;
			int outSize = this.quotients.size();
			
			outMost: for(int i = 0; i < outSize; i++)
			{
				quotientGroup q = this.quotients.get(i);
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
					
					int[] insert = new int[4];
					insert[0] = c1;
					insert[1] = c2;
					insert[2] = c3;
					insert[3] = c4;
					
					doInsertionSort(insert);
					
					//if(insert[2] != insert[1])
					{
						System.out.println("$\\langle" + quotient.H.list.get(0) + "\\rangle$&$" + 
					quotient.a + "+B$&$" + insert[0] + "," + insert[1] + "," + insert[2] + ","
					+ insert[3] + "$\\\\");
						System.out.println("\\hline");
						
					}
				}
			}
			**/
			//prove(x,y);	
		}
	}
	
	public void prove(int x, int y)
	{
		group g = new group(x,y);
		ArrayList<element> orderEight = new ArrayList<element> ();
		for(element e : g.list)
		{
			if(e.order() == 8)
			{
				orderEight.add(e);
			}
		}
		ArrayList<non_separating_set> successed = new ArrayList<non_separating_set>();
		ArrayList<non_separating_set> failed = new ArrayList<non_separating_set>();
		
		for(int i = 0; i < orderEight.size(); i++)
		{
			
			element current = orderEight.get(i);
			for(int j = i + 1; j < orderEight.size(); j++)
			{
				element next = orderEight.get(j);
				
				if(current.inverse().equals(next))
				{
					continue;
				}
				else
				{
					element twoA = current.operation(current, 1);
					element twoB = next.operation(next, 1);
					if(twoA.equals(twoB)) // 2A = 2B
					{
						for(element e : g.list)
						{
							if(!e.equals(current) && !e.equals(current.inverse())
									&& !e.equals(next) && !e.equals(next.inverse())) // we have A,B,C here
							{
								element D = e.operation(twoA, 1);
								if(!D.equals(current) && !D.equals(current.inverse())
										&& !D.equals(next) && !D.equals(next.inverse())
										&& !D.equals(e) && !D.equals(e.inverse()))
								{
									element[] candidate = {current, next, e, D};
									non_separating_set nss = new non_separating_set(candidate);
									
									element h1 = candidate[0];
									element ih1 = h1.inverse();
									element h2 = candidate[1];
									element ih2 = h2.inverse();
									element h3 = candidate[2];
									element ih3 = h3.inverse();
									element h4 = candidate[3];
									element ih4 = h4.inverse();
									boolean success = true;
									int outSize = quotients.size();
									outMost: for(int i1 = 0; i1 < outSize; i1++)
									{
										quotientGroup q = quotients.get(i1);
										
										ArrayList<quotientGroup> grouplist = q.cyclicgroups;
										int innerSize = grouplist.size();
										
										for(int j1 = 0; j1 < innerSize; j1++)
										{
											quotientGroup quotient = grouplist.get(j1);
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
											
											int[] insert = new int[4];
											insert[0] = c1;
											insert[1] = c2;
											insert[2] = c3;
											insert[3] = c4;
											
											doInsertionSort(insert);
											
											
											if(insert[1] != insert[2])
											{
												success = false;
												break outMost;
											}
										}
									}
									
									if(success)
									{
										if(!successed.contains(nss))
										{
											successed.add(nss);
										}
									}
									else
									{
										if(!failed.contains(nss))
										{
											failed.add(nss);
										};
									}
								}
							}
						}
					}
					else
					{
						continue;
					}
				}
			}
		}
		
		for(non_separating_set nss : successed)
		{
			System.out.println(nss + " is a nss."); 
		}
		for(non_separating_set nss : failed)
		{
			System.out.println(nss + " is not a nss.");
		}
	}
	
	public static int[] doInsertionSort(int[] input){
        
        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }

}
