package toolbox;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;


public class JavaToMySQL {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    private static HashMap<String, String> info = new HashMap<String, String>();
    
    public static void putNewWorldWithPlayer(HashMap<String, String> map) {
    	
    	info = map;
    	
    	String queryPlayers = "insert into players (id, name, gender, coords, skin) values ("+ (getLastPlayerId() +1) +", '"+ map.get("chName") +"', "
    			+ "'"+ map.get("gender") +"', '"+ map.get("coords") +"', 'playerTexture');";
    	
    	String queryWorlds = "";
    	
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing query
            stmt.executeUpdate(queryPlayers);
            
            System.out.println("Insert to players complete");


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
        	
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
//        System.out.println("Insert players end.");
        
        Date utilDate = new Date();
        
        queryWorlds = "insert into worlds (id, name, mode, seed, playerId, lastPlayed) values ("+ (getLastWorldId() + 1) +" ,'"+ map.get("worldName") +"', "
        		+ "'"+ map.get("mode") +"', '"+ map.get("sName") +"', "+ getLastPlayerId() +", '"+ new Timestamp(utilDate.getTime()) +"')";
        
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing query
            
            stmt.executeUpdate(queryWorlds);
            
            System.out.println("Insert to worlds complete");


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
        	
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        System.out.println("Insert worlds end.");
        
    }
    
    public static int getLastPlayerId() {
    	String query = "select id from players order by id desc limit 1;";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
//                int count = rs.getInt(1);
//            		System.out.println(rs.getInt(1));
            		
            		return rs.getInt(1);
//                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		return 0;
    }
    
    public static int getLastWorldId() {
    	String query = "select id from worlds order by id desc limit 1;";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
//                int count = rs.getInt(1);
//            		System.out.println(rs.getInt(1));
            		
            		return rs.getInt(1);
//                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		return 0;
    }
    
    public static int getNextIdIn(String table) {
    	String query = "select id from "+ table +" order by id desc limit 1;";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
//                int count = rs.getInt(1);
//            		System.out.println(rs.getInt(1));
            		int res = rs.getInt(1) + 1;
            		return res;
//                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		return 1;
    }
    
    public static List<Entity> insertEntities(List<Entity> entities) {
    	
    	
    	for(int i = 0; i< entities.size(); i++) {
    		Entity e = entities.get(i);
    		
    		e.setId(getNextIdIn("entities"));
    		
    		String query = "insert into entities (id, name, isAlive, coords, object, texture, ind, isNormal, worldId, rotation, scale, shine, reflectivity, "
    				+ "fakeLighting, seeTh, isAtlas)"
    				+ " values ("+ e.getId() +", '"+ e.getName() +"', "+ e.getIsAlive() +", "
    				+ "'"+ e.getPosition().x +"/"+ e.getPosition().y +"/"+ e.getPosition().z +"', "
    				+ "'"+ e.getModel().getRawModel().getModelName() +"', '"+ e.getModel().getTexture().getName() +"', "
    				+ ""+ e.gettextureIndex() +", false, "+ e.getWorldId() +", "
    				+ "'"+ e.getRotX() +"/"+ e.getRotY() +"/"+ e.getRotZ() +"', "+ e.getScale() +", "
    				+ ""+ e.getModel().getTexture().getShineDamper() +", "+ e.getModel().getTexture().getReflectivity() +", "
    				+ ""+ e.getModel().getTexture().isUseFakeLighting() +", "+ e.getModel().getTexture().isHasTransparency() +", "
    						+ ""+ e.getModel().getTexture().getNumberOfRows() +")";
    		
    		try {
                // opening database connection to MySQL server
                con = DriverManager.getConnection(url, user, password);

                // getting Statement object to execute query
                stmt = con.createStatement();

                // executing query
                
                stmt.executeUpdate(query);
                
//                System.out.println("Insert to entities complete");


            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } finally {
            	
                //close connection ,stmt and resultset here
                try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            }
    		
    	}
    	System.out.println("Insert entities end.");
    	
		return entities;
    	
    }
    
