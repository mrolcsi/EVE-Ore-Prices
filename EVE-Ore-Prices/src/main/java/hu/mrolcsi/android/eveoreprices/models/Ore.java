package hu.mrolcsi.android.eveoreprices.models;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 14:42
 */

public class Ore {

    private final int id;
    private final String name;
    private final float volume;

    public Ore(int id, String name, float volume) {
        this.id = id;
        this.name = name;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPricePerM3(float unitPrice) {
        return unitPrice / volume;
    }
}
