package com.example.hotelbookingservice.statistics.service;

import com.example.hotelbookingservice.statistics.dto.BookingEvent;
import com.example.hotelbookingservice.statistics.dto.UserEvent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;

public class Toolkit {

  public static String NAME_PATH = "templates";

  public static boolean createFileStatInfo( String fileName, List<UserEvent> allUsers, List<BookingEvent> allBookings, ClassLoader classLoader ) {
    if ( ! new File( NAME_PATH ).exists() ) {
      try {
        Files.createDirectories( Paths.get( NAME_PATH ) );
      } catch ( IOException ex ) {
        return false;
      }
    }

    try {
      File file = new File(classLoader.getResource( NAME_PATH ).getFile() + fileName );
      file.createNewFile();

      FileWriter outFile = new FileWriter( file );
      if ( allUsers != null && ! allUsers.isEmpty() ) {
        addFileInfoUser( outFile, allUsers );
      }

      if ( allBookings != null && ! allBookings.isEmpty() ) {
        addFileInfoBooking( outFile, allBookings );
      }

      outFile.close();
    } catch ( IOException ex ) {
      return false;
    }
    return true;
  }

  private static void addFileInfoUser( FileWriter outFile, List<UserEvent> allUsers ) throws IOException {
    outFile.write( "Зарегистрированные пользователи: \n"  );

    for ( UserEvent user : allUsers ) {
      outFile.write( "Пользователь с ID: " + user.getUserId() + " \n" );
    }
  }

  private static void addFileInfoBooking( FileWriter outFile, List<BookingEvent> allBookings ) throws IOException {
    outFile.write( "Регистрация комнат: \n"  );

    for ( BookingEvent booking : allBookings ) {
      outFile.write( MessageFormat.format( "Комната с ID: {0}, зарегистрирована пользователем с ID: {1}, на даты с {2} по {3} ",
                                           booking.getRoomId(),
                                            booking.getUserId(),
                                            booking.getDtBegin(),
                                            booking.getDtEnd() + "\n" ) );
    }
  }
}
