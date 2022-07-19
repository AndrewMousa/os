package draftForOS.src.draftForOS.OS.src.copy;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prosses {
	String id;
	int line;
	int arrivalTime;
	ArrayList<String> instArray = new ArrayList<>();
	boolean isFinished;
	boolean Done;
	PCB pcb;
    String[] block = new String[20];
	
	
	
	
	
	public prosses(String id, int line) {
		this.id = id;
		this.line = line;
        this.pcb = new PCB ("",State.READY,0,1);
		
	}
	
	
	public void createBlock(String id) throws IOException {
		PCB b = new PCB(id,State.READY,0,0);
//		pcb.id = id;
//		pcb.State = State.READY;
//		pcb.pc = 0;
//		pcb.memBoundaries = 0;
		
		block[0] = b.id;
		block[1] = b.State.toString();
		block[2] = "" +b.pc;
		block[3] = "" + b.memBoundaries;
		block[4] = "NA";
		block[5] = "NA";
		block[6] = "NA";
		
		 FileReader reader = new FileReader(id);
		 Scanner sc = new Scanner(reader);
		 String res = "";
		 int i = 7;
		 
		 while (sc.hasNextLine()) {
			  res = sc.nextLine();
              block[i] = res;
              i++;

          }
		  while(i<block.length) {
			  block[i] = "Empty";
			  i++;
		  }
		
	}


	public boolean isFinished() {
		return isFinished;
		
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public static void main(String[] args) {

	}

}
