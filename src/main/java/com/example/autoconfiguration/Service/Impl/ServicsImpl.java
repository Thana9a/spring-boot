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

    // main of this file
    // retrive all data on database one by one and change to dto and then sent to
    // client by controller
    @Override
    public List<ClubsDto> findAllClubs() {
        List<Clubs> clubs = clubRepository.findAll();
        return clubs.stream()
                .map(this::mapToDtoClub)
                .collect(Collectors.toList());
    }

    // this create for create new clubs in db and save on db
    @Override
    public Clubs saveClub(Clubs club) {// Clubs club it is Dependency Injection it like create a new box for ervery
                                       // field to box (clubs) not
        return clubRepository.save(club);//
    }

    @Override
    public ClubsDto findClubById(long clubId) {
        Clubs club = clubRepository.findById(clubId).get();
        return mapToDtoClub(club);
    }

    @Override
    public ClubsDto findClubsById(Long clubId) {
        return findClubById(clubId);
    }

    @Override
    public String deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
        return "redirect:/clubs";
    }

    @Override
    public String validation(ClubsDto clubDto) {
        if (clubDto.getTitle() == null || clubDto.getTitle().isEmpty()) {
            return "Title is required";
        }
        if (clubDto.getDescription() == null || clubDto.getDescription().isEmpty()) {
            return "Description is required";
        }
        if (clubDto.getEmail() == null || clubDto.getEmail().isEmpty()) {
            return "Email is required";
        }
        return null;
    }

    @Override
    public void updateClub(ClubsDto clubDto) {
        Clubs club = clubRepository.findById(clubDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid club Id:" + clubDto.getId()));
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
                clubDto.getCreatedOn());
    }

    // it for mapClubs to ClubsDto it mean we create a new box for ClubsDto and put
    // data from Clubs to ClubsDto
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