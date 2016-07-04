package fiketMafian.Hazel;

import android.app.SearchManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;

public class SearchActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Log.d("oncreate", "oncreate");
        //System.out.println("oncreate");

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        handleIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //Log.d("onnewintent", "onnewintent");
        //System.out.println("onnewintent");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }
}
