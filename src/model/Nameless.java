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
		
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				Position positionDefault = getPositionDefault(state[i][j]);
				some += Math.abs(positionDefault.getI() - i) + Math.abs(positionDefault.getJ() - j);
			}
		}
		
		return some;
	}
	
}
