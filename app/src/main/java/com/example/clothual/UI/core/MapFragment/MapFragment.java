package com.example.clothual.UI.core.MapFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clothual.databinding.FragmentMapBinding;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("deprecation")
public class MapFragment extends Fragment {

    public FragmentMapBinding binding;
    private MyLocationNewOverlay locationOverlay;
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

        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        binding.mapView.setTileSource(TileSourceFactory.MAPNIK);
        binding.mapView.setBuiltInZoomControls(true);
        binding.mapView.setMultiTouchControls(true);
        binding.mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        IMapController mapController = binding.mapView.getController();
        mapController.setZoom(17);

        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);
        mapController.setCenter(startPoint);
        GpsMyLocationProvider provider = new GpsMyLocationProvider(ctx);
        provider.addLocationSource(LocationManager.NETWORK_PROVIDER);
        locationOverlay = new MyLocationNewOverlay(provider, binding.mapView);
        locationOverlay.enableFollowLocation();
        locationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                Log.d("MyTag", String.format("First location fix: %s", locationOverlay.getLastFix()));
            }
        });
        binding.mapView.getOverlayManager().add(locationOverlay);

        binding.center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationOverlay.enableFollowLocation();
            }
        });

    }

    public void onResume(){
        super.onResume();
        locationOverlay.enableMyLocation();
    }

    public void onPause(){
        super.onPause();
        locationOverlay.disableMyLocation();
    }

}