    public static void updateEntities(List<Entity> entities) {
    	
    	
    	for(int i = 0; i< entities.size(); i++) {
    		Entity e = entities.get(i);
    		
    		String query = "update entities set name = '"+ e.getName() +"', isAlive = "+e.getIsAlive()+", "
    				+ "coords = '"+ e.getPosition().x +"/"+ e.getPosition().y +"/"+ e.getPosition().z +"', "
    				+ "object = '"+e.getModel().getRawModel().getModelName()+"', texture = '"+ e.getModel().getTexture().getName() +"', "
    						+ "ind = "+e.gettextureIndex()+", scale = "+ e.getScale() +", rotation = '"+ e.getRotX() +"/"+ e.getRotY() +"/"+ e.getRotZ() +"' where id = "+ e.getId() +"";
    		
    		try {
                // opening database connection to MySQL server
                con = DriverManager.getConnection(url, user, password);

                // getting Statement object to execute query
                stmt = con.createStatement();

                // executing query
                
                stmt.executeUpdate(query);
                
//                System.out.println("Update to entities complete");


            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } finally {
            	
                //close connection ,stmt and resultset here
                try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            }
    		
    	}
    	System.out.println("update entities end.");
    	
    }
    
    public static void updatePlayer(Player player) {

    		
    		String query = "update players set coords = '"+ player.getPosition().x + "/"+ player.getPosition().y +"/"+ player.getPosition().z +"', "
    				+ "skin = '"+ player.getModel().getTexture().getName() +"', "
    						+ "health = "+ player.getHealth() +", maxHealth = "+ player.getMaxHealth() +", "
    								+ "xp = "+ player.getXP() +", level = "+ player.getLevel() +" where id = "+ player.getPlayerId() +"";
    		
    		try {
                // opening database connection to MySQL server
                con = DriverManager.getConnection(url, user, password);

                // getting Statement object to execute query
                stmt = con.createStatement();

                // executing query
                
                stmt.executeUpdate(query);
                
                System.out.println("Update to players complete");


            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } finally {
            	System.out.println("Update player end.");
                //close connection ,stmt and resultset here
                try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            }
    		
    	
    }
    
    public static List<JPanel> getWorlds(JFrame win) {
    	String query = "select * from worlds as w join players as p on w.playerId = p.id;";
    	
    	List<JPanel> panels = new ArrayList<JPanel>();

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);
            
            int i = 1;

            while (rs.next()) {
//                int count = rs.getInt(1);
//            		System.out.println(rs.getInt(1));
            		int id = rs.getInt(7);
            		int wId = rs.getInt(1);
            		String wName = rs.getString(2);
            		String chName = rs.getString(8);
            		String lPlayed = rs.getString(6);
            		
            		panels.add(createJWorld(i, wId, id, wName, chName, lPlayed, win));
            		i++;
//            	System.err.println(rs.getInt("id"));
//                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
            
        }
        
		return panels;
    }
    
    private static JPanel createJWorld(int ind, int wId, int id, String wName, String chName, String lPlayed, JFrame win) {
    	
    	JPanel w = new JPanel();
    	
    	JLabel name = new JLabel(wName, JLabel.CENTER);
    	JLabel plName = new JLabel(chName, JLabel.CENTER);
    	JLabel lastPlayed = new JLabel(lPlayed, JLabel.CENTER);
    	JLabel number = new JLabel(""+wId);
    	
    	number.setBounds(0, 0, 0, wId);
    	
    	number.setName("number");
    	
    	JButton load = new JButton("Load");
    	
    	load.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	load.setBackground(new Color(15, 157, 2));
    	load.setForeground(Color.WHITE);
    	load.setFocusable(false);
    	load.setBounds(400, 50, 70, 40);
    	try {
			load.setFont(Fonts.loadFont("Englisht", 18f));
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
    	
    	load.addMouseListener(new MouseAdapter() {
    		
    		@Override
    		public void mouseExited(MouseEvent e) {
    			super.mouseExited(e);
    			load.setBackground(new Color(15, 157, 2));
    	    	load.setForeground(Color.WHITE);
    		}
    		
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			super.mouseEntered(e);
    			load.setBackground(new Color(15, 216, 2));
    	    	load.setForeground(Color.BLACK);
    		}
    		
		});
    	
    	load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

					win.dispose();
					
					MainGameLoop.worldId = wId;
					MainGameLoop.heroId = id;
					MainGameLoop.heroName = plName.getText();
					
					MainGameLoop.loadGame();

				
			}
		});
    	
    	w.setLayout(null);
    	w.setSize(550, 100);
    	
//    	boolean isI = (ind > 1);
    	
    	int y = (ind - 1) * 100;
    	
//    	if(isI) {
    		y+=10*ind;
//    	}
    	
