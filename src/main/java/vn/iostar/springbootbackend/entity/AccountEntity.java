package vn.iostar.springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_account;

    @Column(name = "username", nullable = false, columnDefinition = "varchar(100)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(100)")
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;

}
