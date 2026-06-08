package com.example.autoconfiguration.Controller;


import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.Service.ServicsClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.autoconfiguration.dto.ClubsDto;


import java.util.List;

@Controller
public class ClubsController {

    private ServicsClub servicsClub;

//    contstructor
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
}
