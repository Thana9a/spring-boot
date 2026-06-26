package com.example.autoconfiguration.Service.Impl;

import com.example.autoconfiguration.Model.Clubs;
import com.example.autoconfiguration.Repository.ClubRepository;
import com.example.autoconfiguration.Service.ServicsClub;
import com.example.autoconfiguration.dto.ClubsDto;
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
        List<Clubs> clubs = clubRepository.findAll();// create obj array list(emty data) and then go run build in
                                                     // methods to clubRepo findall() and then it assign to obj
        return clubs.stream()// return
                .map(this::mapToDtoClub)
                .collect(Collectors.toList());
    }

    @Override
    public Clubs saveClub(Clubs club) {
        return clubRepository.save(club);
    }

    @Override // this path is implement about the service here in kitchen have chef ready to
              // cook for the meun
    public ClubsDto findClubsById(Long clubId) {// here what it will be to retrive the data from database and it go to
                                                // DTO
        Clubs club = clubRepository.findById(clubId).get();
        return mapToDtoClub(club);
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

    //
    @Override
    public void updateClub(ClubsDto clubDto) {
        Clubs club = clubRepository.findById(clubDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid club Id:" + clubDto.getId()));
        club.setTitle(clubDto.getTitle());
        club.setDescription(clubDto.getDescription());
        club.setEmail(clubDto.getEmail());
        clubRepository.save(club);
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