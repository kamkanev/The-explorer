package collision_detection;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Hitbox {
	
	private List<Vector3f> local_Positions = new ArrayList<Vector3f>();
	private List<Vector3f> world_positions = new ArrayList<Vector3f>();
	private Matrix4f transMat = null;
	private List<Vector3f> absolutePoints = new ArrayList<Vector3f>();
	
	public Hitbox(List<Vector3f> vectors) {
		local_Positions = vectors;
	}
	
	public Hitbox(List<Vector3f> vectors, Matrix4f matrix) {
		local_Positions = vectors;
		transMat = matrix;
		
		world_positions = calculateWorldPosition(vectors, matrix);
	}
	
	public List<Vector3f> getWorld_positions() {
		return world_positions;
	}

	public void setTransformationMatrix(Matrix4f matrix) {
		transMat = matrix;
	}
	
	private List<Vector3f> calculateWorldPosition(List<Vector3f> local_Pos, Matrix4f mat){
		
		List<Vector3f> worldPos = new ArrayList<Vector3f>();
		
		for(Vector3f vec : local_Pos) {
			Vector3f newVec = new Vector3f();
			
			newVec.x = mat.m00 * vec.x + mat.m01 * vec.y + mat.m02 * vec.z * mat.m03 * 1.0f;
			newVec.y = mat.m10 * vec.x + mat.m11 * vec.y + mat.m12 * vec.z * mat.m13 * 1.0f;
			newVec.z = mat.m20 * vec.x + mat.m21 * vec.y + mat.m22 * vec.z * mat.m23 * 1.0f;
			
			worldPos.add(newVec);
			
		}
		
		local_Positions = null;
		
		return worldPos;
		
	}
	
	public void calculateWorldPosition(){
		
		List<Vector3f> worldPos = new ArrayList<Vector3f>();
		
		if(local_Positions != null) {
			for(Vector3f vec : local_Positions) {
				Vector3f newVec = new Vector3f();
				
				Matrix4f mat = transMat;
				
				newVec.x = mat.m00 * vec.x + mat.m01 * vec.y + mat.m02 * vec.z * mat.m03 * 1.0f;
				newVec.y = mat.m10 * vec.x + mat.m11 * vec.y + mat.m12 * vec.z * mat.m13 * 1.0f;
				newVec.z = mat.m20 * vec.x + mat.m21 * vec.y + mat.m22 * vec.z * mat.m23 * 1.0f;
				
				worldPos.add(newVec);
				
			}
		}
		
//		System.out.println(local_Positions);
		
		world_positions = worldPos;
		local_Positions = null;
		
	}
	

}
