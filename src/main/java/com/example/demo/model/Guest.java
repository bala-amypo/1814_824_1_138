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
public String getfullName(){
    return fullName;
}
public String setfullName(Long fullName){
    this.fullName=fullName;
}
public String getEmail(){
    return email;

}
public String setemail(){
    this.email=email;
}
public String PhoneNumber(){
    return phoneNumber;
}
public String setphoneNumber(){
    this.phoneNumber=phoneNumber;
}
public Boolean getVerified(){
    return verified;
}
public Boolean setverified(){
    this.verified;
}
public Boolean getActive(){
    return active;
}
public Boolean setactive(){
    this.active;
}
public String getRole(){
    return role;

}
public String setrole(){
    this.role;
}
public Guest(Long id,String fullName,String email,String phoneNumber,Boolean verified,Boolean active,String role){
    this
}