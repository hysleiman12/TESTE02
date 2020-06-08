package br.com.faculdadedelta.exercicio03hysleiman.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.exercicio03hysleiman.modelo.EstoqueHysleiman;

public class DAOHysleiman extends SQLiteOpenHelper {

    private static final String NOME_BD = "estoque";
    private static final int VERSAO_BD = 2;

    public DAOHysleiman(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }
    // private static List<EstoqueHysleiman> listaEstoque = new ArrayList<>();
    //private static Long idGerador = 1L;

    public void incluir(EstoqueHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("produto", hysleiman.getProduto());
        values.put("fornecedor", hysleiman.getFornecedor());
        values.put("valor", hysleiman.getValor());


        db.insert("estoque", null, values);

        //hysleiman.setId(idGerador++);
        //listaEstoque.add(hysleiman);

    }
    public void excluir(EstoqueHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("estoque", "id = ?", new String[] {String.valueOf(hysleiman.getId())});

       // listaEstoque.remove(hysleiman);

        db.close();
    }
    public List<EstoqueHysleiman> listar(){
        List<EstoqueHysleiman> listaEstoque = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM estoque", null);
        if(cursor.moveToFirst()){
            do {
                EstoqueHysleiman estoqueHysleiman = new EstoqueHysleiman();
                estoqueHysleiman.setId(cursor.getLong(0));
                estoqueHysleiman.setProduto(cursor.getString(1));
                estoqueHysleiman.setFornecedor(cursor.getString(2));
                estoqueHysleiman.setValor(cursor.getDouble(3));

                listaEstoque.add(estoqueHysleiman);

            }while (cursor.moveToNext());

        }

        return listaEstoque;
    }
    public void alterar(EstoqueHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("produto", hysleiman.getProduto());
        values.put("fornecedor", hysleiman.getFornecedor());
        values.put("valor", hysleiman.getValor());
        db.update("estoque", values, "id = ?", new String[]{String.valueOf(hysleiman.getId())});


        /*for (EstoqueHysleiman hysleiman1: listaEstoque) {
            long idHysleiman = hysleiman.getId();
            long IdHysleiman1 = hysleiman1.getId();
            if(idHysleiman == IdHysleiman1){
                listaEstoque.remove(hysleiman1);
                listaEstoque.add(hysleiman);
                break;

            }

        }

         */
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE estoque(id INTEGER PRIMARY KEY AUTOINCREMENT, produto TEXT, fornecedor TEXT, valor DOUBLE(10,5));");

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
