package minMaxQuarto;

public class Heuristic {
	
	public static int getHeuristic(Board currentState, int depth){
		int heuristic = 0;
		int[] piecesRemaining = currentState.getEqualRemainings();
		if (depth%2 == 1 && currentState.checkWin()) {
//			System.out.println("halp");
			return 500;
		}
		else if (depth%2 == 0 && currentState.checkWin()) {
			return -500;
		}
		else if(currentState.usedAllPieces()){
			return 0;
		}
		//if(depth%2 == 0){
			for (int i = 0; i < 4; i++) {
				if(currentState.getColCounterAt(i)==3){
					int[] col = currentState.getEqualOnCol(i);
					for (int j = 0; j < 4; j++) {
						if(col[j*2] == 3 && piecesRemaining[j*2]>=1 && piecesRemaining[j*2+1]%2 == 0 ||col[j*2+1] == 3 && piecesRemaining[j*2+1]>=1 && piecesRemaining[j*2]%2 == 0 ){
							heuristic+=5;
						}
						
					}
				}
				if(currentState.getColCounterAt(i)==2){
					int[] col = currentState.getEqualOnCol(i);
					for (int j = 0; j < 4; j++) {
						if(col[j*2] == 2 && piecesRemaining[j*2]>=1 ||col[j*2+1] == 2){
							heuristic+=2;
						}
						
					}
				}
				if(currentState.getRowCounterAt(i)==3){
					int[] row = currentState.getEqualOnRows(i);
					for (int j = 0; j < 4; j++) {
						if(row[j*2] == 3 && piecesRemaining[j*2]>=1 && piecesRemaining[j*2+1]%2 == 0||row[j*2+1] == 3 && piecesRemaining[j*2+1]>=1 && piecesRemaining[j*2]%2 == 0){
							heuristic+=5;
						}
					}
				}
				if(currentState.getRowCounterAt(i)==2){
					int[] row = currentState.getEqualOnRows(i);
					for (int j = 0; j < 4; j++) {
						if(row[j*2] == 2 ||row[j*2+1] == 2){
							heuristic+=2;
						}
					}
				}
			}
			if(currentState.getForwardDiagonal()==3){
				int[] fdia = currentState.getEqualOnForwardDiagonal();
				for (int j = 0; j < 4; j++) {
					if(fdia[j*2] == 3 && piecesRemaining[j*2]>=1&& piecesRemaining[j*2+1]%2 == 0||fdia[j*2+1] == 3 && piecesRemaining[j*2+1]>=1&& piecesRemaining[j*2]%2 == 0){
						heuristic+=5;
					}
				}
			}
			if(currentState.getForwardDiagonal()==2){
				int[] fdia = currentState.getEqualOnForwardDiagonal();
				for (int j = 0; j < 4; j++) {
					if(fdia[j*2] == 2 ||fdia[j*2+1] == 2){
						heuristic+=2;
					}
				}
			}
			if(currentState.getBackwardDiagonal()==3){
				int[] bdia = currentState.getEqualOnBackwardDiagonal();
				for (int j = 0; j < 4; j++) {
					if(bdia[j*2] == 3 && piecesRemaining[j*2]>=1 && piecesRemaining[j*2+1]%2 == 0||bdia[j*2+1] == 3 && piecesRemaining[j*2+1]>=1&& piecesRemaining[j*2]%2 == 0){
						heuristic+=5;
					}
				}
			}
			if(currentState.getBackwardDiagonal()==2){
				int[] bdia = currentState.getEqualOnBackwardDiagonal();
				for (int j = 0; j < 4; j++) {
					if(bdia[j*2] == 2||bdia[j*2+1] == 2){
						heuristic+=2;
					}
				}
			}
		//}
		/*else if(depth%2 == 1){
			for (int i = 0; i < 4; i++) {
				if(currentState.getColCounterAt(i)==3){
					int[] col = currentState.getEqualOnCol(i);
					for (int j = 0; j < 4; j++) {
						if(col[j*2] == 3 && piecesRemaining[j*2+1] == 0||col[j*2+1] == 3 && piecesRemaining[j*2] == 0){
							heuristic-=5;
						}
						//(col[j*2]==2 && col[j*2+1]!=1) ||(col[j*2]==1 && col[j*2+1]!=2))/
						
					}
				}
				if(currentState.getRowCounterAt(i)==3){
					int[] row = currentState.getEqualOnRows(i);
					for (int j = 0; j < 4; j++) {
						if(row[j*2] == 3 && piecesRemaining[j*2+1] == 0||row[j*2+1] == 3 && piecesRemaining[j*2] == 0){
							heuristic-=5;
						}
					}
				}
			}
			if(currentState.getForwardDiagonal()==3){
				int[] fdia = currentState.getEqualOnForwardDiagonal();
				for (int j = 0; j < 4; j++) {
					if(fdia[j*2] == 3 && piecesRemaining[j*2+1] == 0||fdia[j*2+1] == 3 && piecesRemaining[j*2] == 0){
						heuristic-=5;
					}
				}
			}
			if(currentState.getBackwardDiagonal()==3){
				int[] bdia = currentState.getEqualOnBackwardDiagonal();
				for (int j = 0; j < 4; j++) {
					if(bdia[j*2] == 3 && piecesRemaining[j*2+1] == 0 ||bdia[j*2+1] == 3 && piecesRemaining[j*2] == 0){
						heuristic-=5;
					}
				}
			}
		}*/
		return heuristic;
	}
	
}
