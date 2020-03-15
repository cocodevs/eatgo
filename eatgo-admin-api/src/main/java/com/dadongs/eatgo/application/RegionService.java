package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.Region;
import com.dadongs.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    public Region addRegion(String name){
        Region region = Region.builder().name(name).build();
        regionRepository.save(region);
        return region;
    }
}
