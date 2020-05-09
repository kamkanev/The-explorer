package engineTester;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.MousePicker;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guis.GuiRenderer;
import guis.GuiTexture;

public class MainGameLoop {
	
	public static String heroName = "";

	public static void main(String[] args) {

		runGame();

	}
	
	public static void runGame() {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		TextMaster.init(loader);
		
		FontType font = new FontType(loader.loadTexture("arial"), new File("res/arial.fnt"));
		GUIText text = new GUIText("Hello "+heroName, 1f, font, new Vector2f(0 ,0 ), 1f, true);
		text.setColour(0.5f, 0.5f, 0f);
		
		MasterRenderer renderer = new MasterRenderer(loader);
		
		//Terrain textures
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy2"));
		TerrainTexture backgroundTexture2 = new TerrainTexture(loader.loadTexture("snow"));
//		backgroundTexture2.setShineDamper(10);
//		backgroundTexture2.setReflectivity(1);
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path2"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexturePack texturePack2 = new TerrainTexturePack(backgroundTexture2, rTexture, gTexture, bTexture);
		
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap2"));
		TerrainTexture blendMap2 = new TerrainTexture(loader.loadTexture("blendMap3"));
		
		//**************************
		
		TexturedModel cube = new TexturedModel(OBJLoader.loadObjModel("crate", loader), new ModelTexture(loader.loadTexture("white")));
		
		RawModel model = OBJLoader.loadObjModel("tree", loader);
		TexturedModel treeModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
		
		TexturedModel tree1Model = new TexturedModel(OBJLoader.loadObjModel("tree1", loader), new ModelTexture(loader.loadTexture("tree1")));
		tree1Model.getTexture().setHasTransparency(true);
		
		
		ModelTexture pineTextureAtlas = new ModelTexture(loader.loadTexture("pine2"));
		pineTextureAtlas.setNumberOfRows(2);
		pineTextureAtlas.setReflectivity(0);
		
		TexturedModel tree2Model = new TexturedModel(OBJLoader.loadObjModel("pine", loader), pineTextureAtlas);
		tree2Model.getTexture().setHasTransparency(true);
		
		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);
		
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader), fernTextureAtlas);	
		
		fern.getTexture().setHasTransparency(true);
		fern.getTexture().setUseFakeLighting(false);
		fern.getTexture().setReflectivity(0);
		
		ModelTexture bushTextureAtlas = new ModelTexture(loader.loadTexture("bushAtlas"));
		bushTextureAtlas.setNumberOfRows(2);
		
		TexturedModel bush = new TexturedModel(OBJLoader.loadObjModel("bush", loader), bushTextureAtlas);
		bush.getTexture().setHasTransparency(true);
		//bush.getTexture().setUseFakeLighting(true);
		
		
		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("grassTexture")));
		//grass.getTexture().setHasTransparency(true);
		//grass.getTexture().setUseFakeLighting(true);
		
		
		TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader), new ModelTexture(loader.loadTexture("lamp")));
		
		lamp.getTexture().setReflectivity(0);
		lamp.getTexture().setUseFakeLighting(true);
		lamp.getTexture().setHasTransparency(true);
		
		Terrain terrain = new Terrain(0, 0,loader, texturePack, blendMap, "heightmap");
		Terrain terrain2 = new Terrain(-1,0,loader, texturePack2, blendMap2, "heightmap");
		
		List<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(terrain);
		terrains.add(terrain2);
		
		WaterShader waterShader = new WaterShader();
		WaterFrameBuffers fbos = new WaterFrameBuffers();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), fbos);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		
		WaterTile water = new WaterTile(70, 300, -4, true); 	
		waters.add(water);
		waters.add(new WaterTile(160, 85, -5));
		waters.add(new WaterTile(100, 205, -5));
		waters.add(new WaterTile(40, 85, -5));
		
		
		List<Entity> entities = new ArrayList<Entity>();
		List<Entity> normalMapEntities = new ArrayList<Entity>();
		
		TexturedModel stoneModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("boulder", loader), 
				new ModelTexture(loader.loadTexture("boulder")));
		stoneModel.getTexture().setNormalMap(loader.loadTexture("boulderNormal"));
		stoneModel.getTexture().setShineDamper(20);
		stoneModel.getTexture().setReflectivity(1f);
		
		Random random = new Random();
		
//		entities.add(new Entity(cube, new Vector3f(100, 10, 100), 0, 0, 0, 0.1f));
		
		for(int i=0; i< 10; i++) {
			float x = random.nextFloat()* Terrain.getSize() + terrain.getX();
			float z = random.nextFloat()* Terrain.getSize() + terrain.getZ();
			float y = terrain.getHeightOfTerrain(x, z) + (random.nextFloat() * 20 - 15);
			
				normalMapEntities.add(new Entity(stoneModel, new Vector3f(x, y, z), random.nextFloat() * 360, random.nextFloat() * 360, random.nextFloat() * 360, 1));
		}
		
