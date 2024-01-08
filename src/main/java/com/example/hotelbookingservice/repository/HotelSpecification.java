package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.web.dto.hotel.HotelFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {

  static Specification<Hotel> withFilter( HotelFilterRequest newsFilter ) {
    return Specification.where( byId( newsFilter.getId() ) )
                          .and( byName( newsFilter.getName() ) )
                          .and( byHeadline( newsFilter.getHeadline() ) )
                          .and( byCity( newsFilter.getCity() ) )
                          .and( byAddress( newsFilter.getAddress() ) )
                          .and( byDistance( newsFilter.getDistance() ) )
                          .and( byRating( newsFilter.getRating() ) )
                          .and( byCountRating( newsFilter.getCountRating() ) );
  }

  static Specification<Hotel> byId( Long id ) {
    return ( root, query, cb ) -> {
      if ( id == null ) {
        return null;
      }

      return cb.equal( root.get( "id" ), id );
    };
  }

  static Specification<Hotel> byName( String name ) {
    return ( root, query, cb ) -> {
      if ( name == null ) {
        return null;
      }

      return cb.equal( root.get( "name" ), name );
    };
  }

  static Specification<Hotel> byHeadline( String headline ) {
    return ( root, query, cb ) -> {
      if ( headline == null ) {
        return null;
      }

      return cb.equal( root.get( "headline" ), headline );
    };
  }

  static Specification<Hotel> byCity( String city ) {
    return ( root, query, cb ) -> {
      if ( city == null ) {
        return null;
      }

      return cb.equal( root.get( "city" ), city );
    };
  }

  static Specification<Hotel> byAddress( String address ) {
    return ( root, query, cb ) -> {
      if ( address == null ) {
        return null;
      }

      return cb.equal( root.get( "address" ), address );
    };
  }

  static Specification<Hotel> byDistance( Long distance ) {
    return ( root, query, cb ) -> {
      if ( distance == null ) {
        return null;
      }

      return cb.equal( root.get( "distance" ), distance );
    };
  }

  static Specification<Hotel> byRating( Double rating ) {
    return ( root, query, cb ) -> {
      if ( rating == null ) {
        return null;
      }

      return cb.equal( root.get( "rating" ), rating );
    };
  }

  static Specification<Hotel> byCountRating( Long countRating ) {
    return ( root, query, cb ) -> {
      if ( countRating == null ) {
        return null;
      }

      return cb.equal( root.get( "countRating" ), countRating );
    };
  }


}
