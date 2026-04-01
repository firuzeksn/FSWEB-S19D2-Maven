package com.workintech.s18d4.service;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    public AddressServiceImpl(AddressRepository addressRepository) { this.addressRepository = addressRepository; }

    @Override public List<Address> findAll() { return addressRepository.findAll(); }
    @Override public Address find(Long id) { return addressRepository.findById(id).orElse(null); }
    @Override public Address save(Address address) { return addressRepository.save(address); }
    @Override public Address delete(Long id) {
        Address address = find(id);
        if (address != null) addressRepository.delete(address);
        return address;
    }
}