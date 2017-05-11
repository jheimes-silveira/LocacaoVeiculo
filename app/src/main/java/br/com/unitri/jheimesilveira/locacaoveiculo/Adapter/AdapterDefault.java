package br.com.unitri.jheimesilveira.locacaoveiculo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.ViewHolder;

/**
 * Created by jheimesilveira on 01/03/2016.
 */
public class AdapterDefault <T> extends BaseAdapter {

    private Context context;
    private List<T> list;
    private int idImage;


    /**
     * construtor
     * @param context da pagina
     * @param list de dados
     */
    public AdapterDefault(Context context, List<T> list, int idImage) {
        this.context = context;
        this.list = list;
        this.idImage = idImage;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_list_default, parent, false);
        }

        Object object = list.get(position);
        ViewHolder holder = (ViewHolder) view.getTag();

        TextView tvTitulo;
        ImageView ivImagem;
        LinearLayout llImagem;
        if (holder == null) {
            tvTitulo = (TextView) view.findViewById(R.id.tv_titulo);
            ivImagem = (ImageView) view.findViewById(R.id.iv_imagem);
            llImagem = (LinearLayout) view.findViewById(R.id.ll_container_imagem);
            holder = new ViewHolder();
            holder.setProperty(ivImagem, "ivImagem");
            holder.setProperty(tvTitulo, "tvTitulo");
            holder.setProperty(llImagem, "llImagem");
            view.setTag(holder);
        }

        tvTitulo = (TextView) holder.getProperty("tvTitulo");
        tvTitulo.setText(object.toString());

        ivImagem = (ImageView) holder.getProperty("ivImagem");
        llImagem = (LinearLayout) holder.getProperty("llImagem");

        if (idImage != 0) {
            llImagem.setVisibility(View.VISIBLE);
            ivImagem.setBackgroundResource(idImage);
        } else {
            llImagem.setVisibility(View.GONE);
        }
        return view;
    }
}
