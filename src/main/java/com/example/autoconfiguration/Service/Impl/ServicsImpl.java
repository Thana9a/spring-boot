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

    @Override
    public ClubsDto findClubById(long clubId) {
        Clubs club = clubRepository.findById(clubId).get();
        return mapToDtoClub(club);
    }

    @Override
    public void updateClub(ClubsDto clubDto) {
        Clubs club = clubRepository.findById(clubDto.getId()).orElseThrow(() -> new RuntimeException("Club not found"));
        club.setTitle(clubDto.getTitle());
        club.setDescription(clubDto.getDescription());
        club.setEmail(clubDto.getEmail());
        clubRepository.save(club);
    }

    private Clubs mapToClub(ClubsDto clubDto) {
        return new Clubs(
                clubDto.getId(),
                clubDto.getTitle(),
                clubDto.getDescription(),
                clubDto.getEmail(),
                clubDto.getUpdatedOn(),
                clubDto.getCreatedOn()
        );
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