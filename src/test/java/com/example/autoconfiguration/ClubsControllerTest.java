package com.example.autoconfiguration;

import com.example.autoconfiguration.Controller.ClubsController;
import com.example.autoconfiguration.dto.ClubsDto;
import com.example.autoconfiguration.Service.ServicsClub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClubsControllerTest {

    private MockMvc mockMvc;
    private ServicsClub servicsClub;
    private ClubsController clubsController;

    @BeforeEach
    public void setup() {
        servicsClub = Mockito.mock(ServicsClub.class);
        clubsController = new ClubsController(servicsClub);
        mockMvc = MockMvcBuilders.standaloneSetup(clubsController).build();
    }

    @Test
    public void testUpdateClubBackend() throws Exception {
        // Perform POST request to edit/update endpoint, mimicking form submission
        mockMvc.perform(post("/clubs/1/edit")
                .param("title", "New Title")
                .param("email", "new@email.com")
                .param("description", "New Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clubs"));

        // Verify the service update method was called
        verify(servicsClub).updateClub(any(ClubsDto.class));
    }
}
