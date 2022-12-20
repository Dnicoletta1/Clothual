package com.example.clothual.UI.core.MapFragment;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import com.example.clothual.databinding.FragmentMapBinding;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("deprecation")
public class MapFragment extends Fragment implements LocationListener {

    public FragmentMapBinding binding;

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    private IMapController mapController;
    protected double latitude;
    protected double longitude;
    private static final String TAG = "OsmActivity";


    private static final int PERMISSION_REQUEST_CODE = 1;

    public MapFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);

        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED
                                            &&
                ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }






        System.out.println(latitude);
        System.out.println(longitude);
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK);
        binding.mapView.setMultiTouchControls(true);
        mapController = binding.mapView.getController();
        mapController.setZoom(15);
        GeoPoint startPoint = new GeoPoint(longitude, latitude);
        binding.mapView.setZoomRounding(true);
        mapController.setCenter(startPoint);

        binding.center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1,     new LocationListener()
                {

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onLocationChanged(Location location) {
                        // TODO Auto-generated method stub
                        GeoPoint geo = new GeoPoint(location.getLatitude(), location.getLongitude());
                        mapController.setCenter(geo);
                    }
                });


            }
        });

    }

    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        if (binding.mapView != null)
            binding.mapView.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        if (binding.mapView != null)
            binding.mapView.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PermissionChecker.PERMISSION_GRANTED && checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PermissionChecker.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}