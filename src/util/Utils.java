package util;

import model.Position;

public class Utils {

	/**
	 * 
	 * @param state
	 * @return Position where is the empty tile 
	 */
	public Position getTileEmpty(int [][] state){
		int i = 0, j = 0;
		while(i < state.length){
			j = 0;
			while(j < state.length && state[i][j] != 0){
				j++;
			}
			if(j == state.length){
				i++;
			}
			else{
				break;
			}
		}
		return new Position(i,j);
	}

}
