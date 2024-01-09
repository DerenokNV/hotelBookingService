package com.example.hotelbookingservice;

import com.example.hotelbookingservice.statistics.service.Toolkit;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class DaemonThread extends Thread {

  @Override
  public void run() {
    File file = null;
    try {
      file = new File( getClass().getClassLoader().getResource( Toolkit.NAME_PATH ).toURI() );
    } catch ( URISyntaxException e ) {
      Thread.currentThread().interrupt();
      throw new RuntimeException( e );
    }

    while ( true ) {

      File[] contents =  file.listFiles();
      if ( Objects.nonNull( contents )  ) {
        for ( File content : contents ) {
          content.delete();
        }
      }

      try {
        TimeUnit.SECONDS.sleep(5 );
      } catch ( InterruptedException e ) {
        Thread.currentThread().interrupt();
        e.printStackTrace();
      }
    }
  }

}
