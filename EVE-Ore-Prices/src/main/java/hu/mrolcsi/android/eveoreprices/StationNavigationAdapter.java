package hu.mrolcsi.android.eveoreprices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.mrolcsi.android.eveoreprices.models.Station;

import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.AMARR_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.AMARR_STATION;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.DODIXIE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.DODIXIE_STATION;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.HEK_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.HEK_STATION;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.JITA_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.JITA_STATION;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.RENS_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.Stations.RENS_STATION;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 17:02
 */

public class StationNavigationAdapter extends ArrayAdapter<Station> {

    private final LayoutInflater inflater;
    private List<Station> stations;

    private int selectedItem;

    public StationNavigationAdapter(Context context) {
        super(context, R.layout.navigation_item);
        inflater = LayoutInflater.from(context);

        stations = new ArrayList<Station>();

        stations.add(new Station(AMARR_ID, AMARR_STATION));
        stations.add(new Station(DODIXIE_ID, DODIXIE_STATION));
        stations.add(new Station(HEK_ID, HEK_STATION));
        stations.add(new Station(JITA_ID, JITA_STATION));
        stations.add(new Station(RENS_ID, RENS_STATION));
    }

    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Station getItem(int position) {
        return stations.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.navigation_item_stations, parent, false);

            holder.icon = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.label = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        final Station station = getItem(position);

        convertView.setBackgroundColor(getContext().getResources().getColor(selectedItem == position ? R.color.navigation_selected : R.color.drawer_background));

        holder.label.setText(station.getShortName());

        switch (station.getId()) {
            case AMARR_ID:
                holder.icon.setImageResource(R.drawable.station_amarr_corp);
                break;
            case DODIXIE_ID:
                holder.icon.setImageResource(R.drawable.station_dodixie_corp);
                break;
            case HEK_ID:
                holder.icon.setImageResource(R.drawable.station_hek_corp);
                break;
            case JITA_ID:
                holder.icon.setImageResource(R.drawable.station_jita_corp);
                break;
            case RENS_ID:
                holder.icon.setImageResource(R.drawable.station_rens_corp);
                break;
        }

        return convertView;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }
}