package com.example.autoconfiguration.Service;

import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.dto.ClubsDto;

import java.util.List;

public interface ServicsClub {
        List<ClubsDto> findAllClubs();

        Clubs saveClub(Clubs club);

        ClubsDto findClubsById(Long clubId);// it like a new meun of restarence but it can't it just image show about
                                            // the food we have

        void updateClub(ClubsDto club);

        String validation(ClubsDto club);
}