import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class RoomBooking{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private Boolean
    
}