package hu.mrolcsi.android.eveoreprices.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.mrolcsi.android.eveoreprices.MainActivity;
import hu.mrolcsi.android.eveoreprices.R;
import hu.mrolcsi.android.eveoreprices.models.Ore;
import hu.mrolcsi.android.eveoreprices.models.Station;
import hu.mrolcsi.android.eveoreprices.net.PriceLoaderTask;

import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.AZURE_PLAGIOCLASE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.CONCENTRATED_VELDSPAR_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.CONDENSED_SCORDITE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.DENSE_VELDSPAR_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.FIERY_KERNITE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.GOLDEN_OMBER_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.KERNITE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.KERNITE_VOLUME;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.LUMINOUS_KERNITE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.MASSIVE_SCORDITE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.OMBER_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.OMBER_VOLUME;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.PLAGIOCLASE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.PLAGIOCLASE_VOLUME;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.PYROXERES_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.PYROXERES_VOLUME;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.RICH_PLAGIOCLASE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.SCORDITE_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.SCORDITE_VOLUME;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.SILVERY_OMBER_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.SOLID_PYROXERES_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.VELDSPAR_ID;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.VELDSPAR_VOLUME;
import static hu.mrolcsi.android.eveoreprices.models.Constants.HighSecOres.VISCIOUS_PYROXERES_ID;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 15:01
 */

public class PriceListAdapter extends ArrayAdapter<List<Ore>> {

    private static final int POSITION_VELDSPAR = 0;
    private static final int POSITION_SCORDITE = 1;
    private static final int POSITION_PYROXERES = 2;
    private static final int POSITION_PLAGIOCLASE = 3;
    private static final int POSITION_OMBER = 4;
    private static final int POSITION_KERNITE = 5;

    private final Station station;
    private final LayoutInflater inflater;
    private List<Ore> ores;


    public PriceListAdapter(Context context, int securityLevel, Station station) {
        super(context, R.layout.pricelist_item);
        this.station = station;

        inflater = LayoutInflater.from(context);

        switch (securityLevel) {
            case MainActivity.FRAGMENT_HIGHSEC:
                prepareHighSecOres();
                break;
            case MainActivity.FRAGMENT_LOWSEC:
                prepareLowSecOres();
                break;
            case MainActivity.FRAGMENT_NULLSEC:
                prepareNullSecOres();
                break;
        }
    }

    private void prepareHighSecOres() {
        ores = new ArrayList<Ore>();

        ores.add(new Ore(VELDSPAR_ID, getContext().getString(R.string.ore_name_veldspar), VELDSPAR_VOLUME));
        ores.add(new Ore(CONCENTRATED_VELDSPAR_ID, getContext().getString(R.string.ore_name_concentratedveldspar), VELDSPAR_VOLUME));
        ores.add(new Ore(DENSE_VELDSPAR_ID, getContext().getString(R.string.ore_name_denseveldspar), VELDSPAR_VOLUME));

        ores.add(new Ore(SCORDITE_ID, getContext().getString(R.string.ore_name_scordite), SCORDITE_VOLUME));
        ores.add(new Ore(CONDENSED_SCORDITE_ID, getContext().getString(R.string.ore_name_condensedscordite), SCORDITE_VOLUME));
        ores.add(new Ore(MASSIVE_SCORDITE_ID, getContext().getString(R.string.ore_name_massivescordite), SCORDITE_VOLUME));

        ores.add(new Ore(PYROXERES_ID, getContext().getString(R.string.ore_name_pyroxeres), PYROXERES_VOLUME));
        ores.add(new Ore(SOLID_PYROXERES_ID, getContext().getString(R.string.ore_name_solidpyroxeres), PYROXERES_VOLUME));
        ores.add(new Ore(VISCIOUS_PYROXERES_ID, getContext().getString(R.string.ore_name_visciouspyroxeres), PYROXERES_VOLUME));

        ores.add(new Ore(PLAGIOCLASE_ID, getContext().getString(R.string.ore_name_plagioclase), PLAGIOCLASE_VOLUME));
        ores.add(new Ore(AZURE_PLAGIOCLASE_ID, getContext().getString(R.string.ore_name_azureplagioclase), PLAGIOCLASE_VOLUME));
        ores.add(new Ore(RICH_PLAGIOCLASE_ID, getContext().getString(R.string.ore_name_richplagioclase), PLAGIOCLASE_VOLUME));

        ores.add(new Ore(OMBER_ID, getContext().getString(R.string.ore_name_omber), OMBER_VOLUME));
        ores.add(new Ore(SILVERY_OMBER_ID, getContext().getString(R.string.ore_name_silveryomber), OMBER_VOLUME));
        ores.add(new Ore(GOLDEN_OMBER_ID, getContext().getString(R.string.ore_name_goldenomber), OMBER_VOLUME));

        ores.add(new Ore(KERNITE_ID, getContext().getString(R.string.ore_name_kernite), KERNITE_VOLUME));
        ores.add(new Ore(LUMINOUS_KERNITE_ID, getContext().getString(R.string.ore_name_luminouskernite), KERNITE_VOLUME));
        ores.add(new Ore(FIERY_KERNITE_ID, getContext().getString(R.string.ore_name_fierykernite), KERNITE_VOLUME));
    }

