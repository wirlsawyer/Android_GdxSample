package com.example.android_gdxsample;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGDX implements ApplicationListener {
	OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;


	@Override
	public void create() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(onTouchEvent);
		
		int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.position.set(w/2, h/2, 0);
        camera.update();
        
		batch = new SpriteBatch();
		texture = new Texture("badlogic.jpg");
		sprite = new Sprite(texture);
		sprite.setBounds( camera.position.x - (sprite.getWidth()/2), camera.position.y - (sprite.getHeight()/2), sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		texture.dispose();
	}

	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.draw(texture, 0, 0);
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	private InputAdapter onTouchEvent = new InputAdapter(){
		@Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {

            if(sprite.getBoundingRectangle().contains(screenX, screenY))
            {
            	Log.v("MainGDX", "Sprite Clicked");
            }
                 
            return true;
        }

	};  
}
