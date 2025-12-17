import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence
@Entity
public class Guest{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @column(unique=true)
    private String email;

}