package br.com.unitri.jheimesilveira.locacaoveiculo.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.sql.SQLException;
import java.util.List;

import br.com.unitri.jheimesilveira.locacaoveiculo.Adapter.AdapterDefault;
import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.VeiculoBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Veiculo;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;

/**
 * A placeholder fragment containing a simple view.
 */
public class FgtVeiculo extends Fragment {

    VeiculoBO bo;
    private RelativeLayout rlMessageAlert;
    private ListView listView;
    private View view;
    private FloatingActionButton btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fgt_cliente, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initConponents();
    }

    private void initConponents() {
        initViews();
        initDados();
        initListView();
        onbtnAdd();
    }

    private void onbtnAdd() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileApp.setFragmentReplacePage(
                        getFragmentManager(),
                        new FgtVeiculoAdd());
            }
        });
    }

    private void initDados() {
        try {
            bo = new VeiculoBO(getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        rlMessageAlert = (RelativeLayout) view.findViewById(R.id.rl_mensagem_alerta);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        listView = (ListView) view.findViewById(R.id.lista);
    }

    /**
     * inicializa a lista de treinos
     */
    private void initListView() {
        List<Veiculo> itens = bo.queryForAll();

        if (itens.size() == 0) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewMessage = layoutInflater.inflate(R.layout.view_message_alert, null);
            rlMessageAlert.addView(viewMessage);
            TextView tvPrimary = (TextView) viewMessage.findViewById(R.id.view_message_alert_tv_text_primary);
            TextView tvSubText = (TextView) viewMessage.findViewById(R.id.view_message_alert_tv_sub_text);
            ImageView ivEmoticon = (ImageView) viewMessage.findViewById(R.id.view_message_alert_iv_emoticon);
            tvPrimary.setText("Nenhuma veiculo cadastrado");
            tvSubText.setText("Cadastre um novo veiculo");
            ivEmoticon.setBackgroundResource(R.drawable.emoticon_sad);
        } else {
            rlMessageAlert.removeAllViews();
        }
        listView.setAdapter(new AdapterDefault(getContext(), itens, R.drawable.car_black));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Veiculo item = (Veiculo) listView.getAdapter().getItem(position);
                FgtVeiculoAdd fgt = new FgtVeiculoAdd();
                Bundle args = new Bundle();
                args.putInt("id", item.getId());
                fgt.setArguments(args);
                MobileApp.setFragmentReplacePage(
                        getFragmentManager(),
                        fgt);
            }
        });
    }
}
