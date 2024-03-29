package PSO;

import java.util.Arrays;

/**
 * The Particle, with both the data required and some methods to perform a PSO search. 
 * @author Eivind
 *
 */

public class Particle{
	protected double[] x;
	protected double[] v;
	protected double[] p;
	private double[] g;
	protected double c_1, c_2, w, lowerCap, upperCap;
	protected int dimensions, maxIteration;
	protected boolean inertia;
	protected int id;

	public Particle(int dimensions, double c1, double c2, boolean inertia, int maxIter, double lowerCap, double upperCap, int id) {
		this.x = new double[dimensions];
		this.v = new double[dimensions];
		this.g = new double[dimensions];		
		this.p = new double[dimensions];
		this.setDimensions(dimensions);
		this.c_1 = c1;
		this.c_2 = c2;
		this.w = 1; 

		this.inertia = inertia;
		this.maxIteration = maxIter;
		this.lowerCap = lowerCap*1.0;
		this.upperCap = upperCap*1.0;
		this.id = id;
	}
	
	public void nextIteration(){
		for (int i = 0; i < v.length; i++) {
			double r1 = Math.random(), r2 = Math.random();
			v[i] = (w*v[i]) + (c_1 * r1 *(p[i]-x[i])) + (c_2 * r2 *(g[i]-x[i]));
			
			//clamping
			if(v[i]>upperCap)
				v[i] = upperCap;
			else if(v[i]<-upperCap)
				v[i] = -upperCap;
		}
		updatePosition();
		if(inertia)
			adjustInertia();
	}
	
	public void updatePosition(){
		for (int i = 0; i < v.length; i++) {
			x[i] = x[i] + v[i];
		}
	}
	
	public double[] initializeParticle(){
		double valueArea = upperCap-lowerCap;
		for (int i = 0; i < dimensions; i++) {
			x[i] = Math.random()*valueArea;
			v[i] = Math.random() > 0.5? Math.random()*valueArea : -Math.random()*valueArea;
		}
		setLocalPosition(x);
		return p;
	}
	
	protected void adjustInertia(){
		if(w > 0.4)
			w = w-(.6/maxIteration);
		else
			w = 0.4;
	}
	
	public double getDistance(Particle p){
		if(p==this)
			return Double.MAX_VALUE;
		double distance = 0;
		for (int j = 0; j < dimensions; j++) {
			distance += Math.pow(p.getPositionVector()[j]-x[j],2);
		}
		return Math.sqrt(distance);
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
	
	public double [] getGlobalPosition(){
		return g;
	}
	
	public void setLocalPosition(double[] local){
		this.p = local.clone();
	}
	
	public double[] getLocalPosition(){
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
		buf.append("id  " + id+"\n");
		buf.append("pos "+Arrays.toString(x)+"\n");
		buf.append("vel "+Arrays.toString(v)+"\n");
		buf.append("loc "+Arrays.toString(p)+"\n");
		buf.append("glo "+Arrays.toString(g)+"\n");
		return buf.toString();
	}
}
