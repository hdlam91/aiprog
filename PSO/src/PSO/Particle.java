package PSO;

public class Particle {
	private double[] x;
	private double[] v;
	private double[] p;
	private double[] g;
	static double[] goal; 
	private double c_1, c_2, w;
	private int dimensions, maxIteration;
	private boolean inertia;
	
	
	public Particle(int dimensions, double[] goal, double c1, double c2, boolean inertia, int maxIter) {
		this.x = new double[dimensions];
		this.v = new double[dimensions];
		this.g = new double[dimensions];		
		this.p = new double[dimensions];
		this.setDimensions(dimensions);
		this.c_1 = c1;
		this.c_2 = c2;
		this.w = 1;
		this.goal = goal;
		this.inertia = inertia;
		this.maxIteration = maxIter;
	}
	
	
	public void nextIteration(){
		double r1 = Math.random(), r2 = Math.random();
		for (int i = 0; i < v.length; i++) {
			v[i] = w*v[i]+ (c_1 * r1 *(p[i]-x[i]))+(c_2* r2 *(g[i]-x[i]));
			x[i] = x[i] + v[i];
		}
		if(inertia)
			adjustInertia();
	}
	
	
	public double[] initializeParticle(double lowerCap, double upperCap){
		double valueArea = upperCap-lowerCap;
		for (int i = 0; i < dimensions; i++) {
			x[i] = Math.random()*valueArea;
			v[i] = Math.random()*valueArea - Math.random()*valueArea;
		}
		this.p = x;
		return p;
	}
	
	
	private void adjustInertia(){
		if(w > 0.4)
			w = w-(.6/maxIteration);
		else
			w = 0.4;
	}
	
	//unused?
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
	
	
	
	public void setGlobalPosition(double[] global){
		this.g = global;
	}
	
	public double [] getGlobal(){
		return g;
	}
	
	
	
	public void setLocalPosition(double[] local){
		this.p = local;
	}
	
	public double[] getLocal(){
		return p;
	}
	
	
	

	public int getDimensions() {
		return dimensions;
	}
	
	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}
}
