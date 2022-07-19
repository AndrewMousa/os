package draftForOS.src.draftForOS.OS.src.copy;


public class PCB {
	String id;
	State State;
	int pc;
	int memBoundaries;
	
	public PCB(String id, State State, int pc, int memBoundaries) {
		this.id=id;
		this.State=State;
		this.pc=pc;
		this.memBoundaries = memBoundaries;
	}
	

}
