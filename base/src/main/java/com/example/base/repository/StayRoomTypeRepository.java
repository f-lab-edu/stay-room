package com.example.base.repository;

import com.example.base.entity.StayRoomType;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StayRoomTypeRepository extends JpaRepository<StayRoomType, Long> {
  @Query("select s.roomType from StayRoomType s where s.stayInfo.id = :stayId and s.roomType in :roomTypes")
  List<String> findExistingRoomTypes(@Param("stayId") Long stayId, @Param("roomTypes") List<String> roomTypes);

}
