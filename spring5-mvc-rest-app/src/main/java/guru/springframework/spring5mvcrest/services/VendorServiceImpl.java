package guru.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import guru.springframework.spring5mvcrest.domain.Vendor;
import guru.springframework.spring5mvcrest.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

    VendorRepository vendorRepository;
    VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> findAll() {
        return vendorRepository.findAll()
                               .stream()
                               .map(vendorMapper::vendorToVendorDTO)
                               .collect(Collectors.toList());
    }

    @Override
    public VendorDTO findById(Long id) {
        return vendorMapper.vendorToVendorDTO(
            vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new)
        );
    }

    @Override
    public VendorDTO save(VendorDTO vendorDTO) {
        var vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        var savedVendor = vendorRepository.save(vendor);
        return vendorMapper.vendorToVendorDTO(savedVendor);
    }

    @Override
    public VendorDTO put(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
        return save(vendorDTO);
    }

    @Override
    public VendorDTO patch(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        if (vendorDTO.getName() == null)
            vendorDTO.setName(vendor.getName());

        return put(id, vendorDTO);
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }

}
