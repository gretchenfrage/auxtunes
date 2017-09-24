package kahloringler.auxtunes.gui.tree;

import java.util.Arrays;
import java.util.UUID;

public class GUITable implements GUITree {

    public final int width;
    public final int height;
    public final GUITree[][] contents;

    public final UUID id = UUID.randomUUID();

    public GUITable(int width, int height) {
        this.width = width;
        this.height = height;
        contents = new GUITree[width][height];
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GUITable guiTable = (GUITable) o;

        if (width != guiTable.width) return false;
        if (height != guiTable.height) return false;
        if (!Arrays.deepEquals(contents, guiTable.contents)) return false;
        return id != null ? id.equals(guiTable.id) : guiTable.id == null;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + Arrays.deepHashCode(contents);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
