package draftForOS.src.draftForOS.OS.src.copy;


import java.util.*;

public class resource {
	 int count;
	resourceT name;
	 String ownerID;
	  Queue<prosses> bloked = new LinkedList<prosses>();
	

	public Queue<prosses> getBloked() {
		return bloked;
	}

	public void setBloked(Queue<prosses> bloked) {
		this.bloked = bloked;
	}

	
	public resource(resourceT name) {

		this.name = name;

	}

	public resourceT getName() {
		return name;
	}

	public void setName(resourceT name) {
		this.name = name;
	}

	public int getCount() {
		if (this.ownerID != null) {
			return 0;
		} else
			return 1;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public void setCountZ() {
		this.count = 0;

	}

}