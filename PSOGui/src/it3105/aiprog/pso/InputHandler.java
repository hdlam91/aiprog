package it3105.aiprog.pso;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class InputHandler implements InputProcessor{
	Circle c;
	int dim;
	double low, high;
	PSO pso;
	
	public InputHandler(Circle c, PSO pso) {
		this.c = c;
		this.dim = c.dimensions;
		this.low = c.lowerCap;
		this.high = c.upperCap;
		this.pso = pso;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.R:
			c = new Circle(dim,low, high);
			pso.reset(c);
			break;
		case Keys.NUM_1:
			this.dim = 1;
			c = new Circle(dim,low, high);
			pso.reset(c);
			break;
		case Keys.NUM_2:
			this.dim = 2;
			c = new Circle(dim,low, high);
			pso.reset(c);
			break;
			
		default:
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
