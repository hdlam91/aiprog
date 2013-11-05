
public class Particle {
	double[] x;
	double[] v;
	static double[] p;
	static double[] goal; 
	double[] g;
	double c_1, c_2;
	
	public Particle(int dimension, double[] goal, double c1, double c2) {
		x = new double[dimension];
		
		for (int i = 0; i < dimension; i++) {
			x[i] = Math.pow(Math.random()*5,2);
		}
		
		p = new double[dimension];
		v = new double[dimension];
		c_1 = c1;
		c_2 = c2;
		this.goal = goal;
	}
	
	
	public void nextIteration(){
		for (int i = 0; i < v.length; i++) {
			v[i] = v[i]+ (c_1 * Math.random()*(p[i]-x[i]))+(c_2*Math.random()*(g[i]-x[i]));
			x[i] = x[i] + v[i];
		}
	}
	
	public int getVectorSize(){
		return x.length;
	}
	
	public double getPosition(int i){
		if(i>x.length || i < 0){
			return -1;
		}
		else return x[i];
	}
	
	public void setBestGlobalPos(double []pos){
		p = pos.clone();
	}
	
}
