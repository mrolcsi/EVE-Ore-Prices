package hu.mrolcsi.android.eveoreprices.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import hu.mrolcsi.android.eveoreprices.R;
import hu.mrolcsi.android.eveoreprices.models.Station;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 14:46
 */

public class PriceFragment extends Fragment {

    public static final String ARG_SECURITY_LEVEL = "argSecurityLevel";
    public static final String ARG_STATION = "argStation";

    private int securityLevel = -1;
    private Station station;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.securityLevel = args.getInt(ARG_SECURITY_LEVEL);
        this.station = (Station) args.getSerializable(ARG_STATION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View layout = inflater.inflate(R.layout.fragment_pricelist, container);

        TextView tvStation = (TextView) layout.findViewById(R.id.tvStation);
        tvStation.setText(station.getFullName());

        ListView lvPriceList = (ListView) layout.findViewById(R.id.lvPrices);
        lvPriceList.setAdapter(new PriceListAdapter(getActivity(), securityLevel, station));

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
