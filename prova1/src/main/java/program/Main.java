package program;

import java.util.ArrayList;

import db.Db;
import model.AulaDto;

public class Main {

    public static void main(String[] args) {
        // Obtendo a instância do banco de dados
        Db db = Db.getInstance();

        // Resetando o banco de dados para testes (opcional)
        db.reset();

        // Buscando todos os registros no banco de dados
        ArrayList<AulaDto> lista = db.findAll();
        System.out.println("Registros encontrados:");
        for (AulaDto dto : lista) {
            System.out.println(dto.toString());
        }

        // Buscando e exibindo um registro específico por ID
        String idBusca = "1";
        System.out.println("\nBuscando registro com ID " + idBusca + ":");
        AulaDto aulaEncontrada = db.findById(idBusca);
        if (aulaEncontrada != null) {
            System.out.println(aulaEncontrada.toString());
        } else {
            System.out.println("Nenhum registro encontrado com o ID " + idBusca);
        }

        // Criando um novo registro
        System.out.println("\nCriando novo registro:");
        AulaDto novaAula = new AulaDto();
        novaAula.setCodDisciplina("5");
        novaAula.setAssunto("Geometria Analítica");
        novaAula.setDuracao("3");
        novaAula.setData("2024-05-14");
        novaAula.setHorario("09:00");
        db.create(novaAula);

        // Atualizando um registro existente
        System.out.println("\nAtualizando registro com ID " + idBusca + ":");
        aulaEncontrada = db.findById(idBusca);
        if (aulaEncontrada != null) {
            aulaEncontrada.setAssunto("Cálculo Integral");
            db.update(aulaEncontrada);
            System.out.println("Registro atualizado:");
            System.out.println(aulaEncontrada.toString());
        } else {
            System.out.println("Nenhum registro encontrado com o ID " + idBusca);
        }

        // Excluindo um registro
        String idExcluir = "2";
        System.out.println("\nExcluindo registro com ID " + idExcluir);
        db.delete(idExcluir);

        // Exibindo todos os registros após a exclusão
        System.out.println("\nRegistros após a exclusão:");
        lista = db.findAll();
        for (AulaDto dto : lista) {
            System.out.println(dto.toString());
        }

        // Fechando a conexão com o banco de dados
        db.close();
    }
    
    
}
