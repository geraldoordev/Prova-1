package model;

import enums.DisciplinaEnum;

public class AulaDto {

    private String id;
    private String disciplina;
    private String codDisciplina;
    private String assunto;
    private String duracao;
    private String data;
    private String horario;

    public AulaDto() {
    }

    public AulaDto(Aula aula, int codDisciplina) {
        this.id = Long.toString(aula.getId());
        this.disciplina = geraNomeDisc(codDisciplina);
        this.codDisciplina = Integer.toString(codDisciplina);
        this.assunto = aula.getAssunto();
        this.duracao = Integer.toString(aula.getDuracao());
        this.data = formataData(aula.getData());
        this.horario = aula.getHorario();
    }

    private String geraNomeDisc(int cod) {
        DisciplinaEnum disciplinaEnum = DisciplinaEnum.getDiscByCodigo(cod);
        if (disciplinaEnum != null) {
            return disciplinaEnum.getNome();
        } else {
            return "Disciplina Desconhecida";
        }
    }


    private String formataData(String dt) {
        String[] partes = dt.split("-");
        String resposta = partes[2] + "/" + partes[1] + "/" + partes[0];
        return resposta;
    }

    public void reverteFormatoData() {
        String[] partes = this.data.split("/");
        this.data = partes[2] + "-" + partes[1] + "-" + partes[0];
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(String string) {
        this.codDisciplina = string;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "AulaDto [id=" + id + ", disciplina=" + disciplina + ", codDisciplina=" + codDisciplina + ", assunto="
                + assunto + ", duracao=" + duracao + ", data=" + data + ", horario=" + horario + "]";
    }

}
