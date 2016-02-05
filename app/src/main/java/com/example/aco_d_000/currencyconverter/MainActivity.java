package com.example.aco_d_000.currencyconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText prijsInvoer;
    private Button naarUSD, naarEuro;
    private TextView eindWaarde;
    private Double bedragDouble, resultaat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prijsInvoer = (EditText) findViewById(R.id.bedragField);
        naarUSD = (Button) findViewById(R.id.naarUSD);
        naarEuro = (Button) findViewById(R.id.naarEuro);
        eindWaarde = (TextView) findViewById(R.id.resultaat);

        textWatcher();
        naarEuro.setOnClickListener(this);
        naarUSD.setOnClickListener(this);
    }

    private void textWatcher(){
       prijsInvoer.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               bereken();

           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
    }

    private void bereken(){
        String bedragString = prijsInvoer.getText().toString();

        if(bedragString.equals("")){
            naarEuro.setEnabled(false);
            naarUSD.setEnabled(false);
        }
        else{
            bedragDouble = Double.parseDouble(bedragString);
            naarEuro.setEnabled(true);
            naarUSD.setEnabled(true);
        }


        if (resultaat != null) {
            eindWaarde.setText(String.format("%.2f",resultaat));
        }


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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.naarEuro:
                resultaat= bedragDouble*0.92;
                bereken();
                break;

            case R.id.naarUSD:
                resultaat= bedragDouble*1.08;
                bereken();
        }
    }
}
