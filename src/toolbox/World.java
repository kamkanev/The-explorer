package toolbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class World {
	
	private static HashMap<String, String> names = new HashMap<String, String>();
	
	public static void main(String[] args) {
		
//		makeWorld(args);
//		load(args);
		
	}
	
	public static void setMap(HashMap<String, String> map) {
		
		names = map;
		
	}
	
	public static void test() {
		
		makeWorld();
		load();
		
	}
	
	public static void loadAndTest(HashMap<String, String> map) {
		
		setMap(map);
		makeWorld();
		load();
		
	}
	
	public static void createNewWorld(HashMap<String, String> map) {
		setMap(map);
		makeWorld();
	}
	
	private static void makeWorld() {
		
		final String path = "worlds/"+names.get("worldName");
		
		File world = new File(path);//.mkdir();
		
		if(!world.exists()) {
			
			world.mkdir();
			
			JSONArray worldList = new JSONArray();
	        
			JSONObject worldDetails = new JSONObject();
			
			for(String key : names.keySet()) {
				worldDetails.put(key, names.get(key));
			}
			
			worldList.add(worldDetails);
	        
	        //Write JSON file
	        try (FileWriter file = new FileWriter(path+"/info.json")) {
	 
	            file.write(worldList.toJSONString());
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	       
	        
			
		}else {
			
			JOptionPane.showMessageDialog(null, "This world already exits!", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
         
		
		
	}
	
	public static void load() {
		
		final String path = "worlds/"+names.get("worldName");
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(path+"/info.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray worldList = (JSONArray) obj;
            System.out.println(worldList);
//            JSONObject worldDetails = (JSONObject) obj;
//            System.out.println(worldDetails); 
            
            //Iterate over employee array
            worldList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	 private static void parseEmployeeObject(JSONObject world) 
	    {
	       for(String key : names.keySet()) {
	    	   String element = (String) world.get(key);
	    	   System.out.println(element);
	       }
	    }

}