    private void prepareLowSecOres() {
        ores = new ArrayList<Ore>();
        //TODO
    }

    private void prepareNullSecOres() {
        ores = new ArrayList<Ore>();
        //TODO
    }

    @Override
    public int getCount() {
        return ores.size() / 3;
    }

    @Override
    public List<Ore> getItem(int position) {
        return ores.subList(position * 3, position * 3 + 3);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        final PriceHolder holder;
//        if (convertView == null) {
//            holder = new PriceHolder();
//
//            convertView = inflater.inflate(R.layout.pricelist_item, parent, false);
//
//            holder.icon = (ImageView) convertView.findViewById(R.id.imgIcon);
//            holder.tvNormalOreName = (TextView) convertView.findViewById(R.id.tvNormalOreName);
//            holder.tvNormalOrePrice = (TextView) convertView.findViewById(R.id.tvNormalOrePrice);
//            holder.tv33OreName = (TextView) convertView.findViewById(R.id.tv33OreName);
//            holder.tv33OrePrice = (TextView) convertView.findViewById(R.id.tv33OrePrice);
//            holder.tv66OreName = (TextView) convertView.findViewById(R.id.tv66OreName);
//            holder.tv66OrePrice = (TextView) convertView.findViewById(R.id.tv66OrePrice);
//
//            holder.names = new TextView[]{holder.tvNormalOreName, holder.tv33OreName, holder.tv66OreName};
//            holder.prices = new TextView[]{holder.tvNormalOrePrice, holder.tv33OrePrice, holder.tv66OrePrice};
//
//            convertView.setTag(holder);
//        } else holder = (PriceHolder) convertView.getTag();

        convertView = inflater.inflate(R.layout.pricelist_item, parent, false);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        TextView tvNormalOreName = (TextView) convertView.findViewById(R.id.tvNormalOreName);
        TextView tvNormalOrePrice = (TextView) convertView.findViewById(R.id.tvNormalOrePrice);
        TextView tv33OreName = (TextView) convertView.findViewById(R.id.tv33OreName);
        TextView tv33OrePrice = (TextView) convertView.findViewById(R.id.tv33OrePrice);
        TextView tv66OreName = (TextView) convertView.findViewById(R.id.tv66OreName);
        TextView tv66OrePrice = (TextView) convertView.findViewById(R.id.tv66OrePrice);

        TextView[] names = new TextView[]{tvNormalOreName, tv33OreName, tv66OreName};
        final TextView[] prices = new TextView[]{tvNormalOrePrice, tv33OrePrice, tv66OrePrice};


        switch (position) {
            case POSITION_VELDSPAR:
                imgIcon.setImageResource(R.drawable.ore_veldspar);
                break;
            case POSITION_SCORDITE:
                imgIcon.setImageResource(R.drawable.ore_scordite);
                break;
            case POSITION_PYROXERES:
                imgIcon.setImageResource(R.drawable.ore_pyroxeres);
                break;
            case POSITION_PLAGIOCLASE:
                imgIcon.setImageResource(R.drawable.ore_plagioclase);
                break;
            case POSITION_OMBER:
                imgIcon.setImageResource(R.drawable.ore_omber);
                break;
            case POSITION_KERNITE:
                imgIcon.setImageResource(R.drawable.ore_kernite);
                break;
        }

        final List<Ore> items = getItem(position);

        for (int i = 0; i < items.size(); i++) {
            names[i].setText(items.get(i).getName());

            final int finalI = i;
            new PriceLoaderTask(getContext(), station.getId()) {
                @Override
                protected void onPostExecute(Float maxUnitPrice) {
                    if (maxUnitPrice != null)
                        prices[finalI].setText(String.format("%.2f", items.get(finalI).getPricePerM3(maxUnitPrice)));
                    else prices[finalI].setText(getContext().getString(R.string.ore_price_notavailable));
                }
            }.execute(items.get(i).getId());
        }

        return convertView;
    }
}

class PriceHolder {
    ImageView icon;
    TextView tvNormalOreName, tvNormalOrePrice, tv33OreName, tv33OrePrice, tv66OreName, tv66OrePrice;
    TextView[] names, prices;
}