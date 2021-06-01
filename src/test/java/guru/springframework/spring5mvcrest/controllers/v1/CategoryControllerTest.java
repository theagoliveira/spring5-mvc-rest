package guru.springframework.spring5mvcrest.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.services.CategoryService;

class CategoryControllerTest {

    private static final Long ID1 = 1L;
    private static final Long ID2 = 2L;
    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void testIndex() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(ID1);
        categoryDTO1.setName(NAME1);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(ID2);
        categoryDTO2.setName(NAME2);

        List<CategoryDTO> categoryDTOs = Arrays.asList(categoryDTO1, categoryDTO2);

        when(categoryService.findAll()).thenReturn(categoryDTOs);

        mockMvc.perform(get("/api/v1/categories").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    void testShowByName() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(ID1);
        categoryDTO1.setName(NAME1);

        when(categoryService.findByName(anyString())).thenReturn(categoryDTO1);

        mockMvc.perform(
            get("/api/v1/categories/name/" + NAME1).contentType(MediaType.APPLICATION_JSON)
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.name", equalTo(NAME1)));
    }

    @Test
    void testShowById() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(ID1);
        categoryDTO1.setName(NAME1);

        when(categoryService.findById(anyLong())).thenReturn(categoryDTO1);

        mockMvc.perform(get("/api/v1/categories/" + ID1).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
               .andExpect(jsonPath("$.name", equalTo(NAME1)));
    }

}
