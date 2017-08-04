package com.example.android_gdxsample;

import java.util.ArrayList;
import java.util.Vector;

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
	private java.util.Vector<Sprite> vecSprites = new java.util.Vector<Sprite>();

	private int lastX;
	private int lastY;
	private int spriteIdx;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(onTouchEvent);

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		camera.position.set(w / 2, h / 2, 0);
		camera.update();

		batch = new SpriteBatch();
		texture = new Texture("badlogic.jpg");
		for (int i = 0; i < 2; i++) {
			Sprite sprite = new Sprite(texture);
			sprite.setBounds(camera.position.x - (sprite.getWidth() / 2),
					camera.position.y - (sprite.getHeight() / 2),
					sprite.getWidth(), sprite.getHeight());
			vecSprites.add(sprite);
		}
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
		for (int i = vecSprites.size() - 1; i >= 0; i--) {
			vecSprites.get(i).draw(batch);
		}
		batch.draw(texture, 0, 0);
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		camera.position.set(w / 2, h / 2, 0);
		camera.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	private InputAdapter onTouchEvent = new InputAdapter() {
		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			screenY = Gdx.graphics.getHeight() - screenY;
			int count = 0;
			for (Sprite sprite : vecSprites) {
				if (sprite.getBoundingRectangle().contains(screenX, screenY)) {
					lastX = screenX;
					lastY = screenY;
					Log.v("MainGDX", "Sprite " + count + " Clicked");
					vecSprites.remove(count);
					vecSprites.add(0, sprite);
					spriteIdx = 0;
					return true;
				}
				count++;
			}
			spriteIdx = -1;
			lastX = -1;
			lastY = -1;
			return true;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			screenY = Gdx.graphics.getHeight() - screenY;
			Log.v("MainGDX", "touchDragged X:" + screenX + " Y:" + screenY);

			if (spriteIdx != -1) {
				Sprite sprite = vecSprites.get(spriteIdx);
				sprite.setPosition(sprite.getX() + (screenX - lastX),
						sprite.getY() + (screenY - lastY));
				lastX = screenX;
				lastY = screenY;
			}
			return super.touchDragged(screenX, screenY, pointer);
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			lastX = -1;
			lastY = -1;
			spriteIdx = -1;
			return super.touchUp(screenX, screenY, pointer, button);
		}

	};
}
