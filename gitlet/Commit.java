package gitlet;

import java.util.HashMap;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Commit implements Serializable {
    /**
     * commits tracked.
     * @return your mom
     */
    public String message() {
        return _message;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    private String _message;
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
    public String dateTime() {
        return _dateTime;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    public HashMap<String, Blob> blobs() {
        return _blobs;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    public String parent() {
        return _parent;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    public String parent2() {
        return _parent2;
    }
    /**
     * commits tracked.
     * @return your mom
     */
    private String _hash;
    /**
     * commits tracked.
     * @return your mom
     */
    private String _dateTime;
    /**
     * commits tracked.
     * @return your mom
     */
    private HashMap<String, Blob> _blobs;
    /**
     * commits tracked.
     * @return your mom
     */
    private String _parent;
    /**
     * commits tracked.
     * @return your mom
     */
    private String _parent2;

    /**
     *
     * @param message
     * @param blobs
     * @param parent
     * @param parent2
     * @param initial
     */
    Commit(String message, HashMap<String, Blob> blobs,
           String parent, String parent2, Boolean initial) {
        _message = message;
        _blobs = blobs;
        _parent = parent;
        _parent2 = parent2;
        _hash = Utils.sha1(_dateTime + _message + _parent + _blobs);
        SimpleDateFormat formatted = new
                SimpleDateFormat("E MMM dd HH:mm:ss yyyy Z");
        if (initial) {
            Date date = new Date(0);
            _dateTime = formatted.format(date);
        } else {
            Date date = new Date();
            _dateTime = formatted.format(date);
        }
    }
}
