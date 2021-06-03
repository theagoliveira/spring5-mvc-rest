package guru.springframework.spring5mvcrest.services;

import java.util.List;

import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;

public interface VendorService {

    List<VendorDTO> findAll();

    VendorDTO findById(Long id);

    VendorDTO save(VendorDTO vendorDTO);

    VendorDTO put(Long id, VendorDTO vendorDTO);

    VendorDTO patch(Long id, VendorDTO vendorDTO);

    void delete(Long id);

}
