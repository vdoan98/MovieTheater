

public class LinkedNode {

	private LinkedNode previous;
	private LinkedNode next;
    private Object item;
    
    /**
	 * @param next
	 * @param item
	 */
	public LinkedNode(Object item, LinkedNode previous, LinkedNode next) {
		super();
		this.previous = previous;
		this.next = next;
		this.item = item;
	}

	public LinkedNode(Object item) {
        this.item = item;
        this.previous = null;
        this.next = null;
    }

    public Object getNodeItem() {
        return item;
    }

    public LinkedNode getNext() {
        return next;
    }
    
    public LinkedNode getPrevious(){
    	return previous;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }
    
    public void setPrevious(LinkedNode previous){
    	this.previous = previous;
    }
}
