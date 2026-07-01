package com.example.autoconfiguration.Controller;

import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.Service.ServicsClub;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.autoconfiguration.dto.ClubsDto;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubsController {

    private ServicsClub servicsClub;

    // contstructor
    public ClubsController(ServicsClub servicsClub) {
        this.servicsClub = servicsClub;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubsDto> clubs = servicsClub.findAllClubs();// service
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

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubsDto club = servicsClub.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs.edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
            @ModelAttribute("club") ClubsDto club) {
        club.setId(clubId);
        servicsClub.updateClub(club);
        return "redirect:/clubs";
    }

    // list detail
    @GetMapping("/club/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId, Model model) {
        ClubsDto club = servicsClub.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs.detail";
    }

    // Delete
    @GetMapping("/club/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        servicsClub.deleteClub(clubId);
        return "redirect:/clubs";
    }

}
