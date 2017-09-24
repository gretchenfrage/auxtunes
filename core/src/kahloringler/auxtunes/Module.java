package kahloringler.auxtunes;

import java.util.List;

public interface Module {

    List<PlaylistCreator> getPlaylistCreators();

    ModuleSettings getSettings();

}
