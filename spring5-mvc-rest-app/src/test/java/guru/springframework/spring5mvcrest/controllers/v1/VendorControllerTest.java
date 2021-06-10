package guru.springframework.spring5mvcrest.controllers.v1;

import static guru.springframework.spring5mvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.model.VendorDTO;
import guru.springframework.spring5mvcrest.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.spring5mvcrest.services.VendorService;
import guru.springframework.spring5mvcrest.services.ResourceNotFoundException;

class VendorControllerTest {

    private static final String VENDORS_URI = VendorController.BASE_URI;
    private static final String VENDORS_URI_SLASH = VENDORS_URI + "/";
    private static final Long ID1 = 1L;
    private static final Long ID2 = 2L;
    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                                 .setControllerAdvice(new RestResponseEntityExceptionHandler())
                                 .build();
    }

    @Test
    void testIndex() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(ID1);
        vendorDTO1.setName(NAME1);
        vendorDTO1.setVendorUrl(VENDORS_URI_SLASH + ID1);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setId(ID2);
        vendorDTO2.setName(NAME2);
        vendorDTO2.setVendorUrl(VENDORS_URI_SLASH + ID2);

        List<VendorDTO> vendorDTOs = Arrays.asList(vendorDTO1, vendorDTO2);

        when(vendorService.findAll()).thenReturn(vendorDTOs);

        mockMvc.perform(get(VENDORS_URI).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void testShow() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(ID1);
        vendorDTO1.setName(NAME1);
        vendorDTO1.setVendorUrl(VENDORS_URI_SLASH + ID1);

        when(vendorService.findById(anyLong())).thenReturn(vendorDTO1);

        mockMvc.perform(get(VENDORS_URI_SLASH + ID1).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.name", equalTo(NAME1)))
               .andExpect(jsonPath("$.vendor_url", equalTo(VENDORS_URI_SLASH + ID1)));
    }

    @Test
    void testShowNotFound() throws Exception {
        when(vendorService.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VENDORS_URI_SLASH + "999").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(NAME1);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setId(ID1);
        vendorDTO2.setName(NAME1);
        vendorDTO2.setVendorUrl(VENDORS_URI_SLASH + ID1);

        when(vendorService.save(any(VendorDTO.class))).thenReturn(vendorDTO2);

        mockMvc.perform(
            post(VENDORS_URI).contentType(MediaType.APPLICATION_JSON)
                             .content(asJsonString(vendorDTO1))
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.name", equalTo(NAME1)))
               .andExpect(jsonPath("$.vendor_url", equalTo(VENDORS_URI_SLASH + ID1)));
    }

    @Test
    void testPut() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(NAME1);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setId(ID1);
        vendorDTO2.setName(NAME1);
        vendorDTO2.setVendorUrl(VENDORS_URI_SLASH + ID1);

        when(vendorService.put(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO2);

        mockMvc.perform(
            put(VENDORS_URI_SLASH + ID1).contentType(MediaType.APPLICATION_JSON)
                                        .content(asJsonString(vendorDTO1))
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.name", equalTo(NAME1)))
               .andExpect(jsonPath("$.vendor_url", equalTo(VENDORS_URI_SLASH + ID1)));
    }

    @Test
    void testPatch() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(NAME1);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setId(ID1);
        vendorDTO2.setName(NAME1);
        vendorDTO2.setVendorUrl(VENDORS_URI_SLASH + ID1);

        when(vendorService.patch(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO2);

        mockMvc.perform(
            patch(VENDORS_URI_SLASH + ID1).contentType(MediaType.APPLICATION_JSON)
                                          .content(asJsonString(vendorDTO1))
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.name", equalTo(NAME1)))
               .andExpect(jsonPath("$.vendor_url", equalTo(VENDORS_URI_SLASH + ID1)));
    }

    @Test
    void testPatchNotFound() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(NAME1);

        when(vendorService.patch(anyLong(), any(VendorDTO.class))).thenThrow(
            ResourceNotFoundException.class
        );

        mockMvc.perform(
            patch(VENDORS_URI_SLASH + "999").contentType(MediaType.APPLICATION_JSON)
                                            .content(asJsonString(vendorDTO1))
        ).andExpect(status().isNotFound());
    }

    @Test
    void testDestroy() throws Exception {
        mockMvc.perform(delete(VENDORS_URI_SLASH + ID1)).andExpect(status().isOk());

        verify(vendorService).delete(anyLong());
    }

}
