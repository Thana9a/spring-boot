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
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

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
    public String createClub(@ModelAttribute("club") Clubs club, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs.create";
        }
        servicsClub.saveClub(club);

        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit") // the for the point ទទួល request form cline to server have customer service to
                                        // chef
    // ready to cook for the meun give it to the waiter and the waiter give it to
    // the customer
    public String editClubsById(@PathVariable("clubId") long clubId, Model model) {// if on link is have the clubid =5.
                                                                                   // it hold the number on hand
                                                                                   // ("clubId") long clubId = 5
        // it have a emty bag (only field not data)
        ClubsDto club = servicsClub.findClubsById(clubId);// the hand have number 5 it go to service and service go to
                                                          // retrive the data from database with id =5
        // it create a obj is ClubsDto with name club and value is the data from
        // database but you know DTO dont have full field to show to user so it show you
        // want to show to user
        model.addAttribute("club", club);// assign the club(box) to the model(bag)
        return "clubs.edit";// take the model or bag to viwe or html
    }

    @PostMapping("/clubs/{clubId}/edit") // when the user click on the button it will go to this path
    public String updateClub(@PathVariable("clubId") Long clubId, // hold the id become with the Link
            @Valid @ModelAttribute("club") ClubsDto clubDto, // take the box become with data from form and then put it
                                                             // to the newbox Object ClubsDto
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs.edit";
        }
        clubDto.setId(clubId);// take the id (id = 5) and casuse some time on the form it don't have id it
                              // will be null
        servicsClub.updateClub(clubDto);
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
