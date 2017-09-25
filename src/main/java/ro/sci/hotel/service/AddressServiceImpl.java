package ro.sci.hotel.service;

import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.repository.AddressRepository;

import java.util.List;

public class AddressServiceImpl implements AddressService<Address> {

    private AddressRepository<Address> addressRepository;

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public void create(Address address) {

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
