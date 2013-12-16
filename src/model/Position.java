package model;

public class Position {
	private int i;
	private int j;

	public Position() {
		super();
	}

	public Position(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public String toString() {
		return "Position [i=" + i + ", j=" + j + "]";
	}

	@Override
	public boolean equals(Object o){
		Position position = (Position)o;
		return (position.getI() == this.i && position.getJ() == this.j);
	}

}
