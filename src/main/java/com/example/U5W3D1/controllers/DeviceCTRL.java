package com.example.U5W3D1.controllers;

import com.example.U5W3D1.entities.Device;
import com.example.U5W3D1.payloads.AssignDTO;
import com.example.U5W3D1.payloads.DeviceDTO;
import com.example.U5W3D1.services.DeviceSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceCTRL {
    @Autowired
    private DeviceSRV deviceSRV;

    @GetMapping
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.deviceSRV.getDevices(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Device getDevicesById(@PathVariable Long id) {
        return this.deviceSRV.getDeviceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody DeviceDTO newDevice) {
        return this.deviceSRV.saveDevice(newDevice);

    }

    @PutMapping("/{id}")
    public Device updateDeviceById(@PathVariable Long id, @RequestBody @Validated DeviceDTO updatedDevice, BindingResult validated) {
        if (validated.hasErrors()) {
            throw new RuntimeException();
        } else {
            return this.deviceSRV.updateDeviceById(updatedDevice, id);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        this.deviceSRV.deleteDevice(id);
    }

    @PutMapping("/{id}/assignDevice")
    @ResponseStatus(HttpStatus.CREATED)
    public Device assignDeviceToEmployee(@PathVariable Long id, @RequestBody AssignDTO assignDTO) {
        return this.deviceSRV.assignDeviceToEmployee(id, assignDTO);
    }
}
