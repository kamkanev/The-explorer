package collision_detection;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Hitbox implements Cloneable{
	
	private List<Vector3f> local_Positions = new ArrayList<Vector3f>();
	private List<Vector3f> world_positions = new ArrayList<Vector3f>();
	private Matrix4f transMat = null;
	private List<Vector3f> absolutePoints = new ArrayList<Vector3f>();
	private List<Float> boxi = new ArrayList<Float>();
	private List<Vector3f> boxi1 = new ArrayList<Vector3f>();
	
	public Hitbox(List<Vector3f> vectors) {
		local_Positions = vectors;
	}
	
	public Hitbox(List<Vector3f> vectors, Matrix4f matrix) {
		local_Positions = vectors;
		transMat = matrix;
		
		calculateWorldPositionOutSide();
//		calculateCubeHitBox();
	}
	
	public List<Vector3f> getWorld_positions() {
		return world_positions;
	}
	
	public List<Vector3f> getLocal_positions() {
		return local_Positions;
	}

	public List<Float> getMaxis_positions() {
		return boxi;
	}
	
	public List<Vector3f> getMaxis1_positions() {
		return boxi1;
	}
	
	public void setTransformationMatrix(Matrix4f matrix) {
		transMat = matrix;
	}
	public Matrix4f getTransformationMatrix() {
		return transMat;
	}
	
	private List<Vector3f> calculateWorldPosition(List<Vector3f> local_Pos, Matrix4f mat){
		
		List<Vector3f> worldPos = new ArrayList<Vector3f>();
		
		for(Vector3f vec : local_Pos) {
			Vector3f newVec = new Vector3f();
			
			newVec.x = mat.m00 * vec.x + mat.m10 * vec.y + mat.m20 * vec.z + mat.m30 * 1.0f;
			newVec.y = mat.m01 * vec.x + mat.m11 * vec.y + mat.m21 * vec.z + mat.m31 * 1.0f;
			newVec.z = mat.m02 * vec.x + mat.m12 * vec.y + mat.m22 * vec.z + mat.m32 * 1.0f;
			
//			System.out.println(vec.x + "  ---  " + newVec.x);
			
			worldPos.add(newVec);
			
		}
		
		
		
//		local_Positions = null;
		
		return worldPos;
		
	}
	
	public void calculateWorldPositionOutSide(){
		
		List<Vector3f> worldPos = new ArrayList<Vector3f>();
		
		if(local_Positions != null) {
			for(Vector3f vec : local_Positions) {
				Vector3f newVec = new Vector3f();
				
				Matrix4f mat = transMat;
				
				newVec.x = mat.m00 * vec.x + mat.m10 * vec.y + mat.m20 * vec.z + mat.m30 * 1.0f;
				newVec.y = mat.m01 * vec.x + mat.m11 * vec.y + mat.m21 * vec.z + mat.m31 * 1.0f;
				newVec.z = mat.m02 * vec.x + mat.m12 * vec.y + mat.m22 * vec.z + mat.m32 * 1.0f;
				
//				System.err.println(mat.m00 + " " + mat.m10 + " " + mat.m20 + " " + mat.m30);
//				System.err.println("-------------------------");
//				System.err.println(newVec);
				worldPos.add(newVec);
				
//				if(vec.x == 0 && vec.y == 0 && vec.z == 0) {
//					
//					boxi.add(newVec);
//					
//				}
				
//				System.err.println(vec.x + "  ---  " + newVec.x);
				
			}
		}
		
//		System.out.println(local_Positions);
		
		world_positions = worldPos;
		
		calculateCubeHitBox();
//		local_Positions = null;
		
	}
	
	private void calculateCubeHitBox() {
		
//		System.err.println(world_positions);
		
		if(world_positions != null && world_positions.size() > 0) {
			float minX = world_positions.get(0).x, maxX = world_positions.get(0).x,
					minY = world_positions.get(0).y, maxY = world_positions.get(0).y,
					minZ = world_positions.get(0).z, maxZ = world_positions.get(0).z;
			
			Vector3f minX1 = world_positions.get(0), maxX1 = world_positions.get(0),
					minY1 = world_positions.get(0), maxY1 = world_positions.get(0),
					minZ1 = world_positions.get(0), maxZ1 = world_positions.get(0);
			
			for(Vector3f vec : world_positions) {
				
				if(vec.x < minX) {
					minX = vec.x;
					minX1 = vec;
				}
				
				if(vec.x > maxX) {
					maxX = vec.x;
					maxX1 = vec;
				}
				
				if(vec.y < minY) {
					minY = vec.y;
					minY1 = vec;
				}
				
				if(vec.y > maxY) {
					maxY = vec.y;
					maxY1 = vec;
				}
				
				if(vec.z < minZ) {
					minZ = vec.z;
					minZ1 = vec;
				}
				
				if(vec.z > maxZ) {
					maxZ = vec.z;
					maxZ1 = vec;
				}
			}
		
			
			boxi.add(minX);
			boxi.add(maxX);
			
			boxi.add(minY);
			boxi.add(maxY);
			
			boxi.add(minZ);
			boxi.add(maxZ);
			
			boxi1.add(minX1);
			boxi1.add(maxX1);
			
			boxi1.add(minY1);
			boxi1.add(maxY1);
			
			boxi1.add(minZ1);
			boxi1.add(maxZ1);
			
		}
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}
	

}
