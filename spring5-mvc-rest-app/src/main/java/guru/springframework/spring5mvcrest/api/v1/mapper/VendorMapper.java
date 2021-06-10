package guru.springframework.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import guru.springframework.model.VendorDTO;
import guru.springframework.spring5mvcrest.controllers.v1.VendorController;
import guru.springframework.spring5mvcrest.domain.Vendor;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorUrl", expression = "java(\"" + VendorController.BASE_URI
            + "/\" + vendor.getId())")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

}
