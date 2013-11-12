package it3105.aiprog.pso;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PSO extends Game{

	
	Texture particleTexture,goalTexture;
	SpriteBatch batch;
	float[][] pos = new float[20][2];
	int w;
	int h;
	Circle c; 
	Particle[] p;
	OrthographicCamera cam;
	
	@Override
	public void create() {
		w = (Gdx.graphics.getWidth()) ;
		h = (Gdx.graphics.getHeight()) ;
		cam = new OrthographicCamera();
		cam.setToOrtho(false,w,h);
		cam.zoom = -0.05f;
		cam.update();
		// TODO Auto-generated method stub
		//batch = new SpriteBatch();
		batch = new SpriteBatch();
		
		batch.setProjectionMatrix(cam.combined);
		particleTexture = new Texture(Gdx.files.internal("data/particle.png"));
		goalTexture = new Texture(Gdx.files.internal("data/goal.png"));
		particleTexture.setFilter(TextureFilter.Linear,	 TextureFilter.Linear);
		randomize();
		c = new Circle(2, 0, 2);
		Gdx.input.setInputProcessor(new InputHandler(c, this));
		
	}
	
	public void reset(Circle c){
		this.c = c; 
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        // Camera --------------------- /
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.iter();
		p = c.getParticles();
		
		batch.begin();
//		for (int i = 0; i < pos.length; i++) {
//			batch.draw(particleTexture, pos[i][0], pos[i][1], 1, 1, 2, 2, 1, 1, 0, 0, 0, 2, 2, false, false);
//		}
		for (int i = 0; i < p.length; i++) {
			if(p[i].getDimensions() >= 2)
				batch.draw(particleTexture, (float)p[i].getPositionVector()[0]+(w/2f), (float)p[i].getPositionVector()[1]+(h/2f), 1, 1, 2, 2, 1, 1, 0, 0, 0, 2, 2, false, false);
			else
				batch.draw(particleTexture, (float)p[i].getPositionVector()[0]+(w/2f), h/2f, 1, 1, 2, 2, 1, 1, 0, 0, 0, 2, 2, false, false);
				
		}
		batch.draw(goalTexture, w/2f, h/2f, 1, 1, 2, 2, 1, 1, 0, 0, 0, 2, 2, false, false);
		//batch.draw(particleTexture, 20f, 20f, 1, 1, 2, 2, 1, 1, 0, 0, 0, 2, 2, false, false);
		
		//batch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2, ship.getHeight() / 2, ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(), 0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		batch.end();
		//randomize();
		super.render();
	}
	
	
	
	
	private void randomize() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			pos[i][0] = (float) (Math.random()*w);
			pos[i][1] = (float) (Math.random()*h);
		}
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		particleTexture.dispose();
		super.dispose();
	}
	
	
	
	
}