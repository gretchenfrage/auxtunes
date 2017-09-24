package kahloringler.auxtunes.gui;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import kahloringler.auxtunes.gui.tree.GUITree;

import java.util.HashMap;
import java.util.Map;

public class Renderer {

    private Stage stage;
    private GUITree tree;

    public void setTree(GUITree tree) {
        this.tree = tree;
        if (stage != null) {
            stage.dispose();
        }
        stage = new Stage(new ScreenViewport());

    }


}