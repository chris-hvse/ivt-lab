package hu.bme.mit.spaceship;


public class LaserStore {
  private int laserCount = 0;

  public LaserStore(int numberOfLasers) {
    laserCount = numberOfLasers;
  }

  public boolean fire(int numberOfLasers) {
    if(numberOfLasers < 1 || numberOfLasers > this.laserCount){
      throw new IllegalArgumentException("numberOfTorpedos");
    }

    this.laserCount -= numberOfLasers;

    return true;
  }

  public boolean isEmpty() {
    return this.laserCount <= 0;
  }

  public int getLaserCount() {
    return this.laserCount;
  }

}
