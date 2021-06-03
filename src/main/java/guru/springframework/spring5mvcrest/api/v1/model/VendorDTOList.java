package guru.springframework.spring5mvcrest.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VendorDTOList {

    List<VendorDTO> vendors;

}
