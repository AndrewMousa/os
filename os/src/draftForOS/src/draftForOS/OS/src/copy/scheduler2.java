package draftForOS.src.draftForOS.OS.src.copy;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class scheduler2 {
    static Interpreter inter;
    static int clock;
    Mutex m;
    int Pc = 1;
    public static Queue<prosses> blocked = new LinkedList<prosses>();
    public static Queue<prosses> ready = new LinkedList<prosses>();
   static Memory mem = new Memory();
    public static Queue<prosses> addProcess() throws IOException {
        prosses p1 = new prosses("program_1.txt", 0);
        prosses p2 = new prosses("program_2.txt", 0);
        prosses p3 = new prosses("program_3.txt", 0);
        p1.setArrivalTime(0);
        p2.setArrivalTime(1);
        p3.setArrivalTime(4);
        p1.createBlock("program_1.txt");
        p2.createBlock("program_2.txt");
        p3.createBlock("program_3.txt");
     /*   for(int an = 0 ;an<p1.block.length;an++) {
        	System.out.println(p1.block[an]);
        } 
        for(int an = 0 ;an<p2.block.length;an++) {
        	System.out.println(p2.block[an]);
        }   
        for(int an = 0 ;an<p3.block.length;an++) {
        	System.out.println(p3.block[an]);
        } */
        Queue<prosses> queue = new LinkedList<>();
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);
        
       
       
//        System.out.println(p2.block.toString());
//        System.out.println(p3.block.toString());
        return queue;

    }

    public static void schedule(Queue<prosses> ready) throws IOException {
    
        Queue<prosses> pq = addProcess();
     //////////////////////////////////// enter from wating to ready for first time 
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the quantum time");
        int qt = sc.nextInt();
        for (int i = 0; i < pq.size(); i++) {
            if (clock == pq.peek().arrivalTime) {
            	Memory.addToMem(pq.peek().block);
//            	System.out.println(pq.peek().id + " is added to the memory from the disk ");
//            	System.out.println(Memory.readFromDisk()[0] + " is add to the disk from memory ");
            	pq.peek().pcb.State= State.READY ;
            	if (Memory.memory[0].equals(pq.peek().id))
            	{
            		pq.peek().pcb.memBoundaries = 0 ;
            		Memory.memory[1] = State.READY.toString();	
            		Memory.memory[3] = 0 + "" ;
            	}
            	else
            	{
            		Memory.memory[21] = State.READY.toString();	
            		pq.peek().pcb.memBoundaries = 20 ;
            		Memory.memory[23] = 20 + "" ;
            	}
                ready.add(pq.remove());
              
            } else {
                pq.add(pq.remove());
            }

        }
////////////////////////// taking the qtt
      
        
/////////////////////////// the main while loop
        while (!ready.isEmpty()) {
        	
            for (prosses elem : ready) { //////////////////////// to know who are in the ready queue
                System.out.println("     My ready queue is " + elem.getId());
            }

            for (int i = 0; i < qt; i++) {  /// execute for the qt
            	 
            	System.out.println("-----------------------------");
                System.out.println(ready.peek().id + " is chosen and executing ");
                String r = ready.peek().id;
                if (!ready.isEmpty()) {
                    ready.peek().setLine(ready.peek().getLine() + 1);
                }
          //      Memory.addToMem(ready.peek().block);
                ready.peek().pcb.State = State.EXECUTING;
                ready.peek().pcb.pc = ready.peek().getLine();
                if (ready.peek().id.equals(Memory.memory[0]))
            	{
                	ready.peek().pcb.memBoundaries = 0 ;
            		Memory.memory[1] = State.EXECUTING.toString();	
            		Memory.memory[2] = ""+ready.peek().getLine();
            		Memory.memory[3] = 0 + "" ;
            	}
            	else
            	{
            		if (Memory.memory[20].equals(ready.peek().id))
            		{
            			ready.peek().pcb.memBoundaries = 20 ;
            		Memory.memory[21] = State.EXECUTING.toString();	
            		Memory.memory[22] = ""+ready.peek().getLine();
            		Memory.memory[23] = 20 + "" ;
            		}
            	}
                if (ready.peek().isFinished) {  // if finished during qt 
                	System.out.println(ready.peek().id + " is finished");
                	ready.peek().pcb.State = State.FINISHED ;
                    if (Memory.memory[0].equals(ready.peek().id))
                	{
                    	ready.peek().pcb.memBoundaries = 0 ;
                    	Memory.memory[2] = ""+ready.peek().getLine();
                		Memory.memory[1] = State.FINISHED.toString();	
                		Memory.memory[3] = 0 + "" ;
                	}
                	else
                	{
                		ready.peek().pcb.memBoundaries = 20 ;
                		Memory.memory[22] = ""+ready.peek().getLine();
                		Memory.memory[21] = State.FINISHED.toString();	
                		Memory.memory[23] = 20 + "" ;
                	}
                    
                    ready.remove();
                }
                System.out.println("ready.peek is " + ready.peek().id + " And state " + ready.peek().pcb.State.toString());
                System.out.println("*******(BEFORE)*********");
                
                for(int an = 0 ;an<Memory.memory.length;an++) {
                	System.out.println(Memory.memory[an]);
                } 
              
               System.out.println("*********************");
               
               
               
                Interpreter.parse(ready.peek(), ready.peek().id, ready.peek().getLine()); /////// +1
          
                
                
                System.out.println("*******(AFTER)*********");
                
                for(int an = 0 ;an<Memory.memory.length;an++) {
                	System.out.println(Memory.memory[an]);
                } 
                System.out.println("*********************");
              
                System.out.println(ready.peek().id + " is now "  + ready.peek().pcb.State.toString() + " inside memory");
              //  int l = sc.nextInt();

                for (prosses elem : ready) {
                    System.out.println("My ready queue is " + elem.getId());
                }
                for (prosses elem : blocked) {
                    System.out.println("My blocked queue is " + elem.getId());
                }
                if (ready.peek().isFinished) {  // if finished during qt 
                	System.out.println(ready.peek().id + " is finished");
                	ready.peek().pcb.State = State.FINISHED ;
                    if (Memory.memory[0].equals(ready.peek().id))
                	{
                    	ready.peek().pcb.memBoundaries = 0 ;
                    	Memory.memory[2] = ""+ready.peek().getLine();
                		Memory.memory[1] = State.FINISHED.toString();	
                		Memory.memory[3] = 0 + "" ;
                	}
                	else
                	{
                		ready.peek().pcb.memBoundaries = 20 ;
                		Memory.memory[22] = ""+ready.peek().getLine();
                		Memory.memory[21] = State.FINISHED.toString();	
                		Memory.memory[23] = 20 + "" ;
                	}
                    
                    ready.remove();
                }
               
                
                if(ready.isEmpty()) {
                	System.out.println("All programs are finished");
                	System.exit(1);
                }
                String rr = ready.peek().id;
                if (!r.equals(rr)) {
                    i = -1;
                    if (ready.peek().id.equals(Memory.readFromDisk()[0]))
        			{
        			Memory.addToMem(Memory.readFromDisk());
//        			System.out.println(pq.peek().id + " is added to the memory from the disk ");
//                	System.out.println(Memory.readFromDisk()[0] + " is added to the disk from memory ");
        			//System.out.println("The id of the swapped process is " + ready.peek().id);
        			
        			if (ready.peek().id.equals(Memory.memory[0]))
                	{
                		Memory.memory[1] = State.EXECUTING.toString();	
                		ready.peek().pcb.memBoundaries = 0 ;
                		Memory.memory[3] = 0 + "" ;
                	}
                	else
                		if(Memory.memory[20].equals(ready.peek().id))
                	{
                		Memory.memory[21] = State.EXECUTING.toString();	
                		ready.peek().pcb.memBoundaries = 20 ;
                		Memory.memory[23] = 20 + "" ;
                	}
        			}
                }
                System.out.println("Value of i is " + i);

                System.out.println("my clk is " + clock);
                
                for (int j = 0; j < pq.size(); j++) { // add to ready queue
                    if (clock == pq.peek().getArrivalTime()) {
                    	Memory.addToMem(pq.peek().block);
                      //  pq.peek().pcb.State=State.READY;
                
                        System.out.println("ready peek  " + ready.peek().id);
                        ready.add(pq.remove());
                        for (prosses elem : ready) {
                            System.out.println("My ready queue is " + elem.getId());
                        }

                    } else {
                        pq.add(pq.remove());
                    }

                }
              
            }
           
            ready.peek().pcb.State = State.READY ;
            ready.peek().pcb.pc = ready.peek().getLine();
            if (Memory.memory[0].equals(ready.peek().id))
        	{
            	Memory.memory[2] = ""+ready.peek().getLine();
        		Memory.memory[1] = State.READY.toString();
        		ready.peek().pcb.memBoundaries = 0 ;
        		Memory.memory[3] = 0 + "" ;
        	}
        	else
        	{
        		if ((Memory.memory[20].equals(ready.peek().id)))
        		{
        		Memory.memory[22] = ""+ready.peek().getLine();
        		Memory.memory[21] = State.READY.toString();	
        		ready.peek().pcb.memBoundaries = 20 ;
        		Memory.memory[23] = 20 + "" ;
        		}
        	}
            
            ready.add(ready.remove());
            
            
            ready.peek().pcb.State = State.EXECUTING ;
            if (Memory.memory[0].equals(ready.peek().id))
        	{
        		Memory.memory[1] = State.EXECUTING.toString();
        		ready.peek().pcb.memBoundaries = 0 ;
        		Memory.memory[3] = 0 + "" ;
        	}
        	else
        		if(Memory.memory[20].equals(ready.peek().id))
        	{
        		Memory.memory[21] = State.EXECUTING.toString();	
        		ready.peek().pcb.memBoundaries = 20 ;
        		Memory.memory[23] = 20 + "" ;
        	}
        		else
        		{
        			if (ready.peek().id.equals(Memory.readFromDisk()[0]))
        			{
        			Memory.addToMem(Memory.readFromDisk());
        			//System.out.println("The id of the swapped process is " + ready.peek().id);
//        			System.out.println(pq.peek().id + " is added to the memory from the disk ");
//                	System.out.println(Memory.readFromDisk()[0] + " is add to the disk from memory ");
        			
        			if (ready.peek().id.equals(Memory.memory[0]))
                	{
                		Memory.memory[1] = State.EXECUTING.toString();	
                		ready.peek().pcb.memBoundaries = 0 ;
                		Memory.memory[3] = 0 + "" ;
                	}
                	else
                		if(Memory.memory[20].equals(ready.peek().id))
                	{
                		Memory.memory[21] = State.EXECUTING.toString();	
                		ready.peek().pcb.memBoundaries = 20 ;
                		Memory.memory[23] = 20 + "" ;
                	}
        			}
        			else
        			{
        				continue ;
        			}
        		}
            
           

        }

    }

    public static void main(String[] args) throws IOException {
        addProcess();
    	
    	schedule(ready); 
    }
}
