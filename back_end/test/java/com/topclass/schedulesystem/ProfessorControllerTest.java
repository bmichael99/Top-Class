package com.topclass.schedulesystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topclass.schedulesystem.controller.ProfessorController;
import com.topclass.schedulesystem.model.Professor;
import com.topclass.schedulesystem.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProfessorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfessorService professorService;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }


    @Test
    public void testGetAllProfessors() throws Exception {
        List<Professor> professors = Arrays.asList(new Professor(), new Professor());
        when(professorService.getAllProfessors()).thenReturn(professors);

        mockMvc.perform(get("/professor/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(professors.size()));
    }

    @Test
    public void testGetClassesNotTaken() throws Exception {
        List<String> notTakenClasses = new ArrayList<>();
        notTakenClasses.add("CS 310");
        notTakenClasses.add("CS 370");

        List<Professor> professors = Arrays.asList(new Professor(), new Professor());
        when(professorService.getClassesNotTaken(notTakenClasses)).thenReturn(professors);

        mockMvc.perform(get("/professor/getClassesNotTaken")
                        .param("notTaken", "CS 310", "CS 370"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(professors.size()));
    }

    /*@Test
    public void testDeleteAllClasses() throws Exception {
        mockMvc.perform(delete("/professor/deleteAllClasses"))
                .andExpect(status().isOk())
                .andExpect(content().string("All classes are deleted"));

        verify(professorService, times(1)).deleteAllProfessors();
    }*/
}
