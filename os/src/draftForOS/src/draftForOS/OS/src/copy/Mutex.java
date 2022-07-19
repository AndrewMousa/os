package draftForOS.src.draftForOS.OS.src.copy;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Mutex {
    
     static Queue<resource> AllIhave = new LinkedList<resource>();

    static prosses p;

   
    public Mutex(prosses p) {
        this.p = p;

    }

    public void semWait(String name) {
    	
        System.out.println("the process count is " + addrec(name).getCount());

        if (addrec(name).getCount() == 1 || addrec(name).getOwnerID().equals(p.id)) {
        	addrec(name).setCountZ();
        	addrec(name).setOwnerID(p.id);
        } else {
        	
        	addrec(name).bloked.add(p);
            scheduler2.blocked.add(p);
            scheduler2.ready.remove();
            p.pcb.State=State.BLOCKED;
            if (Memory.memory[0].equals(p.id))
        	{
        		Memory.memory[1] = State.BLOCKED.toString();
        		scheduler2.ready.peek().pcb.memBoundaries = 0 ;
        		Memory.memory[3] = 0 + "" ;
        	}
        	else
        		if(Memory.memory[20].equals(p.id))
        	{
        		Memory.memory[21] = State.BLOCKED.toString();	
        		scheduler2.ready.peek().pcb.memBoundaries = 20 ;
        		Memory.memory[23] = 20 + "" ;
        	}
            
            System.out.println("process: " + p.getId() + " is blocked");
        }

        System.out.println("The size of blocked is " + addrec(name).getBloked().size());

       

        System.out.println("My resource is " +addrec(name).getName().toString() + "  and the owner is " + addrec(name).getOwnerID()
                + " (semWait) ");

    }

    public void semSignal(String name) throws IOException {
    	 
        if (addrec(name).getOwnerID().equals(p.id)) {
        	
            if (addrec(name).bloked.isEmpty()) {
                  
            	addrec(name).setOwnerID(null);
            	addrec(name).setCount(1);
            } else {
            	scheduler2.blocked.peek().pcb.State=State.READY;
            	 if (Memory.memory[0].equals(scheduler2.blocked.peek().id))
             	{
             		Memory.memory[1] = State.READY.toString();
             		scheduler2.blocked.peek().pcb.memBoundaries = 0 ;
             		Memory.memory[3] = 0 + "" ;
             	}
             	else
             		if(Memory.memory[20].equals(scheduler2.blocked.peek().id))
             	{
             		Memory.memory[21] = State.READY.toString();	
             		scheduler2.blocked.peek().pcb.memBoundaries = 20 ;
             		Memory.memory[23] = 20 + "" ;
             	}
             	else
             		{
             			if (Memory.readFromDisk()[0] != null)
             			{

            	Memory.addToMem(Memory.readFromDisk());
                if (Memory.memory[0].equals( scheduler2.blocked.peek().id))
            	{
            		Memory.memory[1] = State.READY.toString();	
            		scheduler2.blocked.peek().pcb.memBoundaries = 0 ;
            		Memory.memory[3] = 0 + "" ;
            	}
            	else
            		if(Memory.memory[20].equals( scheduler2.blocked.peek().id))
            	{
            		Memory.memory[21] = State.READY.toString();	
            		scheduler2.blocked.peek().pcb.memBoundaries = 20 ;
            		Memory.memory[23] = 20 + "" ;
            	}
             			}
             		}
            	                 // scheduler2.ready.peek().pcb.State=State.READY;
                
            	 if (scheduler2.ready.size()==2)
                 {
              	   scheduler2.ready.add(scheduler2.ready.remove());
              	   scheduler2.ready.add(scheduler2.blocked.peek());
                    
                     scheduler2.ready.add(scheduler2.ready.remove());
              	   
                 }
                  
               if ( scheduler2.ready.size()==1)
               {
            	   scheduler2.ready.add(scheduler2.blocked.peek());
               }
              
           //     System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ our ready peek is  "  + scheduler2.blocked.peek().id );
                scheduler2.blocked.remove();
                addrec(name).setOwnerID(addrec(name).bloked.remove().id);
                /* release one from the block list */
            }
        }

        for (prosses elem : addrec(name).bloked) {
            System.out.println(elem.getId());
        }

        System.out.println("My resource is " + addrec(name).getName().toString() + "  and the owner is " + addrec(name).getOwnerID()
                + " (semSignal) ");
    }

     public static resource addrec(String x) {
    	 if(AllIhave.isEmpty()) {

     resource file = new resource(resourceT.file);
     resource userInput = new resource( resourceT.userInput);
     resource userOutput = new resource( resourceT.userOutput);

     AllIhave.add(file);
     AllIhave.add(userInput);
     AllIhave.add(userOutput);
    
      }
    	 for(int i =0; i<AllIhave.size();i++) {
    		 if(AllIhave.peek().getName().toString().equals(x)) {
    			 return AllIhave.peek();
    		 }
    		 else {
    			 AllIhave.add(AllIhave.remove());
    		 }
    		 
    	 }
    	 return AllIhave.peek();
     }
    // public static void main(String[] args) {
    // Process p = new Process(123);

    // }

}
