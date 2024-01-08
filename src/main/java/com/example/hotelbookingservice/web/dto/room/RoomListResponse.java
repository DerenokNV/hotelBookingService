package com.example.hotelbookingservice.web.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomListResponse {

  private List<RoomResponse> roomAll;
}
