package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	OrthographicCamera cam;
	int WIDTH, HEIGHT;

	int imgWidth, imgHeight;
	int imgX, imgY;

	InputHandler handler;
	CameraShake shaker;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
		imgX = 0;
		imgY = 0;

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(WIDTH,HEIGHT);

		handler = new InputHandler(batch, cam, imgX, imgY);
		Gdx.input.setInputProcessor(handler);

		shaker = new CameraShake(cam, 100, batch, null, 50, 95, "v");

		cam.translate(WIDTH/2, HEIGHT/2);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			shaker.start();
		}
		shaker.play();

		batch.begin();
		batch.draw(img, handler.imgX, handler.imgY, imgWidth, imgHeight);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
