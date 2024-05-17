package enums;

public enum DisciplinaEnum {
    CALCULO(1, "CÁLCULO"),
    LOGICA(2, "LÓGICA"),
    GEOMETRIA(3, "GEOMETRIA"),
    FISICA(4, "FÍSICA"),
    COMPILADORES(5, "COMPILADORES");

    private final int codigo;
    private final String nome;

    DisciplinaEnum(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public static DisciplinaEnum getDiscByCodigo(int codigo) {
        for (DisciplinaEnum disc : values()) {
            if (disc.getCodigo() == codigo) {
                return disc;
            }
        }
        return null;
    }
}
