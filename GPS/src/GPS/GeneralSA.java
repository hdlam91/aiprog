package GPS;
import java.util.ArrayList;


public class GeneralSA extends LocalSearch{
		private double T; // The "tempature"
		private double dT; //How much we'll reduce T each iteration
		private double fTarget;
//		private StateManager manager;
		private State currentState; //The current GeneralNode.
		
		public GeneralSA (double fTarget, State state, StateManager manager, double t, double dt, int maxIter) {
			super(maxIter,manager);
//			this.manager = manager;
			this.fTarget = fTarget;
			//Begin at a start point P (either user-selected or randomly-generated).
			this.currentState = state;
			//Set the temperature, T, to it's starting value: Tmax
			this.T = t;
			this.dT = dt;
		}
		
		//Solves the problem.
		//Will run until either a optimal solution is found, or T == 0.
		public void solve () {
			ArrayList<State> children;
			State newGeneralState;
			double q;
			double p;
			double x;
			//While the tempature is not zero, search for a solution. 
			while(T > 0) {
				//If F(P) == Ftarget then EXIT and return P as the solution; else continue
				if (currentState.getF() == fTarget) {
					//return currentState.getState();
					System.out.println(currentState);
				}
				//Generate n neighbors of P in the search space: (P1, P2, ..., Pn).
				children = getManager().createChildren(currentState);
				//Let Pmax be the neighbor with the highest evaluation
				newGeneralState = getManager().findBestChild(children);
				//Let q = (F(Pmax)-F(P))/F(P)
				q = (newGeneralState.getF()-currentState.getF())/currentState.getF();
				//Let p = min [1, e^(-q/T)]
				p = Math.min(1.0, Math.pow(Math.E, (-q)/T));
				//Generate x, a random real number in the closed range [0,1]
				x = Math.random();
				//If x > p then P  <-- Pmax ;; ( Exploiting )
				if (x > p) 
					currentState = newGeneralState;
				//else P <--  a random choice among the n neighbors. ;; (Exploring)
				else {
					int random = (int)(Math.random()*children.size());
					currentState = children.get(random);
				}
				T  -= dT;
			}
			System.out.println("T is zero, no optimal solution found");
			System.out.println(currentState);
			//return currentState.getState();
		}
		
	public State getCompletedState(){
		return null;
	}
}
