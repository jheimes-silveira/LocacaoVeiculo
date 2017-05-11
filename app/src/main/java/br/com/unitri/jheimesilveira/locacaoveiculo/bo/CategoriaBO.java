package br.com.unitri.jheimesilveira.locacaoveiculo.bo;

import android.content.Context;

import java.sql.SQLException;

import br.com.unitri.jheimesilveira.locacaoveiculo.db.GenericCRUD;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Categoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Cliente;

/**
 * Created by jheimes on 04/04/17.
 */

public class CategoriaBO extends GenericCRUD<Categoria, Integer> {

    public CategoriaBO(Context context) throws SQLException {
        super(context, Categoria.class, Categoria.class.getSimpleName());
    }

}
