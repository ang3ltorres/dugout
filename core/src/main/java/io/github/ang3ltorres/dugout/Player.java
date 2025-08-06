package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import io.github.ang3ltorres.dugout.KeyboardInput;

public class Player
{
	int health;
	Rectangle rect;

	public Player(float posX, float posY, int health)
	{
		this.health = health;
		rect = new Rectangle(posX, posY, 32, 32);
	}

	public void update()
	{
		if (KeyboardInput.keys.get(Keys.D).down) rect.x ++;
		if (KeyboardInput.keys.get(Keys.A).down) rect.x --;

		rect.y += 2.0f;

		while (Level.collision(rect))
			rect.y -= 1.0f;
	}

	public void draw(Batch batch)
	{
		batch.draw(Assets.level.region[1], rect.x, rect.y);
	}
}
