import java.sql.*;

public class OperacoesBanco {

    public static void criarTabelaClientes() {
        try (Connection conexao = ConexaoBanco.conectar();
             Statement statement = conexao.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS clientes (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nome TEXT NOT NULL," +
                            "cpf TEXT NOT NULL UNIQUE," +
                            "senha TEXT NOT NULL," +
                            "valorContaPoupanca REAL DEFAULT 0," +
                            "valorContaCorrente REAL DEFAULT 0)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarCliente(String nome, String cpf, String senha) {
        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement statement = conexao.prepareStatement(
                     "INSERT INTO clientes (nome, cpf, senha) VALUES (?, ?, ?)"
             )) {
            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setString(3, senha);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean realizarLogin(String cpf, String senha) {
        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement statement = conexao.prepareStatement(
                     "SELECT * FROM clientes WHERE cpf = ? AND senha = ?"
             )) {
            statement.setString(1, cpf);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();

            // Se houver algum resultado, o login é bem-sucedido
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Login falhou
    }

    public static void depositarContaCorrente(String cpf, double valor) {
        depositar(cpf, valor, false); 
    }

    public static void sacarContaCorrente(String cpf, double valor) {
        sacar(cpf, valor, false); 
    }

    public static void depositar(String cpf, double valor, boolean contaPoupanca) {
        String coluna = contaPoupanca ? "valorContaPoupanca" : "valorContaCorrente";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement statement = conexao.prepareStatement(
                     "UPDATE clientes SET " + coluna + " = " + coluna + " + ? WHERE cpf = ?"
             )) {
            statement.setDouble(1, valor);
            statement.setString(2, cpf);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sacar(String cpf, double valor, boolean contaPoupanca) {
        String coluna = contaPoupanca ? "valorContaPoupanca" : "valorContaCorrente";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement statement = conexao.prepareStatement(
                     "UPDATE clientes SET " + coluna + " = " + coluna + " - ? WHERE cpf = ? AND " + coluna + " >= ?"
             )) {
            statement.setDouble(1, valor);
            statement.setString(2, cpf);
            statement.setDouble(3, valor);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double consultarSaldo(String cpf, boolean contaPoupanca) {
        String coluna = contaPoupanca ? "valorContaPoupanca" : "valorContaCorrente";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement statement = conexao.prepareStatement(
                     "SELECT " + coluna + " FROM clientes WHERE cpf = ?"
             )) {
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble(coluna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

class ConexaoBanco {

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:bancoJava.db");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver SQLite não encontrado", e);
        }
    }
}
