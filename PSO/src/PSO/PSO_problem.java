package PSO;

public class PSO_problem {
	protected Particle[] particles;
	protected int dimensions;
	
	public PSO_problem(int dimensions, int numParticles ){
		this.dimensions = dimensions;
		this.particles = new Particle[numParticles];
	}
}
