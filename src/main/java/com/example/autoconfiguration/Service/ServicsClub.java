package com.example.autoconfiguration.Service;

import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.dto.ClubsDto;

import java.util.List;


public interface ServicsClub {
        List<ClubsDto> findAllClubs();
}