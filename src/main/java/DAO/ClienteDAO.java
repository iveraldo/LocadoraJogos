package DAO;

import Model.Cliente;
import Model.Endereco;
import Util.FabricaConexao;
import Util.Utilitario.EnumEstado;
import Util.Utilitario.EnumSexo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void salvar(Cliente pCliente) throws SQLException, ClassNotFoundException {
        int contadorParametros = 1;
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        if(pCliente.getId() == null){
            comando = "INSERT INTO Cliente (nome, cpf, idade, celular, telefone, email, sexo, data_cadastro) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(contadorParametros++, pCliente.getNome());
            stmt.setString(contadorParametros++, pCliente.getCpf());
            stmt.setInt(contadorParametros++, pCliente.getIdade());
            stmt.setString(contadorParametros++, pCliente.getCelular());
            stmt.setString(contadorParametros++, pCliente.getTelefone());
            stmt.setString(contadorParametros++, pCliente.getEmail());
            stmt.setString(contadorParametros++, pCliente.getSexo().toString());
            stmt.setDate(contadorParametros++, pCliente.getDataCadastro());
            
            //insere na tabela cliente
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            //recupera o id gerado na tabela cliente
            
            pCliente.setId(rs.getLong(1));
                        
            //zera o contador de parametros
            contadorParametros = 1;
            
            comando = "INSERT INTO Endereco (id_cliente, logradouro, numero, complemento, bairro, cidade, estado, "
                    + "cep, data_criacao, obs) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            stmt = conexao.prepareStatement(comando);
            stmt.setLong(contadorParametros++, pCliente.getId());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getLogradouro());
            stmt.setInt(contadorParametros++, pCliente.getEndereco().getNumero());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getComplemento());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getBairro());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getCidade());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getEstado().toString());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getCep());
            stmt.setDate(contadorParametros++, pCliente.getEndereco().getDataCriacao());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getObs());

            //insere na tabela endereco
            stmt.executeUpdate();
            
            conexao.close();
            
        } else {
            comando = "UPDATE Cliente"
                    + " INNER JOIN Endereco ON cliente.id = Endereco.id_cliente SET"
                    + " Cliente.nome = ?"
                    + ", Cliente.cpf = ?"
                    + ", Cliente.idade = ?"
                    + ", Cliente.celular = ?"
                    + ", Cliente.telefone = ?"
                    + ", Cliente.email = ?"
                    + ", Cliente.sexo = ?"
                    + ", Cliente.data_cadastro = ?"
                    
                    + ", Endereco.logradouro = ?"
                    + ", Endereco.numero = ?"
                    + ", Endereco.complemento = ?"
                    + ", Endereco.bairro = ?"
                    + ", Endereco.cidade = ?"
                    + ", Endereco.estado = ?"
                    + ", Endereco.cep = ?"
                    + ", Endereco.data_criacao = ?"
                    + ", Endereco.obs = ?"
                    
                    + " WHERE Cliente.id = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt = conexao.prepareStatement(comando);
            stmt.setString(contadorParametros++, pCliente.getNome());
            stmt.setString(contadorParametros++, pCliente.getCpf());
            stmt.setInt(contadorParametros++, pCliente.getIdade());
            stmt.setString(contadorParametros++, pCliente.getCelular());
            stmt.setString(contadorParametros++, pCliente.getTelefone());
            stmt.setString(contadorParametros++, pCliente.getEmail());
            stmt.setString(contadorParametros++, pCliente.getSexo().toString());
            stmt.setDate(contadorParametros++, pCliente.getDataCadastro());
            
            stmt.setString(contadorParametros++, pCliente.getEndereco().getLogradouro());
            stmt.setInt(contadorParametros++, pCliente.getEndereco().getNumero());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getComplemento());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getBairro());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getCidade());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getEstado().toString());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getCep());
            stmt.setDate(contadorParametros++, pCliente.getEndereco().getDataCriacao());
            stmt.setString(contadorParametros++, pCliente.getEndereco().getObs());
            
            stmt.setLong(contadorParametros++, pCliente.getId());
            
            stmt.executeUpdate();
            
            conexao.close();
        }
    }

    public static List<Cliente> consultar(Cliente pCliente) throws SQLException, ClassNotFoundException{
        int contadorParametros = 1;
        List<Cliente> clientes = new ArrayList<>();
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = null;
        
        String comando = "SELECT Cliente.id, Cliente.nome, Cliente.cpf, Cliente.idade, Cliente.celular"
                + ", Cliente.telefone, Cliente.email, Cliente.sexo, Cliente.data_cadastro"
                + ", Endereco.id AS id_endereco, Endereco.logradouro, Endereco.numero, Endereco.complemento, Endereco.bairro"
                + ", Endereco.cidade, Endereco.estado, Endereco.cep, Endereco.data_criacao, Endereco.obs "
                + "FROM Cliente INNER JOIN Endereco ON Cliente.id = Endereco.id_cliente "
                + "WHERE 1 = 1 ";
        
        if(pCliente.getId() != null){
            comando += "AND Cliente.id = ? ";
        }
        
        stmt = conexao.prepareStatement(comando);
        
        if(pCliente.getId() != null){
            stmt.setLong(contadorParametros++, pCliente.getId());
        }
                 
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            clientes.add(montarObjeto(rs));
        }
        
        //fecha a conexao
        conexao.close();
        
        return clientes;
    }

    public static void excluir(Cliente pCliente) throws SQLException, ClassNotFoundException {
        String comando;
        Connection conexao = FabricaConexao.getConnection();
        
        comando = "DELETE FROM Endereco WHERE id_cliente = ?";
        PreparedStatement stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pCliente.getId());
        
        //Exclui da tabela Endereco
        stmt.executeUpdate();
        
        comando = "DELETE FROM Cliente WHERE id = ?";
        stmt = conexao.prepareStatement(comando);
        stmt.setLong(1, pCliente.getId());
        
        //Exclui da tabela Cliente
        stmt.executeUpdate();
    }
    
    private static Cliente montarObjeto(ResultSet rs) throws SQLException{
            Cliente cliente = new Cliente();
            Endereco endereco = new Endereco();
                
            //Monta o objeto cliente
            cliente.setId(rs.getLong("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setIdade(rs.getInt("idade"));
            cliente.setCelular(rs.getString("celular"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSexo(EnumSexo.valueOf(rs.getString("sexo")));
            cliente.setDataCadastro(rs.getDate("data_cadastro"));
            
            //Monta o objeto Endereco
            endereco.setId(rs.getLong("id_endereco"));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setEstado(EnumEstado.valueOf(rs.getString("estado")));
            endereco.setCep(rs.getString("cep"));
            endereco.setDataCriacao(rs.getDate("data_criacao"));
            endereco.setObs(rs.getString("obs"));
            
            cliente.setEndereco(endereco);
            
            return cliente;
    }
}