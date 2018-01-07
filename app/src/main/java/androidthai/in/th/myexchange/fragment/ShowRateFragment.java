package androidthai.in.th.myexchange.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidthai.in.th.myexchange.MainActivity;
import androidthai.in.th.myexchange.R;

/**
 * Created by masterung on 7/1/2018 AD.
 */

public class ShowRateFragment extends Fragment{


    public static ShowRateFragment showRateInstance(String dateString,
                                                    double factorAdouble) {

        ShowRateFragment showRateFragment = new ShowRateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MyDate", dateString);
        bundle.putDouble("Factor", factorAdouble);
        showRateFragment.setArguments(bundle);
        return showRateFragment;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarShowRate);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Curren Rate USD/THB");

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

//        Show Date
        showDate();

//        Show Rate
        showRate();


    }

    private void showRate() {
        TextView rateTextView = getView().findViewById(R.id.txtShowRate);
        rateTextView.setText(Double.toString(getArguments().getDouble("Factor")));
    }

    private void showDate() {
        TextView dateTextView = getView().findViewById(R.id.txtShowDate);
        dateTextView.setText(getArguments().getString("MyDate"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_rate, container, false);
        return view;
    }
}   // Main Class
