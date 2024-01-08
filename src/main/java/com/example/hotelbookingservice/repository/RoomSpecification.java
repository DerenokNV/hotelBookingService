package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.web.dto.room.RoomFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface RoomSpecification {

  static Specification<Room> withFilter( RoomFilterRequest roomFilter ) {
    return Specification.where( byId( roomFilter.getId() ) )
                          .and( byName( roomFilter.getName() ) )
                          .and( byMaxPeople( roomFilter.getMaxPeople() ) )
                          .and( byPeriodCost( roomFilter.getMinCost(), roomFilter.getMaxCost() ) )
                          .and( byHotelId( roomFilter.getHotelId() ) );
  }

  static Specification<Room> byId( Long id ) {
    return ( root, query, cb ) -> {
      if ( id == null ) {
        return null;
      }

      return cb.equal( root.get( "id" ), id );
    };
  }

  static Specification<Room> byName( String name ) {
    return ( root, query, cb ) -> {
      if ( name == null ) {
        return null;
      }

      return cb.equal( root.get( "name" ), name );
    };
  }

  static Specification<Room> byMaxPeople( Long maxPeople ) {
    return ( root, query, cb ) -> {
      if ( maxPeople == null ) {
        return null;
      }

      return cb.lessThanOrEqualTo( root.get( "maxPeople" ), maxPeople );
    };
  }

  static Specification<Room> byPeriodCost( Long minCost, Long maxCost ) {
    return ( root, query, cb ) -> {
      if ( minCost == null || maxCost == null ) {
        return null;
      }

      if ( minCost == null ) {
        return cb.lessThanOrEqualTo( root.get( "price" ), maxCost );
      }

      if ( maxCost == null ) {
        return cb.greaterThanOrEqualTo( root.get( "price" ), minCost );
      }

      return cb.between( root.get( "price" ), minCost, maxCost );
    };
  }

  static Specification<Room> byHotelId( Long hotelId ) {
    return ( root, query, cb ) -> {
      if ( hotelId == null ) {
        return null;
      }

      return cb.equal( root.get( "hotel" ).get( "id" ), hotelId );
    };
  }

}
