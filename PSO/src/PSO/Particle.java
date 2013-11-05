package PSO;

public class Particle {
	private double[] x;
	private double[] v;
	private double[] p;
	private double[] g;
	static double[] goal; 
	private double c_1, c_2, w;
	private int dimensions;
	
	public Particle(int dimensions, double[] goal, double c1, double c2, double w) {
		this.x = new double[dimensions];
		
//		for (int i = 0; i < dimensions; i++) {
//			x[i] = Math.pow(Math.random()*5,2);
//		}
		
		this.p = new double[dimensions];
		this.v = new double[dimensions];
		
		this.setDimensions(dimensions);
		this.c_1 = c1;
		this.c_2 = c2;
		this.w = w;
		this.goal = goal;
	}
	
	
	public void nextIteration(){
		double r1 = Math.random(), r2 = Math.random();
		for (int i = 0; i < v.length; i++) {
			v[i] = w*v[i]+ (c_1 * r1 *(p[i]-x[i]))+(c_2* r2 *(g[i]-x[i]));
			x[i] = x[i] + v[i];
		}
	}
	
	public double getPosition(int i){
		if(i>x.length || i < 0){
			return -1;
		}
		else return x[i];
	}
	
	public double[] getPositionVector(){
		return x;
	}
	
	public double[] getVelocityVector(){
		return v;
	}
	
	public void updateGlobalPosition(double[] global){
		g = global;
	}
	
	public void updateLocalPosition(double[] local){
		this.p = local;
	}


	public int getDimensions() {
		return dimensions;
	}


	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}
}
