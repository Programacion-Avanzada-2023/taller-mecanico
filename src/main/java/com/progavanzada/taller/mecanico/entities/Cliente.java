
import com.progavanzada.taller.mecanico.entities.Persona;
import jakarta.persistence.*;

@Entity
@Table(name = "tm_cliente")
public class Cliente{
    
    @OneToOne(targetEntity = Persona.class, optional = false)
    @MapsId
    public Persona person;
    
    public Integer getLicenciaConducir() {
        // Obtener la licencia de conducir desde la persona
        if (person != null) {
            return person.getDni(); // Asumimos que DNI es igual a la licencia de conducir
        } else {
            return null; // O maneja el caso cuando persona es nula
        }
    }
}


