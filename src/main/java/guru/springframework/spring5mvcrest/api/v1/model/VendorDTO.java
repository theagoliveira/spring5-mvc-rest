package guru.springframework.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    @ApiModelProperty(value = "Vendor ID")
    private Long id;
    @ApiModelProperty(value = "Vendor name", required = true)
    private String name;

    @ApiModelProperty(value = "Vendor endpoint", required = true)
    @JsonProperty("vendor_url")
    private String vendorUrl;

}
