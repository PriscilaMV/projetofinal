package br.iesb.grupo3.projetofinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EscolaService {
    @GET("rest/escolas")
    Call<List<Escola>> listarEscolas();
}
