package PSO;

import java.util.Arrays;

public class Particle {
	private double[] x;
	private double[] v;
	private double[] p;
	private double[] g;
	static double[] goal; 
	private double c_1, c_2, w, lowerCap, upperCap;
	private int dimensions, maxIteration;
	private boolean inertia;
	
	
	public Particle(int dimensions, double[] goal, double c1, double c2, boolean inertia, int maxIter, int lowerCap, int upperCap) {
		this.x = new double[dimensions];
		this.v = new double[dimensions];
		this.g = new double[dimensions];		
		this.p = new double[dimensions];
		this.setDimensions(dimensions);
		this.c_1 = c1;
		this.c_2 = c2;
		this.w = 0.7298; //hax... w = 0.7298
		this.goal = goal;
		this.inertia = inertia;
		this.maxIteration = maxIter;
		this.lowerCap = lowerCap*1.0;
		this.upperCap = upperCap*1.0;
	}
	
	
	public void nextIteration(){
		double r1 = Math.random(), r2 = Math.random();
		for (int i = 0; i < v.length; i++) {
			v[i] = w*v[i]+ (c_1 * r1 *(p[i]-x[i]))+(c_2 * r2 *(g[i]-x[i]));
			
			//clamping
			if(v[i]>upperCap)
				v[i] = upperCap;
			else if(v[i]<-upperCap)
				v[i] = -upperCap;
			
			x[i] = x[i] + v[i];
		}
		if(inertia)
			adjustInertia();
	}
	
	
	public double[] initializeParticle(){
		double valueArea = upperCap-lowerCap;
		for (int i = 0; i < dimensions; i++) {
			x[i] = Math.random()*valueArea;
			v[i] = Math.random()*valueArea - Math.random()*valueArea;
		}
		this.p = x.clone();
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
		this.g = global.clone();
	}
	
	public double [] getGlobal(){
		return g;
	}
	
	
	
	public void setLocalPosition(double[] local){
		this.p = local.clone();
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
	
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("pos "+Arrays.toString(x)+"\n");
		buf.append("vel "+Arrays.toString(v)+"\n");
		buf.append("loc "+Arrays.toString(p)+"\n");
		buf.append("glo "+Arrays.toString(g)+"\n\n");
		return buf.toString();
	}
}