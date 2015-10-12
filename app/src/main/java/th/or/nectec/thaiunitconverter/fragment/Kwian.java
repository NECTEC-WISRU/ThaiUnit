package th.or.nectec.thaiunitconverter.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.or.nectec.thaiunitconverter.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Kwian.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Kwian#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Kwian extends Fragment {

    public static Kwian newInstance() {
        Kwian fragment = new Kwian();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Kwian() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kwian, container, false);
    }

}
