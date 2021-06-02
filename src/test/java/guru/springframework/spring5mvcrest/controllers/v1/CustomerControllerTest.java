package guru.springframework.spring5mvcrest.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        customerDTO1.setCustomerUrl("/api/v1/customers/" + ID1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(ID2);
        customerDTO2.setFirstName(FIRST_NAME2);
        customerDTO2.setLastName(LAST_NAME2);
        customerDTO2.setCustomerUrl("/api/v1/customers/" + ID2);

        List<CustomerDTO> customerDTOs = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.findAll()).thenReturn(customerDTOs);

        mockMvc.perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void testShowById() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(ID1);
        customerDTO1.setFirstName(FIRST_NAME1);
        customerDTO1.setLastName(LAST_NAME1);
        customerDTO1.setCustomerUrl("/api/v1/customers/" + ID1);

        when(customerService.findById(anyLong())).thenReturn(customerDTO1);

        mockMvc.perform(get("/api/v1/customers/" + ID1).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.first_name", equalTo(FIRST_NAME1)))
               .andExpect(jsonPath("$.last_name", equalTo(LAST_NAME1)))
               .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/" + ID1)));
    }

}
