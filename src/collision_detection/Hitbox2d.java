package collision_detection;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Hitbox2d {
	
	private List<Vector2f> local_Pos = new ArrayList<Vector2f>();
	private List<Vector2f> gui_pos = new ArrayList<Vector2f>();
	
	private Matrix4f transMatrix = null;
	
	public Hitbox2d(Matrix4f mat) {
		transMatrix = mat;
		loadLocal_positions();
		loadGUI_positions();
	}
	
	public void loadGUI_positions() {
		
		if(transMatrix != null & local_Pos.size()>0) {
			
			for(Vector2f vec : local_Pos) {
				Vector2f newVec = new Vector2f();
				
				newVec.x = transMatrix.m00 * vec.x + transMatrix.m10 * vec.y + transMatrix.m20 * 1.0f + transMatrix.m30 * 1.0f;
				newVec.y = transMatrix.m01 * vec.x + transMatrix.m11 * vec.y + transMatrix.m21 * 1.0f + transMatrix.m31 * 1.0f;
				
//				System.out.println(vec.x + "  ---  " + newVec.x);
				
				gui_pos.add(newVec);
				
			}
			
		}
		
	}
	
	public List<Vector2f> getGUIMaxPositions(){
		return gui_pos;
	}
	
	private void loadLocal_positions() {
		
		float[] positions = {-1, 1, -1, -1, 1, 1, 1, -1};
		
		for(int i =0; i < positions.length; i+= 2) {
			
			local_Pos.add(new Vector2f(positions[i], positions[i+1]));
			
		}
		
	}

}
