package guru.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.model.VendorDTO;
import guru.springframework.model.VendorDTOList;
import guru.springframework.spring5mvcrest.services.VendorService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = VendorController.BASE_URI, produces = {
        "application/json", "application/xml"})
public class VendorController {

    public static final String BASE_URI = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get a list of all vendors.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorDTOList index() {
        var vendorDTOList = new VendorDTOList();
        vendorDTOList.getVendors().addAll(vendorService.findAll());
        return vendorDTOList;
    }

    @ApiOperation(value = "Get information about a particular vendor.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO show(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @ApiOperation(value = "Create a new vendor.")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO create(@RequestBody VendorDTO vendorDTO) {
        return vendorService.save(vendorDTO);
    }

    @ApiOperation(value = "Update an existing vendor or create a new one, based on the ID.", notes = "If the ID does not exist in the database, a new vendor with the next available ID will be created. ")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO put(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.put(id, vendorDTO);
    }

    @ApiOperation(value = "Update an existing vendor.")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patch(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patch(id, vendorDTO);
    }

    @ApiOperation(value = "Delete a vendor.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable Long id) {
        vendorService.delete(id);
    }

}
