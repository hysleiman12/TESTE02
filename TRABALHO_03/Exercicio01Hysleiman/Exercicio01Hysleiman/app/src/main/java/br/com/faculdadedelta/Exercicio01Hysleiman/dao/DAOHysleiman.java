package br.com.faculdadedelta.Exercicio01Hysleiman.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.Exercicio01Hysleiman.modelo.BemHysleiman;

public class DAOHysleiman  extends SQLiteOpenHelper {
    private static final String NOME_BD = "bem";
    private static final int VERSAO_BD = 1;


    public DAOHysleiman(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    // private static List<BemHysleiman> listaBem = new ArrayList<>();
    //private static Long idGerador = 1L;

    public void incluir(BemHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descricao", hysleiman.getDescricao());
        values.put("especificacao", hysleiman.getEspecificacao());
        values.put("departamento", hysleiman.getDepartamento());
        values.put("valor", hysleiman.getValor());
        values.put("quantidade", hysleiman.getQuantidade());

        db.insert("bem", null, values);


      // hysleiman.setId(idGerador++);
       // listaBem.add(hysleiman);

    }
    public void excluir(BemHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("bem", "id = ?", new String[] {String.valueOf(hysleiman.getId())});

       // listaBem.remove(hysleiman);
        db.close();

    }
    public List<BemHysleiman> listar(){

        List <BemHysleiman> listabem = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM bem", null );

        if (cursor.moveToFirst()) {
            do {
                BemHysleiman bemHysleiman = new BemHysleiman();
                bemHysleiman.setId((long) Integer.parseInt(cursor.getString(0)));
                bemHysleiman.setDescricao(cursor.getString(1));
                bemHysleiman.setEspecificacao(cursor.getString(2));
                bemHysleiman.setDepartamento(cursor.getString(3));
                bemHysleiman.setValor(Double.parseDouble(cursor.getString(4)));
                bemHysleiman.setQuantidade(Integer.parseInt(cursor.getString(5)));


                listabem.add(bemHysleiman);


            } while (cursor.moveToNext());
        }
        return listabem;
    }
    public void alterar(BemHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descricao", hysleiman.getDescricao());
        values.put("especificacao", hysleiman.getEspecificacao());
        values.put("departamento", hysleiman.getDepartamento());
        values.put("valor", hysleiman.getValor());
        values.put("quantidade", hysleiman.getQuantidade());
        db.update("bem", values, "id = ?", new String[]{String.valueOf(hysleiman.getId())});
       /* for (BemHysleiman hysleiman1: listaBem) {
            long idBemHysleiman = hysleiman.getId();
            long IdHysleiman1 = hysleiman1.getId();
            if(idBemHysleiman == IdHysleiman1){
                listaBem.remove(hysleiman1);
                listaBem.add(hysleiman);
                break;

            }

        }

        */
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE bem(id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT  , especificacao TEXT , departamento TEXT , valor DOUBLE(10,5), quantidade INTEGER);");
        this.onUpgrade(db, 1, VERSAO_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("HELPER", "onUpgrade");
        //  onCreate(db);

        switch (oldVersion){
            case 1:
                Log.d("HElper", "Atualização 2");

            case 2:
                Log.d("Helper", "Atualização 3");
            case 3:
                Log.d("Helper", "Atualização 4");
            case 4:
                Log.d("Helper", "Atualização 5");
        }
    }
}