//		for(int i=0;i<40;i++){
//			float x = random.nextFloat()* Terrain.getSize() * (terrain2.getX() / Terrain.getSize() - 1);
//			float z = random.nextFloat()* Terrain.getSize() * (terrain2.getZ() / Terrain.getSize() + 1);
//			float y = terrain2.getHeightOfTerrain(x, z);
//			
//				entities.add(new Entity(tree2Model, 1, new Vector3f(x, y, z),0,0,0,1.3f));
//
//			
//			 x = random.nextFloat()* Terrain.getSize() * (terrain2.getX() / Terrain.getSize() -1);
//			 z = random.nextFloat()* Terrain.getSize() * (terrain2.getZ() / Terrain.getSize() + 1);
//			 y = terrain2.getHeightOfTerrain(x, z);
//			entities.add(new Entity(bush, 1, new Vector3f(x, y, z),0,0,0,3));
//			
//		}
		
		
		for(int i=0;i<50;i++){
			float x = random.nextFloat()* Terrain.getSize() * (terrain.getX() / Terrain.getSize() + 1);
			float z = random.nextFloat()* Terrain.getSize() * (terrain.getZ() / Terrain.getSize() + 1);
			float y = terrain.getHeightOfTerrain(x, z);
			
					if(i % 2 == 0) {
//						entities.add(new Entity(treeModel, new Vector3f(x, y, z),0,0,0,5));
						entities.add(new Entity(tree2Model, 0, new Vector3f(x, y, z),0,0,0,1.3f));
					}else {
//						entities.add(new Entity(tree1Model, new Vector3f(x, y, z),0,0,0,0.3f));
						entities.add(new Entity(tree2Model, 2, new Vector3f(x, y, z),0,0,0,1.3f));
					}

				if(i % 2 == 0) {
//					entities.add(new Entity(treeModel, new Vector3f(x, y, z),0,0,0,5));
					entities.add(new Entity(tree2Model, 0, new Vector3f(x, y, z),0,0,0,1.3f));
				}else {
//					entities.add(new Entity(tree1Model, new Vector3f(x, y, z),0,0,0,0.3f));
					entities.add(new Entity(tree2Model, 2, new Vector3f(x, y, z),0,0,0,1.3f));
				}
			
			
			 x = random.nextFloat()* Terrain.getSize() * (terrain.getX() / Terrain.getSize() + 1);
			 z = random.nextFloat()* Terrain.getSize() * (terrain.getZ() / Terrain.getSize() + 1);
			 y = terrain.getHeightOfTerrain(x, z);
			 
				 
					 entities.add(new Entity(bush, 0, new Vector3f(x, y, z),0,0,0,3));
				 
			 
				 if(i%2 == 0) {
					 entities.add(new Entity(bush, 0, new Vector3f(x, y, z),0,0,0,3));
				 }else {
					 entities.add(new Entity(bush, 2, new Vector3f(x, y, z),0,0,0,3));
				 }
			 
			
		}
		
		
		for(int i=0;i<10; i++) {
			
			float x = random.nextFloat()* Terrain.getSize() * (terrain.getX() / Terrain.getSize() + 1);
			float z = random.nextFloat()* Terrain.getSize() * (terrain.getZ() / Terrain.getSize() + 1);
			float y = terrain.getHeightOfTerrain(x, z);
			

					entities.add(new Entity(fern, random.nextInt(4), new Vector3f(x, y, z), 0, 0, 0, 0.6f));
				
			
					entities.add(new Entity(fern, random.nextInt(4), new Vector3f(x, y, z), 0, 0, 0, 0.6f));

			
		}
		
		
		Light sun = new Light(new Vector3f(0,10000, -7000),new Vector3f(1f, 1f, 1f));
		
		List<Light> lights = new ArrayList<Light>();
		lights.add(sun);
		lights.add(new Light(new Vector3f(220, terrain.getHeightOfTerrain(220, 170) + 12, 170), new Vector3f(2, 2, 2), new Vector3f(1f, 0.005f, 0.002f)));
		lights.add(new Light(new Vector3f(185, terrain.getHeightOfTerrain(185, 293) + 12, 293), new Vector3f(0, 2, 2), new Vector3f(1f, 0.002f, 0.001f)));
		
		entities.add(new Entity(lamp, new Vector3f(220, terrain.getHeightOfTerrain(220, 170), 170), 0, 0, 0, 1));
		entities.add(new Entity(lamp, new Vector3f(185, terrain.getHeightOfTerrain(185, 293), 293), 0, 0, 0, 1));
		
		
		
		RawModel playerModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel pl = new TexturedModel(playerModel, new ModelTexture(loader.loadTexture("playerTexture")));
		
		Player player = new Player(pl, new Vector3f(100, 0, 100), 0, 0, 0, 0.5f); 
		
