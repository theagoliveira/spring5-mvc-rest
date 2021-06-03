package guru.springframework.spring5mvcrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import guru.springframework.spring5mvcrest.controllers.v1.VendorController;
import guru.springframework.spring5mvcrest.domain.Vendor;
import guru.springframework.spring5mvcrest.repositories.VendorRepository;

class VendorServiceTest {

    private static final String VENDORS_URI_SLASH = VendorController.BASE_URI + "/";
    private static final Long ID = 1L;
    private static final String NAME = "name";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void testFindAll() {
        // given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);

        // when
        List<VendorDTO> vendorDTOs = vendorService.findAll();

        // then
        assertEquals(vendors.size(), vendorDTOs.size());
    }

    @Test
    void testFindById() {
        // given
        var vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        // when
        VendorDTO vendorDTO = vendorService.findById(ID);

        // then
        assertEquals(vendor.getId(), vendorDTO.getId());
        assertEquals(vendor.getName(), vendorDTO.getName());
        assertEquals(VENDORS_URI_SLASH + vendor.getId(), vendorDTO.getVendorUrl());
    }

    @Test
    void testFindByIdNotFound() {
        // given
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        assertThrows(ResourceNotFoundException.class, () -> vendorService.findById(ID));
    }

    @Test
    void testSave() {
        // given
        var vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        var vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        // when
        VendorDTO returnVendorDTO = vendorService.save(vendorDTO);

        // then
        assertEquals(vendor.getId(), returnVendorDTO.getId());
        assertEquals(vendor.getName(), returnVendorDTO.getName());
        assertEquals(VENDORS_URI_SLASH + vendor.getId(), returnVendorDTO.getVendorUrl());
    }

    @Test
    void testPut() {
        // given
        var vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        var vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        // when
        VendorDTO returnVendorDTO = vendorService.put(ID, vendorDTO);

        // then
        assertEquals(ID, returnVendorDTO.getId());
        assertEquals(vendorDTO.getName(), returnVendorDTO.getName());
        assertEquals(VENDORS_URI_SLASH + ID, returnVendorDTO.getVendorUrl());
    }

    @Test
    void testPatchNotFound() {
        // given
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.empty());
        VendorDTO vendorDTO = new VendorDTO();

        // when
        assertThrows(ResourceNotFoundException.class, () -> vendorService.patch(ID, vendorDTO));
    }

    @Test
    void testDelete() {
        // when
        vendorService.delete(ID);

        // then
        verify(vendorRepository).deleteById(anyLong());
    }

}
