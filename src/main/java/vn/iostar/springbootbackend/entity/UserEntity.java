package vn.iostar.springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "first_name", nullable = false, columnDefinition = "nvarchar(1000)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "nvarchar(1000)")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "varchar(1000)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(1000)")
    private String password;

    @Column(name = "gender", nullable = false)
    private int gender;

    @Column(name = "avatar", columnDefinition = "varchar(1000)")
    private String avatar;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private List<CommentLikedEntity> commentLikeds;

    @OneToMany(mappedBy = "user")
    private List<PlaylistEntity> playlists;

    @OneToMany(mappedBy = "user")
    private List<SongCommentEntity> songComments;

    @OneToMany(mappedBy = "song")
    private List<SongLikedEntity> songLikeds;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
