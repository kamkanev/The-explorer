package entities;

import java.util.Collection;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import models.TexturedModel;
import renderEngine.DisplayManager;
import terrains.Terrain;
import water.WaterTile;

public class Player extends Entity {
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 20;
	
	private static final float TERRAIN_HEIGHT = 0;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	private boolean isInAir = false;
	
	private int health = 100;
	private int maxHealth = 100;
	private int level = 0;
	private int xp = 0;
	private int id = 1;
	private float radius = 2;

	public Player(int worldId, int id, String name, TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(worldId, model, position, rotX, rotY, rotZ, scale);
		this.isAlive = true;
		this.name = name;
		this.id = id;
		this.radius = this.getHitbox().getMaxis_positions().get(3) - this.getHitbox().getMaxis_positions().get(2);
//		System.err.println(radius);
//		System.err.println("Player " + id);
	}
	
	public void move(Terrain terrain) {

		checkInputs();
		super.increaseRotation(0, this.currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = this.currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeightv = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y < terrainHeightv) {
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = terrainHeightv;
		}
		
	}
	
	public void move(Collection<Terrain> terrains, Collection<WaterTile> waters) {
		checkInputs();
		super.increaseRotation(0, this.currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = this.currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		
		float terrainHeightv = 0;
		float waterHeightv = 0;
		boolean isInWater = false;
		
		for(WaterTile water : waters) {
			if(this.getPosition().x >= water.getX() - water.TILE_SIZE && this.getPosition().x <= water.getX() + water.TILE_SIZE && 
					this.getPosition().z >= water.getZ() - water.TILE_SIZE && this.getPosition().z <= water.getZ() + water.TILE_SIZE && 
					water.getIsFrozen()) {
				waterHeightv = water.getHeight();
				isInWater = true;
				break;
			}
		}
		
		for(Terrain terrain : terrains) {
			if(this.getPosition().x >= terrain.getX() && this.getPosition().x <= terrain.getX() + terrain.getSize() && 
					this.getPosition().z >= terrain.getZ() && this.getPosition().z <= terrain.getZ() + terrain.getSize()) {
				terrainHeightv = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
				
				break;
			}
		}
		
		if(isInWater && waterHeightv < terrainHeightv) {
			isInWater = false;
		}
		
		if(!isInWater) {
			if(super.getPosition().y < terrainHeightv) {
				upwardsSpeed = 0;
				isInAir = false;
				super.getPosition().y = terrainHeightv;
			}
		}else {
			if(super.getPosition().y < waterHeightv) {
				upwardsSpeed = 0;
				isInAir = false;
				super.getPosition().y = waterHeightv;
			}
		}
		
	}
	
	private void jump() {
		if(!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}
	
	private void checkInputs() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = RUN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		}else {
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed = TURN_SPEED;
		}else {
			this.currentTurnSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
		
	}
	
	public int getPlayerId() {
		return id;
	}
	public int getLevel() {
		return level;
	}
	public int getXP() {
		return xp;
	}
	public int getHealth() {
		return health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setPlayerId(int id) {
		this.id = id;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setXP(int xp) {
		this.xp = xp;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void increaseXP(int xp) {
		
		this.xp += xp;
		
		if(this.xp > level * 100) {
			
			this.xp -= level * 100;
			this.level ++;
			
		}
		
	}
	
	public void takeDamge(int damage) {
		
		this.health -= damage;
		
		if(health <= 0) {
			this.health = 0;
			//dead
		}
		
	}

	public GUIText getPositionToText(FontType font) {
		
		String text = ""+String.format("%.0f", this.getPosition().x)+" / "+String.format("%.0f", this.getPosition().y)+" / "+String.format("%.0f", this.getPosition().z);
		GUIText pos = new GUIText(text, 1f, font, new Vector2f(0.85f ,0 ), 0.2f, true);
		
		pos.setColour(1f, 1f, 1f);
		
		return pos;
		
	}

}
