package com.e_commerce.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "public")
public class Role implements GrantedAuthority { // yetkilendirme

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "authority")
    private String authority;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<User> users; // Bir rol birçok kullanıcıya atanabilir


    // Rolü bir kullanıcıya ekleyen bir yardımcı metot
    public void addUser(User user){
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }


    public String getAuthority() {
        return this.authority; // `authority` rol ismini içerir (örneğin "ADMIN")
    }


}
