package br.com.faculdadedelta.exercicio03hysleiman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.faculdadedelta.exercicio03hysleiman.adapter.AdapterHysleiman;
import br.com.faculdadedelta.exercicio03hysleiman.dao.DAOHysleiman;
import br.com.faculdadedelta.exercicio03hysleiman.modelo.EstoqueHysleiman;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListar;
    private DAOHysleiman daoHysleiman = new DAOHysleiman(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListar = findViewById(R.id.lvlista);

        lvListar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EstoqueHysleiman hysleimanSelecionado = (EstoqueHysleiman) parent.getItemAtPosition(position);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("hysleimanSelecionado", hysleimanSelecionado);
                startActivity(intent);
            }
        });

        lvListar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EstoqueHysleiman hysleimanSelecionado = (EstoqueHysleiman) parent.getItemAtPosition(position);

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
        lvListar.setAdapter(adapter);

    }
    public void novo(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);


    }
}
