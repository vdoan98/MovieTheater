package main;
public class LinkedList {
	private LinkedNode start = null;
	private LinkedNode end = null;
	private int size;
	

	/**
	 * 
	 */

	public void clear() {
		start = null;
		end = null;
		size = 0;
	}

	public void addToStart(Object object) {
		LinkedNode aNode = new LinkedNode (object, start, null);
		if (start == null){
			start = aNode;
			end = start;
		}else{
			aNode.setNext(start);
			start.setPrevious(aNode);
			start = aNode;
		}
		
		size++;
	}

	public void addToEnd(Object object) {
		LinkedNode aNode = new LinkedNode(object, null, null);
		size++;
		if (start == null){
			start = aNode;
			end = aNode;
		}else{
			end.setNext(aNode);
			aNode.setPrevious(end);
			end = aNode;
		}
	}

	public boolean remove(int position) {
		
		if(position < 1 || position > size){
			return false;
		}
		
		LinkedNode currentNode = start;
		
		
		if (position == 1){
			start.getNext().setPrevious(null);
			start = start.getNext();
			size--;
			return true;
		}else if(position == size){
			for(int i = 1; i < position - 1; i++){
				if(currentNode.getNext() == null){
					return false;
				}
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(null);
			currentNode.getPrevious().setNext(null);
			size--;
			return true;
		}else{
			LinkedNode previousNode = start;
			for(int i = 1; i < position-1; i++){
				if(previousNode.getNext() == null){
					return false;
				}
				previousNode = previousNode.getNext();
			}
			LinkedNode nodeToBeRemoved = previousNode.getNext();
			LinkedNode nextNode = nodeToBeRemoved.getNext();
			previousNode.setNext(nextNode);
			nextNode.setPrevious(previousNode);
			end = nextNode;
			size--;
			return true;
		}

		
	}

	private LinkedNode getLinkedNode(int position) {
		LinkedNode headNode = start;
		for(int i = 1; i< position; i++){
			if(headNode.getNext() == null){
				return null;
			}
			headNode = headNode.getNext();
		}
		return headNode;
		
	}

	public Object getObject(int position) {
		LinkedNode headNode = start;
		for(int i = 1; i< position; i++){
			if(headNode.getNext() == null){
				return null;
			}
			headNode = headNode.getNext();
		}
		return headNode.getNodeItem();
	}
	
	public int getSize(){
		return this.size;
	}

//	public void print() {
//		TruckListNode currentNode = start;
//		TruckListNode nextNode;
//		System.out.println("Size: " + size);
//		for (int i = 0; i < size; i++){
//			nextNode = currentNode.getNext();
//			System.out.println(currentNode.getTruck().toString());
//			currentNode = nextNode;
//
//		}
//	}
	
//	public void getStart(){
//		this.start.getTruck().toString();
//	}
}
