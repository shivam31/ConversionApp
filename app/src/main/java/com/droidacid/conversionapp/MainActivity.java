package com.droidacid.conversionapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView teaspoon, tablespoon, cup, ounce, pint, quart,
            gallon, pound, milliliter, liter, milligram, kilogram;
    private Spinner unitTypeSpinner;
    private EditText amountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        amountText = (EditText) findViewById(R.id.etAmount);
        addItemsToUnitTypeSpinner();

        AddListenerToUnitTypeListener();

        initializeTextViews();
    }

    public void AddListenerToUnitTypeListener() {
        unitTypeSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String itemSelectedInSpinner =
                                parent.getItemAtPosition(position).toString();

                        //checkIfConvertingFromTsp(itemSelectedInSpinner);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    public void addItemsToUnitTypeSpinner() {


        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.conversionTypes,
                        android.R.layout.simple_spinner_item);
        unitTypeSpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
    }

    private void initializeTextViews() {
        teaspoon = (TextView) findViewById(R.id.tsp_text_view);
        tablespoon = (TextView) findViewById(R.id.tbs_text_view);
        cup = (TextView) findViewById(R.id.cup_text_view);
        ounce = (TextView) findViewById(R.id.oz_text_view);
        pint = (TextView) findViewById(R.id.pint_text_view);
        quart = (TextView) findViewById(R.id.quart_text_view);
        gallon = (TextView) findViewById(R.id.gallon_text_view);
        pound = (TextView) findViewById(R.id.pound_text_view);
        milliliter = (TextView) findViewById(R.id.ml_text_view);
        liter = (TextView) findViewById(R.id.liter_text_view);
        milligram = (TextView) findViewById(R.id.mg_text_view);
        kilogram = (TextView) findViewById(R.id.kg_text_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkIfConvertingFromTsp(String currentUnit) {
        if (currentUnit.equals("teaspoon")) {
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        } else {
            if (currentUnit.equals("tablespoon")) {
                updateUnitTypeUsingOther(Quantity.Unit.tbs);
            } else if (currentUnit.equals("cup")) {
                updateUnitTypeUsingOther(Quantity.Unit.cup);
            } else if (currentUnit.equals("ounce")) {
                updateUnitTypeUsingOther(Quantity.Unit.oz);
            } else if (currentUnit.equals("pint")) {
                updateUnitTypeUsingOther(Quantity.Unit.pint);
            } else if (currentUnit.equals("quart")) {
                updateUnitTypeUsingOther(Quantity.Unit.quart);
            } else if (currentUnit.equals("gallon")) {
                updateUnitTypeUsingOther(Quantity.Unit.gallon);
            } else if (currentUnit.equals("pound")) {
                updateUnitTypeUsingOther(Quantity.Unit.pound);
            } else if (currentUnit.equals("milliliter")) {
                updateUnitTypeUsingOther(Quantity.Unit.ml);
            } else if (currentUnit.equals("liter")) {
                updateUnitTypeUsingOther(Quantity.Unit.liter);
            } else if (currentUnit.equals("milligram")) {
                updateUnitTypeUsingOther(Quantity.Unit.mg);
            } else {
                updateUnitTypeUsingOther(Quantity.Unit.kg);
            }

        }
    }

    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountText.getText().toString());
        String teaspoonValueAndUnit = doubleToConvert + " " + teaspoon;
        teaspoon.setText(teaspoonValueAndUnit);

        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.cup, cup);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.oz, ounce);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.pint, pint);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.quart, quart);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallon);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.pound, pound);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.ml, milliliter);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.liter, liter);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.mg, milligram);
        updateUnitTextFieldsUsingTsp(doubleToConvert, Quantity.Unit.kg, kilogram);

    }

    public void updateUnitTextFieldsUsingTsp(double doubleToConvert,
                                             Quantity.Unit unitConvertingTo,
                                             TextView theTextView) {
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);
        String tempUnit = unitQuantity.to(unitConvertingTo).toString();
        theTextView.setText(tempUnit);
    }

    public void updateUnitTypeUsingOther(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountText.getText().toString());

        Quantity currentyQuantitySelected = new Quantity(doubleToConvert
                , currentUnit);

        String valueInTeaspoons = currentyQuantitySelected.to(Quantity.Unit.tsp).toString();
        teaspoon.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoon);

        if (currentUnit.name().equals(currentyQuantitySelected.unit.name())) {
            String currentUnitTextViewText = doubleToConvert + " " +
                    currentyQuantitySelected.unit.name();

            String currentTextViewName = currentyQuantitySelected.unit.name() +
                    "_text_view_";

            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());
            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextViewText);
        }
    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert,
                                            Quantity.Unit currentUnit,
                                            Quantity.Unit preferredUnit,
                                            TextView targetTextView) {
        Quantity currentQuantitySelected = new
                Quantity(doubleToConvert, currentUnit);

        String tempTextViewText = currentQuantitySelected.to(
                preferredUnit).toString();
        targetTextView.setText(tempTextViewText);
    }

}
