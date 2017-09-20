import java.util.ArrayList;


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
	
	public void run(int x, int y)
	{
		group A = new group(x,y);
		group twoA = A.operation(2);
		quotientGroup ATWOA = new quotientGroup(A, twoA);
		System.out.println("i am here");
		
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
				group gb = A.generate(b);
							
				if(!B.contains(gb))
				{
					B.add(gb);
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
						//size++;
						//size += AB.cyclicgroups.size();
					}
				}
			}
			
			combination combo = new combination();
			combo.generate(A, this);
			
			/**
			element[] sublist = new element[4];
			sublist[0] = new element(10,10,2,0);
			sublist[1] = new element(10,10,0,2);
			sublist[2] = new element(10,10,2,2);
			sublist[3] = new element(10,10,4,4);
			element h1 = sublist[0];
			element ih1 = h1.inverse();
			element h2 = sublist[1];
			element ih2 = h2.inverse();
			element h3 = sublist[2];
			element ih3 = h3.inverse();
			element h4 = sublist[3];
			element ih4 = h4.inverse();
			boolean success = false;
			int outSize = this.quotients.size();
			
			for(int i = 0; i < outSize; i++)
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
					
					int n = q.order;
					int length = n/2;
					
					ArrayList<coset> cosets = quotient.list;
					//ArrayList<HashTable<element>> tables = quotient.tables;
					for(int o = 0; o <= length; o++)
					{
						//HashTable<element> table = tables.get(o);
						coset C = cosets.get(o);
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
						System.out.println("Set: " + h1 + h2 + h3 + h4);
						System.out.println(insert[1] + "!=" + insert[2]);
						System.out.println("B = <" + cosets.get(0).left + ">" + " a = " + 
								cosets.get(1).list.get(0));
						
						//break outMost;
					}
					else
					{
						success = true;
					}
				}
			}
			
			if(success)
			{
				element[] NSS = new element[4];
				NSS[0] = sublist[0];
				NSS[1] = sublist[1];
				NSS[2] = sublist[2];
				NSS[3] = sublist[3];
				non_separating_set set = new non_separating_set(NSS);
				

					this.successful.add(set);
					this.NSS++;

			}
			**/	
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
