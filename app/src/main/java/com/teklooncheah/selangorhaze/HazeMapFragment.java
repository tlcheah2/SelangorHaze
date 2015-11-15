package com.teklooncheah.selangorhaze;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teklooncheah.selangorhaze.model.HazeReading;
import com.teklooncheah.selangorhaze.util.Cache;
import com.teklooncheah.selangorhaze.util.LocationHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tekloon on 11/13/15.
 */
public class HazeMapFragment extends Fragment {

    @Bind(R.id.map_container)
    RelativeLayout mapContainer;
    @Bind(R.id.imgMyLocation)
    ImageView imgMyLocation;

    private ArrayList<HazeReading> mHazeReading;
    private GoogleMap map;
    private LatLngBounds.Builder bounds;
    private LatLng mLatLng;
    private Map<Marker, HazeReading> mMarkerMap = new HashMap<>();
    private SupportMapFragment fragment;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Cache.getInstance().getLru().get("HazeInfo")!=null){
            mHazeReading = HazeReading.deserializeList(Cache.getInstance().getLru().get("HazeInfo").toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        ButterKnife.bind(this, view);

        /*
        In order to inflate the MapView within the Child Fragment.
        You will need to directly getChildFragment manager and replace the Child Fragment
        directly using the SupportMapFragment class.

        fragment class in xml is not working. That is only applicable when your mapView activity hold your map fragment.
        The reason is being you cannot have a fragment within a fragment, which means nested fragment.
        http://developer.android.com/about/versions/android-4.2.html#NestedFragments
        */
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, fragment).commit();
        }
        return  view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        initializeMap();
    }

    public void initializeMap() {
        if(map == null){
            map = fragment.getMap();
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setMapToolbarEnabled(false);
            bounds = new LatLngBounds.Builder();

            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    LocationHelper.setLocation(location);
                }
            });
            for (int i = 0; i < mHazeReading.size(); i++) {
                HazeReading mHazeInfo = mHazeReading.get(i);
                mLatLng = new LatLng(mHazeInfo.getLocation().getCoordinates().getLatitude(),
                        mHazeInfo.getLocation().getCoordinates().getLongitude());
                bounds.include(mLatLng);
                final MarkerOptions markerOptions = new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .title(mHazeInfo.getLocation().getArea())
                        .position(mLatLng);
                Marker marker = map.addMarker(markerOptions);
                mMarkerMap.put(marker, mHazeInfo);
            }
            if (mHazeReading.size() == 1) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));
            } else {
                map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition arg0) {
                        // Move camera.
                        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50));
                        // Remove listener to prevent position reset on camera move.
                        map.setOnCameraChangeListener(null);
                        map.moveCamera(CameraUpdateFactory.scrollBy(1, 1));
                    }
                });
            }

            /*Currently Not Working*/
//            imgMyLocation.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (LocationHelper.getLocation() != null) {
//                        Location myCurLoc = LocationHelper.getLocation();
//                        LatLng curLocLatLng = new LatLng(myCurLoc.getLatitude(), myCurLoc.getLongitude());
//                        MarkerOptions markerCurLocOptions = new MarkerOptions()
//                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//                                .title(getResources().getString(R.string.YouAreHere))
//                                .position(curLocLatLng);
//                        map.addMarker(markerCurLocOptions);
//                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocLatLng, 12));
//                    } else {
//                        AlertDialog gpsAlert = new AlertDialog.Builder(getActivity())
//                                .setTitle(getResources().getString(R.string.Alert))
//                                .setMessage(getResources().getString(R.string.LocationNotFound))
//                                .setPositiveButton(getResources().getString(R.string.Ok), new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        return;
//                                    }
//                                })
//                                .create();
//                        gpsAlert.show();
//                    }
//                }
//            });
        }

    }



    //    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        map.getUiSettings().setMapToolbarEnabled(false);
//        bounds = new LatLngBounds.Builder();
//
//        map.setMyLocationEnabled(true);
//        map.getUiSettings().setMyLocationButtonEnabled(false);
//        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//                LocationHelper.setLocation(location);
//            }
//        });
//////            map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
//////                @Override
//////                public boolean onMyLocationButtonClick() {
//////                    return false;
//////                }
//////            });
//////        map.getUiSettings().setMyLocationButtonEnabled(true);
////            map.moveCamera(CameraUpdateFactory.newLatLng(park));
////        } else {
//        for (int i = 0; i < mHazeReading.size(); i++) {
//            HazeReading mHazeInfo = mHazeReading.get(i);
//            mLatLng = new LatLng(mHazeInfo.getLocation().getCoordinates().getLatitude(),
//                    mHazeInfo.getLocation().getCoordinates().getLongitude());
//            bounds.include(mLatLng);
//            final MarkerOptions markerOptions = new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
//                    .title(mHazeInfo.getLokasi())
//                    .position(mLatLng);
//            Marker marker = map.addMarker(markerOptions);
//            mMarkerMap.put(marker, mHazeInfo);
//        }
//        if (mHazeReading.size() == 1) {
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));
//        } else {
//            map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
//                @Override
//                public void onCameraChange(CameraPosition arg0) {
//                    // Move camera.
//                    map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50));
//                    // Remove listener to prevent position reset on camera move.
//                    map.setOnCameraChangeListener(null);
//                    map.moveCamera(CameraUpdateFactory.scrollBy(1, 1));
//                }
//            });
//
////            map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
////                @Override
////                public void onInfoWindowClick(Marker marker) {
////                    if(marker.getTitle().equals(getString(R.string.YouAreHere)))
////                    {
////                        return;
////                    }
////                    else {
////                        Park park = mMarkerMap.get(marker);
////                        Intent i = new Intent(MapViewActivity.this, ParkDetailActivity.class);
////                        i.putExtra("Park", park.toString());
////                        startActivity(i);
////                    }
////                }
////            });
//
//        }
//
//        imgMyLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (LocationHelper.getLocation() != null) {
//                    Location myCurLoc = LocationHelper.getLocation();
//                    LatLng curLocLatLng = new LatLng(myCurLoc.getLatitude(), myCurLoc.getLongitude());
//                    MarkerOptions markerCurLocOptions = new MarkerOptions()
//                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//                            .title(getResources().getString(R.string.YouAreHere))
//                            .position(curLocLatLng);
//                    map.addMarker(markerCurLocOptions);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocLatLng, 12));
//                } else {
//                    AlertDialog gpsAlert = new AlertDialog.Builder(getActivity())
//                            .setTitle(getResources().getString(R.string.Alert))
//                            .setMessage(getResources().getString(R.string.LocationNotFound))
//                            .setPositiveButton(getResources().getString(R.string.Ok), new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    return;
//                                }
//                            })
//                            .create();
//                    gpsAlert.show();
//                }
//            }
//        });
}
