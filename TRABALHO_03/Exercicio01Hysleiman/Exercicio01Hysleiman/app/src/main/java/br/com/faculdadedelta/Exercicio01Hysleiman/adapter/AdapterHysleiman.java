package br.com.faculdadedelta.Exercicio01Hysleiman.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.faculdadedelta.Exercicio01Hysleiman.R;
import br.com.faculdadedelta.Exercicio01Hysleiman.modelo.BemHysleiman;

public class AdapterHysleiman extends BaseAdapter {

    private List<BemHysleiman> listaBem;
    private Context context;

    public AdapterHysleiman(List<BemHysleiman> listaBem, Context context) {
        this.listaBem = listaBem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaBem.size();
    }

    @Override
    public Object getItem(int position) {
        return listaBem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BemHysleiman hysleiman = (BemHysleiman) getItem(position);

        View viewRetorno = View.inflate(context, R.layout.item_lista, null);

        TextView tvId = viewRetorno.findViewById(R.id.tvId);
        tvId.setText("ID : "+hysleiman.getId() );

        TextView tvDescricao = viewRetorno.findViewById(R.id.tvDescricao);
        tvDescricao.setText("DESCRICAO(BEM) : "+ hysleiman.getDescricao());

        TextView tvEspecificacao = viewRetorno.findViewById(R.id.tvEspecificacao);
        tvEspecificacao.setText("ESPECIFICAÇÂO(BEM) : "+ hysleiman.getEspecificacao());

        TextView tvDepartamento = viewRetorno.findViewById(R.id.tvDepartamento);
        tvDepartamento.setText("DEPARTAMENTO: "+ hysleiman.getDepartamento());

        TextView tvValor = viewRetorno.findViewById(R.id.tvValor);
        tvValor.setText("VALOR : "+ hysleiman.getValor());

        TextView tvQuantidade = viewRetorno.findViewById(R.id.tvQuantidade);
        tvQuantidade.setText("QUANTIDADE : "+ hysleiman.getQuantidade());


        if (position % 2 == 0) {
            viewRetorno.setBackgroundColor(R.color.colorAccent);
        }
        return viewRetorno;
    }
}
