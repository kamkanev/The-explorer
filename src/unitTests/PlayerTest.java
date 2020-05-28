package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.util.vector.Vector3f;

import entities.Player;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

class PlayerTest {

	@Test
	void testPlayerMoving() {
		
		float moveF = 10;
		Vector3f oldPlayerPos = new Vector3f(50, 20, -9);
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		TexturedModel plModel = new TexturedModel(OBJLoader.loadObjModel("person", loader), 
				new ModelTexture("playerTexture", loader.loadGameTexture("playerTexture")));
		
		Player pl = new Player(1, 1, "Kiro", plModel, new Vector3f(oldPlayerPos), 0, 0, 0, 1f);
		
		pl.increasePosition(0, 0, moveF);
		
		DisplayManager.closeDisplay();
		
		assertEquals(pl.getPosition().z, oldPlayerPos.z + moveF);
		
	}
	
	@Test
	void testPlayerXp() {
		
		int xp = 20;
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		TexturedModel plModel = new TexturedModel(OBJLoader.loadObjModel("person", loader), 
				new ModelTexture("playerTexture", loader.loadGameTexture("playerTexture")));
		
		Player pl = new Player(1, 1, "Kiro", plModel, new Vector3f(50, 20, -9), 0, 0, 0, 1f);
		
		int oldXp = pl.getXP();
		
		pl.increaseXP(xp);
		
		DisplayManager.closeDisplay();
		
		assertEquals(pl.getXP(), oldXp + xp);

		
	}
	
	@Test
	void testPlayerLevingUp() {
		
		int xp = 20;
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		TexturedModel plModel = new TexturedModel(OBJLoader.loadObjModel("person", loader), 
				new ModelTexture("playerTexture", loader.loadGameTexture("playerTexture")));
		
		Player pl = new Player(1, 1, "Kiro", plModel, new Vector3f(50, 20, -9), 0, 0, 0, 1f);
		
		int oldlevel = pl.getLevel();
		
		pl.increaseXP(xp);
		
		DisplayManager.closeDisplay();
		
		assertEquals(pl.getLevel(), oldlevel + 1);

		
	}
	
	@Test
	void testPlayerDamageTaking() {
		
		int dam = 20;
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		TexturedModel plModel = new TexturedModel(OBJLoader.loadObjModel("person", loader), 
				new ModelTexture("playerTexture", loader.loadGameTexture("playerTexture")));
		
		Player pl = new Player(1, 1, "Kiro", plModel, new Vector3f(50, 20, -9), 0, 0, 0, 1f);
		
		int oldHealth = pl.getHealth();
		
		pl.takeDamge(dam);
		
		DisplayManager.closeDisplay();
		
		assertEquals(pl.getHealth(), oldHealth - dam);

		
	}

}
