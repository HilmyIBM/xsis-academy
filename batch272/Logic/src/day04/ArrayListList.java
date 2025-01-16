package day04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayListList {

	public static void main(String[] args) {
		ArrayList<String> electronic = new ArrayList<String>();
		electronic.add("Radio");
		electronic.add("Televisi");
		electronic.add("Kulkas");
		System.out.println(electronic);
		
		for(String str : electronic) {
			System.out.println(str);
		}
		
		System.out.println(electronic.get(0));
		electronic.set(0, "AC");
		System.out.println(electronic.get(0));
		
		electronic.remove(0);
		System.out.println(electronic.size());
		
		for(String str : electronic) {
			System.out.println(str);
		}
		
		electronic.clear();
		
		System.out.println(electronic);
		
		System.out.println(electronic.size());
		
		List<Integer> list = new ArrayList<>();
		list.add(9);
		list.add(8);
		list.add(7);
		
		System.out.println(list);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Charlie");
		map.put("lastname", "Budi");
		map.put("address", "Bali");
		
		System.out.println(map);
		
		System.out.println(map.get("name"));
		
	}

}
