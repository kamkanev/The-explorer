package models;

import collision_detection.Hitbox;

public class RawModel {
	
	private int vaoID;
	private int vertexCount;
	private Hitbox hitbox;
	private String modelName = "";
	
	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		hitbox = null;
	}
	
	public RawModel(int vaoID, int vertexCount, Hitbox box){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.hitbox = box;
	}
	
	public RawModel(String modelName, int vaoID, int vertexCount, Hitbox box){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.hitbox = box;
		this.modelName = modelName;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
	public String getModelName() {
		return modelName;
	}
	
	

}
