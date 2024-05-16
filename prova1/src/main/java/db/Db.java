package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.AulaDto;

public class Db {

    private static Db instance = null;
    private Connection connection = null;

    private String driver;
    private String url;
    private String user;
    private String password;

    private Db() {
        this.confDB();
        this.conectar();
        this.criarTabela();
        System.out.println("feito");
    }

    public static Db getInstance() {
        if (instance == null) {
            instance = new Db();
        }
        return instance;
    }

    private void confDB() {
        try {
            this.driver = "org.h2.Driver";
            this.url = "jdbc:h2:mem:testdb";
            this.user = "sa";
            this.password = "";
            Class.forName(this.driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Inicia a conexão com o banco de dados
    private void conectar() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void criarTabela() {
        String query = "CREATE TABLE IF NOT EXISTS AULA ("
                + "    ID BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "    COD_DISCIPLINA VARCHAR(255),"
                + "    ASSUNTO VARCHAR(255),"
                + "    DURACAO VARCHAR(255),"
                + "    DATA VARCHAR(20),"
                + "    HORARIO VARCHAR(20)"
                + ")";
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            this.connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encerra a conexão
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * ****************************************************************
     * CRUD
     * ****************************************************************
     */

    // CRUD READ
    public ArrayList<AulaDto> findAll() {
        String query = "SELECT ID, COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO FROM AULA";
        ArrayList<AulaDto> lista = new ArrayList<>();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                AulaDto dto = new AulaDto();
                dto.setId(resultSet.getString("ID"));
                dto.setCodDisciplina(resultSet.getString("COD_DISCIPLINA"));
                dto.setAssunto(resultSet.getString("ASSUNTO"));
                dto.setDuracao(resultSet.getString("DURACAO"));
                dto.setData(resultSet.getString("DATA"));
                dto.setHorario(resultSet.getString("HORARIO"));
                lista.add(dto);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public AulaDto findById(String id) {
        String query = "SELECT ID, COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO FROM AULA WHERE ID = ?";
        AulaDto dto = null;

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dto = new AulaDto();
                dto.setId(resultSet.getString("ID"));
                dto.setCodDisciplina(resultSet.getString("COD_DISCIPLINA"));
                dto.setAssunto(resultSet.getString("ASSUNTO"));
                dto.setDuracao(resultSet.getString("DURACAO"));
                dto.setData(resultSet.getString("DATA"));
                dto.setHorario(resultSet.getString("HORARIO"));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dto;
    }

    // CRUD CREATE
    public void create(AulaDto dto) {
        String query = "INSERT INTO AULA (COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, dto.getCodDisciplina());
            preparedStatement.setString(2, dto.getAssunto());
            preparedStatement.setString(3, dto.getDuracao());
            preparedStatement.setString(4, dto.getData());
            preparedStatement.setString(5, dto.getHorario());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD DELETE
    public void delete(String id) {
        String query = "DELETE FROM AULA WHERE ID = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD UPDATE
    public void update(AulaDto dto) {
        String query = "UPDATE AULA SET COD_DISCIPLINA = ?, ASSUNTO = ?, DURACAO = ?, DATA = ?, HORARIO = ? WHERE ID = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, dto.getCodDisciplina());
            preparedStatement.setString(2, dto.getAssunto());
            preparedStatement.setString(3, dto.getDuracao());
            preparedStatement.setString(4, dto.getData());
            preparedStatement.setString(5, dto.getHorario());
            preparedStatement.setString(6, dto.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * PARA EFEITO DE TESTES
     */

    public void reset() {
        this.deleteAll();
        this.popularTabela();
    }

    public void deleteAll() {
        String query = "DELETE FROM AULA";
        try {
            Statement st = this.connection.createStatement();
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popularTabela() {
        AulaDto dto = new AulaDto();

        dto.setCodDisciplina("1");
        dto.setAssunto("Derivadas");
        dto.setDuracao("2");
        dto.setData("2024-04-12");
        dto.setHorario("14:00");
        this.create(dto);

        dto.setCodDisciplina("3");
        dto.setAssunto("Coordenadas Cartesianas");
        dto.setDuracao("2");
        dto.setData("2024-04-13");
        dto.setHorario("14:00");
        this.create(dto);

        dto.setCodDisciplina("4");
        dto.setAssunto("O Problema dos Três Corpos");
        dto.setDuracao("4");
        dto.setData("2024-04-14");
        dto.setHorario("14:00");
        this.create(dto);
    }

}
