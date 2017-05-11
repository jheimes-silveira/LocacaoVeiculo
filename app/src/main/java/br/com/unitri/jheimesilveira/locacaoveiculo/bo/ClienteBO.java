package br.com.unitri.jheimesilveira.locacaoveiculo.bo;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import br.com.unitri.jheimesilveira.locacaoveiculo.db.GenericCRUD;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Cliente;

/**
 * Created by jheimes on 04/04/17.
 */

public class ClienteBO extends GenericCRUD<Cliente, Integer> {

    public ClienteBO(Context context) throws SQLException {
        super(context, Cliente.class, Cliente.class.getSimpleName());
    }

}
