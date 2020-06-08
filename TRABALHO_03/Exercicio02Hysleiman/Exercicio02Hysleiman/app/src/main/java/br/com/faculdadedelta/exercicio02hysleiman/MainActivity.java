package br.com.faculdadedelta.exercicio02hysleiman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.faculdadedelta.exercicio02hysleiman.dao.DAOHysleiman;
import br.com.faculdadedelta.exercicio02hysleiman.modelo.VendaHysleiman;

public class MainActivity extends AppCompatActivity {

    private EditText etCliente;
    private EditText etProduto;
    private EditText etValor;
    private EditText etQuantidade;

    private VendaHysleiman hysleiman = new VendaHysleiman();
    private DAOHysleiman daoHysleiman = new DAOHysleiman(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCliente = findViewById(R.id.etCliente);
        etProduto = findViewById(R.id.etProduto);
        etValor = findViewById(R.id.etValor);
        etQuantidade = findViewById(R.id.etQuantidade);

        Intent intent = getIntent();

        if (intent != null) {
            VendaHysleiman hysleimanSelecionado = (VendaHysleiman) intent.getSerializableExtra("hysleimanSelecionado");
            if (hysleimanSelecionado != null) {
                pupularFormulario(hysleimanSelecionado);

            }


        }
    }

    private void pupularFormulario(VendaHysleiman hysleimanSelecionado) {
        etCliente.setText(hysleimanSelecionado.getCliente());
        etProduto.setText(hysleimanSelecionado.getProduto());
        etValor.setText(String.valueOf(hysleimanSelecionado.getValor()));
        etQuantidade.setText(String.valueOf(hysleimanSelecionado.getQuantidade()));
        hysleiman.setId(hysleimanSelecionado.getId());
    }

    private void pupularModelo(){
        hysleiman.setCliente(etCliente.getText().toString());
        hysleiman.setProduto(etProduto.getText().toString());
        hysleiman.setValor(Double.parseDouble(etValor.getText().toString()));
        hysleiman.setQuantidade(Integer.parseInt(etQuantidade.getText().toString()));

    }

    private String validarCampos() {
        String mensagemRetorno = "";

        double valorFormat = 0 ;


        try{
            valorFormat = Double.parseDouble(etValor.getText().toString());

        }catch (NumberFormatException e){
            e.printStackTrace();



        }


        if (etCliente.getText().toString().isEmpty()) {
            mensagemRetorno = "O campo ' cliente ' não foi preenchido!";
        }
        if (etProduto.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO  campo ' protudo ' não foi preenchido!";
        }
        if (etValor.getText().toString().isEmpty() || valorFormat <= 0 ) {

            if(etValor.getText().toString().isEmpty()){

                mensagemRetorno += "\nO campo ' valor ' não foi preenchido";
            } else if(valorFormat <= 0){
                mensagemRetorno += "\nO campo ' valor ' tem que ser maior ' 0 ' ";

            }
        }

        if (etQuantidade.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO campo ' quantidade ' não foi preenchido!";
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
        etCliente.setText("");
        etProduto.setText("");
        etValor.setText("");
        etQuantidade.setText("");

        hysleiman= new VendaHysleiman();

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

