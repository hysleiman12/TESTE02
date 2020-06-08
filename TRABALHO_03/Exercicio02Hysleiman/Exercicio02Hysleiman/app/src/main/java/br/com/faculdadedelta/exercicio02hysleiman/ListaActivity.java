package br.com.faculdadedelta.exercicio02hysleiman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.faculdadedelta.exercicio02hysleiman.adapter.AdapterHysleiman;
import br.com.faculdadedelta.exercicio02hysleiman.dao.DAOHysleiman;
import br.com.faculdadedelta.exercicio02hysleiman.modelo.VendaHysleiman;

public class ListaActivity extends AppCompatActivity {
    private ListView lvLista;
    private DAOHysleiman daoHysleiman = new DAOHysleiman(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvLista = findViewById(R.id.lvlista);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VendaHysleiman hysleimanSelecionado = (VendaHysleiman) parent.getItemAtPosition(position);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("hysleimanSelecionado", hysleimanSelecionado);
                startActivity(intent);
            }
        });
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                VendaHysleiman hysleimanSelecionado = (VendaHysleiman) parent.getItemAtPosition(position);
                daoHysleiman.excluir(hysleimanSelecionado);
                carregarLista();

                return false;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
    private void carregarLista(){
        AdapterHysleiman adapter = new AdapterHysleiman(daoHysleiman.listar(), getBaseContext());
        lvLista.setAdapter(adapter);
    }
    public void novo(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);


    }
}
