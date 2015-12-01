import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    TextView lblFirstname, lblLastname, lblMobile, lblLandline;

    JSONObjectAdapter jsonObjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        linkViews();
        loadDataBindings();
        loadData();
    }

    private void linkViews() {
        lblFirstname = (TextView) findViewById(R.id.lbl_firstname);
        lblLastname = (TextView) findViewById(R.id.lbl_lastname);
        lblMobile = (TextView) findViewById(R.id.lbl_mobile);
        lblLandline = (TextView) findViewById(R.id.lbl_landline);
    }

    private void loadDataBindings(){
        jsonObjectAdapter = new JSONObjectAdapter();
        jsonObjectAdapter.setBinding("firstname", lblFirstname);
        jsonObjectAdapter.setBinding("lastname", lblLastname);
        jsonObjectAdapter.setBinding("mobile", lblMobile);
        jsonObjectAdapter.setBinding("landline", lblLandline);
    }

    private void loadData(){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                JSONObject testData = new JSONObject();
                try {
                    testData.put("firstname", "First Name");
                    testData.put("lastname", "Last Name");
                    testData.put("mobile", "0123456789");
                    testData.put("landline", "9876543210");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonObjectAdapter.load(testData);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jsonObjectAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
