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
	
	/**
	 * 
	 * @param state 
	 * @return TRUE if @param state is the objetive state FALSE else
	 */
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
	
	/**
	 * 
	 * @param state 
	 * @return the actions available for @param state
	 */
	public char [] availableAction(int [][] state){
		Position emBranco = getTileEmpty(state);
		//coluna
		if(emBranco.getJ() == 0){
			if(emBranco.getI() == 0){ //linha
				char [] acoes = {'R','D'};//direita e a baixo;
				return acoes;
			}
			if(emBranco.getI() == 1){ //linha
				char [] acoes = {'R','D','U'};//direita, a baixo e acima;
				return acoes;
			}
			if(emBranco.getI() == 2){//linha
				char [] acoes = {'R','U'};//direita e acima;
				return acoes;
			}
		}
		//coluna
		if(emBranco.getJ() == 1){
			if(emBranco.getI() == 0){ //linha
				char [] acoes = {'L','D','R'};//direita e a baixo;
				return acoes;
			}
			if(emBranco.getI() == 1){ //linha
				char [] acoes = {'U','L','R','D'};//direita, a baixo e acima;
				return acoes;
			}
			if(emBranco.getI() == 2){ //linha
				char [] acoes = {'L','U','R'};//direita e acima;
				return acoes;
			}
		}
		//coluna
		if(emBranco.getJ() == 2){
			if(emBranco.getI() == 0){ //linha
				char [] acoes = {'L','D'};//direita e a baixo;
				return acoes;
			}
			if(emBranco.getI() == 1){ //linha
				char [] acoes = {'U','L','D'};//direita, a baixo e acima;
				return acoes;
			}
			if(emBranco.getI() == 2){ //linha
				char [] acoes = {'L','U'};//direita e acima;
				return acoes;
			}
		}
		return null;
	}
	
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
