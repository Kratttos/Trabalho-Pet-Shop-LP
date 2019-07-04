/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.dao;

import br.cefet.lp2.BDApplicationTeste.entidade.OS;
import br.cefet.lp2.BDApplicationTeste.entidade.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class OSDao extends Dao {

    public int inserir(OS o) throws Exception {
        int ret = 0;
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " insert into os (ID_PET) "
                    + " values (?) ";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o.getCodPet());

            ps.execute();
            
            rs = ps.getGeneratedKeys();
            if (rs.next()){
                ret = rs.getInt(1);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        
        return ret;
    }

    public List<OS> consultarTodos() throws Exception {
        List<OS> pList = new ArrayList<OS>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select * from os ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                OS o = getOSFromRs(rs);
                pList.add(o);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return pList;
    }

       public OS consultarPorCod(int cod) throws Exception {
        OS o = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select * from os where ID_OS = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();

            if (rs.next()) {
                o = getOSFromRs(rs);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return o;
    }
    
    private OS getOSFromRs(ResultSet rs) throws SQLException {
        OS o = new OS();
        o.setCod(rs.getInt("ID_OS"));
        o.setCodPet(rs.getInt("ID_PET"));

        return o;
    }
}
