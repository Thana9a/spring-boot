package com.example.autoconfiguration.Service.Impl;

import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.Repository.ClubRepository;
import com.example.autoconfiguration.Service.ServicsClub;
import com.example.autoconfiguration.dto.ClubsDto;
// import org.springframework.beans.factory.annotation.Autowired;
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

    // find clubs by id
    @Override
    public ClubsDto findClubById(long clubId) {
        Clubs club = clubRepository.findById(clubId).get();
        return mapToDtoClub(club);
    }

    // and then for update club after have id we need update it
    @Override
    public void updateClub(ClubsDto clubDto) {
        Clubs club = clubRepository.findById(clubDto.getId()).orElseThrow(() -> new RuntimeException("Club not found"));
        club.setTitle(clubDto.getTitle());
        club.setDescription(clubDto.getDescription());
        club.setEmail(clubDto.getEmail());
        clubRepository.save(club);
    }

    // it for mapClubsDtoClubs to Clubs it mean we create a new box for Clubs and
    // put data from ClubDto to Clubs
    // private Clubs mapToClub(ClubsDto clubDto) {
    // return new Clubs(
    // clubDto.getId(),
    // clubDto.getTitle(),
    // clubDto.getDescription(),
    // clubDto.getEmail(),
    // clubDto.getUpdatedOn(),
    // clubDto.getCreatedOn());
    // }

    // delete
    @Override
    public String deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
        return "redirect:/clubs";
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