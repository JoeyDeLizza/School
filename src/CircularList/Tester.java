import java.util.Iterator;

public class Tester {
	public static void main(String[] args) {
		// Create list
		CircularList<String> list = new CircularList<>();
		
		// Add objects to list
		list.add("A");
		System.out.println(list.getCurrentNode().getData() + ": is the current node");
		list.add("B");
		System.out.println(list.getCurrentNode().getData() + ": is the current node");
		list.add("C");
		System.out.println(list.getCurrentNode().getData() + ": is the current node");
		list.add("D");
		System.out.println(list.getCurrentNode().getData() + ": is the current node");
		list.add("E");
		System.out.println(list.getCurrentNode().getData() + ": is the current node");
		// Printing List
		System.out.println("\nUsing for-each loop:");
		for (String s : list) {
			System.out.println(s);
		}
		// Adds "F" after the current node
		list.add(0, "F");
		// Printing List
		System.out.println("\nUsing for-each loop:");
		for (String s : list) {
			System.out.println(s);
		}
		
		System.out.println("\nLoop Through list twice:");
		for(int i = 0; i < list.size()*2; i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("\nAdd Object G:");
		list.add(3, "G");
		System.out.println("\nLoop Through list twice:");
		for(int i = 0; i < list.size()*2; i++) {
			System.out.println(list.get(i));
		}
		// Remove node after current node
		System.out.println("\nRemove Object: " + list.get(1));
		list.remove(1);
		System.out.println("\nLoop Through list twice:");
		for(int i = 0; i < list.size()*2; i++) {
			System.out.println(list.get(i));
		}
		
		// Remove current node
		System.out.println(list.getCurrentNode().getData());
		list.remove();
		System.out.println("\nLoop Through list twice:");
		for(int i = 0; i < list.size()*2; i++) {
			System.out.println(list.get(i));
		}

		// print list size
		System.out.println("\nlist size: " + list.size());
		
		//copy list
		CircularList<String> list2 = new CircularList<String>(list);
		System.out.println(list2.getCurrentNode().getNext().getData());
		System.out.println("\nUsing for-each loop:");
		for (String s : list2) {
			System.out.println(s);
		}

		// Use iter to remove objects from list
		Iterator<String> iter = list.iterator();
		
		while(iter.hasNext()) {
			System.out.println("removing object: " + list.getCurrentNode().getData());
			iter.remove();
		}		
		System.out.println("\nPrinting list1:");
		for (String s : list) {
			System.out.println(s);
		}
		
		
		System.out.println("\nPrinting list2:");
		for (String s : list2) {
			System.out.println(s);
		}
		System.out.println("\nRemove objects from list2");
		for(int i = list2.size(); i > 0; i--) {
			System.out.println("Removing object: " + list2.getCurrentNode().getData());
			list2.remove();
		}
		
		System.out.println("\nPrinting list2:");
		for (String s : list2) {
			System.out.println(s);
		}
		
		// null currentnode
		System.out.println("\nCurrentNode of List1: " + list.getCurrentNode());
		System.out.println("\nCurrentNode of List2: " + list2.getCurrentNode());


	}
}
