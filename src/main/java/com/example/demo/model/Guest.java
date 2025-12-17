import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public Long getID(){
    return id;
}
public Long setId(Long id){
    this.id=id;
}