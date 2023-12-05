package pollub.pai_lab5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Zadanie {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String nazwa;

    @Column
    @Lob
    private String opis;

    @Column
    private Double koszt;

    @Column
    private Boolean wykonane = false;

    public Zadanie() {
    }

    public Zadanie(String nazwa, String opis, Double koszt) {
        this.nazwa = "Zadanie";
        this.opis = "Zadanie do wykonania";
        this.koszt = 2000.0;
    }

    @Override
    public String toString() {
        return "Zadanie{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                ", koszt=" + koszt +
                ", wykonane=" + wykonane +
                '}';
    }
}
