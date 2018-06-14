package br.iesb.grupo3.projetofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1614290077 on 11/05/2018.
 */

public class CriaBanco extends SQLiteOpenHelper{

        public static final String NOME_BANCO = "usuarios.db";
    public static final String TABELA = "usuario";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String EMAIL   = "email";
    public static final String SENHA = "senha";
    public static final String CONFIRMSENHA="confirmsenha";
    public static final int VERSAO = 1;


    public CriaBanco(Context context){
        super(context, NOME_BANCO,null, VERSAO);
    }

@Override
public void onCreate(SQLiteDatabase db) {
    String sql = "CREATE TABLE"+TABELA+"("
            + ID + " integer primary key autoincrement,"
            + NOME + " text,"
            + EMAIL + " text,"
            + SENHA + " text,"
            + CONFIRMSENHA + " text"
            +")";
    db.execSQL(sql);

        }

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABELA);
    onCreate(db);
        }
        }


