import java.util.ArrayList;
import java.util.Random;

public class GameShow {
	private ArrayList<Curtain> curts;
	private Curtain picked;
	
	private ArrayList<Curtain> setUpCurts() {
		ArrayList<Curtain> newCurts = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			newCurts.add(new Curtain());
		}
		Random rand = new Random();
		newCurts.get(rand.nextInt(newCurts.size())).setPrize(true);
		
		return newCurts;
	}
	
	private void chooseCurtain() {
		Random rand = new Random();
		int num = rand.nextInt(curts.size());
		picked = new Curtain(curts.remove(rand.nextInt(curts.size())));
	}
	
	private void changeCurtain() {
		for(int i = 0; i < curts.size(); i++) {
			if(!(curts.get(i).isPrize())) {
				curts.remove(i);
			}
			picked = curts.get(0);
		}
	}
	
	
	public boolean startGame(boolean keep) {
		curts = new ArrayList<>(setUpCurts());
		chooseCurtain();
		if(keep) {
			return picked.isPrize();
		} else {
			changeCurtain();
			return picked.isPrize();
		}
	}


	public Curtain getPicked() {
		return picked;
	}


	public void setPicked(Curtain picked) {
		this.picked = picked;
	}
	
	public void testKeepCurtain(int num) {
		int wins = 0;
		GameShow game = new GameShow();
		for(int i = 0; i < num; i++) {
			if (game.startGame(true))
				wins++;
		}
		System.out.println("Winning percentage if you change curtain: " + (float) wins/num);
	}
	
	public void testChangeCurtain(int num) {
		int wins = 0;
		GameShow game = new GameShow();
		for(int i = 0; i < num; i++) {
			if (game.startGame(false))
				wins++;
		}
		System.out.println("Winning percentage if you keep curtain: " + (float) wins/num);
	}
	
	public void testGame(int num) {
		testChangeCurtain(num);
		testKeepCurtain(num);
	}
}
