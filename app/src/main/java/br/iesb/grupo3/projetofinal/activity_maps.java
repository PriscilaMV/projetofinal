package br.iesb.grupo3.projetofinal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class activity_maps extends FragmentActivity implements OnMapReadyCallback {

    public static final int MAP_PERMISSION_ACCESS_COURSE_LOCATION = 9999;

    private GoogleMap mMap;
    EscolaService escolaService;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mobile-aceite.tcu.gov.br:80/nossaEscolaRS/")
                .build();

        escolaService = retrofit.create(EscolaService.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Call<List<Escola>> requisicao = escolaService.listarEscolas();

        requisicao.enqueue(new Callback<List<Escola>>() {
            @Override
            public void onResponse(Call<List<Escola>> requisicao, Response<List<Escola>> resposta) {
                if (resposta.isSuccessful()) {
                    List<Escola> lista = resposta.body();

                    if (lista != null && lista.size() > 0) {
                        Escola e = lista.get(0);
                        LatLng escola = new LatLng(e.latitude, e.longitude);
                        mMap.addMarker(new MarkerOptions().position(escola).title(e.nome));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(escola, 15));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Escola>> requisicao, Throwable erro) {
                Toast.makeText(activity_maps.this, erro.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

