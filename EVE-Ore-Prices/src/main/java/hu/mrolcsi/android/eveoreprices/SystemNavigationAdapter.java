package hu.mrolcsi.android.eveoreprices;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.mrolcsi.android.eveoreprices.fragments.PriceFragment;
import hu.mrolcsi.android.eveoreprices.models.Station;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 14:27
 */

public class SystemNavigationAdapter extends ArrayAdapter<Fragment> {

    private final LayoutInflater inflater;

    private List<Station> stations;
    private int selectedItem;

    public SystemNavigationAdapter(Context context) {
        super(context, R.layout.navigation_item);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        final PriceFragment fragment = new PriceFragment();

        Bundle args = new Bundle();
        args.putInt(PriceFragment.ARG_SECURITY_LEVEL, position);
        args.putInt(PriceFragment.ARG_STATION, stations.get(position).getId());

        return fragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.navigation_item, parent, false);

            holder.icon = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.label = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        convertView.setBackgroundColor(getContext().getResources().getColor(selectedItem == position ? R.color.navigation_selected : R.color.drawer_background));

        switch (position) {
            case MainActivity.FRAGMENT_HIGHSEC:
                holder.icon.setImageResource(R.drawable.icon_highsec);
                holder.label.setText(getContext().getString(R.string.security_high));
                break;
            case MainActivity.FRAGMENT_LOWSEC:
                holder.icon.setImageResource(R.drawable.icon_lowsec);
                holder.label.setText(getContext().getString(R.string.security_low));
                break;
            case MainActivity.FRAGMENT_NULLSEC:
                holder.icon.setImageResource(R.drawable.icon_nullsec);
                holder.label.setText(getContext().getString(R.string.security_null));
                break;
        }

        //set label

        return convertView;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }
}

class ViewHolder {
    ImageView icon;
    TextView label;
}