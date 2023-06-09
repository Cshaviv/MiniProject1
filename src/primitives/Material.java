/**
 * 
 */
package primitives;

/**
 * @author Chagit and Yael
 *
 */
public class Material 
{
	public double kD=0;
	public double kS=0;
	public int nShininess=0;
	public double kT=0.0;//factor transparency
	public double kR=0.0;//factor of reflection
	
	/**setter that return Material
	 * @param kD the kD to set
	 * @return Material
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}
	
	/**setter that return Material
	 * @param kS the kS to set
	 * @return Material
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}
	
	/**setter that return Material
	 * @param nShininess the nShininess to set
	 * @return Material
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}


	/**setter that return Material
	 * @param kT the kT to set
	 *  @return Material
	 */
	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}

	
	/**setter that return Material
	 * @param kR the kR to set
	 *  @return Material
	 */
	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}
	
}
