package com.example.api.service;

import com.example.api.service.dto.ReqRegistRoomDetailDTO;
import com.example.api.service.dto.ReqRegistStayInfoDTO;
import com.example.api.service.dto.ReqRoomNumberDTO;
import com.example.base.entity.Seller;
import com.example.base.entity.StayInfo;
import com.example.base.entity.StayRoom;
import com.example.base.entity.StayRoomType;
import com.example.base.enums.ErrorType;
import com.example.base.exception.CommonException;
import com.example.base.repository.SellerRepository;
import com.example.base.repository.StayInfoRepository;
import com.example.base.repository.StayRoomRepository;
import com.example.base.repository.StayRoomTypeRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StayService {

  private final StayInfoRepository stayInfoRepository;
  private final SellerRepository sellerRepository;
  private final StayRoomTypeRepository stayRoomTypeRepository;
  private final StayRoomRepository stayRoomRepository;

  public void registStayInfo(ReqRegistStayInfoDTO req) {
    // 등록된 판매자인지 확인
    Seller seller = sellerRepository.findById(req.sellerId()).orElseThrow(() -> new CommonException(
        ErrorType.BAD_REQUSET_VALUE));

    // 숙소 등록
    StayInfo stayInfo = StayInfo.builder()
        .name(req.name())
        .location(req.location())
        .description(req.description())
        .seller(seller)
        .stayType(req.stayType())
        .build();

    stayInfoRepository.save(stayInfo);
  }

  @Transactional
  public void registStayRoomDetail(Long stayInfoId, List<ReqRegistRoomDetailDTO> req) {
    // 등록된 숙소인지 확인
    StayInfo stayInfo = stayInfoRepository.findById(stayInfoId).orElseThrow(() -> new CommonException(ErrorType.BAD_REQUSET_VALUE));

    // room type이 중복되는지 확인, 중복되면 예외처리
    // req에서 roomType 추출하여 리스트로 생성
    List<String> requestRoomTypes = req.stream()
        .map(ReqRegistRoomDetailDTO::roomType)
        .toList();

    // requestRoomTypes의 roomType으로 리스트 조회
    List<String> existingRoomTypes = stayRoomTypeRepository.findExistingRoomTypes(stayInfo.getId(),requestRoomTypes);
    // 조회된 결과가 있을 경우 예외처리
    if (!existingRoomTypes.isEmpty()) {
      throw new CommonException(ErrorType.DUPLICATE_VALUE);
    }
    // 숙소 타입 리스트 먼저 등록
    List<StayRoomType> roomTypes = req.stream()
        .map(dto -> {
          return StayRoomType.builder()
              .stayInfo(stayInfo)
              .roomType(dto.roomType())
              .count(dto.count())
              .price(dto.price())
              .build();
        })
        .toList();

    stayRoomTypeRepository.saveAll(roomTypes);
    log.info("roomType list === > {}", roomTypes);

    // 실제 방 리스트 등록
    // save된 roomTypes List의 id들을 가져오기 위해 list -> map 형태로 저장하기
    // roomTypeMap의 key는 roomType, value는 roomType 객체 자체를 저장하도록 함
    Map<String, StayRoomType> roomTypeMap = roomTypes.stream()
        .collect(Collectors.toMap(StayRoomType::getRoomType, roomTypeEntity -> roomTypeEntity));

    List<StayRoom> roomList = new ArrayList<>();

    for (ReqRegistRoomDetailDTO dto : req) {
      // roomType 객체 추출
      StayRoomType roomType = roomTypeMap.get(dto.roomType());

      for (ReqRoomNumberDTO roomDto : dto.roomNumberList()) {
        StayRoom room = StayRoom.builder()
            .stayRoomType(roomType)
            .roomNumber(roomDto.roomNumber())
            .build();
        roomList.add(room);
      }
    }

    stayRoomRepository.saveAll(roomList);
  }

}
