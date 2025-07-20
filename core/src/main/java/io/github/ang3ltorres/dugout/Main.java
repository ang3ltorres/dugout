package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.ang3ltorres.dugout.level.Level;
import io.github.ang3ltorres.dugout.level.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.OrthographicCamera;

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

  private Level levelTest;

  public static Assets assets;

  private int frameWidth  = 256;
  private int frameHeight = 256;
  private int scale       = 1;
  private int offsetX     = 0;
  private int offsetY     = 0;

  @Override
  public void create()
  {
    batch = new SpriteBatch();
    texture = new Texture(Gdx.files.internal("png/character.png"));
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
    offsetX = (screenW - frameWidth * scale) / 2;
    offsetY = (screenH - frameHeight * scale) / 2;

    // Cameras
    internalCamera = new OrthographicCamera();
    internalCamera.setToOrtho(true, frameWidth, frameHeight);

    screenCamera = new OrthographicCamera();
    screenCamera.setToOrtho(true, screenW, screenH);

    //
    assets = new Assets();
    levelTest = new Test();
  }

  @Override
  public void render()
  {
    // Draw to framebuffer
    framebuffer.begin();
    ScreenUtils.clear(250.0f / 255.0f, 128.0f / 255.0f, 114.0f / 255.0f, 1.0f);
    batch.setProjectionMatrix(internalCamera.combined);
    batch.begin();
    // batch.draw(sprite, 0, 0);
    levelTest.draw(batch);
    batch.end();
    framebuffer.end();

    // Draw framebuffer to screen
    ScreenUtils.clear(0.9f, 0.8f, 0.8f, 1.0f);
    batch.setProjectionMatrix(screenCamera.combined);
    batch.begin();
    batch.draw(framebufferTexture, offsetX, offsetY, frameWidth * scale, frameHeight * scale);
    batch.end();
  }
  
	@Override
	public void resize (int width, int height)
  {
    scale = Math.min(width / frameWidth, height / frameHeight);
    offsetX = (width - frameWidth * scale) / 2;
    offsetY = (height - frameHeight * scale) / 2;

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
