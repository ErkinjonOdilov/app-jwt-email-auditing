package uz.pdp.appjwtrealemailauditing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;


    @Column(nullable = false,length = 50)
    private String firstName;


    @Column(nullable = false)
    private String lastName;

    @Column(unique = true,nullable =false)
    private String email;  //username sifatida

    @Column(nullable=false)
    private String password;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp     //databasega qachon sqalanganligini oladi
    private Timestamp createdAt;

    @UpdateTimestamp       //qachon ozgarteish kiritganligi oxirgi marta
    private Timestamp updateAt;

    @ManyToMany()
    private Set<Role> roles;


    private boolean accountNonExpired=true; // user amal qilish muddati otmagan

    private boolean accountNonLocked=true; // user quluflanmaganmi blocklanmaganligi

    private boolean isCredentialsNonExpired=true; // user ishonchlimi

    private boolean enabled;

    private String emailCode;



    //UserDetails metodlari


    //user huquqlari
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    //username qaytarish
    @Override
    public String getUsername() {
        return this.email;
    }

    //mudati tugamaganligi amal qilish mudati
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    //user quluflanmaganligi bloklanganlik holati
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    //account ishonchlilik mudati tugagan yoki tugamaganligi qaytaradi
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    //account yoniq ochiqligini bildiradigan metod
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
