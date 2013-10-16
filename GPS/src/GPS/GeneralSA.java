package GPS;
import java.util.ArrayList;


public class GeneralSA extends LocalSearch{
		private double T; // The "tempature"
		private double dT; //How much we'll reduce T each iteration
		private double fTarget;
		private boolean solved;
//		private StateManager manager;
		
		public GeneralSA (StateManager manager,int maxIter) {
			super(maxIter,manager);			
			//Set the temperature, T, to it's starting value: Tmax
			this.fTarget = 1.0;
			this.T = (double) 1.0;
			this.dT = 1.0/maxIter;
			this.solved = false;
			solve();
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
				
				System.out.println("Iteration:" + (getMaxIterations() - (int)Math.ceil(T/dT)));
				System.out.println("Crashes currently: " + getManager().getCurrentState().getCrashes());
				
				
				//If F(P) == Ftarget then EXIT and return P as the solution; else continue
				if (getManager().getCurrentState().getF() == fTarget) {
					System.out.println("Goal state");
					solved = true;
					getManager().getCurrentState().setIterations(getMaxIterations() - (int)Math.ceil(T/dT));
					break;
				}
				//Generate n neighbors of P in the search space: (P1, P2, ..., Pn).
				children = getManager().createChildren(getManager().getCurrentState());
				//Let Pmax be the neighbor with the highest evaluation
				newGeneralState = getManager().findBestChild(children);
				
				
				
//				if (newGeneralState.getF() == fTarget) { //cheat?
//					System.out.println("Goal state");
//					getManager().setCurrentState(newGeneralState);
//					solved = true;
//					getManager().getCurrentState().setIterations(getMaxIterations() - (int)Math.ceil(T/dT));
//					break;
//				}
				
				
				
				
				System.out.println("chosen" + newGeneralState.getF());
				//Let q = (F(Pmax)-F(P))/F(P)
				q = (newGeneralState.getF()-getManager().getCurrentState().getF())/getManager().getCurrentState().getF();
				System.out.println("q:" + q);
				//Let p = min [1, e^(-q/T)]
				p = Math.min(1.0, Math.pow(Math.E, (-q)/T));
				System.out.println("p: " +p );
				//Generate x, a random real number in the closed range [0,1]
				x = Math.random();
				System.out.println("x: "+ x);
				//If x > p then P  <-- Pmax ;; ( Exploiting )
				if (x > p) 
					getManager().setCurrentState(newGeneralState);
				//else P <--  a random choice among the n neighbors. ;; (Exploring)
				else {
					System.out.println("random");
					int random = (int)(Math.random()*children.size());
					getManager().setCurrentState(children.get(random));
				}
				T  -= dT;
				System.out.println("F: "  + getManager().getCurrentState().getF()+"\n");
//				System.out.println("iteration:" + (getMaxIterations() - (int)Math.ceil(T/dT)));
//				System.out.println(getManager().getCurrentState());
			}
			if(!solved){
				System.out.println("T is zero, no optimal solution found");
				getManager().getCurrentState().setIterations(getMaxIterations());
//				System.out.println(getManager().getCurrentState());
			}
			//return currentState.getState();
		}
		
	public State getCompletedState(){
		return getManager().getCurrentState();
	}
}
