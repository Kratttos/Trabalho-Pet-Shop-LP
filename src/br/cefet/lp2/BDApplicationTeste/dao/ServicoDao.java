/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.dao;

import br.cefet.lp2.BDApplicationTeste.entidade.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Everton
 */
public class ServicoDao extends Dao {
    
    public int inserir(Servico p) throws DaoException {
        int ret = 0;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " insert into Servico (Descricao, Valor) "
                    + " values (?, ?) ";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getDescricao());
            ps.setDouble(2, p.getValor());
            

            ps.execute();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ret = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }

        return ret;
    }

    public Servico consultarPorCod(int cod) throws DaoException {
        Servico p = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select * from Servico where CdServico = ? ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = getServicoFromRs(rs);
            }

        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }

        return p;
    }

    public List<Servico> consultarTodos() throws DaoException {
        List<Servico> pList = new ArrayList();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select * from Servico ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Servico p = getServicoFromRs(rs);
                pList.add(p);
            }

        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }

        return pList;
    }

    public List<Servico> consultarPorDescricao(String nome) throws DaoException {
        List<Servico> pList = new ArrayList();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql
                    = " select * from Servico "
                    + " where NOME like ? "
                    + "    or DONO like ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nome + "%");
            ps.setString(2, nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Servico p = getServicoFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }

        return pList;
    }

    public Servico getServicoFromRs(ResultSet rs) throws SQLException {
        Servico p = new Servico();
        p.setCod(rs.getInt("CdServico"));
        p.setDescricao(rs.getString("Descricao"));
        p.setValor(rs.getDouble("Valor"));
        return p;
    }

    public void alterar(Servico p) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();

            String sql
                    = " update Servico "
                    + " set Descricao = ?, "
                    + "     Valor = ?, "
                    + " where CdServico = ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getDescricao());
            ps.setDouble(2, p.getValor());
            ps.setInt(3, p.getCod());

            ps.execute();

        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }
    }

    public void excluir(int cod) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();

            String sql = " delete from Servico  where CdServico = ? ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);

            ps.execute();
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    
}
