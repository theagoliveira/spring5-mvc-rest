package guru.springframework.spring5mvcrest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import guru.springframework.spring5mvcrest.controllers.v1.VendorController;
import guru.springframework.spring5mvcrest.domain.Vendor;

class VendorMapperTest {

    private static final String VENDORS_URI_SLASH = VendorController.BASE_URI + "/";

    private static final Long ID = 1L;
    private static final String NAME = "name";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void testVendorToVendorDTO() {
        // given
        var vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        // when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // then
        assertEquals(ID, vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());
        assertEquals(VENDORS_URI_SLASH + ID, vendorDTO.getVendorUrl());
    }

    @Test
    void testVendorDTOToVendor() {
        // given
        var vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);

        // when
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        // then
        assertEquals(ID, vendor.getId());
        assertEquals(NAME, vendor.getName());
    }

}
