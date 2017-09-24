package kahloringler.auxtunes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.File;


public class AuxTunes extends ApplicationAdapter {

	BitmapFont font;
	Label.LabelStyle labelStyle;
	TextField.TextFieldStyle fieldStyle;
	TextButton.TextButtonStyle buttonStyle;
	File directory;
	Stage stage;


	@Override
	public void create() {
		FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/raleway.ttf"));
		FreeTypeFontParameter fontParam = new FreeTypeFontParameter();
		fontParam.size = 22;
		font = fontGen.generateFont(fontParam);

		labelStyle = new Label.LabelStyle(font, Color.BLACK);
		fieldStyle = new TextField.TextFieldStyle();
		fieldStyle.font = font;
		fieldStyle.fontColor = Color.BLACK;
		buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.checkedFontColor = Color.BLACK;
		buttonStyle.checkedOverFontColor = Color.BLACK;
		buttonStyle.downFontColor = Color.BLACK;
		buttonStyle.overFontColor = Color.BLACK;
		buttonStyle.fontColor = Color.BLACK;
		buttonStyle.font = font;

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		compile();
	}

	public void compile() {
		float y = Gdx.graphics.getHeight() - 20;

		Label header = new Label("auxtunes", labelStyle);
		header.setPosition((Gdx.graphics.getWidth() - header.getWidth()) / 2, y - header.getHeight());
		y -= (header.getHeight() + 20);
		stage.addActor(header);

		final TextField field = new TextField("", fieldStyle);
		field.setSize(Gdx.graphics.getWidth() * 0.8f, 20);
		field.setPosition((Gdx.graphics.getWidth() - field.getWidth()) / 2, y - field.getHeight());
		y -= (field.getHeight() + 20);
		stage.addActor(field);

		TextButton button = new TextButton("load", buttonStyle);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				directory = new File(field.getText());
				if (!directory.isDirectory()) {
					directory = null;
				}
				compile();
			}
		});
		button.setSize(Gdx.graphics.getWidth() * 0.8f, 20);
		button.setPosition((Gdx.graphics.getWidth() - button.getWidth()) / 2, y - button.getHeight());
		y -= (button.getHeight() + 20);
		stage.addActor(button);

		if (directory != null) {
			for (final File musFile : directory.listFiles()) {
				TextButton musLabel = new TextButton(musFile.getName(), buttonStyle);
				musLabel.setPosition((Gdx.graphics.getWidth() - musLabel.getWidth()) / 2, y - header.getHeight());
				y -= (musLabel.getHeight() + 20);
				stage.addActor(musLabel);
				musLabel.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						System.out.println("now playing: " + musFile);
						Music music = Gdx.audio.newMusic(Gdx.files.absolute(musFile.getPath()));
						music.play();
					}
				});
			}
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		stage.clear();
		compile();
	}

	@Override
	public void dispose() {

	}

}
