package com.droidyfennec.neo4j.driver.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Statement;

import static org.neo4j.driver.v1.Values.parameters;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void addPerson(String fname, String lname){
        Driver driver = null;
        try{
            driver = GraphDatabase.driver("bolt://192.168.1.87:7687", AuthTokens.basic("neo4j","password"));
            Statement st = new Statement("CREATE (p:Person{firstname:$fname,lastname:$lname})",
                    parameters("fname",fname,"lname",lname));
            driver.session().run(st);
        }catch (Exception e){
            Log.d("NEO4J", "addPerson: ",e);
        }finally {
            if(driver != null){
                driver.close();
            }
        }
    }

    public void register(View view) {
        final String firstname = ((EditText)findViewById(R.id.firstName)).getEditableText().toString();
        final String lastname = ((EditText)findViewById(R.id.lastName)).getEditableText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                addPerson(firstname,lastname);
            }
        }).start();
    }
}
