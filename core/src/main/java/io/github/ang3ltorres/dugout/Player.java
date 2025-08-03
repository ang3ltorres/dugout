package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Player
{
	int health;
	Rectangle rect;
	int xd = 0;

	public Player(float posX, float posY, int health)
	{
		this.health = health;
		rect = new Rectangle(posX, posY, 32, 32);
	}

	public void update()
	{
		// Level.collision(rect);

		xd++;
		if (Level.collision(rect))
			System.out.println(String.format("** Collision ** %d", xd));
	}

	public void draw(Batch batch)
	{
		batch.draw(Assets.level.region[1], rect.x, rect.y);
	}
}
