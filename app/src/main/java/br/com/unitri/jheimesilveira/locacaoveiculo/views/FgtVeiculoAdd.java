package br.com.unitri.jheimesilveira.locacaoveiculo.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;

import java.sql.SQLException;

import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.CategoriaBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.VeiculoBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Categoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Veiculo;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogAlertCustom;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogSearchList;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;

/**
 * A placeholder fragment containing a simple view.
 */
public class FgtVeiculoAdd extends Fragment {

    VeiculoBO bo;
    CategoriaBO boCategoria;
    View view;
    private EditText etModelo;
    private EditText etMarca;
    private EditText etAno;
    private EditText etChassi;
    private EditText etObs;
    private Categoria categoria;
    private FloatingActionButton btnSalvar;
    private FloatingActionButton btnRemover;
    private Veiculo veiculo;
    private EditText etCategoria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fgt_veiculo_add, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initComponentes();
    }

    private void initComponentes() {
        initViews();
        initDados();
        onBtnSalvar();
        onBtnRemover();
        onTvCategoria();
    }

    private void onTvCategoria() {
        etCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogSearchList dialog = new DialogSearchList(
                        getContext(),
                        boCategoria.queryForAll(),
                        null,
                        false);
                dialog.show("Categorias");
                dialog.getLvDialog().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        categoria = (Categoria) dialog.getLvDialog().getAdapter().getItem(position);
                        etCategoria.setText(categoria.getDescricao() + " - " + categoria.getPreco());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void onBtnRemover() {
        if (veiculo == null) {
            btnRemover.setVisibility(View.GONE);
            return;
        }
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogAlertCustom dialog = new DialogAlertCustom(getContext());
                dialog.show("Alerta", "Tem certeza que deseja remover " + veiculo.getModelo());
                dialog.getBtnPosition().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bo.delete(veiculo);
                        MobileApp.setFragmentReplacePage(
                                getFragmentManager(),
                                new FgtVeiculo());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void onBtnSalvar() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar(ferificarCampos());
            }
        });
    }

    private boolean ferificarCampos() {
        boolean flag = true;
        if (etModelo.getText().length() <= 0) {
            flag = false;
            etModelo.setError("* Campo Obrigatório!");
        }
        if (etMarca.getText().length() <= 0) {
            flag = false;
            etMarca.setError("* Campo Obrigatório!");
        }
        if (etChassi.getText().length() <= 0) {
            flag = false;
            etChassi.setError("* Campo Obrigatório!");
        }
        if (categoria == null) {
            flag = false;
            etCategoria.setError("* Campo Obrigatório!");
        }
        if (etAno.getText().length() <= 0) {
            flag = false;
            etAno.setError("* Campo Obrigatório!");
        }
        return flag;
    }

    private void salvar(boolean isSalvar) {
        if (isSalvar) {
            Veiculo item = new Veiculo();
            item.setMarca(etMarca.getText().toString());
            item.setModelo(etModelo.getText().toString());
            item.setChassi(etChassi.getText().toString());
            item.setAno(Integer.valueOf(etAno.getText().toString()));
            item.setCategoria(categoria);

            if(this.veiculo != null) {
                item.setId(this.veiculo.getId());
                bo.update(item);
            } else {
                bo.create(item);
            }

            MobileApp.setFragmentReplacePage(
                    getFragmentManager(),
                    new FgtVeiculo()
            );
        }
    }

    private void initDados() {
        try {
            bo = new VeiculoBO(getContext());
            boCategoria = new CategoriaBO(getContext());
            if (getArguments() != null) {
                veiculo = bo.queryForId(getArguments().getInt("id"));
                categoria = boCategoria.queryForId(veiculo.getCategoria().getId());
                carregarItemSelecionado();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarItemSelecionado() {
        if (veiculo != null) {
            etMarca.setText(veiculo.getMarca());
            etModelo.setText(veiculo.getModelo());
            etObs.setText(veiculo.getObservacao());
            etAno.setText(String.valueOf(veiculo.getAno()));
            etCategoria.setText(categoria.getDescricao() + " - " + categoria.getPreco());
            etChassi.setText(veiculo.getChassi());
        }
    }

    private void initViews() {

        etAno = (EditText) view.findViewById(R.id.et_ano);
        etMarca = (EditText) view.findViewById(R.id.et_marca);
        etModelo = (EditText) view.findViewById(R.id.et_modelo);
        etCategoria = (EditText) view.findViewById(R.id.et_categoria);
        etObs = (EditText) view.findViewById(R.id.et_obs);
        etChassi = (EditText) view.findViewById(R.id.et_chassi);
        btnSalvar = (FloatingActionButton) view.findViewById(R.id.btn_salvar);
        btnRemover = (FloatingActionButton) view.findViewById(R.id.btn_remover);
    }

}
