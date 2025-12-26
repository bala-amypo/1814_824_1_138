// package com.example.demo.security;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import java.util.Collection;

// public class CustomUserDetails implements UserDetails {

//     private Long id;
//     private String email;
//     private String password;
//     private String role;
//     private Collection<? extends GrantedAuthority> authorities;

//     public CustomUserDetails(
//             Long id,
//             String email,
//             String password,
//             String role,
//             Collection<? extends GrantedAuthority> authorities) {

//         this.id = id;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//         this.authorities = authorities;
//     }

//     public Long getId() {
//         return id;
//     }

//     public String getRole() {
//         return role;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return authorities;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public String getUsername() {
//         return email;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }
package com.example.demo.security;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final GuestRepository guestRepository;

    public CustomUserDetailsService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Guest guest = guestRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email));

        return new User(
                guest.getEmail(),
                guest.getPassword(),
                List.of(new SimpleGrantedAuthority(guest.getRole()))
        );
    }
}
