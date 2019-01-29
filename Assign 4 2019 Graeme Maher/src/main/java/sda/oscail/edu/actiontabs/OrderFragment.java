package sda.oscail.edu.actiontabs;

//

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

//import android.app.Fragment;


public class OrderFragment extends Fragment {

    Uri mPhotoURI;
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    private ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    private static final String TAG = "Assign4";
    SharedPreferences mProductSharedPref;
    Button buttonSend;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.orderfragment, container, false);

        meditOptional = (EditText) rootView.findViewById(R.id.editOptional);
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //initialise spinner using the integer array
        mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
        mCustomerName = (EditText) rootView.findViewById(R.id.editCustomer);
        buttonSend = rootView.findViewById(R.id.button_send);



        mProductSharedPref =getActivity().getSharedPreferences("ClickedProducts", getActivity().MODE_PRIVATE);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.ui_time_entries, R.layout.spinner_days);
        mSpinner.setAdapter(adapter);
        final View button = rootView.findViewById(R.id.imageView);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                        String imageFileName = "my_tshirt_image_" + timeStamp + ".jpg";

                        Log.i(TAG, "imagefile");

                        File file = new File(Environment.getExternalStorageDirectory(), imageFileName);

                        mPhotoURI = Uri.fromFile(file);
                        Log.i(TAG, mPhotoURI.toString());
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
                        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                        //incase of caching if it comes from the activity stack, just a precaution
                        intent.removeExtra(MediaStore.EXTRA_OUTPUT);

                    }
                });

        //return rootView;
   // }




        /**
         * Code and Idea for listener inside fragment for buttons from link below
         * https://stackoverflow.com/questions/18711433/button-listener-for-button-in-fragment-in-android
         *
         *
         *
         *
         *
         */

        //View buttonSend = rootView.findViewById(R.id.button_send);
        buttonSend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //check that Name is not empty, and ask do they want to continue

                        String customerName = mCustomerName.getText().toString();
                        if (customerName.matches("")) {
                            Toast.makeText(getActivity(), getString(R.string.customer_name_blank), Toast.LENGTH_SHORT).show();

            /* we can also use a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notification!").setMessage("Customer Name not set.").setPositiveButton("OK", null).show();
            */
                        } else {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("*/*");
                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)});
                            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
                            intent.putExtra(Intent.EXTRA_STREAM, mPhotoURI);
                            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
                            //intent.putExtra(Intent.EXTRA_TEXT, getSharedElementEnterTransition();
                            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        }
                    }

                });
                    return rootView;
                }

                    private String createOrderSummary() {

                        String orderMessage = getString(R.string.customer_name) + " " + mCustomerName.getText().toString();
                        orderMessage += "\n" + "\n" + getString(R.string.order_message_1);

                        orderMessage += "\n" + "\n";

                        Map<String, ?> ClickedProducts = mProductSharedPref.getAll();

                        for (Map.Entry<String, ?> entry : ClickedProducts.entrySet()) {
                            //Add each item to order message
                            orderMessage += "\n" + entry.getKey() + "." + " " + entry.getValue();
                        }

                        orderMessage += "\n" + "\n";

                        String optionalInstructions = meditOptional.getText().toString();

                        orderMessage += "\n" + getString(R.string.order_message_collect) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
                        orderMessage += "\n" + getString(R.string.order_message_end) + "\n" + mCustomerName.getText().toString();

                        return orderMessage;

                        //update screen
                    }


                    //}


                    //return rootView;








    /**
     * Declares intent to launch camera.
     * Provides the time stamp and date format for the Image.
     * Names the Image file.
     * Saves the image to storage.
     * @link {dispatchTakePictureIntent}
     * @author Graeme Maher
     * @param View v
     * @since 17/12/18
     *
     */




    /**
     * Notifies the enduser that the picture was taken successfully
     * @link {onActivityResult}
     * @author Graeme Maher
     * @param int requestCode
     * @param int resultCode
     * @param Intent data
     * @since 17/12/18
     *
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {



        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {
            //let user know that image saved
            //I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
            CharSequence text = "Image Taken successfully";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getActivity(), text, duration);
            toast.show();

            //or perhaps do a dialog should only use one method i.e. toast or dialog, but have both code here for demo purposes
            //also I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Notification!").setMessage("Image saved successfully.").setPositiveButton("OK", null).show();

        }
     // if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK ) {
       //    Bitmap photo = (mPhotoURI) data.getExtras().get("data");
       // imageView.setImageBitmap(photo);
        // }
        //also can give user a message that everything went ok

    }

    /**
     * Returns the Email Body Message.
     * <p> Email body message is created used prescription related data inputed from user </p>
     * @link {createOrderSummary}
     * @return Email Body Message
     *
     */


    /**
     * Check that the name is not empty and ask do they want to continue
     * If not empty emails the customer with order summary
     * @link {sendEmail}
     * @author Graeme Maher
     * @param View V
     * @since 17/12/18
     *
     */

/**
 *  public void sendEmail(View v)
 *   {

        //check that Name is not empty, and ask do they want to continue

        String customerName = mCustomerName.getText().toString();
        if (customerName.matches(""))
        {
            Toast.makeText(getActivity(), getString(R.string.customer_name_blank), Toast.LENGTH_SHORT).show();

            /* we can also use a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notification!").setMessage("Customer Name not set.").setPositiveButton("OK", null).show();
            */
    //    } else
      //  {
        //  Intent intent = new Intent(Intent.ACTION_SEND);
    //      intent.setType("*/*");
      //      intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)});
        //    intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
          //  intent.putExtra(Intent.EXTRA_STREAM, mPhotoURI);
            //intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
           // if (intent.resolveActivity(getActivity().getPackageManager()) != null)
           // {
           //     startActivity(intent);
          //  }
        //}
    //}




}