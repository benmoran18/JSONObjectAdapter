import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Ben Moran on 23/11/2015.
 */
public class JSONObjectAdapter {
    private final String TAG = "JSONAdapter";

    HashMap<String, TextView> fieldToViewMap;
    JSONObject data;

    public JSONObjectAdapter() {
        fieldToViewMap = new HashMap<String, TextView>();
    }

    /** Sets the data binding between a json field and a TextView
     * @param fieldName The name of the json field to bind
     * @param textView The TextView you want to display the fields value in
     * */
    public void setBinding(String fieldName, TextView textView){
        fieldToViewMap.put(fieldName, textView);
    }

    /** Load JSON data into the adapter. For best performance, use this method on a background thread where possible.
    * @param jsonObject the JSON data object to load.
    * */
    public void load(JSONObject jsonObject){
        data = jsonObject;
    }

    /** Refresh the data-binded text views with the new json data.
     * This method must be called from the UI thread!
    * */
    public void notifyDataSetChanged(){
        if(data != null){
            Iterator<String> iterator = data.keys();
            while(iterator.hasNext()){
                String key = iterator.next();
                try {
                    Object value = data.get(key);
                    if(value.getClass() != JSONArray.class){
                        TextView textView = fieldToViewMap.get(key);
                        if(textView != null && !textView.getText().toString().equals(value)){
                            textView.setText((String) value);
                        }
                    }
                } catch (JSONException e) {

                }
            }
        }
    }
}
