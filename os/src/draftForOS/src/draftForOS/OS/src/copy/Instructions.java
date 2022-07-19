package draftForOS.src.draftForOS.OS.src.copy;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Instructions { /////////////// <T>
    static HashMap<String, Integer> intMap = new HashMap<>();
    static HashMap<String, String> smap = new HashMap<>();
    static boolean flagAssign = false;
    static String variable;
    static String[] var = new String[3];

    // --------------------------------------------------------
    // print method
    public static void print(String x) {
//        String n = smap.get(x);
//        if (n != null) {
//            System.out.println(n);
//        } else {
//
//            int m = intMap.get(x);
//            System.out.println(m);
//        }
    	if(Memory.memory[1].equals("EXECUTING")) {
    	String s = Memory.memory[5];
    	for(int i = 2; i < s.length();i++) {
    		System.out.print(s.charAt(i));
    		
    		
    	}
    	}else if(Memory.memory[21].equals("EXECUTING")) {
    		String s = Memory.memory[5];
        	for(int i = 2; i < s.length();i++) {
        		System.out.print(s.charAt(i));
        		
        		
        	}
    	}

    }

    // --------------------------------------------------------
    // printFromTo method
    public static void printFromTo(String x, String y) {
        // int num1 = Integer.parseInt(x);
        // int num2 = Integer.parseInt(y);
    	
    		if(Memory.memory[1].equals("EXECUTING")) {
    			
    		
    	
    		String x1 = Memory.memory[4];
    		char x2 = x1.charAt(2);
    		int m = Character.getNumericValue(x2);
    		
    		String var2 = Memory.memory[5];
    		char x3 = var2.charAt(2);
    		int n = Character.getNumericValue(x3);
    		int z = m+1;
    	
    		
//        int m = intMap.get(x);
//        int n = intMap.get(y);
//        int z = m + 1;
        for (int i = z; i < n; i++) {
            System.out.println(i);

        }}
    		else if(Memory.memory[21].equals("EXECUTING")) {

        		String x1 = Memory.memory[24];
        		char x2 = x1.charAt(2);
        		int m = Character.getNumericValue(x2);
        		
        		String var2 = Memory.memory[25];
        		char x3 = var2.charAt(2);
        		int n = Character.getNumericValue(x3);
        		int z = m+1;
        	
        		
//            int m = intMap.get(x);
//            int n = intMap.get(y);
//            int z = m + 1;
            for (int i = z; i < n; i++) {
                System.out.println(i);

            }}	
    		}
        
    		

    // ---------------------------------------------------------
    // assign method
    public static <T> T assign(String x, T y) throws IOException {
        String[] split = stringtoarray(y);
        if (split[0].equals("input")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter a value");
            y = (T) sc.nextLine();
            for(int i = 0 ; i < var.length ; i++) {
            	if(var[i] == "")
            	{
            		 var[i] = x +" : " +y;
            	}
            	else continue;
            }
            
//           variable = x +" : " +y;
            //////////////////////////

            ////////////////////////////

        } else if (split[0].equals("readFile")) {
            y = (T) readFile(smap.get(split[1]));
            ///////////////////////////////

            //////////////////////////////
        }
        /// ----------------------
        if (scheduler2.ready.peek().id .equals(Memory.memory[0]))
        {
            if ("NA".equals(Memory.memory[4]))
            {
                Memory.memory[4] = x + "=" + y ;
            }
            else
                if ("NA".equals(Memory.memory[5]))
                {
                    Memory.memory[5] = x + "=" + y ;
                }
                else 
                    if ("NA".equals(Memory.memory[6]))
                    {
                        Memory.memory[6] = x + "=" + y ;
                    }
                    else {
                        Memory.memory[4] = x + "=" + y ;
                    }
        }
        else {
        if (scheduler2.ready.peek().id .equals(Memory.memory[20]))
        {
            if ("NA".equals(Memory.memory[24]))
            {
                Memory.memory[24] = x + "=" + y ;
            }
            else
                if ("NA".equals(Memory.memory[25]))
                {
                    Memory.memory[25] = x + "=" + y ;
                }
                else 
                    if ("NA".equals(Memory.memory[26]))
                    {
                        Memory.memory[26] = x + "=" + y ;
                    }
                    else {
                        Memory.memory[24] = x + "=" + y ;
                    }
        }

        }
        if (isInteger(y)) {
            int m = Integer.parseInt((String) y);
            intMap.put(x, m);
            return (T) intMap.get(x);
        } else {
            smap.put(x, (String) y);
            return (T) smap.get(x);
        }
        
        // System.out.println(intMap.get(x));
        // return map.get(x);

    }

    public static void writeFile(String fileName, String Data) throws IOException {
        // BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        // writer.write(Data);
        // System.out.println(Data);
        // System.out.println("File is created successfully with the content.");
        // writer.close();
        FileWriter file = new FileWriter(fileName, true);
        BufferedWriter b = new BufferedWriter(file);
        // b.newLine();
        b.write("\n" + Data);
        // b.newLine();
        b.close();
        file.close();

    }

    public static void writeFile2(String fileName, String data) throws IOException {

        // create a file object for the current location
        File file = new File(fileName);

        try {

            // create a new file with name specified
            // by the file object
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("New Java File is created.");
            } else {
                System.out.println("The file already exists.");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        // String program = "class JavaFile { " +
        // "public static void main(String[] args) { " +
        // "System.out.println(\"This is file\");"+
        // "}"+
        // "}";
        try {
            // Creates a Writer using FileWriter
            FileWriter output = new FileWriter(fileName);

            // Writes the program to file
            output.write(data);
            System.out.println("Data is written to the file.");

            // Closes the writer
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = reader.readLine();
        }
        String everything = sb.toString();
        // System.out.println(everything);
        reader.close();
        return everything;

    }

    public static <T> boolean isInteger(T y) {
        try {
            Integer.parseInt((String) y);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> String[] stringtoarray(T y) {
        String[] a = ((String) y).split(" ");

        return a;
    }

    public static void main(String[] args) throws IOException {
        // readFileI("Program_1.txt");
        // readFile("Program_1.txt");
        // writeFile("mm.txt", "sarah");
        // print("my");
        // printFromTo(2, 5);
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Please enter a value");
        // String res = sc.nextLine();
        // assign(2, "MO");
        // Mutex M1 = new Mutex();
        // Mutex M2 = new Mutex();
        // Mutex M3 = new Mutex();
        // writeFile2("mm.txt", "momo2");
        writeFile2("kokokkkkk.txt", "momo7");
        // String num1 = Instructions.assign("a", "input");
        // int a = Integer.parseInt(num1);
        // System.err.println(a);
    }

}
