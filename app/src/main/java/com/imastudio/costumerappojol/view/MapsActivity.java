package com.imastudio.costumerappojol.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imastudio.costumerappojol.R;
import com.imastudio.costumerappojol.base.BaseActivity;
import com.imastudio.costumerappojol.helper.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.imastudio.costumerappojol.helper.MyContants.REQUEST_LOCATION;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    @BindView(R.id.imgpick)
    ImageView imgpick;
    @BindView(R.id.lokasiawal)
    TextView lokasiawal;
    @BindView(R.id.lokasitujuan)
    TextView lokasitujuan;
    @BindView(R.id.edtcatatan)
    EditText edtcatatan;
    @BindView(R.id.txtharga)
    TextView txtharga;
    @BindView(R.id.txtjarak)
    TextView txtjarak;
    @BindView(R.id.txtdurasi)
    TextView txtdurasi;
    @BindView(R.id.requestorder)
    Button requestorder;
    @BindView(R.id.rootlayout)
    RelativeLayout rootlayout;
    private GoogleMap mMap;
    private GPSTracker gps;
    private double myLat;
    private double myLng;
    private LatLng myLatLng;
    private String nameLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        callPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_LOCATION);
        checkGpsDevice(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_LOCATION&& grantResults[0] ==
                PackageManager.PERMISSION_GRANTED){
            checkPermissionAndCall();

        }
    }

    private void checkPermissionAndCall() {
        if (Build.VERSION.SDK_INT > 22) {
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
            else{
                getMyLocation();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getMyLocation();

     }

    private void getMyLocation() {
        gps = new GPSTracker(this);

        if (gps.canGetLocation()){
            myLat = gps.getLatitude();
            myLng =gps.getLongitude();
            Toast.makeText(this, "lat :"+myLat+"lon :"+myLng, Toast.LENGTH_SHORT)
                    .show();
            addMarker(myLat,myLng);
            lokasiawal.setText(nameLocation);
        }
    }

    private void addMarker(double myLat, double myLng) {
        myLatLng = new LatLng(myLat,myLng);
        nameLocation = convertLocation(myLat,myLng);
        mMap.addMarker(new MarkerOptions().position(myLatLng).title(nameLocation))
                .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pickup));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,18));
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    private String convertLocation(double myLat, double myLng) {
        nameLocation = null;
        Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
        try {
            List<Address> list = geocoder.getFromLocation(myLat, myLng, 1);
            if (list != null && list.size() > 0) {
                nameLocation = list.get(0).getAddressLine(0) + "" + list.get(0).getCountryName();

                //fetch data from addresses
            } else {
                Toast.makeText(MapsActivity.this, "kosong", Toast.LENGTH_SHORT).show();
                //display Toast message
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameLocation;
    }


    @OnClick({R.id.imgpick, R.id.lokasiawal, R.id.lokasitujuan, R.id.requestorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgpick:
                break;
            case R.id.lokasiawal:
                break;
            case R.id.lokasitujuan:
                break;
            case R.id.requestorder:
                break;
        }
    }
}
