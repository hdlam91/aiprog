package GPS;

import java.util.ArrayList;

public abstract class StateManager {
	
	public StateManager(){
		
	}

	//Finds child with the highest objective function value.
	protected GeneralNode findBestChild(ArrayList<GeneralNode> children) {
		GeneralNode returnGeneralNode = children.get(0);
		for (int i = 0; i < children.size(); i++) {
			if (returnGeneralNode.getF() < children.get(i).getF()) returnGeneralNode = children.get(i);
		}
		return returnGeneralNode;
	}

	//Creates children. Implementation depends on the problem.
	public abstract ArrayList<GeneralNode> createChildren(GeneralNode currentGeneralNode2);
	
	public abstract Object createInitState();
}
