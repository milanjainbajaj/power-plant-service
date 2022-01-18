package com.mjb.power.controller;

import com.mjb.power.db.dto.PlantDto;
import com.mjb.power.db.repository.PlantRepository;
import com.mjb.power.helper.PlantDtoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PlantController.class)
public class PlantControllerTest {
    @MockBean
    PlantRepository plantRepository;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @WithMockUser(value = "spring")
    public void testFindAll() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        PlantDtoImpl plantDto = new PlantDtoImpl();
        List<PlantDto> plantDtoList = new ArrayList<>();
        plantDtoList.add(plantDto);
        Page<PlantDto> plantDtoPage = new PageImpl<>(plantDtoList);
        when(plantRepository.findPlant(null, null, pageable))
                .thenReturn(plantDtoPage);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/plant"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @WithMockUser(value = "spring")
    public void testFindByLocation() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        PlantDtoImpl plantDto = new PlantDtoImpl();
        List<PlantDto> plantDtoList = new ArrayList<>();
        plantDtoList.add(plantDto);
        Page<PlantDto> plantDtoPage = new PageImpl<>(plantDtoList);
        when(plantRepository.findPlant("AK", null, pageable))
                .thenReturn(plantDtoPage);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/plant/search").param("location", "AK"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @WithMockUser(value = "spring")
    public void testFindByPlant() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        PlantDtoImpl plantDto = new PlantDtoImpl();
        List<PlantDto> plantDtoList = new ArrayList<>();
        plantDtoList.add(plantDto);
        Page<PlantDto> plantDtoPage = new PageImpl<>(plantDtoList);
        when(plantRepository.findPlant(null, "Testing", pageable))
                .thenReturn(plantDtoPage);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/plant/search").param("name", "Testing"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @WithMockUser(value = "spring")
    public void testFindAllNoData() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        List<PlantDto> plantDtoList = new ArrayList<>();
        Page<PlantDto> plantDtoPage = new PageImpl<>(plantDtoList);
        when(plantRepository.findPlant(null, null, pageable))
                .thenReturn(plantDtoPage);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/plant"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
