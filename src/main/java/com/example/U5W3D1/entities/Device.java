package com.example.U5W3D1.entities;

import com.example.U5W3D1.enums.DeviceStatus;
import com.example.U5W3D1.enums.DeviceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "devices")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DeviceType type;
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public Device(DeviceType type) {
        this.type = type;
        this.status = DeviceStatus.AVAILABLE;
    }
}
