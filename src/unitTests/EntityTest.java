package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

class EntityTest {

	@Test
	void testHitboxBorder() {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
//		
		TexturedModel stN = new TexturedModel(NormalMappedObjLoader.loadOBJ("boulder", loader), 
				new ModelTexture("boulder", loader.loadGameTexture("boulder")));
		stN.getTexture().setNormalMap(loader.loadGameTexture("boulderNormal"));
		stN.getTexture().setShineDamper(20);
		stN.getTexture().setReflectivity(1f);
		
		Entity e = new Entity(1, stN, new Vector3f(100, 50, -60), 0, 0, 0, 1f);
		
		
		
		
		DisplayManager.closeDisplay();
		
		assertEquals(e.getHitbox().getMaxis_positions().size(), 6);
		
//		assertEquals(0, 0);
		
	}

}
