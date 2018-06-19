package br.iesb.grupo3.projetofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import br.iesb.grupo3.projetofinal.Usuario;

public class UsuarioDAO extends SQLiteOpenHelper {

    public UsuarioDAO(Context context) {
        super(context, "PerfilActivity", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuarios(" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "senha TEXT NOT NULL, " +
                "confirmarsenha TEXT NOT NULL );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private ContentValues getDadosUsuario(Usuario usuario) {
        ContentValues dados = new ContentValues();

        dados.put("nome", usuario.getNome());
        dados.put("email", usuario.getEmail());
        dados.put("senha", usuario.getSenha());
        dados.put("confirmarsenha", usuario.getConfirmarsenha());
        return dados;
    }

    public List<Usuario> buscaUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM Usuarios;";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        while(c.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(c.getLong(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setEmail(c.getString(c.getColumnIndex("email")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));
            usuario.setConfirmarsenha(c.getString(c.getColumnIndex("confirmarsenha")));
            usuarios.add(usuario);
        }

        c.close();
        return usuarios;
    }

    public void insert(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getDadosUsuario(usuario);

        db.insert("Usuarios", null, dados);
    }

    public void delete(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {usuario.getId().toString()};

        db.delete("Usuarios", "id = ?", params);
    }

    public void edit(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getDadosUsuario(usuario);

        String [] params = {usuario.getId().toString()};

        db.update("Usuarios", dados, "id = ?", params);
    }
}
