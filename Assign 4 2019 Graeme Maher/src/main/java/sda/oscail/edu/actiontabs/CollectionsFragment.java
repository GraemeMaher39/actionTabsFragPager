package sda.oscail.edu.actiontabs;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//import android.app.Fragment;

public class CollectionsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.address_list, container, false);

        // Create an ArrayList of AndroidFlavor objects
        final ArrayList<ProductList> addressList = new ArrayList<>();
        addressList.add(new ProductList("Finglas", "10 Main Street ","1231761"));
        addressList.add(new ProductList("Santry", "11 Main Street ","1231762") );
       addressList.add(new ProductList("Swords", "12 Main Street ","1231763" ));
        addressList.add(new ProductList("Dunboyne", "14 Main Street ","1231764" ));
        addressList.add(new ProductList("Clonee", "15 Main Street ","1231765" ));
        addressList.add(new ProductList("Ballymun", "16 Main Street ","1231766" ));
        addressList.add(new ProductList("Cabra", "17 Main Street ","1231767" ));



        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        ProductAdapter productAdapter = new ProductAdapter(getActivity(), addressList);

        // Get a reference to the ListView, and attach the adapter to the listView.
       ListView listView = rootView.findViewById(R.id.listview_address);
       listView.setAdapter(productAdapter);



        //Set an setOnItemClickListener on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         public void onItemClick(AdapterView<?> parent, View view,
                                   int position, long id) {
           //     TextView textView = view.findViewById(R.id.version_name);
                 //Display a Toast message indicting the selected item
            //    Toast.makeText(getActivity(),
             //          textView.getText(), Toast.LENGTH_SHORT).show();

               ProductList selectedItem = addressList.get(position);
               // Getting string value Product name of the clicked product.
               String selectedItemString = selectedItem.getVersionName();

               // Display a Toast message indicting the selected item
               Toast.makeText(getActivity(),
                       selectedItemString + " added", Toast.LENGTH_SHORT).show();
           }

        });
       return rootView;
    }

}