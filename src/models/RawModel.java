package models;

import collision_detection.Hitbox;

public class RawModel {
	
	private int vaoID;
	private int vertexCount;
	private Hitbox hitbox = null;
	
	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	public RawModel(int vaoID, int vertexCount, Hitbox box){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.setHitbox(box);
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	

}
