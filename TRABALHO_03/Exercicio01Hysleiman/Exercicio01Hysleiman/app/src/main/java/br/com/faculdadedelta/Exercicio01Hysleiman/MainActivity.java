package br.com.faculdadedelta.Exercicio01Hysleiman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.faculdadedelta.Exercicio01Hysleiman.dao.DAOHysleiman;
import br.com.faculdadedelta.Exercicio01Hysleiman.modelo.BemHysleiman;

public class MainActivity extends AppCompatActivity {

    private EditText etDescricao;
    private EditText etEspecificacao;
    private EditText etDepartamento;
    private EditText etValor;
    private EditText etQuantidade;

    private BemHysleiman hysleiman = new BemHysleiman();
    private DAOHysleiman daoHysleiman = new DAOHysleiman(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDescricao = findViewById(R.id.etDescricao);
        etEspecificacao = findViewById(R.id.etEspecificacao);
        etDepartamento = findViewById(R.id.etDepartamento);
        etValor = findViewById(R.id.etValor);
        etQuantidade = findViewById(R.id.etQuantidade);

        Intent intent = getIntent();

        if (intent != null) {
            BemHysleiman hysleimanSelecionado = (BemHysleiman) intent.getSerializableExtra("hysleimanSelecionado");
            if(hysleimanSelecionado != null){
                pupularFormulario(hysleimanSelecionado);

            }


        }

    }
    private void pupularFormulario(BemHysleiman hysleimanSelecionado){
        etDescricao.setText(hysleimanSelecionado.getDescricao());
        etEspecificacao.setText(hysleimanSelecionado.getEspecificacao());
        etDepartamento.setText(hysleimanSelecionado.getDepartamento());
        etValor.setText(String.valueOf(hysleimanSelecionado.getValor()));
        etQuantidade.setText(String.valueOf(hysleimanSelecionado.getQuantidade()));
        hysleiman.setId(hysleimanSelecionado.getId());

    }
    private void pupularModelo(){
        hysleiman.setDescricao(etDescricao.getText().toString());
        hysleiman.setEspecificacao(etEspecificacao.getText().toString());
        hysleiman.setDepartamento(etDepartamento.getText().toString());
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


        if (etDescricao.getText().toString().isEmpty()) {
            mensagemRetorno = "O campo ' descrição ' não foi preenchido!";
        }
        if (etEspecificacao.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO ' campo especificação ' não foi preenchido!";
        }
        if (etDepartamento.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO campo ' departamento ' não foi preenchido!";
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
        etDescricao.setText("");
        etEspecificacao.setText("");
        etDepartamento.setText("");
        etValor.setText("");
        etQuantidade.setText("");

        hysleiman= new BemHysleiman();

    }
    public void limpar(View view){
        limparCampos();

    }

    public void listar(View view){
       Intent intent = new Intent(getBaseContext(), ListaActivity.class);
       startActivity(intent);


        finish();

    }


}
