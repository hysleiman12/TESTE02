package br.com.faculdadedelta.exercicio02hysleiman.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.faculdadedelta.exercicio02hysleiman.R;
import br.com.faculdadedelta.exercicio02hysleiman.modelo.VendaHysleiman;

public class AdapterHysleiman extends BaseAdapter {
    private List<VendaHysleiman> listaVenda;
    private Context context;

    public AdapterHysleiman(List<VendaHysleiman> listaVenda, Context context) {
        this.listaVenda = listaVenda;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaVenda.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVenda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       VendaHysleiman hysleiman = (VendaHysleiman) getItem(position);

        View viewRetorno = View.inflate(context, R.layout.item_lista, null);

        TextView tvId = viewRetorno.findViewById(R.id.tvId);
        tvId.setText("ID : " + hysleiman.getId());


        TextView tvCliente = viewRetorno.findViewById(R.id.tvCliente);
        tvCliente.setText("CLIENTE(NOME) : "+ hysleiman.getCliente());

        TextView tvProduto = viewRetorno.findViewById(R.id.tvProduto);
        tvProduto.setText("PRODUTO(NOME) : "+ hysleiman.getProduto());

        TextView tvValor = viewRetorno.findViewById(R.id.tvValor);
        tvValor.setText("VALOR : "+ hysleiman.getValor());

        TextView tvQuantidade = viewRetorno.findViewById(R.id.tvQuantidade);
        tvQuantidade.setText("QUANTIDADE : "+ hysleiman.getQuantidade());

        if (position % 2 == 0) {
            viewRetorno.setBackgroundColor(R.color.colorPrimaryDark);
        }


        return viewRetorno;
    }
}
