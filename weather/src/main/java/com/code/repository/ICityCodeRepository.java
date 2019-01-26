package com.code.repository;


import com.code.model.po.CityCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityCodeRepository extends JpaRepository<CityCode, Integer> {

    @Query(value = "select code from city_code where city_name=:cityName limit 1",nativeQuery = true)
    String findByCityName(@Param("cityName") String cityName);

}
