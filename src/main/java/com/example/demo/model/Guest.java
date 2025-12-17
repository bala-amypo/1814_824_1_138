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
public void setId(Long id){
    this.id=id;
}
public String getfullName(){
    return fullName;
}
public void setfullName(Long fullName){
    this.fullName=fullName;
}
public String getEmail(){
    return email;

}
public void setemail(){
    this.email=email;
}
public String PhoneNumber(){
    return phoneNumber;
}
public void setphoneNumber(){
    this.phoneNumber=phoneNumber;
}
public Boolean getVerified(){
    return verified;
}
public void setverified(){
    this.verified=verified;
}
public Boolean getActive(){
    return active;
}
public void setactive(){
    this.active=active;
}
public String getRole(){
    return role;

}
public void setrole(){
    this.role=role;
}
public Guest(Long id,String fullName,String email,String phoneNumber,Boolean verified,Boolean active,String role){
    thisid=id;
    this.fullName=fullName;
    this.email=email;
    this.phoneNumber=phoneNumbe;
    this.verified=verified;
    this.active=active;
    this.role=role;
}
public Guest(){

}