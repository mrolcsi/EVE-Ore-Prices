package hu.mrolcsi.android.eveoreprices.models;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 15:35
 */

public abstract class Constants {

    public static final String URL_FORMAT = "http://api.eve-central.com/api/marketstat?typeid=%d&usesystem=%d";
    public static final String URL_BASE = "http://api.eve-central.com/api/marketstat";
    public static final String URL_PARAM_TYPEID = "typeid";
    public static final String URL_PARAM_USESYSTEM = "usesystem";
    public static final String XML_MARKETSTAT = "marketstat";
    public static final String XML_BUY = "buy";
    public static final String XML_SELL = "sell";
    public static final String XML_MAX = "max";

    public static abstract class HighSecOres {
        public static final float VELDSPAR_VOLUME = 0.1f;
        public static final int VELDSPAR_ID = 1230;
        public static final int CONCENTRATED_VELDSPAR_ID = 17470;

        public static final int DENSE_VELDSPAR_ID = 17471;

        public static final float SCORDITE_VOLUME = 0.15f;
        public static final int SCORDITE_ID = 1228;
        public static final int CONDENSED_SCORDITE_ID = 17463;
        public static final int MASSIVE_SCORDITE_ID = 17464;

        public static final float PYROXERES_VOLUME = 0.3f;
        public static final int PYROXERES_ID = 1224;
        public static final int SOLID_PYROXERES_ID = 17459;
        public static final int VISCIOUS_PYROXERES_ID = 17460;

        public static final float PLAGIOCLASE_VOLUME = 0.35f;
        public static final int PLAGIOCLASE_ID = 18;
        public static final int AZURE_PLAGIOCLASE_ID = 17455;
        public static final int RICH_PLAGIOCLASE_ID = 17456;

        public static final float OMBER_VOLUME = 0.6f;
        public static final int OMBER_ID = 1227;
        public static final int SILVERY_OMBER_ID = 17867;
        public static final int GOLDEN_OMBER_ID = 17868;

        public static final float KERNITE_VOLUME = 1.2f;
        public static final int KERNITE_ID = 20;
        public static final int LUMINOUS_KERNITE_ID = 17452;
        public static final int FIERY_KERNITE_ID = 17453;
    }

    public static abstract class LowSecOres {
        // TODO: Jaspet, Hemorphite, Hedbergite

        public static final float JASPET_VOLUME = 2.0f;
        public static final int JASPET_ID = 1226;
        public static final int PURE_JASPET_ID = 17448;
        public static final int PRISTINE_JASPET_ID = 17449;

        public static final float HEMORPHITE_VOLUME = 3.0f;
        public static final int HEMORPHITE_ID = 1231;
        public static final int VIVID_HEMORPHITE_ID = 17444;
        public static final int RADIANT_HEMORPHITE_ID = 17445;

        public static final float HEDBERGITE_VOLUME = 3.0f;
        public static final int HEDBERGITE_ID = 21;
        public static final int VITRIC_HEDBERGITE_ID = 17440;
        public static final int GLAZED_HEDBERGITE_ID = 17441;
    }

    public static abstract class NullSecOres {
        // TODO: Gneiss, Dark Ochre, Crokite, Spodumain, Bistot, Arkonor, Mercoxit
    }

    public static abstract class Stations {
        public static final int AMARR_ID = 30002187;
        public static final String AMARR_REGION = "Domain";
        public static final String AMARR_STATION = "Amarr VIII (Oris) - Emperor Family Academy";

        public static final int DODIXIE_ID = 30002659;
        public static final String DODIXIE_REGION = "Sinq Laison";
        public static final String DODIXIE_STATION = "Dodixie IX - Moon 20 - Federation Navy Assembly Plant";

        public static final int HEK_ID = 30002053;
        public static final String HEK_REGION = "Metropolis";
        public static final String HEK_STATION = "Hek VIII - Moon 12 - Boundless Creation Factory";

        public static final int JITA_ID = 30000142;
        public static final String JITA_REGION = "The Forge";
        public static final String JITA_STATION = "Jita IV - Moon 4 - Caldari Navy Assembly Plant";

        public static final int RENS_ID = 30002510;
        public static final String RENS_REGION = "Heimatar";
        public static final String RENS_STATION = "Rens VI - Moon 8 - Brutor Tribe Treasury";

    }
}
