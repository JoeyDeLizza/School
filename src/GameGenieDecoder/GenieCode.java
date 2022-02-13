import java.util.HashMap;

public class GenieCode {
	public String code;
	public HashMap<String, Integer> m = new HashMap<>();
	public char[] cod = new char[6];
	public int[] nums = new int[6];

	
	
	GenieCode(String s) {
		this.code = s.toUpperCase();
		m.put("A", 0x0);
		m.put("P", 0x1);
		m.put("Z", 0x2);
		m.put("L", 0x3);
		m.put("G", 0x4);
		m.put("I", 0x5);
		m.put("T", 0x6);
		m.put("Y", 0x7);
		m.put("E", 0x8);
		m.put("O", 0x9);
		m.put("X", 0xA);
		m.put("U", 0xB);
		m.put("K", 0xC);
		m.put("S", 0xD);
		m.put("V", 0xE);
		m.put("F", 0xF);
		
		cod = code.toCharArray();
		
		for (int i = 0; i < code.length(); i++) {
			nums[i] = m.get(code.substring(i, i+1));
		}





	}
	
public void decode() {
		
		//GenieCode m = new GenieCode(s);
		
		int address = 0x8000 + ((nums[3] & 7) << 12)
				| ((nums[5] & 7) << 8) | ((nums[4] & 8) << 8)
				| ((nums[2] & 7) << 4) | ((nums[1] & 8) << 4)
				| (nums[4] & 7)		  | (nums[3] & 8);
		
		int data = ((nums[1] & 7) << 4) | ((nums[0] & 8) << 4)
				|	(nums[0] & 7)	   | (nums[5] & 8);
		
		System.out.println("address: 0x" + String.format("%x", address));
		System.out.println("address: 0x" + String.format("%x", data));

	}
	
	

}
