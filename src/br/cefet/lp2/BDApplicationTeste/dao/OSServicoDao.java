/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.dao;

import br.cefet.lp2.BDApplicationTeste.entidade.OS;
import br.cefet.lp2.BDApplicationTeste.entidade.Pet;
import br.cefet.lp2.BDApplicationTeste.entidade.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class OSServicoDao extends Dao {

    public void inserir(int codOs, Servico s) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();

            String sql = " insert into os_servico (ID_OS, ID_SERVICO, VALOR) "
                    + " values (?, ?, ?) ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, codOs);
            ps.setInt(2, s.getCod());
            ps.setDouble(3, s.getValor());

            ps.execute();

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    public List<Servico> consultarPorCodOs(int codOs) throws Exception {
        List<Servico> pList = new ArrayList<Servico>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select s.ID_SERVICO, s.DESCRICAO, oss.VALOR "
                    + " from servico s, os_servico oss "
                    + " where s.ID_SERVICO = oss.ID_SERVICO "
                    + "   and oss.ID_OS = ? ";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codOs);
            rs = ps.executeQuery();

            while (rs.next()) {
                Servico s = getServicoFromRs(rs);
                pList.add(s);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return pList;
    }
    
    private Servico getServicoFromRs(ResultSet rs) throws SQLException {
        Servico s = new Servico();
        s.setCod(rs.getInt("ID_SERVICO"));
        s.setDescricao(rs.getString("DESCRICAO"));
        s.setValor(rs.getDouble("VALOR"));

        return s;
    }
}
