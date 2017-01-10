package gr.apphill.searchflights;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import gr.apphill.searchflights.FlightsResults.MySearchFlight;


public class MainActivity extends AppCompatActivity {
    //date
    private Calendar calendar;
    private int year, month, day;
    //number_picker_dialog views
    Dialog dialog;
    NumberPicker infantsNumberPicker, adultsNumberPicker, childrenNumberPicker;
    Button cancelPickerButton, okPickerButton;
    //layout views
    private RadioGroup returnOnewayRadioGroup;
    private RadioButton radioReturn, radioOneWay;
    Spinner spinner;
    private TextView adultsTextViewValue, childrenTextViewValue, infantsTextViewValue;
    private TextView departureTextView, destinationTextView, departureDateTextView, returnDateTextView;
    //flight parameters
    private String originAirportLabel, originAirportValue;
    private String destinationAirportLabel, destinationAirportValue;
    private String departureDate, returnDate;
    private int adults, children, infants;
    private String travelClass;
    private String currency;
    //variables
    int originOrDestination = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayoutViews();
        initSpinnerView();
        getDateInstance();

    }//onCreate

    //menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, MyPreferencesActivity.class));
                return true;
        }//switch
        return true;
    }//onOptionsItemSelected

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }//onCreateOptionsMenu

    //date
    private void getDateInstance() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }//getDateInstance

    //date
    public void departureDateMethod(View v) {
        DatePickerDialog.OnDateSetListener myDateListener = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0,
                                          int arg1, int arg2, int arg3) {
                        // TODO Auto-generated method stub
                        String month;
                        arg2 += 1;
                        if (arg2 < 10) {
                            month = "0" + arg2;
                        } else {
                            month = "" + arg2;
                        }
                        String day;
                        if (arg3 < 10) {
                            day = "0" + arg3;
                        } else {
                            day = "" + arg3;
                        }
                        if (arg3 < 10) {
                            departureDateTextView.setText(new StringBuilder().append(arg1).append("-")
                                    .append(month).append("-").append(day));
                        } else {
                            departureDateTextView.setText(new StringBuilder().append(arg1).append("-")
                                    .append(month).append("-").append(day));
                        }
                    }
                };
        new DatePickerDialog(this,
                myDateListener, year, month, day).show();

    }//departureDateMethod

    //date
    public void returnDateMethod(View v) {
        DatePickerDialog.OnDateSetListener myDateListener = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0,
                                          int arg1, int arg2, int arg3) {
                        String month;
                        arg2 += 1;
                        if (arg2 < 10) {
                            month = "0" + arg2;
                        } else {
                            month = "" + arg2;
                        }
                        String day;
                        if (arg3 < 10) {
                            day = "0" + arg3;
                        } else {
                            day = "" + arg3;
                        }
                        if (arg3 < 10) {
                            returnDateTextView.setText(new StringBuilder().append(arg1).append("-")
                                    .append(month).append("-").append(day));
                        } else {
                            returnDateTextView.setText(new StringBuilder().append(arg1).append("-")
                                    .append(month).append("-").append(day));
                        }
                    }
                };
        new DatePickerDialog(this,
                myDateListener, year, month, day).show();

    }//returnDateMethod

    private void initSpinnerView() {
        String[] spinnerItems = new String[]{getString(R.string.economy), getString(R.string.prenium_economy), getString(R.string.business_class), getString(R.string.first_class)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(adapter);
    }//initSpinnerView

    private void initLayoutViews() {
        spinner = (Spinner) findViewById(R.id.spinnerClass);
        departureTextView = (TextView) findViewById(R.id.departureTextView);
        destinationTextView = (TextView) findViewById(R.id.destinationTextView);
        departureDateTextView = (TextView) findViewById(R.id.departureDateTextView);
        returnDateTextView = (TextView) findViewById(R.id.returnDateTextView);
        adultsTextViewValue = (TextView) findViewById(R.id.adultsTextViewValue);
        childrenTextViewValue = (TextView) findViewById(R.id.childrenTextViewValue);
        infantsTextViewValue = (TextView) findViewById(R.id.infantsTextViewValue);
        returnOnewayRadioGroup = (RadioGroup) findViewById(R.id.returnOnewayRadioGroup);
        radioOneWay = (RadioButton) findViewById(R.id.radioOneWay);
        radioReturn = (RadioButton) findViewById(R.id.radioReturn);
        radioOneWay = (RadioButton) findViewById(R.id.radioOneWay);
        returnOnewayRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioReturn.isChecked()) {
                    returnDateTextView.setVisibility(View.VISIBLE);
                } else {
                    returnDateTextView.setVisibility(View.INVISIBLE);
                }
            }
        });
        initNumberPickerDialog();
    }//initLayoutViews

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK && data != null) {
            originOrDestination = requestCode;//1 for origin, 2 for destination
            if (requestCode == 1) {
                originAirportLabel = data.getStringExtra("airportLabel");
                originAirportValue = data.getStringExtra("airportValue");
                departureTextView.setText(originAirportLabel);
            } else if (requestCode == 2) {
                destinationAirportLabel = data.getStringExtra("airportLabel");
                destinationAirportValue = data.getStringExtra("airportValue");
                destinationTextView.setText(destinationAirportLabel);
            }
        }
    }//onActivityResult

    public void departureAirportMethod(View v) {
        startActivityForResult(new Intent(this, SearchAirportsActivity.class).putExtra("chooseAirport", "departure"), 1);
    }//departureAirportMethod

    public void destinationAirportMethod(View v) {
        startActivityForResult(new Intent(this, SearchAirportsActivity.class).putExtra("chooseAirport", "destination"), 2);
    }//destinationAirportMethod

    public void searchFlightsMethod(View v) {
        MySearchFlight mySearchFlight = getSearchFlightsParameters();
        startActivity(new Intent(this, SearchFlightsActivity.class).putExtra("mySearchFlight", mySearchFlight));//pass the object to SearchFlightsActivity
    }//SearchFlights

    private MySearchFlight getSearchFlightsParameters() {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String currency = SP.getString("currencyPref", "EUR");
        if (adults == 0) {
            adults = 1;
        }
        departureDate = departureDateTextView.getText().toString();
        if (radioReturn.isChecked()) {
            returnDate = returnDateTextView.getText().toString();
        } else {
            returnDate = null;
        }
        travelClass = getSpinnerSelectedItem();
        MySearchFlight myMySearchFlight = new MySearchFlight(originAirportValue, destinationAirportValue, departureDate, returnDate, adults, children, infants, travelClass, currency);
        return myMySearchFlight;
    }//getSearchFlightsParameters

    private String getSpinnerSelectedItem() {
        switch (spinner.getSelectedItem().toString()) {
            case "Economy":
                travelClass = getString(R.string.economy_value);
                break;
            case "Prenium economy":
                travelClass = getString(R.string.prenium_economy_value);
                break;
            case "Business class":
                travelClass = getString(R.string.business_class_value);
                break;
            case "First class":
                travelClass = getString(R.string.first_class_value);
                break;
            default:
                travelClass = getString(R.string.economy_value);
                break;
        }//switch
        return travelClass;
    }//getSpinnerSelectedItem

    private void initNumberPickerDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.number_picker_dialog);
        adultsNumberPicker = (NumberPicker) dialog.findViewById(R.id.adultsNumberPicker);
        adultsNumberPicker.setMinValue(1);
        adultsNumberPicker.setMaxValue(9);
        adultsNumberPicker.setWrapSelectorWheel(true);
        childrenNumberPicker = (NumberPicker) dialog.findViewById(R.id.childrenNumberPicker);
        childrenNumberPicker.setMinValue(0);
        childrenNumberPicker.setMaxValue(7);
        childrenNumberPicker.setWrapSelectorWheel(true);
        infantsNumberPicker = (NumberPicker) dialog.findViewById(R.id.infantsNumberPicker);
        infantsNumberPicker.setMinValue(0);
        infantsNumberPicker.setMaxValue(7);
        infantsNumberPicker.setWrapSelectorWheel(true);
        cancelPickerButton = (Button) dialog.findViewById(R.id.cancelPickerButton);
        cancelPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        okPickerButton = (Button) dialog.findViewById(R.id.okPickerButton);
        okPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adults = adultsNumberPicker.getValue();
                adultsTextViewValue.setText(adults + "");
                children = childrenNumberPicker.getValue();
                childrenTextViewValue.setText(children + "");
                infants = infantsNumberPicker.getValue();
                infantsTextViewValue.setText(infants + "");
                dialog.dismiss();
            }
        });
    }//initNumberPickerDialog

    public void openNumberPickerDialog(View v) {
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }//openNumberPickerDialog

    //handle orientation changes - save values
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //save all values from the views
        savedInstanceState.putInt("spinner", spinner.getSelectedItemPosition());
        savedInstanceState.putInt("adults", adultsNumberPicker.getValue());
        savedInstanceState.putInt("children", childrenNumberPicker.getValue());
        savedInstanceState.putInt("infants", infantsNumberPicker.getValue());
        savedInstanceState.putString("departureDate", departureDateTextView.getText().toString());
        savedInstanceState.putString("returnDate", returnDateTextView.getText().toString());
        savedInstanceState.putString("departureTextView", departureTextView.getText().toString());
        savedInstanceState.putString("departure", originAirportValue);
        savedInstanceState.putString("destinationTextView", destinationTextView.getText().toString());
        savedInstanceState.putString("destination", destinationAirportValue);
        savedInstanceState.putInt("radiiobuttonid", returnOnewayRadioGroup.getCheckedRadioButtonId());
        /*
        Log.v("onSaveInstanceState", " Saved Values:");//
        Log.v("spinnerClass", " " + spinner.getSelectedItem().toString());//
        Log.v("adults", " " + adultsNumberPicker.getValue());//
        Log.v("adults", " " + childrenNumberPicker.getValue());//
        Log.v("adults", " " + infantsNumberPicker.getValue());//
        Log.v("departureDate", " " + departureDateTextView.getText().toString());//
        Log.v("returnDate", " " + returnDateTextView.getText().toString());//
        Log.v("departure", " " + departureTextView.getText().toString());//
        Log.v("destination", " " + destinationTextView.getText().toString());//
        Log.v("radiiobuttonid", " " + returnOnewayRadioGroup.getCheckedRadioButtonId());//
        */
    }//onSaveInstanceState

    //handle orientation changes - restore values
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //restore all values
        spinner.setSelection(savedInstanceState.getInt("spinner"));
        adultsTextViewValue.setText("" + savedInstanceState.getInt("adults"));
        childrenTextViewValue.setText("" + savedInstanceState.getInt("children"));
        infantsTextViewValue.setText("" + savedInstanceState.getInt("infants"));
        adultsNumberPicker.setValue(savedInstanceState.getInt("adults"));
        childrenNumberPicker.setValue(savedInstanceState.getInt("children"));
        infantsNumberPicker.setValue(savedInstanceState.getInt("infants"));
        adults = savedInstanceState.getInt("adults");
        infants = savedInstanceState.getInt("children");
        children = savedInstanceState.getInt("infants");
        departureDateTextView.setText(savedInstanceState.getString("departureDate"));
        returnDateTextView.setText(savedInstanceState.getString("returnDate"));
        departureTextView.setText(savedInstanceState.getString("departureTextView"));
        originAirportValue = savedInstanceState.getString("departure");
        destinationTextView.setText(savedInstanceState.getString("destinationTextView"));
        destinationAirportValue = savedInstanceState.getString("destination");
        returnOnewayRadioGroup.check(savedInstanceState.getInt("radiiobuttonid"));
        /*
        Log.v("onRestoreInstanceState", " Restored Values:");//
        Log.v("spinnerClass", " " + spinner.getSelectedItem().toString());//
        Log.v("adults", " " + adultsNumberPicker.getValue());//
        Log.v("adults", " " + childrenNumberPicker.getValue());//
        Log.v("adults", " " + infantsNumberPicker.getValue());//
        Log.v("departureDate", " " + departureDateTextView.getText().toString());//
        Log.v("returnDate", " " + returnDateTextView.getText().toString());//
        Log.v("departure", " " + departureTextView.getText().toString());//
        Log.v("destination", " " + destinationTextView.getText().toString());//
        Log.v("radiiobuttonid", " " + returnOnewayRadioGroup.getCheckedRadioButtonId());//
        */
    }//onRestoreInstanceState

}//MainActivity
