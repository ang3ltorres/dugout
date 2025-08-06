package io.github.ang3ltorres.dugout;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class KeyboardInput extends InputAdapter
{

	public static class KeyState
	{
		public boolean pressed = false;
		public boolean down = false;
		public boolean up = false;
		boolean lastFrameDown = false;
	}

	public static final Map<Integer, KeyState> keys = new HashMap<>();
	public static final int[] trackedKeys = { Keys.A, Keys.S, Keys.W, Keys.D };

	public KeyboardInput()
	{
		for (int key : trackedKeys)
			keys.put(key, new KeyState());
	}

	@Override
	public boolean keyDown(int keycode)
	{
		KeyState state = keys.get(keycode);

		if (state != null)
			state.down = true;
			
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		KeyState state = keys.get(keycode);

		if (state != null)
			state.down = false;
			
		return false;
	}

	public void update()
	{
		for (Map.Entry<Integer, KeyState> entry : keys.entrySet())
		{
			KeyState state = entry.getValue();

			// Transitions
			boolean wasDown = state.lastFrameDown;
			boolean isDown = state.down;

			state.pressed = !wasDown && isDown;
			state.up = wasDown && !isDown;

			state.lastFrameDown = isDown;
		}
	}
}

