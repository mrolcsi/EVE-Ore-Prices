package hu.mrolcsi.android.eveoreprices.models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 14:42
 */

public class Station implements Serializable {

    private int id;
    private String fullName;

    public Station(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return fullName.substring(0, fullName.indexOf(' '));

    }

    public String getFullName() {
        return fullName;
    }

}
