package draftForOS.src.draftForOS.OS.src.copy;

import java.io.*;
import java.time.Clock;
import java.util.*;
//import java.nio.file.Path;

public class Interpreter {

    static Queue<prosses> blocked = new LinkedList<prosses>();
    static Queue<prosses> ready = new LinkedList<prosses>();
    static boolean Done = false;

    public Interpreter(Queue<prosses> blocked, Queue<prosses> ready) {
        this.blocked = blocked;
        this.ready = ready;
    }

    public static <T> void parse(prosses p, String fileName, int instruction) throws IOException {
        FileReader reader = new FileReader(fileName);

        Mutex m = new Mutex(p); 

        Scanner sc = new Scanner(reader);

        String res = "";
        String[] result;
        String instCell;
        
        if (p.instArray.isEmpty()) {
            while (sc.hasNextLine()) {

                res = sc.nextLine();
                p.instArray.add(res);

            }
            p.instArray.add("done");
            
        }

       

        
//        if (instruction == p.instArray.size()) {
//            p.isFinished = true;
//        }

        
        
        instCell = p.instArray.get(instruction-1);

        result = Instructions.stringtoarray(instCell);
       

        if (result[0].equals("semWait")) {
            System.out.println(p.id + " is executing instruction " + result[0] + " clock is " + scheduler2.clock);
            switch (result[1]) {
                case "userInput":
                    m.semWait("userInput");

                    break;
                case "userOutput":
                    m.semWait("userOutput");
                    break;
                case "file":
                    m.semWait("file");
                    break;

            }

            scheduler2.clock++;

            // m.semWait(result[1]);
        }

        else if (result[0].equals("semSignal")) {
            System.out.println(p.id + " is executing instruction " + result[0] + " clock is " + scheduler2.clock);
            switch (result[1]) {
                case "userInput":
                    m.semSignal("userInput");

                    break;
                case "userOutput":
                    m.semSignal("userOutput");
                    break;
                case "file":
                    m.semSignal("file");
                    break;

            }
            scheduler2.clock++;

            // m.semWait(result[1]);
        }

        else if (result[0].equals("assign")) {
            System.out.println(p.id + " is executing instruction " + result[0] + " clock is " + scheduler2.clock);
            String value;
            if (result[2].equals("readFile")) {
                result[2] = result[2] + " " + result[3];
            }
            ////////////////////////////////////
            if (Instructions.flagAssign == true) {
                value = Instructions.variable;
            }

            ///////////////////////////////////
            Instructions.assign(result[1], result[2]);
            scheduler2.clock++;

        }

        else if (result[0].equals("printFromTo")) {
            System.out.println(p.id + " is executing instruction " + result[0] + " clock is " + scheduler2.clock);
            Instructions.printFromTo(result[1], result[2]);
            // Instructions.printFromTo(,);
            scheduler2.clock++;

        }

        else if (result[0].equals("writeFile")) {
            System.out.println(p.id + " is executing instruction " + result[0] + " clock is " + scheduler2.clock);
            // ne call el method 3ala process wla keda??????????????????????????
            Instructions.writeFile(Instructions.smap.get(result[1]), Instructions.smap.get(result[2]));
            scheduler2.clock++;

        }

        else if (result[0].equals("print")) {
            System.out.println(p.id + " is executing instruction " + result[0] + " clock is " + scheduler2.clock);
            Instructions.print(result[1]);
            scheduler2.clock++;

        }

        else {

        	 if (instruction == p.instArray.size()) {
                 p.isFinished = true;
             }

            System.out.println("No more lines");
        }


        sc.close();

    }
   

    public static void main(String[] args) throws FileNotFoundException, IOException {
       
    }

}
