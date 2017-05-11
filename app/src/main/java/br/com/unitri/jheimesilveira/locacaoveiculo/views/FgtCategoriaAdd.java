package br.com.unitri.jheimesilveira.locacaoveiculo.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;

import java.sql.SQLException;

import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.CategoriaBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Categoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogAlertCustom;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;

/**
 * A placeholder fragment containing a simple view.
 */
public class FgtCategoriaAdd extends Fragment {

    CategoriaBO bo;
    View view;
    private EditText etDescricao;
    private EditText etPreco;
    private FloatingActionButton btnSalvar;
    private FloatingActionButton btnRemover;
    private Categoria categoria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fgt_categoria_add, container, false);
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
    }

    private void onBtnRemover() {
        if (categoria == null) {
            btnRemover.setVisibility(View.GONE);
            return;
        }
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogAlertCustom dialog = new DialogAlertCustom(getContext());
                dialog.show("Alerta", "Tem certeza que deseja remover " + categoria.getDescricao());
                dialog.getBtnPosition().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bo.delete(categoria);
                        MobileApp.setFragmentReplacePage(
                                getFragmentManager(),
                                new FgtCategoria());
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
        if (etDescricao.getText().length() <= 0) {
            flag = false;
            etDescricao.setError("* Campo Obrigatório!");
        }
        if (etPreco.getText().length() <= 0) {
            flag = false;
            etPreco.setError("* Campo Obrigatório!");
        }
        return flag;
    }

    private void salvar(boolean isSalvar) {
        if (isSalvar) {
            Categoria item = new Categoria();
            item.setDescricao(etDescricao.getText().toString());
            item.setPreco(etPreco.getText().toString());

            if(this.categoria != null) {
                item.setId(this.categoria.getId());
                bo.update(item);
            } else {
                bo.create(item);
            }

            MobileApp.setFragmentReplacePage(
                    getFragmentManager(),
                    new FgtCategoria()
            );
        }
    }

    private void initDados() {
        try {
            bo = new CategoriaBO(getContext());
            if (getArguments() != null) {
                categoria = bo.queryForId(getArguments().getInt("id"));
                carregarItemSelecionado();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarItemSelecionado() {
        if (categoria != null) {
            etDescricao.setText(categoria.getDescricao());
            etPreco.setText(categoria.getPreco().toString());
        }
    }

    private void initViews() {
        etPreco = (EditText) view.findViewById(R.id.et_preco);
        etDescricao = (EditText) view.findViewById(R.id.et_descricao);
        btnSalvar = (FloatingActionButton) view.findViewById(R.id.btn_salvar);
        btnRemover = (FloatingActionButton) view.findViewById(R.id.btn_remover);
    }

}
