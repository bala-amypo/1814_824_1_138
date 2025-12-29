// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.util.Set;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String email;

//     @Column(nullable = false)
//     private String password;

//     @ElementCollection(fetch = FetchType.EAGER)
//     @CollectionTable(
//         name = "user_roles",
//         joinColumns = @JoinColumn(name = "user_id")
//     )
//     @Column(name = "role")
//     private Set<String> roles;

//     public User() {}

//     public Long getId() {
//         return id;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public Set<String> getRoles() {
//         return roles;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public void setRoles(Set<String> roles) {
//         this.roles = roles;
//     }
// }


package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    private Set<String> roles;

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Set<String> getRoles() { return roles; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
