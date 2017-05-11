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
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.FuncionarioBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Funcionario;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogAlertCustom;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;

/**
 * A placeholder fragment containing a simple view.
 */
public class FgtFuncionarioAdd extends Fragment {

    FuncionarioBO bo;
    View view;
    private EditText etMatricula;
    private EditText etCpf;
    private EditText etNome;
    private EditText etEndereco;
    private EditText etTelefone;
    private FloatingActionButton btnSalvar;
    private FloatingActionButton btnRemover;
    private Funcionario funcionario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fgt_funcionario_add, container, false);
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
        if (funcionario == null) {
            btnRemover.setVisibility(View.GONE);
            return;
        }
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogAlertCustom dialog = new DialogAlertCustom(getContext());
                dialog.show("Alerta", "Tem certeza que deseja remover " + funcionario.getNome());
                dialog.getBtnPosition().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bo.delete(funcionario);
                        MobileApp.setFragmentReplacePage(
                                getFragmentManager(),
                                new FgtFuncionario());
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
        if (etCpf.getText().length() <= 0) {
            flag = false;
            etCpf.setError("* Campo Obrigatório!");
        }
        if (etNome.getText().length() <= 0) {
            flag = false;
            etNome.setError("* Campo Obrigatório!");
        }
        if (etEndereco.getText().length() <= 0) {
            flag = false;
            etEndereco.setError("* Campo Obrigatório!");
        }
        if (etTelefone.getText().length() <= 0) {
            flag = false;
            etTelefone.setError("* Campo Obrigatório!");
        }
        return flag;
    }

    private void salvar(boolean isSalvar) {
        if (isSalvar) {
            Funcionario funcionario = new Funcionario();
            funcionario.setMatricula(etMatricula.getText().toString());
            funcionario.setTelefone(etTelefone.getText().toString());
            funcionario.setNome(etNome.getText().toString());
            funcionario.setEndereco(etEndereco.getText().toString());
            funcionario.setCpf(etCpf.getText().toString());

            if(this.funcionario != null) {
                funcionario.setId(this.funcionario.getId());
                bo.update(funcionario);
            } else {
                bo.create(funcionario);
            }

            MobileApp.setFragmentReplacePage(
                    getFragmentManager(),
                    new FgtFuncionario()
            );
        }
    }

    private void initDados() {
        try {
            bo = new FuncionarioBO(getContext());
            if (getArguments() != null) {
                funcionario = bo.queryForId(getArguments().getInt("id"));
                carregarFuncionario();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarFuncionario() {
        if (funcionario != null) {
            etCpf.setText(funcionario.getCpf());
            etTelefone.setText(funcionario.getTelefone());
            etNome.setText(funcionario.getNome());
            etEndereco.setText(funcionario.getEndereco());
            etMatricula.setText(funcionario.getMatricula());
        }
    }

    private void initViews() {
        etMatricula = (EditText) view.findViewById(R.id.et_matricula);
        etCpf = (EditText) view.findViewById(R.id.et_cpf);
        etNome = (EditText) view.findViewById(R.id.et_nome);
        etEndereco = (EditText) view.findViewById(R.id.et_endereco);
        etTelefone = (EditText) view.findViewById(R.id.et_telefone);
        btnSalvar = (FloatingActionButton) view.findViewById(R.id.btn_salvar);
        btnRemover = (FloatingActionButton) view.findViewById(R.id.btn_remover);
    }

}
