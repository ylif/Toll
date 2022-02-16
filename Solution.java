//Toll fee calculator
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Solution {
	
	public static double calculator(int start, int end, List<Integer>[] roads, double[][] distances, boolean[] visited) {
		double[] node = new double[2];
		Queue<double[]> que = new LinkedList<>();
		
		node[0] = (double)start;
		que.add(node);
		while (!que.isEmpty()) {
			node = new double[2];
			node = que.poll();
			int curr = (int)node[0];
			visited[curr] = true;
			List<Integer> neighbor = roads[curr];
			
			if (neighbor!=null) {
				for (Integer num: neighbor) {					
					if (!visited[num]) {
						node[0] = num;
						node[1] += distances[curr][num]; 
					}
					if (num == end) {
						if (start == end) {
							node[1] = 0;
						}
						return node[1];
					} else if (!visited[num]){
						double[] newNode = new double[2];
						newNode[0] = node[0];
						newNode[1] = node[1];
						que.add(newNode);
						node[1] -= distances[curr][num];
					}
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		String str1 = "", str2 = "";
		
		int stops = 0;
		double journey = 0, fee = 0.25;
		Map<String, Integer> map = new HashMap<>();
		List<Integer>[] roads = null;
		double[][] distances = null;
		
		JSONParser parser = new JSONParser();
	    
		try {
	         Object obj = parser.parse(new FileReader("C:\\JSON\\interchanges.json"));
	         JSONObject jsonObject = (JSONObject)obj;
	         
	         JSONObject locations = (JSONObject)jsonObject.get("locations");
	         stops = locations.size() + 4;
	         distances = new double[stops][stops];
	         roads = new ArrayList[stops];
	         
	         Iterator iterator = locations.keySet().iterator();	         
	         
	         while (iterator.hasNext()) {
	        	 String key = (String)iterator.next();
	        	 int ikey = Integer.valueOf(key);
	        	 if (key != null && roads[ikey] == null) {
	        		 roads[ikey] = new ArrayList<>();
	        	 }	        	 
	        	 JSONObject cur = (JSONObject)locations.get(key);
	        	 String name = (String)cur.get("name");
	        	 map.put(name, ikey);
	        	 //System.out.println(key + ", " + name);
	        	 JSONArray routes = (JSONArray)cur.get("routes");
	        	 Iterator iterator2 = routes.iterator();
		         while (iterator2.hasNext()) {
		        	 JSONObject route = (JSONObject)iterator2.next();
		        	 long neighbor = (long)route.get("toId");
		        	 int neigNum = (int)neighbor;
		        	 Object distan = route.get("distance");
		        	 double dist = 0;
		        	 if (distan.getClass() == Double.class) {
		        		 dist = (double)distan;
		        	 } else if (distan.getClass() == long.class) {
		        		 dist = (double)distan;
		        	 }
		        	 
		        	 distances[ikey][neigNum] = dist;
		        	 roads[ikey].add(neigNum);
		         }
	         }
	         
	    } catch(Exception e) {
	         e.printStackTrace();
	    }

		int start = -1, end = -1;
		/*start = map.get(str2);
		end = map.get(str1);
		journey = calculator(start, end, roads, distances, new boolean[stops+4]);
		System.out.println("Distance : " + String.format("%.2f", journey));
		System.out.println("Cost : " + String.format("%.2f", journey*fee));*/
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the location name, which is case sensitive. If you want to exit, just enter \"exit\".");
		while (true) {
			System.out.println("from");
			str1 = scanner.nextLine();
			if (str1.equals("exit")) {
				break;
			}
			if (map.containsKey(str1)) {
				start = map.get(str1);
			} else {
				System.out.println("invalid input, try again");
				continue;
			}
			System.out.println("to");
			str2 = scanner.nextLine();
			if (str2.equals("exit")) {
				break;
			}
			if (map.containsKey(str2)) {
				end = map.get(str2);
			} else {
				System.out.println("invalid input, try again");
				continue;
			}
			if (start>0 && start<=stops && end>0 && end<=stops) {
				journey = calculator(start, end, roads, distances, new boolean[stops+4]);
				System.out.println("from : " + str1 + ", to : " + str2);
				System.out.println("Distance : " + String.format("%.2f", journey) + "Km");
				System.out.println("Cost : " + "$" + String.format("%.2f", journey*fee));
				//break;
			}
		}
	}
}
