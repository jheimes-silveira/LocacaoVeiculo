package br.com.unitri.jheimesilveira.locacaoveiculo.bo;

import android.content.Context;

import java.sql.SQLException;

import br.com.unitri.jheimesilveira.locacaoveiculo.db.GenericCRUD;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Categoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Veiculo;

/**
 * Created by jheimes on 04/04/17.
 */

public class VeiculoBO extends GenericCRUD<Veiculo, Integer> {

    public VeiculoBO(Context context) throws SQLException {
        super(context, Veiculo.class, Veiculo.class.getSimpleName());
    }

}