//		System.err.println(player.getModel().getRawModel().getHitbox().getWorld_positions());
		
		Camera camera = new Camera(player);
		
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("health"), new Vector2f(-0.75f, 0.95f), new Vector2f(0.25f, 0.25f));
		//guis.add(gui);
		
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		
//		Entity lampEntity = new Entity(lamp, new Vector3f(293, -6.8f, -305), 0, 0, 0, 1);
//		entities.add(lampEntity);
//		
//		Light light = new Light(new Vector3f(293, 7, -305), new Vector3f(2, 2, 0), new Vector3f(1, 0.01f, 0.002f));
//		lights.add(light);
		
		
		
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrains);
		
		float time = 0;
		
		while(!Display.isCloseRequested()){
			
//			time += DisplayManager.getFrameTimeSeconds() * 1000;
//			time %= 24000;
//			float blendFactor = 0.1f;		
//			if(time >= 0 && time < 5000){
//				
////				sun.setColour(new Vector3f(0.1f, 0.1f, 0.1f));
//				
//			}else if(time >= 5000 && time < 8000){
//				
////				sun.setColour(new Vector3f(0.4f, 0.4f, 0.4f));
//				blendFactor = (time - 5000)/(8000 - 5000);
//				
//			}else if(time >= 8000 && time < 21000){
//				
////				sun.setColour(new Vector3f(1f, 1f, 1f));
//				blendFactor = (time - 8000)/(21000 - 8000);
////				System.out.println(blendFactor);
//				
//			}else{
//				
////				sun.setColour(new Vector3f(0.6f, 0.6f, 0.6f));
//				blendFactor = (time - 21000)/(24000 - 21000);
//				
//			}
//			sun.setColour(new Vector3f(blendFactor, blendFactor, blendFactor));
//			System.out.println(blendFactor);
			
			// -------------------------- moving and collision detection ----------------------------- //
			
			camera.move();
			
//			for(WaterTile w : waters) {
//				if(player.getPosition().x >= w.getX() && player.getPosition().x <= w.getX() + Terrain.getSize() && 
//						player.getPosition().z >= w.getZ() && player.getPosition().z <= w.getZ() + Terrain.getSize() && !w.getIsFrozen()) {
//					player.getPosition().y = w.getHeight();
//				}
//			}
			
//			boolean onTerrain = false;
//			Vector2f offSetToNearestTerrain = null;
//			for (Terrain terr : terrains) {
//				if(player.getPosition().x >= terr.getX() && player.getPosition().x <= terr.getX() + Terrain.getSize() && 
//						player.getPosition().z >= terr.getZ() && player.getPosition().z <= terr.getZ() + Terrain.getSize()) {
					player.move(terrains, waters);
//					onTerrain = true;
//				}else {
////					System.out.println("out");
//					Vector2f currOffset = new Vector2f(terr.getX() - player.getPosition().x, terr.getZ() - player.getPosition().z);
//					
//					
//					if(offSetToNearestTerrain != null) {
//						float currDiff = (float) Math.sqrt(Math.pow(currOffset.x, 2) + Math.pow(currOffset.y, 2));
//						float nearDiff = (float) Math.sqrt(Math.pow(offSetToNearestTerrain.x, 2) + Math.pow(offSetToNearestTerrain.y, 2));
//						
//						if(currDiff < nearDiff) {
//							offSetToNearestTerrain = currOffset;
//						}
//						
//						
//					}else {
//						offSetToNearestTerrain = currOffset;
//					}
//				}
//				
//			}
//			
//			
//			if(!onTerrain) {
//				player.setPosition(new Vector3f(player.getPosition().x + offSetToNearestTerrain.x, player.getPosition().y, player.getPosition().z + offSetToNearestTerrain.y));
//			}
			
			picker.update();
//			Vector3f terrainPoint = picker.getCurrentTerrainPoint();
//			if(terrainPoint != null) {
//				lampEntity.setPosition(terrainPoint);
//				light.setPosition(new Vector3f(terrainPoint.x, terrainPoint.y + 15, terrainPoint.z));
//			}
			
			// -------------------------------------------------------------- ///
			
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			
			//render reflection texture
			fbos.bindReflectionFrameBuffer();
			float distance = 2 * (camera.getPosition().y - water.getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, player, new Vector4f(0, 1, 0, -water.getHeight()+1f));
			camera.getPosition().y += distance;
			camera.invertPitch();
			
			//render refraction texture
			fbos.bindRefractionFrameBuffer();
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, player, new Vector4f(0, -1, 0, water.getHeight()+1f));
			
			
			//render to screen
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			fbos.unbindCurrentFrameBuffer();
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, player, new Vector4f(0, -1, 0, 100000));
			waterRenderer.render(waters, camera, sun);
			
			guiRenderer.render(guis);
			TextMaster.render();
			
			DisplayManager.updateDisplay();
			
		}

		fbos.cleanUp();
		waterShader.cleanUp();
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
