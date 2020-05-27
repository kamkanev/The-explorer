package entities;

import models.TexturedModel;
import toolbox.Maths;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import collision_detection.Hitbox;

public class Entity {

	private TexturedModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	private Hitbox hitbox = null;
	
	private int textureIndex = 0;
	
	private int id = 1;
	
	protected String name = "entity";
	protected boolean isAlive = false;
	private int worldId = 1;

	public Entity(int worldId, TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.worldId = worldId;
		
		Matrix4f mat = Maths.createTransformationMatrix(position, rotX, rotY, rotZ, scale);
		
//		System.err.println(mat.m00 + " " + mat.m10 + " " + mat.m20 + " " + mat.m30);
//		System.err.println("-------------------");
//		System.err.println(mat);
		
		hitbox = new Hitbox(this.getModel().getRawModel().getHitbox().getLocal_positions(), mat);
		
//		this.model.getRawModel().getHitbox().setTransformationMatrix(mat);
//		this.model.getRawModel().getHitbox().calculateWorldPositionOutSide();
		
//		System.err.println("normal "+this.model.getRawModel().getHitbox().getWorld_positions());
	}
	
	public Entity(int worldId, TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		this.model = model;
		this.textureIndex = index;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.worldId = worldId;
		
		Matrix4f mat = Maths.createTransformationMatrix(position, rotX, rotY, rotZ, scale);
		
//		System.err.println(mat.m00 + " " + mat.m10 + " " + mat.m20 + " " + mat.m30);
//		System.err.println("-------------------");
//		System.err.println(mat);
		
		hitbox = new Hitbox(this.getModel().getRawModel().getHitbox().getLocal_positions(), mat);
		
//		this.model.getRawModel().getHitbox().setTransformationMatrix(Maths.createTransformationMatrix(position, rotX, rotY, rotZ, scale));
//		this.model.getRawModel().getHitbox().calculateWorldPositionOutSide();
		
//		System.err.println("atlas "+this.model.getRawModel().getHitbox().getLocal_positions());
	}
	
	public boolean areCollidingWithPoint(Vector3f point) {
		
		Hitbox box = hitbox;
		
//		System.err.println(box.getMaxis_positions());
//		System.err.println("::::::::::::::::::::::");
//		System.err.println(box.getMaxis1_positions());
//		System.err.println("---------------------");
//		System.err.println(point);
//		System.err.println("oooooooooooooooooooooooo");
//		System.err.println(hitbox.getTransformationMatrix());
		
		if(point.x >= box.getMaxis_positions().get(0) && point.x <= box.getMaxis_positions().get(1) && 
			point.y >= box.getMaxis_positions().get(2) && point.y <= box.getMaxis_positions().get(3) && 
			point.z >= box.getMaxis_positions().get(4) && point.z <= box.getMaxis_positions().get(5)) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public float getTextureXOffset() {
		int column = textureIndex % model.getTexture().getNumberOfRows();
		return (float)column / (float)model.getTexture().getNumberOfRows();
	}
	
	public float getTextureYOffset() {
		int row = textureIndex / model.getTexture().getNumberOfRows();
		return (float)row / (float)model.getTexture().getNumberOfRows();
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}

	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	public int getWorldId() {
		return worldId;
	}
	
	public int getId() {
		return id;
	}
	public boolean getIsAlive() {
		return isAlive;
	}
	public String getName() {
		return name;
	}
	public int gettextureIndex() {
		return textureIndex;
	}

}
