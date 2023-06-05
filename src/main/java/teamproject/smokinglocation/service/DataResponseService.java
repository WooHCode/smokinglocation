package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.entity.*;
import teamproject.smokinglocation.repository.TotalDataRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
@EnableCaching
public class DataResponseService {
    private final TotalDataRepository dataRepository;
    private final CacheManager cacheManager;

    @Cacheable("totalDataCache")
    public List<TotalData> getTotalData() {
        return dataRepository.findAll();
    }

    @CacheEvict("totalDataCache")
    public void deleteTotalDataCache() {
        Cache cache = cacheManager.getCache("totalDataCache");
        if (cache != null) {
            cache.clear();
        }
    }
}
