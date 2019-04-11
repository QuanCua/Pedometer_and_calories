package com.skyfree.pedometer_and_calorimeter.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.skyfree.pedometer_and_calorimeter.Key;
import com.skyfree.pedometer_and_calorimeter.R;


import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.media.CamcorderProfile.get;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    Spinner spnStepGoal, spnSensitivity, spnWeight, spnGender, spnStepLength, spnFirstDay;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

        spnStepGoal = (Spinner) view.findViewById(R.id.spnStepGoal);
        spnSensitivity = (Spinner) view.findViewById(R.id.spnSensitivity);
        spnWeight = (Spinner) view.findViewById(R.id.spnWeight);
        spnGender = (Spinner) view.findViewById(R.id.spnGender);
        spnStepLength = (Spinner) view.findViewById(R.id.spnStepLength);
        spnFirstDay = (Spinner) view.findViewById(R.id.spnFirstDay);

        final List<Integer> listStepGoal = new ArrayList<>();
        final List<String> listGender = new ArrayList<>();

        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        String genderValue = sharedPreferences.getString(Key.GENDER, "notsetValue");
//        if (!genderValue.equalsIgnoreCase("notsetValue")) {
//            System.out.println(genderValue + "----------------------------");
//        }



        stepGoalData(listStepGoal);
        genderData(listGender);

        ArrayAdapter<Integer> adapterStepGoal = new ArrayAdapter<Integer>(this.getActivity(), R.layout.custom_spinner_item, listStepGoal);
        adapterStepGoal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStepGoal.setAdapter(adapterStepGoal);

        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(this.getActivity(), R.layout.custom_spinner_item, listGender);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGender.setAdapter(adapterGender);

//        String getSaved = sharedPreferences.getString("KEY_SAVE_STEP_GOAL", "notSaved");
//        if (!getSaved.equalsIgnoreCase("notSaved")) {
//            spnStepGoal.setSelection();
//        }


        spnStepGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saveStepGoal(Key.STEP_GOAL,listStepGoal.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saveGender(Key.GENDER, listGender.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;



    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    public void stepGoalData(List list){
        list.add(500);
        list.add(1000);
        list.add(1500);
        list.add(2000);
        list.add(2500);
        list.add(3000);
        list.add(3500);
        list.add(4000);
        list.add(4500);
        list.add(5000);
        list.add(5500);
        list.add(6000);
        list.add(6500);
        list.add(7000);
    }

    public void genderData(List list){
        list.add("Male");
        list.add("Female");
    }

    public void saveStepGoal(String key, Integer value){
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveGender(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }


}
