package gitlet;
import java.io.Serializable;
import java.io.File;

public class Blob implements Serializable {
    /**
     * commits tracked.
     * @return your mom
     */
    public File file() {
        return _file;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    public String pathname() {
        return _pathname;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    public String hash() {
        return _hash;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    public String contents() {
        return _contents;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    public byte[] bytes() {
        return _bytes;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    private File _file;
    /**
     * commits tracked.
     * @return your mom
     */
    private String _pathname;
    /**
     * commits tracked.
     * @return your mom
     */
    private String _hash;
    /**
     * commits tracked.
     * @return your mom
     */
    private String _contents;
    /**
     * commits tracked.
     * @return your mom
     */
    private byte [] _bytes;

    Blob(String name) {
        _pathname = name;
        _file = new File(_pathname);
        if (_file.exists()) {
            _bytes = Utils.readContents(_file);
            _contents = Utils.readContentsAsString(_file);
            _hash = Utils.sha1(_pathname + _bytes);
        }
    }
}
