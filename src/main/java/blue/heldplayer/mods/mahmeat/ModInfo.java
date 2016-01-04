package blue.heldplayer.mods.mahmeat;

import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class ModInfo {

    public final String modId;
    public final String modName;
    public final String modVersion;

    public ModInfo(String modId, String modName) {
        this.modId = modId;
        this.modName = modName;

        Properties prop = new Properties();

        String version = null;

        try {
            InputStream stream = ModInfo.class.getClassLoader().getResourceAsStream(this.modId.toLowerCase() + ".version");
            if (stream == null) {
                throw new NullPointerException(String.format("Could not load version file '%s'", this.modId.toLowerCase() + ".version"));
            }
            prop.load(stream);
            stream.close();
            version = prop.getProperty("version");
        } catch (Exception e) {
            version = "MISSING";
            LogManager.getLogger(modId).log(Level.ERROR, "Failed loading mod version", e);
        } finally {
            this.modVersion = version;
        }
    }

    public ModInfo(String modId, String modName, String modVersion) {
        this.modId = modId;
        this.modName = modName;
        this.modVersion = modVersion;
    }
}
