package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter
{
  private SpriteBatch batch;
  private Texture texture;
  private TextureRegion image;
  private Sprite sprite;
  private FrameBuffer framebuffer;
  private Texture framebufferTexture;

  private OrthographicCamera internalCamera;
  private OrthographicCamera screenCamera;

  private int frameWidth  = 256;
  private int frameHeight = 256;
  private int scale       = 1;
  private float offsetX   = 0;
  private float offsetY   = 0;

  @Override
  public void create()
  {
    batch = new SpriteBatch();
    texture = new Texture(Gdx.files.internal("libgdx.png"));
    texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

    image = new TextureRegion(texture);
    image.flip(false, true);
    sprite = new Sprite(image);

    framebuffer = new FrameBuffer(Pixmap.Format.RGBA8888, frameWidth, frameHeight, false);
    framebufferTexture = framebuffer.getColorBufferTexture();
    framebufferTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

    // Scaling & offset
    int screenW = Gdx.graphics.getWidth();
    int screenH = Gdx.graphics.getHeight();
    scale = Math.min(screenW / frameWidth, screenH / frameHeight);
    offsetX = (screenW - frameWidth * scale) / 2.0f;
    offsetY = (screenH - frameHeight * scale) / 2.0f;

    // Cameras
    internalCamera = new OrthographicCamera(frameWidth, frameHeight);
    internalCamera.setToOrtho(true);

    screenCamera = new OrthographicCamera(screenW, screenH);
    screenCamera.setToOrtho(true);
  }

  @Override
  public void render()
  {
    // Draw to framebuffer
    framebuffer.begin();
    ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
    batch.setProjectionMatrix(internalCamera.combined);
    batch.begin();
    batch.draw(sprite, 0, 0, sprite.getWidth() * 2, sprite.getHeight() * 2); // Draw something
    batch.end();
    framebuffer.end();

    // Draw framebuffer to screen
    ScreenUtils.clear(0f, 0f, 0f, 1);
    batch.setProjectionMatrix(screenCamera.combined);
    batch.begin();
    batch.draw(framebufferTexture, offsetX, offsetY, frameWidth * scale, frameHeight * scale);
    batch.end();
  }
  
	@Override
	public void resize (int width, int height)
  {
    scale = Math.min(width / frameWidth, height / frameHeight);
    offsetX = (width - frameWidth * scale) / 2.0f;
    offsetY = (height - frameHeight * scale) / 2.0f;

    screenCamera.setToOrtho(true, width, height);
    screenCamera.update();
	}

  @Override
  public void dispose()
  {
    batch.dispose();
    texture.dispose();
    framebuffer.dispose();
  }
}
