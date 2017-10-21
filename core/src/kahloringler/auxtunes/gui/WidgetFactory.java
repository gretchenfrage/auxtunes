package kahloringler.auxtunes.gui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory implements Disposable {

    public static int colorToRGBA8888(Color color) {
        return (((int) (color.r * 255)) << 24) |
                (((int) (color.g * 255)) << 16) |
                (((int) (color.b * 255)) << 8) |
                ((int) (color.a * 255));
    }

    private interface ResourceDescriptor<E extends Disposable> {
        E generate();
    }
    private class FTFGDescriptor implements ResourceDescriptor<FreeTypeFontGenerator> {
        FileHandle ttf;
        public FTFGDescriptor(FileHandle ttf) {
            this.ttf = ttf;
        }
        @Override
        public FreeTypeFontGenerator generate() {
            return new FreeTypeFontGenerator(ttf);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FTFGDescriptor that = (FTFGDescriptor) o;
            return ttf != null ? ttf.equals(that.ttf) : that.ttf == null;
        }
        @Override
        public int hashCode() {
            return ttf != null ? ttf.hashCode() : 0;
        }
    }
    private class FontDescriptor implements ResourceDescriptor<BitmapFont> {
        FileHandle ttf;
        int size;
        public FontDescriptor(FileHandle ttf, int size) {
            this.ttf = ttf;
            this.size = size;
        }
        @Override
        public BitmapFont generate() {
            FreeTypeFontGenerator generator = resource(new FTFGDescriptor(ttf));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = size;
            return generator.generateFont(parameter);
        }
    }
    private class MonochromeTextureDescriptor implements ResourceDescriptor<Texture> {
        Color color;
        public MonochromeTextureDescriptor(Color color) {
            this.color = color;
        }
        @Override
        public Texture generate() {
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.drawPixel(0, 0, colorToRGBA8888(color));
            return new Texture(pixmap);
        }
    }

    private Map<ResourceDescriptor<?>, Disposable> resources = new HashMap<>();

    private <E extends Disposable> E resource(ResourceDescriptor<E> descriptor) {
        if (resources.containsKey(descriptor)) {
            return (E) resources.get(descriptor);
        } else {
            E resource = descriptor.generate();
            resources.put(descriptor, resource);
            return resource;
        }
    }

    public Widget label(String text, FileHandle ttf, int size, Color color) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = resource(new FontDescriptor(ttf, size));
        style.fontColor = color;
        return new Label(text, style);
    }

    public Table table() {
        return new Table();
    }

    public Texture monochromeTexture(Color color) {
        return resource(new MonochromeTextureDescriptor(color));
    }

    public Drawable monochromeBackground(Color color) {
        return new TextureRegionDrawable(new TextureRegion(monochromeTexture(color)));
    }

    @Override
    public void dispose() {
        resources.values().forEach(Disposable::dispose);
    }
}
