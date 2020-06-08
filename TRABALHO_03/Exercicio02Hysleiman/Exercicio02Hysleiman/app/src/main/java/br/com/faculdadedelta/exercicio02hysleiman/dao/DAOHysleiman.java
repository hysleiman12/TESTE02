package br.com.faculdadedelta.exercicio02hysleiman.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.exercicio02hysleiman.modelo.VendaHysleiman;

public class DAOHysleiman extends SQLiteOpenHelper {

    private static final String NOME_BD = "venda";
    private static final int VERSAO_BD = 1;


    public DAOHysleiman(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    public void incluir(VendaHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cliente", hysleiman.getCliente());
        values.put("produto", hysleiman.getProduto());
        values.put("valor", hysleiman.getValor());
        values.put("quantidade", hysleiman.getQuantidade());

        db.insert("venda", null, values);
        db.close();
        //hysleiman.setId(idGerador++);
        // listaVenda.add(hysleiman);

    }
    public void excluir(VendaHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("venda", "id = ?", new String[] {String.valueOf(hysleiman.getId())});

        db.close();

        //listaVenda.remove(hysleiman);

    }
    public List<VendaHysleiman> listar(){
        List <VendaHysleiman> listaVenda = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM venda", null);

        if (cursor.moveToFirst()) {
            do {
                VendaHysleiman vendaHysleiman = new VendaHysleiman();
                vendaHysleiman.setId(cursor.getLong(0));
                vendaHysleiman.setCliente(cursor.getString(1));
                vendaHysleiman.setProduto(cursor.getString(2));
                vendaHysleiman.setValor(cursor.getDouble(3));
                vendaHysleiman.setQuantidade(cursor.getInt(4));


                listaVenda.add(vendaHysleiman);


            } while (cursor.moveToNext());
        }


        return listaVenda;
    }
    public void alterar(VendaHysleiman hysleiman){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cliente", hysleiman.getCliente());
        values.put("produto", hysleiman.getProduto());
        values.put("valor", hysleiman.getValor());
        values.put("quantidade", hysleiman.getQuantidade());
        db.update("venda", values, "id = ?", new String[]{String.valueOf(hysleiman.getId())});
       /* for (VendaHysleiman hysleiman1: listaVenda) {
            long idHysleiman = hysleiman.getId();
            long IdHysleiman1 = hysleiman1.getId();
            if(idHysleiman == IdHysleiman1){
                listaVenda.remove(hysleiman1);
                listaVenda.add(hysleiman);
                break;

            }

        }

        */
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE venda(id INTEGER PRIMARY KEY AUTOINCREMENT, cliente TEXT, produto TEXT, valor DOUBLE(10,5),quantidade INTEGER);");

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