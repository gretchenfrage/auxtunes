package kahloringler.auxtunes.gui.tree;

import com.badlogic.gdx.graphics.Color;
import kahloringler.auxtunes.gui.GUIFont;

import java.util.UUID;

public class GUILabel implements GUITree {

    public final String name;
    public final GUIFont font;
    public final float size;
    public final Color fontColor;
    public final Color bgColor;
    public final Color borderColor;
    public final Color borderWidth;

    public final UUID id = UUID.randomUUID();


    public GUILabel(String name, GUIFont font, float size, Color fontColor, Color bgColor, Color borderColor, Color borderWidth) {
        this.name = name;
        this.font = font;
        this.size = size;
        this.fontColor = fontColor;
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GUILabel guiLabel = (GUILabel) o;

        if (Float.compare(guiLabel.size, size) != 0) return false;
        if (name != null ? !name.equals(guiLabel.name) : guiLabel.name != null) return false;
        if (font != guiLabel.font) return false;
        if (fontColor != null ? !fontColor.equals(guiLabel.fontColor) : guiLabel.fontColor != null) return false;
        if (bgColor != null ? !bgColor.equals(guiLabel.bgColor) : guiLabel.bgColor != null) return false;
        if (borderColor != null ? !borderColor.equals(guiLabel.borderColor) : guiLabel.borderColor != null)
            return false;
        if (borderWidth != null ? !borderWidth.equals(guiLabel.borderWidth) : guiLabel.borderWidth != null)
            return false;
        return id != null ? id.equals(guiLabel.id) : guiLabel.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (font != null ? font.hashCode() : 0);
        result = 31 * result + (size != +0.0f ? Float.floatToIntBits(size) : 0);
        result = 31 * result + (fontColor != null ? fontColor.hashCode() : 0);
        result = 31 * result + (bgColor != null ? bgColor.hashCode() : 0);
        result = 31 * result + (borderColor != null ? borderColor.hashCode() : 0);
        result = 31 * result + (borderWidth != null ? borderWidth.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
