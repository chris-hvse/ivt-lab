package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  @Before
  public void init() {
    primaryTorpedoStore = mock(TorpedoStore.class);
    secondaryTorpedoStore = mock(TorpedoStore.class);

    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
    verify(this.secondaryTorpedoStore, times(1)).fire(1);
  }


  // Leírás alapján definiált tesztek
  @Test
  public void fireTorpedo_First_Is_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.primaryTorpedoStore, times(0)).fire(1);
    verify(this.secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Second_Is_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_First_Fails(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(false);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Fire_Both_Twice(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    // Act
    result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(this.primaryTorpedoStore, times(2)).fire(1);
    verify(this.secondaryTorpedoStore, times(2)).fire(1);
  }

  // Forráskód alapján definiált tesztek
  @Test
  public void fireTorpedo_All_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(0)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  // Kiegészítés
  @Test
  public void fireTorpedo_Fire_Both_Second_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    // Act
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.primaryTorpedoStore, times(2)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Fire_Both_Second_Not_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    // Act
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
    verify(this.secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Fire_Both_First_Become_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    when(this.primaryTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Both_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(0)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_First_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(0)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Second_Empty(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(0)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_First_Fails(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(false);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
    verify(this.secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Second_Fails(){
    // Arrange
    when(this.primaryTorpedoStore.fire(1)).thenReturn(true);
    when(this.secondaryTorpedoStore.fire(1)).thenReturn(false);
    when(this.primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(this.secondaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(this.primaryTorpedoStore, times(1)).fire(1);
    verify(this.secondaryTorpedoStore, times(1)).fire(1);
  }
}
