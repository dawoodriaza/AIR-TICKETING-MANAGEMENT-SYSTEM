package com.ga.airticketmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String emailAddress;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean emailVerified = false;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIgnore
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Airport> createdAirports;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Flight> createdFlights;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Booking> bookings;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @PrePersist
    public void prePersist() {
        this.emailVerified = false;
        this.active = true;
    }
}