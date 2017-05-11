package br.com.unitri.jheimesilveira.locacaoveiculo.bo;

import android.content.Context;

import java.sql.SQLException;

import br.com.unitri.jheimesilveira.locacaoveiculo.db.GenericCRUD;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Categoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Locacao;

/**
 * Created by jheimes on 04/04/17.
 */

public class LocacaoBO extends GenericCRUD<Locacao, Integer> {

    public LocacaoBO(Context context) throws SQLException {
        super(context, Locacao.class, Locacao.class.getSimpleName());
    }

}
