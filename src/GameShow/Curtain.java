
public class Curtain {
	private boolean prize;
	
	Curtain() {
		prize = false;
	}
	
	Curtain(Curtain c) {
		this.setPrize(c.isPrize());;
	}

	public boolean isPrize() {
		return prize;
	}

	public void setPrize(boolean prize) {
		this.prize = prize;
	}
	
	
}
