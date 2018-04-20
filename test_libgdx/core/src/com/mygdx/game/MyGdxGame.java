package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x, y;
	float vx, vy;
	Color imgColor;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("DVD_Logo.png");
		x = (float)((Gdx.graphics.getWidth() / 2.0) - (img.getWidth() / 2.0));
		y = (float)((Gdx.graphics.getHeight() / 2.0) - (img.getHeight() / 2.0));
		vx = 3f;
		vy = 3f;
		imgColor = Color.BLUE;
		change_color();
	}

	@Override
	public void render () {
		// set background to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw logo
		batch.begin();
		Color old = new Color(batch.getColor());
		batch.setColor(imgColor);
		batch.draw(img, x, y);
		batch.setColor(old);
		batch.end();

		// check collision with edge
		if (at_edge_h()){
			vx *= -1f;
			change_color();
		}

		if (at_edge_v()){
			vy *= -1f;
			change_color();
		}

		// move logo
		x = x + vx;
		y = y + vy;
	}

	public boolean at_edge_h () {
		float max_x = x + (float)img.getWidth();

		return (x <= 0f || max_x >= (float)Gdx.graphics.getWidth());
	}

	public boolean at_edge_v () {
		float max_y = y + (float)img.getHeight();

		return (y <= 0f || max_y >= (float)Gdx.graphics.getHeight());
	}

	public void change_color () {
		Random rand = new Random();
		float r = rand.nextFloat() / 2f + 0.5f;
		float g = rand.nextFloat() / 2f + 0.5f;
		float b = rand.nextFloat() / 2f + 0.5f;
		imgColor = new Color(r, g, b, 1f);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
