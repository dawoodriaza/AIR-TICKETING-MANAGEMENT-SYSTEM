package com.ga.airticketmanagement.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
 @AllArgsConstructor
@Entity
@Table(name = "users")
@ToString(exclude = {"password","userProfile"})
@JsonIgnoreProperties(ignoreUnknown = false)
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
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private boolean active = true;

    @Column(nullable = false)
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private boolean emailVerified = false;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-profile")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference("created-airports")
    @JsonIgnore
    private List<Airport> createdAirports;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference("created-flights")
    @JsonIgnore
    private List<Flight> createdFlights;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference("user-bookings")
    @JsonIgnore
    private List<Booking> bookings;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-assets")
    @JsonIgnore
    private List<Asset> assets;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @PrePersist
    public void prePersist(){
        this.active = true;
    }
}
