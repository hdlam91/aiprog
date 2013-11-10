package it3105.aiprog.pso;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PSO extends Game{

	
	Texture particleTexture;
	SpriteBatch batch;
	float[][] pos = new float[20][2];
	int w;
	int h;
	@Override
	public void create() {
		w = (Gdx.graphics.getWidth()) ;
		h = (Gdx.graphics.getHeight()) ;
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		particleTexture = new Texture(Gdx.files.internal("data/particle.png"));
		particleTexture.setFilter(TextureFilter.Linear,	 TextureFilter.Linear);
		randomize();
		
	}
	
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		batch.begin();
		for (int i = 0; i < pos.length; i++) {
			batch.draw(particleTexture, pos[i][0], pos[i][1], 1, 1, 2, 2, 1, 1, 0, 0, 0, 2, 2, false, false);
		}
		//batch.draw(particleTexture, 20f, 20f, 1, 1, 2, 2, 1, 1, 0, 0, 0, 2, 2, false, false);
		
		//batch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2, ship.getHeight() / 2, ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(), 0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		batch.end();
		randomize();
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