package GPS;

public class CBLocalSearch {
	private StateManager manager;
	
	private StateManager mode(int type){
		switch(type){
		case 0:
			return new QueenManager();
		case 1:
			return new GraphManager();
		default:
			return null;
		}
	}
	
	public CBLocalSearch(int type){
		this.manager = mode(type);
	}
	
	public static void main(String[] args) {
		System.out.println("stuff");
	}
}
