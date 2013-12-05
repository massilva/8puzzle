package model;

public class Nameless {

	/**
	 * @param number, you want to known to its default position
	 * @return Position containing this default position
	 * 
	 */
	public Position getPositionDefault(int number){

		Position position = new Position();
		
		int line = number/3;
		int col = number%3;
		
		position.setI(line);
		position.setJ(col);
		
		return position;
	}
	
	/**
	 * Without presentation
	 * @param state
	 * @return
	 */
	public int manhattanDistance(int [][] state){
		int some = 0;
		
		//Assumes the order of number into state, instead of ascending order
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if(state[i][j] != 0){ //DO NOT sum the default distance of empty space.
					Position positionDefault = getPositionDefault(state[i][j]);
					some += Math.abs(positionDefault.getI() - i) + Math.abs(positionDefault.getJ() - j);
				}
			}
		}
		
		return some;
	}
	
	public boolean isObjetiveState(int [][] state){
		int [][] objetiveState = {{0,1,2},{3,4,5},{6,7,8}};
		int  i = 0, j = 0;
		while(i < objetiveState.length){
			while(j < objetiveState.length && objetiveState[i][j] == state[i][j]){
				j++;
			}
			if(j != objetiveState.length){
				break;
			}
			i++;
		}
		if(i == objetiveState.length && j == objetiveState.length){
			return true;
		}
		return false;
	}
	
}
