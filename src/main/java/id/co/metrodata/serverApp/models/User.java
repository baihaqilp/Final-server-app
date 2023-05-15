package id.co.metrodata.serverApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Boolean is_enabled = true;
    private Boolean is_account_locked = true;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Employee employee;
}
