package com.example.mapsactivity;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //-23.5856199,-46.6669873
        LatLng ibirapuera = new LatLng(-23.5856199, -46.6669873);
/*
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(ibirapuera);
        circleOptions.radius(500);
        circleOptions.strokeColor(Color.GRAY);
        circleOptions.strokeWidth(10);
        circleOptions.fillColor(Color.argb(128,255,153,0));*/

        //PolygonOptions polygonOptions=new PolygonOptions();


        //mMap.addCircle(circleOptions);
        mMap.addMarker(
                new MarkerOptions().
                        position(ibirapuera).
                        title("Local").snippet("descricao")
                        // .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_carro_roxo_48px))
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ibirapuera, 16));
        //set click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add(ibirapuera);
                polylineOptions.add(latLng);
                polylineOptions.color(Color.BLUE);
                polylineOptions.width(20);

                mMap.addPolyline(polylineOptions);

                Toast.makeText(MapsActivity.this,
                        "Lat: " + latitude + "Long:" + longitude,
                        Toast.LENGTH_SHORT).show();
                mMap.addMarker(
                        new MarkerOptions().
                                position(latLng).
                                title("Local").snippet("descricao")
                                // .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_loja))
                );

            }
        });


    }
}