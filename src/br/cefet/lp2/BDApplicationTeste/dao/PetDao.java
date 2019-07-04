/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.dao;

import br.cefet.lp2.BDApplicationTeste.entidade.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class PetDao extends Dao {

    /**
     * Método utilizado para inserir um pet no banco de dados. <br>
     * O DaoException pode ocorrer por alguns motivos: <br>
     * 1-SGBD offline.<br>
     * 2-Usuarios não possui acesso.<br>
     * 3-Banco de dados informado não existe.<br>
     * 4-Erro de sitaxe no sql.<br>
     *
     * @param Pet p
     * @throws DaoException
     */
    public int inserir(Pet p) throws DaoException {
        int ret = 0;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " insert into pet (NOME, DONO, RACA, DT_NASC) "
                    + " values (?, ?, ?, ?) ";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDono());
            ps.setString(3, p.getRaca());
            ps.setTimestamp(4, new Timestamp(p.getDtNasc().getTime()));

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

    public Pet consultarPorCod(int cod) throws DaoException {
        Pet p = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select * from pet where ID_PET = ? ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = getPetFromRs(rs);
            }

        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }

        return p;
    }

    public List<Pet> consultarTodos() throws DaoException {
        List<Pet> pList = new ArrayList();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select * from pet ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pet p = getPetFromRs(rs);
                pList.add(p);
            }

        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }

        return pList;
    }

    public List<Pet> consultarPorNome(String nome) throws DaoException {
        List<Pet> pList = new ArrayList();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql
                    = " select * from pet "
                    + " where NOME like ? "
                    + "    or DONO like ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nome + "%");
            ps.setString(2, nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Pet p = getPetFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }

        return pList;
    }

    public Pet getPetFromRs(ResultSet rs) throws SQLException {
        Pet p = new Pet();
        p.setCod(rs.getInt("ID_PET"));
        p.setNome(rs.getString("NOME"));
        p.setDono(rs.getString("DONO"));
        p.setRaca(rs.getString("RACA"));
        p.setDtNasc(rs.getDate("DT_NASC"));

        return p;
    }

    public void alterar(Pet p) throws DaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();

            String sql
                    = " update pet "
                    + " set NOME = ?, "
                    + "     DONO = ?, "
                    + "     RACA = ?, "
                    + "     DT_NASC = ? "
                    + " where ID_PET = ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDono());
            ps.setString(3, p.getRaca());
            ps.setTimestamp(4, new Timestamp(p.getDtNasc().getTime()));
            ps.setInt(5, p.getCod());

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

            String sql
                    = " delete from pet "
                    + " where ID_PET = ? ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);

            ps.execute();
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) throws DaoException {
        Scanner scs = new Scanner(System.in);
        Scanner sci = new Scanner(System.in);

        PetDao dao = new PetDao();
        Pet p = null;

        /*
        p = new Pet();
        p.setNome("MrM");
        p.setDono("Malandro");
        p.setRaca("Pit Bull");
        p.setDtNasc(new Date());
        dao.inserir(p);*/
        dao = new PetDao();
        List<Pet> pList = dao.consultarTodos();
        for (int i = 0; i < pList.size(); i++) {
            p = pList.get(i);
            System.out.println(p.getCod() + " - " + p.getNome() + " - "
                    + p.getDono() + " - " + p.getRaca() + " - " + p.getDtNasc());
        }

        System.out.println("--------------------");

        pList = dao.consultarPorNome("M");
        for (int i = 0; i < pList.size(); i++) {
            p = pList.get(i);
            System.out.println(p.getCod() + " - " + p.getNome() + " - "
                    + p.getDono() + " - " + p.getRaca() + " - " + p.getDtNasc());
        }

        System.out.println("--------------------");
        System.out.println("Informe o cod do Pet a ser alterado");
        int cod = sci.nextInt();
        p = dao.consultarPorCod(cod);
        if (p != null) {
            System.out.println(p.getCod() + " - " + p.getNome() + " - "
                    + p.getDono() + " - " + p.getRaca() + " - " + p.getDtNasc());

            System.out.print("Informe o novo nome do Pet: ");
            String nome = scs.nextLine();

            p.setNome(nome);

            dao.alterar(p);
        } else {
            System.out.println("Pet nao encontrado");
        }

        System.out.println("--------------------");
        System.out.println("Informe o cod do Pet a ser excluido");
        cod = sci.nextInt();
        p = dao.consultarPorCod(cod);
        if (p != null) {
            System.out.println(p.getCod() + " - " + p.getNome() + " - "
                    + p.getDono() + " - " + p.getRaca() + " - " + p.getDtNasc());

            System.out.print("Confirmar a exclusao [S|N]? ");
            String conf = scs.nextLine();

            if (conf.equalsIgnoreCase("s")) {
                dao.excluir(cod);
            }
        } else {
            System.out.println("Pet nao encontrado");
        }

    }

}
