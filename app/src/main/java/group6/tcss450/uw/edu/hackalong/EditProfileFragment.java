/**
 * TCSS 450 Spring 2017 Group 6
 * EditProfileFragment.java
 * May 28, 2017
 */


package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import group6.tcss450.uw.edu.hackalong.tasks.EditProfileWebService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditProfileFragment extends ProfileFragment implements EditProfileWebService.OnEditTaskCompleteListener {


    private OnFragmentInteractionListener mListener;
    EditText userName;
    EditText userAge;
    EditText userLoc;
    EditText userBio;
    EditText userEvents;
    EditText userTag;
    TextView userEmail;

    String un;
    int ua;
    String ul;
    String ub;
    String ue;
    String ut;
    String uev;


    /**
     * required constructor
     */
    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * creates layout of editProfileFragment and populates textFields with pre existing values
     * @param inflater           inflates the fragment
     * @param container          the container that holds the fragment
     * @param savedInstanceState the previous state, if any
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        Button b = (Button) v.findViewById(R.id.savebutton);
        b.setOnClickListener(this);
        userName = (EditText) v.findViewById(R.id.editusername);
        userAge = (EditText) v.findViewById(R.id.editage);
        userLoc = (EditText) v.findViewById(R.id.editlocation);
        userBio = (EditText) v.findViewById(R.id.editbio);
        userEvents = (EditText) v.findViewById(R.id.editevents);
        userTag = (EditText) v.findViewById(R.id.editinterests);
        userEmail = (TextView) v.findViewById(R.id.editUEmailText);

        SharedPreferences mPref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        userEmail.setText(mPref.getString(getString(R.string.UE), null));
        userName.setText(mPref.getString(getString(R.string.UN), null));
        String age = mPref.getString(getString(R.string.UA), null);
        if (age == null){
            //do nothing, do not update field if no data is present
        }else {
            userAge.setText(age);
        }
        userLoc.setText(mPref.getString(getString(R.string.UL), null));
        userBio.setText(mPref.getString(getString(R.string.UB), null));
        userEvents.setText(mPref.getString(getString(R.string.UEv), null));
        userTag.setText(mPref.getString(getString(R.string.UT), null));

        return v;
    }

    /**
     *
     * @param context is the context of this class
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     *
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * onClick listener for the save button to send values to database and be saved to sharedPrefs
     * users must have the name field populated
     * Calls AsyncTask editProfileWebSercives
     * @param v is the view for this fragment
     */
    @Override
    public void onClick(View v) {
        SharedPreferences mPref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        if (mListener != null) {


            un = userName.getText().toString();
            mPref.edit().putString(getString(R.string.UN),un).apply();
            if(userAge.getText().toString().equals("")){
                ua=0;
            }else {
                ua = Integer.parseInt(userAge.getText().toString());
                mPref.edit().putString(getString(R.string.UA),userAge.getText().toString()).apply();
            }

            ul = userLoc.getText().toString();
            mPref.edit().putString(getString(R.string.UL),ul).apply();
            ub = userBio.getText().toString();
            mPref.edit().putString(getString(R.string.UB),ub).apply();
            ut = userTag.getText().toString();
            mPref.edit().putString(getString(R.string.UT),ut).apply();
            uev = userEvents.getText().toString();
            mPref.edit().putString(getString(R.string.UEv),uev).apply();
            ue = userEmail.getText().toString();
            mPref.edit().putString(getString(R.string.UE),ue).apply();

            //mListener.onFragmentInteraction("events", username, password);
            if(userName.getText().toString().equals("")){
                Toast.makeText(getActivity().getApplicationContext(), "You must have a name", Toast.LENGTH_LONG).show();
            }else{
                un = userName.getText().toString();
                EditProfileWebService task = new EditProfileWebService(EditProfileFragment.this, ue, un, ul, ua, uev, ub, ut);
                task.execute();
            }

        }
    }


    /**
     * parses the JSON response from AsyncTask editProfileWebSercives to see if successful
     * @param message
     */
    private void parseRegJSON(String message) {
        if (message.equals("1")) {
            mListener.onFragmentInteraction("profile", null, null);
            Toast.makeText(getActivity().getApplicationContext(), "Edits have been saved", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getActivity().getApplicationContext(), "Error " + message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * AsyncTask editProfileWebSercives completion repsonse
     * @param message
     */
    @Override
    public void onEditTaskCompletion(String message) {
        parseRegJSON(message);

    }

    /**
     * AsyncTask editProfileWebSercives error repsonse
     * @param error
     */
    @Override
    public void onEditTaskError(String error) {
        Toast.makeText(getActivity().getApplicationContext(), "An error with the web service has occured. Try again later.", Toast.LENGTH_LONG).show();
    }
}
