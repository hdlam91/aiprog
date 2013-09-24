package GPS;
import java.util.ArrayList;


public class GeneralSA {
		private double T; // The "tempature"
		private double dT; //How much we'll reduce T each iteration
		private int fTarget; //Optimal number of eggs
		private int k; //Number of eggs in each row/column/diagonal
		private int gridLength; //Length and width of the grid
		private Object initState; //Starting state for the problem
		private GeneralNode currentGeneralNode; //The current GeneralNode.
		
		public GeneralSA (int gridLength, int k) {
			fTarget = gridLength*k;
			this.k = k;
			this.gridLength = gridLength;
			initState = createInitState();
			//Begin at a start point P (either user-selected or randomly-generated).
			currentGeneralNode = new GeneralNode(initState);
			//Set the temperature, T, to it's starting value: Tmax
			T = 1;
			dT = 0.0001;
			
		}
		

		//Solves the problem.
		//Will run until either a optimal solution is found, or T == 0.
		public Object solve () {
			ArrayList<GeneralNode> children;
			GeneralNode newGeneralNode;
			double q;
			double p;
			double x;
			//While the tempature is not zero, search for a solution. 
			while(T > 0) {
				//If F(P) == Ftarget then EXIT and return P as the solution; else continue
				if (currentGeneralNode.getF() == fTarget) {
					return currentGeneralNode.getState();
				}
				//Generate n neighbors of P in the search space: (P1, P2, ..., Pn).
				children = createChildren(currentGeneralNode);
				//Let Pmax be the neighbor with the highest evaluation
				newGeneralNode = findBestChild(children);
				//Let q = (F(Pmax)-F(P))/F(P)
				q = (newGeneralNode.getF()-currentGeneralNode.getF())/currentGeneralNode.getF();
				//Let p = min [1, e^(-q/T)]
				p = Math.min(1.0, Math.pow(Math.E, (-q)/T));
				//Generate x, a random real number in the closed range [0,1]
				x = Math.random();
				//If x > p then P  <-- Pmax ;; ( Exploiting )
				if (x > p) currentGeneralNode = newGeneralNode;
				//else P <--  a random choice among the n neighbors. ;; (Exploring)
				else {
					int random = (int)(Math.random()*children.size());
					currentGeneralNode = children.get(random);
				}
				T  -= dT;
			}
			System.out.println("T is zero, no optimal solution found");
			return currentGeneralNode.getState();
		}

		
		//Finds child with the highest objective function value.
		private GeneralNode findBestChild(ArrayList<GeneralNode> children) {
			GeneralNode returnGeneralNode = children.get(0);
			for (int i = 0; i < children.size(); i++) {
				if (returnGeneralNode.getF() < children.get(i).getF()) returnGeneralNode = children.get(i);
			}
			return returnGeneralNode;
		}

		//Creates children. Implementation depends on the problem.
		private ArrayList<GeneralNode> createChildren(GeneralNode currentGeneralNode2) {
			// TODO Auto-generated method stub
			return null;
		}
		
		//Creates the intital state of the problem. Implementation depends on the problem.
		private Object createInitState() {
			// TODO Auto-generated method stub
			return null;
		}
}
