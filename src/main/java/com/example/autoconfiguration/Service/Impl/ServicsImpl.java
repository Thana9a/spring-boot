package com.example.autoconfiguration.Service.Impl;

import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.Repository.ClubRepository;
import com.example.autoconfiguration.Service.ServicsClub;
import com.example.autoconfiguration.dto.ClubsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicsImpl implements ServicsClub {

    private final ClubRepository clubRepository;
    

    public ServicsImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubsDto> findAllClubs() {
        List<Clubs > clubs = clubRepository.findAll();
        return clubs.stream()
                .map(this::mapToDtoClub)
                .collect(Collectors.toList());
    }

    @Override
    public Clubs saveClub(Clubs club) {
        return clubRepository.save(club);
    }

    private ClubsDto mapToDtoClub(Clubs club) {
        return ClubsDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .description(club.getDescription())
                .email(club.getEmail())
                .CreatedOn(club.getCreatedOn())
                .UpdatedOn(club.getUpdatedOn())
                .build();

    }
}