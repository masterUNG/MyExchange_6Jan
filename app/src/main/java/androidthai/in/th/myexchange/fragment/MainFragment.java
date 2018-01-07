package androidthai.in.th.myexchange.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.Format;

import androidthai.in.th.myexchange.R;
import androidthai.in.th.myexchange.utility.MyAlert;
import androidthai.in.th.myexchange.utility.MyGetRateFromAPI;

/**
 * Created by masterung on 6/1/2018 AD.
 */

public class MainFragment extends Fragment{

    //    Explicit
    private double factorADouble = 33.00; // Constance Rate USD ==> THB


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Update Factor
        updateFactor();

//        Exchange Controller
        exchangeController();



    }   // Main Method

    private void updateFactor() {

        String tag = "7JanV1";
        String urlJSON = "https://api.fixer.io/latest?symbols=THB&base=USD";

        try {

            MyGetRateFromAPI myGetRateFromAPI = new MyGetRateFromAPI(getActivity());



        } catch (Exception e) {
            e.printStackTrace();
        }

    }   // updateFactor

    private void exchangeController() {
        Button button = getView().findViewById(R.id.btnExchange);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Initial View
                EditText editText = getView().findViewById(R.id.edtMoney);
                String moneyString = editText.getText().toString().trim();

                if (moneyString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog("Have Space",
                            "Please Fill All Every Blank");

                } else {

//                    No Space
                    double moneyAdouble = Double.parseDouble(moneyString);
                    double answerAdouble = moneyAdouble / factorADouble;

                    String myAnswerString = String.format("%,.2f", answerAdouble);

                    String answerString = "Your Dolla ==> " + myAnswerString + " USD";

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog("Thai Bath ==> " + moneyString + " THB", answerString);





                }   // if

            }   // onClick
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}   // Main Class
