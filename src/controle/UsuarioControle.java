/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;
import util.BancoDados;

/**
 *
 * @author sala303b
 */
public class UsuarioControle {

    public static long temUsuario(String logon, String password) {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT id FROM tb_usuario ";
            sql += " WHERE login = ? AND senha = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, logon);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long cod = rs.getInt("id");
                if (cod > 0) {
                    return cod;
                }
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static Usuario getUsuarioPorId(long iduser) {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_usuario WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, iduser);
            final ResultSet rs = ps.executeQuery();

            Usuario u = new Usuario();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNomeUsuario(rs.getString("nome_usuario"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setDataCadastro(rs.getDate("data_cadastro"));
            }
            return u;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
