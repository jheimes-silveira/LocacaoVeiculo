package br.com.unitri.jheimesilveira.locacaoveiculo.bo;

import android.content.Context;

import java.sql.SQLException;

import br.com.unitri.jheimesilveira.locacaoveiculo.db.GenericCRUD;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Categoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Funcionario;

/**
 * Created by jheimes on 04/04/17.
 */

public class FuncionarioBO extends GenericCRUD<Funcionario, Integer> {

    public FuncionarioBO(Context context) throws SQLException {
        super(context, Funcionario.class, Funcionario.class.getSimpleName());
    }

}
