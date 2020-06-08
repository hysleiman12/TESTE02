package br.com.faculdadedelta.exercicio03hysleiman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.faculdadedelta.exercicio03hysleiman.dao.DAOHysleiman;
import br.com.faculdadedelta.exercicio03hysleiman.modelo.EstoqueHysleiman;

public class MainActivity extends AppCompatActivity {

    private EditText etProduto;
    private EditText etFornecedor;
    private EditText etValor;

    private EstoqueHysleiman hysleiman = new EstoqueHysleiman();
    private DAOHysleiman daoHysleiman = new DAOHysleiman(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProduto = findViewById(R.id.etProduto);
        etFornecedor = findViewById(R.id.etFornecedor);
        etValor = findViewById(R.id.etValor);

        Intent intent = getIntent();
        if (intent != null) {
            EstoqueHysleiman hysleimanSelecionado = (EstoqueHysleiman) intent.getSerializableExtra("hysleimanSelecionado");
            if(hysleimanSelecionado != null){
                pupularFormulario(hysleimanSelecionado);

            }


        }

    }
    private void pupularFormulario(EstoqueHysleiman hysleimanSelecionado){
        etProduto.setText(hysleimanSelecionado.getProduto());
        etFornecedor.setText(hysleimanSelecionado.getFornecedor());
        etValor.setText(String.valueOf(hysleimanSelecionado.getValor()));
        hysleiman.setId(hysleimanSelecionado.getId());

    }
    private void pupularModelo(){
        hysleiman.setProduto(etProduto.getText().toString());
        hysleiman.setFornecedor(etFornecedor.getText().toString());
        hysleiman.setValor(Double.parseDouble(etValor.getText().toString()));
    }

    private String validarCampos() {
        String mensagemRetorno = "";

        double valorFormat = 0 ;


        try{
            valorFormat = Double.parseDouble(etValor.getText().toString());

        }catch (NumberFormatException e){
            e.printStackTrace();



        }


        if (etProduto.getText().toString().isEmpty()) {
            mensagemRetorno = "O campo ' produto ' não foi preenchido!";
        }
        if (etFornecedor.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO  campo ' fornecedor ' não foi preenchido!";
        }
        if (etValor.getText().toString().isEmpty() || valorFormat <= 100) {

            if(etValor.getText().toString().isEmpty()){

                mensagemRetorno += "\nO campo ' valor produto' não foi preenchido";
            } else if(valorFormat <= 100){
                mensagemRetorno += "\nO campo ' valor produto ' tem que ser maior ' 100 ' ";

            }
        }


        return mensagemRetorno;
    }

    public void salvar(View view) {


        String mensagemValidacao = validarCampos();
        if (mensagemValidacao.isEmpty()) {
            pupularModelo();
            if (hysleiman.getId() == null) {
                daoHysleiman.incluir(hysleiman);
                Toast.makeText(getBaseContext(),
                        "Inclusão realizada com sucesso!" ,Toast.LENGTH_LONG).show();
                limparCampos();
            } else {
                daoHysleiman.alterar(hysleiman);
                Toast.makeText(getBaseContext(),
                        "Alteração realizada com sucesso!" ,Toast.LENGTH_LONG).show();
                limparCampos();
            }
        } else {
            Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
        }

    }
    private void limparCampos(){
        etProduto.setText("");
        etFornecedor.setText("");
        etValor.setText("");


        hysleiman= new EstoqueHysleiman();

    }
    public void limpar(View view){
        limparCampos();

    }

    public void listar(View view){
      /* Intent intent = new Intent(getBaseContext(), ListaActivity.class);
       startActivity(intent);

       */
        finish();

    }


}
