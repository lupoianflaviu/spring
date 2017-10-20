package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.repository.AddressRepository;
@Service("Address Service")
public class AddressServiceImpl implements AddressService<Address> {

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public void create(Address address) {
        this.addressRepository.create(address);
    }

    @Override
    public void delete(Address address) {

    }

    @Override
    public void update(Address address) {

    }

    @Override
    public Address searchByEmployeeId(Integer employeeId) {
        return this.addressRepository.searchByEmployeeId(employeeId);
    }

    @Override
    public void setAddressRepository(AddressRepository<Address> addressRepository) {
        this.addressRepository=addressRepository;

    }
}
