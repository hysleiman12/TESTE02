package br.com.faculdadedelta.exercicio03hysleiman.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.faculdadedelta.exercicio03hysleiman.R;
import br.com.faculdadedelta.exercicio03hysleiman.modelo.EstoqueHysleiman;

public class AdapterHysleiman extends BaseAdapter {

    private List<EstoqueHysleiman> listaEstoque;
    private Context context;

    public AdapterHysleiman(List<EstoqueHysleiman> listaEstoque, Context context) {
        this.listaEstoque = listaEstoque;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaEstoque.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEstoque.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EstoqueHysleiman hysleiman = (EstoqueHysleiman) getItem(position);

        View viewRetorno = View.inflate(context, R.layout.item_lista, null);

        TextView tvId = viewRetorno.findViewById(R.id.tvId);
        tvId.setText("ID : "+hysleiman.getId() );

        TextView tvProduto = viewRetorno.findViewById(R.id.tvProduto);
        tvProduto.setText("PRODUTO(NOME) : "+ hysleiman.getProduto());

        TextView tvFornecedor = viewRetorno.findViewById(R.id.tvFornecedor);
        tvFornecedor.setText("FORNECEDOR(NOME) : "+ hysleiman.getFornecedor());

        TextView tvValor = viewRetorno.findViewById(R.id.tvValor);
        tvValor.setText("VALOR : "+ hysleiman.getValor());




        if (position % 2 == 0) {
            viewRetorno.setBackgroundColor(R.color.colorAccent);
        }
        return viewRetorno;
    }
}
