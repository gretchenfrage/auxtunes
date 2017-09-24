package kahloringler.auxtunes;

import kahloringler.auxtunes.guitree.GUITree;

import java.util.Optional;

public interface PlaylistCreator {

    String getTypeName();

    GUITree getGUITree();

    Optional<Playlist> create();

}
