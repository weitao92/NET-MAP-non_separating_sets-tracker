import java.util.ArrayList;

/**
 * a minor program i wrote to help prove non-existence of nonseparating-sets in certain groups.
 * 
 * @author weitao92
 *
 */
public class proofFive {
	
	public static void main(String agrs[])
	{
		boolean valid = false;
		ArrayList<ArrayList<element>> subgroups = new ArrayList<ArrayList<element>>();
		ArrayList<ArrayList<element>> results = new ArrayList<ArrayList<element>>();
		ArrayList<element> generators = new ArrayList<element>();
		for(int i = 0; i < 5; i++)
		{
			element e = new element(5,5,1,i);
			generators.add(e);
		}
		generators.add(new element(5,5,0,1));
		
		for(int i = 0; i < 6; i++)
		{
			ArrayList<element> sub = new ArrayList<element>();
			ArrayList<element> result = new ArrayList<element>();
			element generator = generators.get(i);
			for(int j = 0; j < 5; j++)
			{
				element temp = generator.operation(generator, j);
				sub.add(temp);
			}
			results.add(result);
			subgroups.add(sub);
		}
		
		group G = new group(5,5);
		element[] H = new element[4];
		eliminate(G.list);
		int size = G.list.size();
		ArrayList<element> cs = new ArrayList<element>();
		element c1 = new element(5,5,0,0);
		element c2 = new element(5,5,0,0);
		element c3 = new element(5,5,0,0);
		element c4 = new element(5,5,0,0);
		element c5 = new element(5,5,0,0);
		element c6 = new element(5,5,0,0);
		element c7 = new element(5,5,0,0);
		element c8 = new element(5,5,0,0);
		element c9 = new element(5,5,0,0);
		element c10 = new element(5,5,0,0);
		element c11 = new element(5,5,0,0);
		element c12 = new element(5,5,0,0);
		element identity = new element(5,5,0,0);
		System.out.println("phase 1 is done.");
		outmost: for(int i = 0; i <= size - 4; i++)
		{
			H[0] = G.list.get(i);
			for(int j = i + 1; j <= size - 3; j++)
			{
				H[1] = G.list.get(j);
				for(int k = j + 1; k <= size - 2; k++)
				{
					H[2] = G.list.get(k);
					for(int l = k + 1; l <= size - 1; l++)
					{
						H[3] = G.list.get(l);
						valid = false;
						
						H[0].operationThis(c1, H[1]);
						H[0].operationThis(c2, H[1].inverse());
						H[0].operationThis(c3, H[2]);
						H[0].operationThis(c4, H[2].inverse());
						H[0].operationThis(c5, H[3]);
						H[0].operationThis(c6, H[3].inverse());
						H[1].operationThis(c7, H[2]);
						H[1].operationThis(c8, H[2].inverse());
						H[1].operationThis(c9, H[3]);
						H[1].operationThis(c10, H[3].inverse());
						H[2].operationThis(c11, H[3]);
						H[2].operationThis(c12, H[3].inverse());
						//ArrayList<element> cs = new ArrayList<element>();
						cs.add(c1);
						cs.add(c2);
						cs.add(c3);
						cs.add(c4);
						cs.add(c5);
						cs.add(c6);
						cs.add(c7);
						cs.add(c8);
						cs.add(c9);
						cs.add(c10);
						cs.add(c11);
						cs.add(c12);
						
						for(element e : cs)
						{
							if(subgroups.get(0).contains(e))
							{
								results.get(0).add(e);
							}
							else if(subgroups.get(1).contains(e))
							{
								results.get(1).add(e);
							}
							else if(subgroups.get(2).contains(e))
							{
								results.get(2).add(e);
							}
							else if(subgroups.get(3).contains(e))
							{
								results.get(3).add(e);
							}
							else if(subgroups.get(4).contains(e))
							{
								results.get(4).add(e);
							}
							else if(subgroups.get(5).contains(e))
							{
								results.get(5).add(e);
							}
							
							/**
							else if(subgroups.get(6).contains(e))
							{
								results.get(6).add(e);
							}
							else if(subgroups.get(7).contains(e))
							{
								results.get(7).add(e);
							}
							
							else if(subgroups.get(8).contains(e))
							{
								results.get(8).add(e);
							}
							else if(subgroups.get(9).contains(e))
							{
								results.get(9).add(e);
							}
							else if(subgroups.get(10).contains(e))
							{
								results.get(10).add(e);
							}
							else if(subgroups.get(11).contains(e))
							{
								results.get(11).add(e);
							}
							**/
						}
						
						for(ArrayList<element> list : results)
						{
							if(list.size() == 0 || (list.size() == 1 && list.get(0).
									equals(identity)))
							{
								valid = true;
								break;
							}
						}
						
						if(!valid)
						{
							int[] unique = {0,0,0,0,0,0};
							for(int y = 0; y < 6; y++)
							{
								ArrayList<element> temp = subgroups.get(y);
								if(temp.contains(H[0]))
								{
									unique[y]++;
								}
								if(temp.contains(H[1]))
								{
									unique[y]++;
								}
								if(temp.contains(H[2]))
								{
									unique[y]++;
								}
								if(temp.contains(H[3]))
								{
									unique[y]++;
								}
							}
							
							for(int z = 0; z < 6; z++)
							{
								if(unique[z] == 2)
								{
									valid = true;
								}
							}
							
							if(!valid)
							{
								System.out.println(H[0] + " " + H[1] + " " + H[2] + " " + H[3]);
								for(element c : cs)
								{
									System.out.print(c + " ");
								}
								
								System.out.println("");
								
								for(ArrayList<element> sub : subgroups)
								{
									for(element e : sub)
									{
										System.out.print(e);
									}
									
									System.out.println("");
								}
								
								System.out.println("");
								
								for(ArrayList<element> list : results)
								{
									for(element e : list)
									{
										System.out.print(e);
										
									}
									System.out.println("");
								}
								System.out.println("");
								break outmost;
							}
						}
						
						cs.removeAll(cs);
						for(ArrayList<element> list : results)
						{
							list.removeAll(list);
						}
					}
				}
			}
		}
		
		System.out.println(valid);
	}
	
	private static void eliminate(ArrayList<element> list)
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
