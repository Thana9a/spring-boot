package com.example.autoconfiguration.Controller;

import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.Service.ServicsClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.autoconfiguration.dto.ClubsDto;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubsController {

    private ServicsClub servicsClub;

    // contstructor
    @Autowired
    public ClubsController(ServicsClub servicsClub) {
        this.servicsClub = servicsClub;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubsDto> clubs = servicsClub.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs";
    }

    // CURD
    // Create

    @GetMapping("/clubs/new")
    public String createClub(Model model) {
        Clubs club = new Clubs();
        model.addAttribute("club", club);
        return "clubs.create";
    }

    @PostMapping("/clubs/new")
    public String createClub(@ModelAttribute("club") Clubs club) {

        servicsClub.saveClub(club);

        return "redirect:/clubs";
    }
}
