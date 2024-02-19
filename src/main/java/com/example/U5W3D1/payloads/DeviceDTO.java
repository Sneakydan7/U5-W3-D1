package com.example.U5W3D1.payloads;

import com.example.U5W3D1.enums.DeviceStatus;
import com.example.U5W3D1.enums.DeviceType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceDTO {
    @NotEmpty(message = "A device type is required") DeviceType deviceType;
    DeviceStatus deviceStatus;
}
