package sda.oscail.edu.actiontabs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

//import android.app.Fragment;

public class ProductsFragment extends Fragment {
SharedPreferences mProductSharedPref;
SharedPreferences.Editor mEidtor;
private static final AtomicInteger ID = new AtomicInteger(0);
private int clickedProductID;
private String clickedProductStringID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.product_list, container, false);

        // Create an ArrayList of AndroidFlavor objects
        final ArrayList<ProductList> productLists = new ArrayList<ProductList>();
        productLists.add(new ProductList("Blue Hoodie", "€55","1231765"));
        productLists.add(new ProductList("Black Short Sleeve Hoodie", "€50", "1231765"));
        productLists.add(new ProductList("Green Hoodie", "€50", "1231765"));
        productLists.add(new ProductList("Grey Short Sleeve Hoodie", "€50", "1231765"));
        productLists.add(new ProductList("Short Sleeve Hoodie", "€45", "1231765"));
        productLists.add(new ProductList("White Hoodie", "€45", "1231765"));
        productLists.add(new ProductList("White T-Shirt", "€45", "1231765"));
        productLists.add(new ProductList("Black Long Sleeve Hoodie", "€45", "1231765"));
        productLists.add(new ProductList("Sleevless Hoodie", "€30","1231765"));
      //  productLists.add(new ProductList("Green T-Shirt", "€30", R.drawable.greentshirt));

        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        ProductAdapter productAdapter = new ProductAdapter(getActivity(), productLists);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) rootView.findViewById(R.id.listview_flavor);
        listView.setAdapter(productAdapter);

        //Set an setOnItemClickListener on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = view.findViewById(R.id.version_name);
               // String clickedItemString = textView.toString();

                ProductList clickedItem = productLists.get(position);
                // Getting string value Product name of the clicked product.
                String clickedItemString = clickedItem.getVersionName();

                mProductSharedPref =getActivity().getSharedPreferences("ClickedProducts", getActivity().MODE_PRIVATE);
                mEidtor = mProductSharedPref.edit();
                clickedProductID =  ID.incrementAndGet();
                clickedProductStringID = Integer.toString(clickedProductID);

                mEidtor.putString(clickedProductStringID, clickedItemString);

                mEidtor.apply();

                // Display a Toast message indicting the selected item and added
                   Toast.makeText(getActivity(),
                           clickedItemString + " added", Toast.LENGTH_SHORT).show();






            }



        });
    return rootView;
    }

}