package kahloringler.auxtunes.gui.tree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.UUID;

public class GUIImage implements GUITree {

    public final Pixmap pixmap;
    public final Color bgColor;
    public final Color borderColor;
    public final float borderWidth;

    public final UUID id = UUID.randomUUID();


    @Override
    public UUID getID() {
        return id;
    }

}
