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

import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.services.CustomerService;

class CustomerControllerTest {

    private static final String CUSTOMERS_URI = "/api/v1/customers";
    private static final String CUSTOMERS_URI_SLASH = "/api/v1/customers/";
    private static final Long ID1 = 1L;
    private static final Long ID2 = 2L;
    private static final String FIRST_NAME1 = "firstName1";
    private static final String FIRST_NAME2 = "firstName2";
    private static final String LAST_NAME1 = "lastName1";
    private static final String LAST_NAME2 = "lastName2";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testIndex() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(ID1);
        customerDTO1.setFirstName(FIRST_NAME1);
        customerDTO1.setLastName(LAST_NAME1);
        customerDTO1.setCustomerUrl(CUSTOMERS_URI_SLASH + ID1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(ID2);
        customerDTO2.setFirstName(FIRST_NAME2);
        customerDTO2.setLastName(LAST_NAME2);
        customerDTO2.setCustomerUrl(CUSTOMERS_URI_SLASH + ID2);

        List<CustomerDTO> customerDTOs = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.findAll()).thenReturn(customerDTOs);

        mockMvc.perform(get(CUSTOMERS_URI).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void testShow() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(ID1);
        customerDTO1.setFirstName(FIRST_NAME1);
        customerDTO1.setLastName(LAST_NAME1);
        customerDTO1.setCustomerUrl(CUSTOMERS_URI_SLASH + ID1);

        when(customerService.findById(anyLong())).thenReturn(customerDTO1);

        mockMvc.perform(get(CUSTOMERS_URI_SLASH + ID1).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.first_name", equalTo(FIRST_NAME1)))
               .andExpect(jsonPath("$.last_name", equalTo(LAST_NAME1)))
               .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMERS_URI_SLASH + ID1)));
    }

    @Test
    void testCreate() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME1);
        customerDTO1.setLastName(LAST_NAME1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(ID1);
        customerDTO2.setFirstName(FIRST_NAME1);
        customerDTO2.setLastName(LAST_NAME1);
        customerDTO2.setCustomerUrl(CUSTOMERS_URI_SLASH + ID1);

        when(customerService.save(any(CustomerDTO.class))).thenReturn(customerDTO2);

        mockMvc.perform(
            post(CUSTOMERS_URI).contentType(MediaType.APPLICATION_JSON)
                               .content(asJsonString(customerDTO1))
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.first_name", equalTo(FIRST_NAME1)))
               .andExpect(jsonPath("$.last_name", equalTo(LAST_NAME1)))
               .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMERS_URI_SLASH + ID1)));
    }

    @Test
    void testPut() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME1);
        customerDTO1.setLastName(LAST_NAME1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(ID1);
        customerDTO2.setFirstName(FIRST_NAME1);
        customerDTO2.setLastName(LAST_NAME1);
        customerDTO2.setCustomerUrl(CUSTOMERS_URI_SLASH + ID1);

        when(customerService.put(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO2);

        mockMvc.perform(
            put(CUSTOMERS_URI_SLASH + ID1).contentType(MediaType.APPLICATION_JSON)
                                          .content(asJsonString(customerDTO1))
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.first_name", equalTo(FIRST_NAME1)))
               .andExpect(jsonPath("$.last_name", equalTo(LAST_NAME1)))
               .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMERS_URI_SLASH + ID1)));
    }

    @Test
    void testPatch() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(ID1);
        customerDTO2.setFirstName(FIRST_NAME1);
        customerDTO2.setLastName(LAST_NAME1);
        customerDTO2.setCustomerUrl(CUSTOMERS_URI_SLASH + ID1);

        when(customerService.patch(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO2);

        mockMvc.perform(
            patch(CUSTOMERS_URI_SLASH + ID1).contentType(MediaType.APPLICATION_JSON)
                                            .content(asJsonString(customerDTO1))
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.first_name", equalTo(FIRST_NAME1)))
               .andExpect(jsonPath("$.last_name", equalTo(LAST_NAME1)))
               .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMERS_URI_SLASH + ID1)));
    }

    @Test
    void testDestroy() throws Exception {
        mockMvc.perform(delete(CUSTOMERS_URI_SLASH + ID1)).andExpect(status().isOk());

        verify(customerService).delete(anyLong());
    }

}
