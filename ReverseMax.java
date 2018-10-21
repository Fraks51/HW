import java.util.*;

public class ReverseMax {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		String current = new String();
		while (scan.hasNextLine()) {
			current = scan.nextLine();
    			list.add(current);
		}
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<>();
		int maxSize = 0;
		for (int i = 0 ; i < list.size() ; i++) {
			temp = new ArrayList<>();
			for (String now : list.get(i).split(" ")) {
				if (now.length() != 0) {
					temp.add(Integer.parseInt(now));
				}
			}
			matrix.add(temp);
			maxSize = Math.max(maxSize, temp.size());
		}
		ArrayList<Integer> MaxStr = new ArrayList<>();
		ArrayList<Integer> MaxColom = new ArrayList<>();
		for (ArrayList<Integer> now : matrix) {
			if (now.size() > 0) {
				int Max = Collections.max(now);
				MaxStr.add(Max);
			} else {
				MaxStr.add(Integer.MIN_VALUE);
			}
		}  
		for (int i = 0; i < maxSize; i++) {
			int Max = Integer.MIN_VALUE;
			for (ArrayList<Integer> now : matrix) {
				if (i < now.size()) {
					Max = Math.max(Max, now.get(i));
				}
			}
			MaxColom.add(Max);
		}
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				System.out.print(Math.max(MaxStr.get(i), MaxColom.get(j)) + " ");
			}
			System.out.print("\n");
		}  
	}
}
