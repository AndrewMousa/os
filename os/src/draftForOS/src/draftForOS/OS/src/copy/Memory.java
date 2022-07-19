package draftForOS.src.draftForOS.OS.src.copy;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class Memory {
	static String[] memory = new String[40];
	
	
	
	
	
	public static void addToMem(String[] p) throws IOException {
		
    	
		if (p[0] == null)
			return ;
		String [] temp = new String [20];
			if(memory[0] == null) {
			for(int i = 0 ; i<p.length ; i++) {
				memory[i] = p[i];
				}
			System.out.println(p[0] + " is added to the memory ");
		}
		else {
			if(memory[20] == null) {
				for(int i = 0; i<p.length;i++)
				{
					memory[i +20] = p[i];
			    }
				System.out.println(p[0] + " is added to the memory ");
			}
			else {
				if(!State.EXECUTING.toString().equals(memory[1]))
				{
				for(int i = 0 ; i<20 ; i++) {
					temp[i] = memory[i];
					memory[i] = p[i];
					
				}
				System.out.println(p[0] + " is added to the memory ");
				if (temp[0] != null) {
				writeToDisk(temp);
				
            	System.out.println(temp[0] + " is added to the disk from memory ");
				}
			}
			else {
				if (!State.EXECUTING.toString().equals(memory[21]))
				{
					for(int i = 0; i<20;i++)
					{
						temp[i] = memory[i+20];
						memory[i+20] = p[i];
					}
					System.out.println(p[0] + " is added to the memory");
					if (temp[0] != null) {
						writeToDisk(temp);
						
		            	System.out.println(temp[0] + " is added to the disk from memory ");
						}
		
			}
		}
		}}
	}
	
	
	
	 public static String[] readFromDisk() throws IOException {
	        
            String[] array = new String[20];
            FileReader reader = new FileReader("disk.txt");

             

            Scanner sc = new Scanner(reader);
            int i =0;
            String res ="";
            while (sc.hasNextLine() && i<20) {

                res = sc.nextLine();
                array[i] = res;
               
                i++;

            }
	       
            
            
     /*       File file = new File("disk.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
            	
            	*/
	        
	        reader.close();
	        return array;

	    }
	 
	public static void writeToDisk(String[] array) throws IOException {
		
		
		 File f = new File("disk.txt");
         PrintWriter writer = new PrintWriter(f);
         writer.print("");
         writer.close();
         	
         	
        
        FileWriter file = new FileWriter("disk.txt", true);
        BufferedWriter b = new BufferedWriter(file);
        // b.newLine();
        
        for(int i = 0 ;i<array.length ; i++) {
        	b.write(array[i] + "\n");
        	        }
        
        // b.newLine();
        b.close();
        file.close();

    }
	public static void main(String[] args) throws IOException {
		//readFromDisk();
	//String[] array = {"ed" , "ahhh"};
	//	writeToDisk(array);
	}
	
	
	

}
