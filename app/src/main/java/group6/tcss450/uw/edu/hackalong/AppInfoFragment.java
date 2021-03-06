/**
 * TCSS 450 Spring 2017 Group 6
 * AppInfoFragment.java
 * May 19, 2017
 */
package group6.tcss450.uw.edu.hackalong;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * This class controls the user profile
 */
public class AppInfoFragment extends LoginFragment {
    /* the fragment listener */
    private OnFragmentInteractionListener mListener;

    /**
     * Required empty constructor
     */
    public AppInfoFragment() {
    }

    /**
     * Creates the view for the fragment
     *
     * @param inflater           inflates the fragment
     * @param container          the container that holds the fragment
     * @param savedInstanceState the previous state, if any
     * @return returns the view for this fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_info, container, false);
    }

    /**
     * Checks the context to make sure it is an OnFragmentInteractionListener
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
     * Calls the super method and sets the mListener to null
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
