package guis;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import collision_detection.Hitbox2d;
import toolbox.Maths;

public class GuiTexture {
	
	private int texture;
	private Vector2f position;
	private Vector2f scale;
	private Hitbox2d hitbox = null;
	
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		super();
		this.texture = texture;
		this.position = position;
		this.scale = scale;
		
		Matrix4f mat = Maths.createTransformationMatrix(position, scale);
		
		hitbox = new Hitbox2d(mat);
	}
	
	public boolean areCollidingWithPoint(Vector2f point) {
		
		List<Vector2f> border = hitbox.getGUIMaxPositions();
		
		if(point.x >= border.get(0).x && point.x <= border.get(2).x &&
				point.y >= border.get(1).y && point.y <= border.get(0).y) {
			return true;
		}
		
		return false;
		
	}

	public int getTexture() {
		return texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public Vector2f getScale() {
		return scale;
	}
	
	public void setPosition(Vector2f positions) {
		this.position = positions;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

}