//    	System.err.println(y);
    	
    	w.setLocation(5, y);
    	
    	name.setBounds(10, 10, 100, 50);
    	plName.setBounds(10, 60, 100, 30);
    	lastPlayed.setBounds(150, 10, 200, 80);
    	
    	w.setBackground(Color.WHITE);
    	w.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	w.add(number);
    	w.add(load);
    	w.add(name);
    	w.add(plName);
    	w.add(lastPlayed); 	
    	
    	return w;
    	
    	
    }
    
    public static Player loadPlayer(int wId) {
    	
    	String query = "select * from players where id = "+ wId +"";
    	
    	try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	Loader loader = new Loader();
            	
            	TexturedModel pl = new TexturedModel(OBJLoader.loadObjModel("person", loader), new ModelTexture(rs.getString("skin"), loader.loadGameTexture(rs.getString("skin"))));
            	
            	String[] posArr = rs.getString("coords").split("/");
            	
            	
            	Vector3f position = new Vector3f(Float.parseFloat(posArr[0]), Float.parseFloat(posArr[1]), Float.parseFloat(posArr[2]));
            	
            	Player player = new Player(wId, rs.getInt("id"), rs.getString("name"), pl, position, 0, 0, 0, 0.5f);
            	
            	player.setHealth(rs.getInt("health"));
            	player.setMaxHealth(rs.getInt("maxHealth"));
            	player.setXP(rs.getInt("xp"));
            	player.setLevel(rs.getInt("level"));
            	
            	
               return player;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    	
    	return null;
    	
    }
    
    public static List<Entity> loadEntities(int wId) {
    	
    	List<Entity> entities = new ArrayList<Entity>();
    	
    		
    		String query = "select * from entities where worldId= "+ wId +"";
    		
    		try {
                // opening database connection to MySQL server
                con = DriverManager.getConnection(url, user, password);

                // getting Statement object to execute query
                stmt = con.createStatement();

                // executing query
                
                rs = stmt.executeQuery(query);
                
//                System.out.println("Update to entities complete");
                
                while(rs.next()) {
                	
                	Loader loader = new Loader();
                	
                	RawModel eModel = OBJLoader.loadObjModel(rs.getString("object"), loader);
                	
                	TexturedModel eTextModel = new TexturedModel(eModel, new ModelTexture(rs.getString("texture"), loader.loadGameTexture(rs.getString("texture"))));
                	
                	if(rs.getInt("isAtlas") > 1) {
                		eTextModel.getTexture().setNumberOfRows(rs.getInt("isAtlas"));
                	}
                	
                	eTextModel.getTexture().setShineDamper(rs.getFloat("shine"));
                	eTextModel.getTexture().setReflectivity(rs.getFloat("reflectivity"));
                	eTextModel.getTexture().setHasTransparency(rs.getBoolean("seeTh"));
                	eTextModel.getTexture().setUseFakeLighting(rs.getBoolean("fakeLighting"));
                	
                	String[] posArr = rs.getString("coords").split("/");
                	
                	String[] rotArr = rs.getString("rotation").split("/");
                	
                	Vector3f position = new Vector3f(Float.parseFloat(posArr[0]), Float.parseFloat(posArr[1]), Float.parseFloat(posArr[2]));
                	
                	Entity e = new Entity(wId, eTextModel, rs.getInt("ind"), position, Float.parseFloat(rotArr[0]), Float.parseFloat(rotArr[1]), Float.parseFloat(rotArr[2]), rs.getFloat("scale"));
                	
                	entities.add(e);
                	
                }


            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } finally {
            	
                //close connection ,stmt and resultset here
                try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
                try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
            }
    		
    	System.out.println("update entities end.");
    	
    	return entities;
    	
    }
    
    public static void deletePlayer(int id) {

		
		String query = "delete from players where id = "+ id +"";
		
		try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing query
            
           int rows = stmt.executeUpdate(query);
            
           System.out.println(rows);
            System.out.println("Delete players complete");


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }

    }
    
    public static void deleteEntities(int id) {

		
		String query = "delete from entities where worldId = "+ id +"";
		
		try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing query
            
           int rows = stmt.executeUpdate(query);
            
           System.out.println(rows);
//            System.out.println("Delete enti complete");


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
        	System.out.println("Delete entities complete.");
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		
    }
    
    public static void deleteWorld(int id) {

		
		String query = "delete from worlds where id = "+ id +"";
		
		try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing query
            
           int rows = stmt.executeUpdate(query);
            
           System.out.println(rows);
            System.out.println("Delete world complete");


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
    

}
