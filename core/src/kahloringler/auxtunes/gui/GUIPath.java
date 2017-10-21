package kahloringler.auxtunes.gui;

import jdk.nashorn.internal.runtime.options.Option;

import java.util.Arrays;
import java.util.UUID;

public class GUIPath {
    private UUID[] ids;

    public GUIPath(UUID... ids) {
        this.ids = ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GUIPath guiPath = (GUIPath) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(ids, guiPath.ids);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ids);
    }

    @Override
    public String toString() {
        return Arrays.toString(ids);
    }
}
