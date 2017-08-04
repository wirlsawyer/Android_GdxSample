package com.example.android_gdxsample;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


//http://libgdx.badlogicgames.com/releases/
//https://code.google.com/archive/p/libgdx/downloads?page=1
public class MainGDX implements ApplicationListener {
	OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private java.util.Vector<Sprite> vecSprites = new java.util.Vector<Sprite>();

	private int lastX;
	private int lastY;
	private int spriteIdx;


	BitmapFont font15;
	BitmapFont font22;
	

	
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
		
		String FONT_CHARACTERS = "中文abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/kaiu.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.characters = FONT_CHARACTERS;
		parameter.borderWidth = 3;
		parameter.borderColor = Color.YELLOW;
		parameter.color = Color.BLUE;
		parameter.size = generator.scaleForPixelHeight(15*3);
		font15 = generator.generateFont(parameter);
		
		parameter.borderColor = Color.WHITE;
		parameter.color = Color.RED;
		parameter.size = generator.scaleForPixelHeight(22*3);
		font22 = generator.generateFont(parameter);
		generator.dispose();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		texture.dispose();
		font15.dispose();
		font22.dispose();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i = vecSprites.size() - 1; i >= 0; i--) {
			vecSprites.get(i).draw(batch);
		}
		batch.draw(texture, 0, Gdx.graphics.getHeight()-texture.getHeight());
		
		//font15.setColor(0f, 0f, 0f, 1f);
		font15.draw(batch, "hello", 0, font15.getLineHeight());
		//font22.setColor(0f, 0f, 0f, 1f);
		font22.draw(batch, "This is 中文", 50, 100+font22.getLineHeight());
		
		
		
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
