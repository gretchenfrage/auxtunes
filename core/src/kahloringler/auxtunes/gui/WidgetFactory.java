package kahloringler.auxtunes.gui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory implements Disposable {

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

    @Override
    public void dispose() {
        resources.values().forEach(Disposable::dispose);
    }
}
