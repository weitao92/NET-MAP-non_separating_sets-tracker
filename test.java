import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class test {
	
	/**
	 * using by change parameters in c.run(), first parameter should be the order of first group.
	 * second parameter should be order of first group. Third parameter is mode, 1 stands for find
	 * non_separating_set, 2 stands for complete obstructed set.
	 * 
	 * For example if you try to find all the non_separating_sets in Z30 * Z30, then use c.run(30,30,1)
	 * 
	 * if you try to run the classifier, uncomment the bottom part code and go to disjointedUnion class and 
	 * change parameter there.
	 * 
	 * The program will generate all the nonseparating_sets and write them into a file called
	 * "non-separating-set.txt".
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException
	{				
		checker c = new checker();
		long start = System.currentTimeMillis();
		c.run(4,8,1);
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		int elapsedTimeHour = (int) (elapsedTimeMillis/(60*60*1000F));
		int elapsedTimeMin = (int) ((elapsedTimeMillis - elapsedTimeHour * (60*60*1000F))/(60*1000F));
		int elapsedTimeSec = (int) ((elapsedTimeMillis - elapsedTimeHour * (60*60*1000F)
				- elapsedTimeMin * (60*1000F))/1000F);
		System.out.println("the number of non-seperating set is: " + c.NSS);
		System.out.println("The program took: " + elapsedTimeHour + "hours, " + elapsedTimeMin
				 + "mins, " + elapsedTimeSec + "secs.");
		
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
		
		/**
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
		**/
		writer.close();		
	}
}
