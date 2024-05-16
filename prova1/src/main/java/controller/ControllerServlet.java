package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AulaDto;
import db.Db;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = { "/prova1", "/nova", "/edit" })
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ControllerServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/nova")) {
            RequestDispatcher rd = request.getRequestDispatcher("nova.jsp");
            rd.forward(request, response);
        } else if (action.equals("/edit")) {
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            Db db = Db.getInstance();
            AulaDto dto = db.findById(id);
            session.setAttribute("dto", dto);
            RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        switch (op) {
            case "START_SESSION":
                this.poeDadosNaSessao(request);
                break;
            case "RESET":
                this.reset();
                break;
            case "CREATE":
                this.create(request);
                break;
            case "READ":
                this.getAula(request, response);
                break;
            case "UPDATE":
                this.update(request);
                break;
            case "DELETE":
                this.delete(request);
                break;
        }
    }

    private void poeDadosNaSessao(HttpServletRequest request) {
    	// Obtém uma instância do banco de dados
        Db db = Db.getInstance();

        // Obtém a lista de todos os DTOs (no caso, AulaDto) do banco de dados
        ArrayList<AulaDto> listaDeAulas = db.findAll();

        // Define a lista de DTOs como um atributo da sessão
        HttpSession session = request.getSession();
        session.setAttribute("listaDeAulas", listaDeAulas);
    }

    private void reset() {
    	// Obtém uma instância do banco de dados
        Db db = Db.getInstance();

        // Remove todas as aulas do banco de dados
        db.deleteAll();

        // Popula novamente o banco de dados com os valores padrão para efeito de testes
        db.popularTabela();
    }

    private void create(HttpServletRequest request) {
    	// Recuperar os parâmetros enviados via AJAX
        String codDisciplina = request.getParameter("codDisciplina");
        String assunto = request.getParameter("assunto");
        String duracao = request.getParameter("duracao");
        String data = request.getParameter("data");
        String horario = request.getParameter("horario");

        // Criar um objeto DTO com os dados recebidos
        AulaDto aulaDto = new AulaDto();
        aulaDto.setCodDisciplina(codDisciplina);
        aulaDto.setAssunto(assunto);
        aulaDto.setDuracao(duracao);
        aulaDto.setData(data);
        aulaDto.setHorario(horario);

        // Enviar o objeto DTO para o banco de dados
        Db db = Db.getInstance();
        db.create(aulaDto); // Supondo que haja um método save() em Db para salvar o DTO no banco de dados
    }

    private void delete(HttpServletRequest request) {
    	// Recupera o parâmetro 'id' da requisição
        String id = request.getParameter("id");

        // Verifica se o 'id' foi fornecido
        if (id != null && !id.isEmpty()) {
            // Obtém uma instância do banco de dados
            Db db = Db.getInstance();

            // Remove a aula com o 'id' especificado do banco de dados
            db.delete(id);
        } else {
            // Caso o 'id' não tenha sido fornecido na requisição, você pode lidar com isso de acordo com sua lógica de negócio
            System.err.println("Erro: ID não fornecido para exclusão.");
            // Aqui você pode adicionar uma lógica para lidar com o erro, como enviar uma resposta de erro para o cliente
        }
    }

    private void getAula(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         *  Este método recupera um dto a partir do parâmetro id.
         *  Em seguida, cria um json 'manualmente' e o envia como resposta da requisição.
         */
        String id = request.getParameter("id");
        Db db = Db.getInstance();
        AulaDto dto = db.findById(id);
        response.setContentType("application/json");
        String json = "{\"id\": \"" + id + "\","
                + "\"disciplina\": \"" + dto.getDisciplina() + "\","
                + "\"codDisciplina\": \"" + dto.getCodDisciplina() + "\","
                + "\"assunto\": \"" + dto.getAssunto() + "\","
                + "\"duracao\": \"" + dto.getDuracao() + "\","
                + "\"data\": \"" + dto.getData() + "\","
                + "\"horario\": \"" + dto.getHorario() + "\"}";
        response.getWriter().write(json);
    }

    private void update(HttpServletRequest request) {
    	// Recupera os parâmetros enviados na requisição
        String id = request.getParameter("id");
        String codDisciplina = request.getParameter("codDisciplina");
        String assunto = request.getParameter("assunto");
        String duracao = request.getParameter("duracao");
        String data = request.getParameter("data");
        String horario = request.getParameter("horario");

        // Verifica se todos os parâmetros necessários foram fornecidos
        if (id != null && !id.isEmpty() && codDisciplina != null && assunto != null && duracao != null && data != null && horario != null) {
            // Cria um objeto AulaDto com os parâmetros fornecidos
            AulaDto dto = new AulaDto();
            dto.setId(id);
            dto.setCodDisciplina(codDisciplina);
            dto.setAssunto(assunto);
            dto.setDuracao(duracao);
            dto.setData(data);
            dto.setHorario(horario);

            // Obtém uma instância do banco de dados
            Db db = Db.getInstance();

            // Atualiza o registro da aula no banco de dados
            db.update(dto);
        } else {
            // Caso algum dos parâmetros não tenha sido fornecido na requisição, você pode lidar com isso de acordo com sua lógica de negócio
            System.err.println("Erro: Parâmetros incompletos para atualização.");
            // Aqui você pode adicionar uma lógica para lidar com o erro, como enviar uma resposta de erro para o cliente
        }
    }
}
