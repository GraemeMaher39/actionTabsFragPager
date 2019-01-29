package sda.oscail.edu.actiontabs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



     /*
     * {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
     * based on a data source, which is a list of {@link AndroidFlavor} objects.
     * */
    public class ProductAdapter extends ArrayAdapter<ProductList> {

        private static final String LOG_TAG = ProductAdapter.class.getSimpleName();

        /**
         * This is our own custom constructor (it doesn't mirror a superclass constructor).
         * The context is used to inflate the layout file, and the list is the data we want
         * to populate into the lists.
         *
         * @param context        The current context. Used to inflate the layout file.
         * @param productLists A List of AndroidFlavor objects to display in a list
         */
        public ProductAdapter(Activity context, ArrayList<ProductList> productLists) {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView.
            // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
            // going to use this second argument, so it can be any value. Here, we used 0.
            // due to the fact we have string, int and photo, we must extend ArrayAdapter class as androidFlavorAdapter
            super(context, 0, productLists);
        }

        /**
         * Provides a view for an AdapterView (ListView, GridView, etc.)
         *
         * @param position The position in the list of data that should be displayed in the
         *                 list item view.
         * @param convertView The recycled view to populate.
         * @param parent The parent ViewGroup that is used for inflation.
         * @return The View for the position in the AdapterView.
         */

        //Return list item view, which is an inflated view populated by the custom ArrayAdapter
        //getView creates the view, it is called indirectly when the activity opens, or when the view of the list changes
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the {@link AndroidFlavor} object located at this position in the list
            ProductList currentAndroidFlavor = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID version_name
            TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
            // Get the version name from the current AndroidFlavor object and
            // set this text on the name TextView
            nameTextView.setText(currentAndroidFlavor.getVersionName());

            // Find the TextView in the list_item.xml layout with the ID version_number
            TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);
            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView
            numberTextView.setText(currentAndroidFlavor.getVersionNumber());

            // Find the ImageView in the list_item.xml layout with the ID list_item_icon
            TextView phoneTextView = listItemView.findViewById(R.id.phone);
            // Get the image resource ID from the current AndroidFlavor object and
            // set the image to iconView
            phoneTextView.setText(currentAndroidFlavor.getPhone());

            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            return listItemView;
        }

    }


