package androidthai.in.th.myexchange.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

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
    private String dateString;
    private boolean aBoolean = true;
    private EditText editText;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Initial View
        initialView();

//        Update Factor
        updateFactor();

//        Exchange Controller
        exchangeController();

//        ShowRate Controller
        showRateController();

//        ChengeCurrent Controller
        changeCurrentController();


    }   // Main Method

    private void initialView() {
        editText = getView().findViewById(R.id.edtMoney);
    }

    private void changeCurrentController() {
        final ImageView imageView = getView().findViewById(R.id.imvMoney);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aBoolean = !aBoolean;

                if (aBoolean) {

                    imageView.setImageResource(R.drawable.tha);
                    editText.setHint("Thai Bath");


                } else {

                    imageView.setImageResource(R.drawable.usd);
                    editText.setHint("Us USD");

                }

            }
        });
    }

    private void showRateController() {
        Button button = getView().findViewById(R.id.btnShowRate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment,
                                ShowRateFragment.showRateInstance(dateString, factorADouble))
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    private void updateFactor() {

        String tag = "7JanV1";
        String urlJSON = "https://api.fixer.io/latest?symbols=THB&base=USD";

        try {

            MyGetRateFromAPI myGetRateFromAPI = new MyGetRateFromAPI(getActivity());
            myGetRateFromAPI.execute(urlJSON);

            String jsonString = myGetRateFromAPI.get();
            jsonString = "[" + jsonString + "]";

            Log.d(tag, "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            dateString = jsonObject.getString("date");
            Log.d(tag, "date ==> " + dateString);

            String ratesString = jsonObject.getString("rates");
            ratesString = "[" + ratesString + "]";
            Log.d(tag, "ratesString ==> " + ratesString);

            JSONArray jsonArray1 = new JSONArray(ratesString);
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);

            String factorString = jsonObject1.getString("THB");
            Log.d(tag, "factor ==> " + factorString);

            factorADouble = Double.parseDouble(factorString);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }   // updateFactor

    private void exchangeController() {
        Button button = getView().findViewById(R.id.btnExchange);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String moneyString = editText.getText().toString().trim();

                if (moneyString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog("Have Space",
                            "Please Fill All Every Blank");

                } else {

//                    No Space
                    double moneyAdouble = Double.parseDouble(moneyString);

                    double answerAdouble;


                    if (aBoolean) {
                        answerAdouble = moneyAdouble / factorADouble;
                    } else {
                        answerAdouble = moneyAdouble * factorADouble;
                    }



                    String myAnswerString = String.format("%,.2f", answerAdouble);

                    if (aBoolean) {

                        String answerString = "Your Dolla ==> " + myAnswerString + " USD";

                        MyAlert myAlert = new MyAlert(getActivity());
                        myAlert.normalDialog("Thai Bath ==> " + moneyString + " THB", answerString);

                    } else {

                        String answerString = "Your Thai Bath ==> " + myAnswerString + " THB";

                        MyAlert myAlert = new MyAlert(getActivity());
                        myAlert.normalDialog("US USD ==> " + moneyString + " USD", answerString);

                    }







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
