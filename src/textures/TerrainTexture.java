package textures;

public class TerrainTexture {
	
	private int textureID;
	private float shineDamper = 1;
	private float reflectivity = 0;

	public TerrainTexture(int textureID) {
		this.textureID = textureID;
	}

	public int getTextureID() {
		return textureID;
	}
	 
	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

}
