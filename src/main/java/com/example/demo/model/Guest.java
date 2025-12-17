import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Guest{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @column(unique=true)
    private String email;
    private String phoneNumber;
    private Boolean verified;
    private Boolean active;
    private String role;


}