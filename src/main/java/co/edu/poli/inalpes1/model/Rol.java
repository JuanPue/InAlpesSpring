package co.edu.poli.inalpes1.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Erol rolNombre;

    public Rol() {
    }

    public Rol(@NotNull Erol rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Erol getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(Erol rolNombre) {
        this.rolNombre = rolNombre;
    }

}
