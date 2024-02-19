package com.example.U5W3D1.services;

import com.example.U5W3D1.entities.Device;
import com.example.U5W3D1.entities.Employee;
import com.example.U5W3D1.enums.DeviceStatus;
import com.example.U5W3D1.exceptions.NotFoundException;
import com.example.U5W3D1.payloads.AssignDTO;
import com.example.U5W3D1.payloads.DeviceDTO;
import com.example.U5W3D1.repositories.DeviceDAO;
import com.example.U5W3D1.repositories.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceSRV {
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private EmployeeDAO employeeDAO;

    public Page<Device> getDevices(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return deviceDAO.findAll(pageable);
    }


    public Device getDeviceById(Long id) {
        return deviceDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device saveDevice(DeviceDTO newDevice) {
        return deviceDAO.save(new Device(newDevice.getDeviceType()));
    }

    public Device updateDeviceById(DeviceDTO updatedDevice, Long id) {
        Device found = getDeviceById(id);
        found.setStatus(updatedDevice.getDeviceStatus());
        found.setType(updatedDevice.getDeviceType());
        deviceDAO.save(found);
        return found;
    }

    public void deleteDevice(Long id) {
        Device found = getDeviceById(id);
        deviceDAO.delete(found);
    }

    public Device assignDeviceToEmployee(Long id, AssignDTO assignDTO) {
        Employee foundEmployee = employeeDAO.findByEmail(assignDTO.getEmail()).orElseThrow(() -> new RuntimeException("Employee not found"));
        Device foundDevice = getDeviceById(id);
        foundDevice.setStatus(DeviceStatus.ASSIGNED);
        foundDevice.setEmployee(foundEmployee);
        return deviceDAO.save(foundDevice);
    }
}
