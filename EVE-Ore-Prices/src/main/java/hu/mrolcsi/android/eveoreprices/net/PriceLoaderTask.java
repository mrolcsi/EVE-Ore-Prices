package hu.mrolcsi.android.eveoreprices.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import hu.mrolcsi.android.eveoreprices.models.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 14:59
 */

public class PriceLoaderTask extends AsyncTask<Integer, Integer, Float> {

    private final Context context;
    private final int stationId;

    public PriceLoaderTask(Context context, int stationId) {
        this.context = context;
        this.stationId = stationId;
    }

    @Override
    protected Float doInBackground(Integer... ints) {
        int oreId = ints[0];

        //check network state
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED || conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {

            //notify user you are online
            HttpClient httpclient = new DefaultHttpClient();

            String uri = null;
            try {
                uri = Uri.parse(Constants.URL_BASE)
                        .buildUpon()
                        .appendQueryParameter(Constants.URL_PARAM_TYPEID, String.valueOf(oreId))
                        .appendQueryParameter(Constants.URL_PARAM_USESYSTEM, String.valueOf(stationId))
                        .build().toString();
            } catch (NullPointerException e) {
                Log.w(getClass().getName(), e);
            }

            // Prepare a request object
            HttpGet httpget = new HttpGet(uri);

            // Execute the request
            HttpResponse response;
            try {
                response = httpclient.execute(httpget);
                // Examine the response status
                switch (response.getStatusLine().getStatusCode()) {
                    case 200:
                        // Get hold of the response entity
                        HttpEntity entity = response.getEntity();
                        // If the response does not enclose an entity, there is no need
                        // to worry about connection release

                        if (entity != null) {
                            InputStream inStream = entity.getContent();
                            String result = convertStreamToString(inStream);
                            // now you have the string representation of the HTML request

                            return parseMaxValueFromXml(result);
                        }
                        break;
                    case 404:
                        cancel(true);
                    default:
                        break;
                }
            } catch (ClientProtocolException e) {
                Log.w(getClass().getName(), e);
            } catch (IOException e) {
                Log.w(getClass().getName(), e);
            }

        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED) {
            //notify user you are not online
            cancel(true);
        }
        return null;
    }

    private String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        int i = 0;
        String progress = "";
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");

                progress += ".";
                publishProgress(i++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public float parseMaxValueFromXml(String xml) {
        Document doc;
        try {
            final DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            doc = db.parse(is);

            //get buy order statistics
            final NodeList buyNodes = doc.getElementsByTagName(Constants.XML_BUY).item(0).getChildNodes();
            int i = 0;
            while (i < buyNodes.getLength() && !buyNodes.item(i).getNodeName().equals(Constants.XML_MAX)) i++;
            final NodeList maxNodes = buyNodes.item(i).getChildNodes();
            final String maxValue = maxNodes.item(0).getNodeValue();

            Log.d(getClass().getName(), "");

            return Float.valueOf(maxValue);


        } catch (ParserConfigurationException e) {
            Log.w(getClass().getName(), e);
        } catch (SAXException e) {
            Log.w(getClass().getName(), e);
        } catch (IOException e) {
            Log.w(getClass().getName(), e);
        }
        return -1;
    }
}
