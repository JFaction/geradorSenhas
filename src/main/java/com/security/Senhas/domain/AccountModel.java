package com.security.Senhas.domain;

import com.security.Senhas.dto.AccountDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID idUser;

    @NotBlank
    private String title;

    @NotBlank
    private String username;

    @Column
    private String password;

    public AccountModel(UUID id, String title, String username, String password) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.password = password;
    }

    public AccountModel() {

    }

    public AccountModel(AccountDTO accountDTO){
        this.id = accountDTO.id();
        this.title = accountDTO.title();
        this.username = accountDTO.username();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }
}
