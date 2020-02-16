package water;

public class WaterTile {
	
	public static final float TILE_SIZE = 60;
	
	private float height;
	private float x,z;
	private boolean isFrozen = false;
	
	public WaterTile(float centerX, float centerZ, float height){
		this.x = centerX;
		this.z = centerZ;
		this.height = height;
	}
	
	public WaterTile(float centerX, float centerZ, float height, boolean isFrozen){
		this.x = centerX;
		this.z = centerZ;
		this.height = height;
		this.isFrozen = isFrozen;
	}

	public float getHeight() {
		return height;
	}

	public float getX() {
		return x;
	}

	public float getZ() {
		return z;
	}

	public boolean getIsFrozen() {
		return isFrozen;
	}



}
