package com.example.aplicacionfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplicacionfirebase.pojo.UbicacionPojo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient ubicacion;
    Button btnUbi;

    FirebaseDatabase database;
    DatabaseReference reubicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=FirebaseDatabase.getInstance();
        reubicacion=database.getReference("ubicacion");

        btnUbi=findViewById(R.id.button);
        btnUbi.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"si funciono",Toast.LENGTH_SHORT).show();
                dameUbicacion();
            }
        });

    }

    private void dameUbicacion() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this,"si permiso",Toast.LENGTH_SHORT).show();

        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                   Manifest.permission.ACCESS_COARSE_LOCATION,
                   Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        ubicacion= LocationServices.getFusedLocationProviderClient(MainActivity.this);
        ubicacion.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null)
                {
                    Double latitud=location.getLatitude();
                    Double longitud=location.getLongitude();


                    UbicacionPojo ubi=new UbicacionPojo(latitud,longitud);
                    reubicacion.push().setValue(ubi);
                    Toast.makeText(MainActivity.this,"Ubicacion agregada",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this,"Latitud"+latitud+"Longitud"+longitud,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}