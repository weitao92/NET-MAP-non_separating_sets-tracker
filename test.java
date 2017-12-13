import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class test {
	
	public static void main(String args[]) throws IOException
	{		
		/**
		element e = new element(10,10,1,4);
		quotientGroup G = new quotientGroup();
		G.order = qg.order;
		G.identity = qg.identity;
		G.a = e;
		G.list.add(G.identity);
		coset c = G.identity;
		for(int i = 1; i <= qg.order; i++)
		{
			c = c.operation(e, 1);
			c.left = e;
			G.list.add(c);
		}
		for(coset c1 : G.list)
		{
			System.out.print(c1);
			System.out.println(" with a: " + c1.left + " has order of " + c1.order(qg));
		}
		
		element e1 = new element(10,10,5,8);
		quotientGroup G1 = new quotientGroup();
		G1.order = qg.order;
		G1.identity = qg.identity;
		G1.a = e;
		G1.list.add(G1.identity);
		coset c1 = G.identity;
		for(int i = 1; i <= qg.order; i++)
		{
			c1 = c1.operation(e1, 1);
			c1.left = e1;
			G1.list.add(c1);
		}
		for(coset c11 : G.list)
		{
			System.out.print(c11);
			System.out.println(" with a: " + c11.left + " has order of " + c11.order(qg));
		}
		System.out.println(G1.equals(G));
		**/
		
		checker c = new checker();
		long start = System.currentTimeMillis();
		c.run(6,6,1);
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		int elapsedTimeHour = (int) (elapsedTimeMillis/(60*60*1000F));
		int elapsedTimeMin = (int) ((elapsedTimeMillis - elapsedTimeHour * (60*60*1000F))/(60*1000F));
		int elapsedTimeSec = (int) ((elapsedTimeMillis - elapsedTimeHour * (60*60*1000F)
				- elapsedTimeMin * (60*1000F))/1000F);
		System.out.println("the number of non-seperating set is: " + c.NSS);
		System.out.println("The program took: " + elapsedTimeHour + "hours, " + elapsedTimeMin
				 + "mins, " + elapsedTimeSec + "secs.");
		//System.out.println("the number of seperating set is: " + c.SS);
		
		File text = new File("non-separating-set.txt");
		FileWriter writer = new FileWriter(text);
		
		if(c.NSS != 0)
		{
			for(non_separating_set s : c.successful)
			{
				
				writer.write(s.toString());

				writer.write("\n");
			}
		}
		
		disjointedUnion u = new disjointedUnion(c.successful);
		u.generate();
		System.out.println(u.distinct.size());
		for(disjointedUnion.equivalence e : u.distinct)
		{
			System.out.println(e.root + " is the root, the number of NSS in this equivalence class"
					+ " is: " + e.num);
			
			for(non_separating_set nss : e.sets)
			{
				System.out.print(nss + " ");
			}
			System.out.println();
			System.out.println();
		}
		
		/**
		mapping_48 map = new mapping_48();
		//System.out.println(map.phase2.size());
		
		element[] H1 = new element[4];
		H1[0] = new element(4,8,0,0);
		H1[1] = new element(4,8,0,2);
		H1[2] = new element(4,8,0,4);
		H1[3] = new element(4,8,2,2);
		non_separating_set h1 = new non_separating_set(H1);
		element[] H2 = new element[4];
		H2[0] = new element(4,8,0,0);
		H2[1] = new element(4,8,1,0);
		H2[2] = new element(4,8,1,4);
		H2[3] = new element(4,8,2,0);
		non_separating_set h2 = new non_separating_set(H2);
		element[] H3 = new element[4];
		H3[0] = new element(4,8,0,2);
		H3[1] = new element(4,8,1,1);
		H3[2] = new element(4,8,1,5);
		H3[3] = new element(4,8,2,2);
		non_separating_set h3 = new non_separating_set(H3);
		
		map.generate(h3);
		map.display();
		System.out.println(map.set.size());
		System.out.println(map.contains(h2));
		**/
		writer.close();		
	}
}
