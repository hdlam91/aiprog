package minMaxQuarto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The implementation of the novice bot
 * @author Eivind
 *
 */

public class NoviceBot extends Bot{

	private Board internalBoard;

	public NoviceBot(Board board) {
		super(board);
		this.name = "novice";
		internalBoard = new Board(board);
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public String toString(){
		return getName();
	}
	
	@Override
	public void placePiece(int x, int y, int index) {
	
		while(!b.placePieceOnBoard(x, y, b.getPieceFromRemaining(index),index)){
			x = (int)(Math.random()*4);
			y = (int)(Math.random()*4);
		}
		networkPosition = 4*y+x;
	}
	

	@Override
	public int choosePiece() {
		boolean winState = false;
		internalBoard = new Board(b);
		List<Integer> dataList = new ArrayList<Integer>();
	    for (int i = 0; i < 16; i++) {
	      dataList.add(i);
	    }
	    Collections.shuffle(dataList);
	    int[] numList = new int[dataList.size()];
	    for (int i = 0; i < dataList.size(); i++) {
	      numList[i] = dataList.get(i);
	    }
		int numListIndex = 0;
		
		
		Piece pieceOnHand;
		int index;
		
		while(numListIndex < 16){
			board:
			for (int i = 0; i < 4; i++) {
				winState = false;
				for (int j = 0; j < 4; j++) {
					index = numList[numListIndex];
					pieceOnHand = internalBoard.getPieceFromRemaining(index);
					if(pieceOnHand==null){
						numListIndex++;
						winState=true;
						break board;
					}
					
					if(internalBoard.placePieceOnBoard(i, j, pieceOnHand, index)){
						if(internalBoard.checkWin()){
							internalBoard.removePieceOnBoard(i, j);
							internalBoard.addPieceToRemaining(pieceOnHand, index);
							numListIndex++;
							winState = true;
							break board;
						}
						else{
							internalBoard.removePieceOnBoard(i, j);
							internalBoard.addPieceToRemaining(pieceOnHand, index);
						}
						
					}
					
					
				}
			}
			if(!winState)
				return numList[numListIndex];
		}
		
		int random = (int)(Math.random()*16);
		while(b.getPieceFromRemaining(random) == null){
			random = (int)(Math.random()*16);
		}
		return random;
	}


	@Override
	public void choseWheretoPlacePiece(int index) {
		internalBoard = new Board(b);
		Piece pieceOnHand = internalBoard.getPieceFromRemaining(index);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				
				if(internalBoard.placePieceOnBoard(i, j, pieceOnHand, index)){
					if(internalBoard.checkWin()){
						placePiece(i, j, index);
						return;
					}
					else{
						internalBoard.removePieceOnBoard(i, j);
						internalBoard.addPieceToRemaining(pieceOnHand, index);
					}
					
				}
			}
		}
		int randomX = (int)(Math.random()*4);
		int randomY = (int)(Math.random()*4);
		placePiece(randomX, randomY, index);
		
	}


}
