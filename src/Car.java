class Car {
    private boolean engineOn;
    private int gear;
    private int speed;
    private int engineRPM;
    private final boolean engineBlocked;
    private int fuelLevel;

    private static final int MAX_GEARS = 6;
    private static final int MAX_SPEED = 220;
    private static final int MAX_ENGINE_RPM = 7000;

    public Car() {
        engineOn = false;
        gear = 0;
        speed = 0;
        engineRPM = 0;
        engineBlocked = false;
        fuelLevel = 100;
    }
    public boolean isEngineOn() {
        return engineOn;
    }

    public void turnOnEngine() {
        if (engineOn) {
            System.out.println("\u001B[32mSilnik jest już włączony.\u001B[0m");
        } else {
            engineOn = true;
            System.out.println("\u001B[32mSilnik został włączony.\u001B[0m");
        }
    }

    public void turnOffEngine() {
        if (engineOn) {
            engineOn = false;
            System.out.println("Silnik został wyłączony.");
        } else {
            System.out.println("Silnik jest już wyłączony.");
        }
    }

    public void changeGear(int gear) {
        if (engineOn) {
            if (gear >= 0 && gear <= MAX_GEARS) {
                this.gear = gear;
                System.out.println("Zmieniono bieg na: " + gear);
            } else {
                System.out.println("Nieprawidłowy numer biegu.");
            }
        } else {
            System.out.println("Nie można zmienić biegu. Silnik jest wyłączony.");
        }
    }

    public void accelerate(int speedIncrease) {
        if (engineOn) {
            if (fuelLevel > 0) {
                if (gear == 0) {
                    System.out.println("Nie można zwiększyć prędkości na biegu 0.");
                } else if (gear > 3 && speed == 0) {
                    System.out.println("Nie można przyspieszać z biegu wyższego niż 3 i prędkością początkową 0.");
                    turnOffEngine();
                } else {
                    int newSpeed = speed + speedIncrease;
                    if (newSpeed <= MAX_SPEED) {
                        speed = newSpeed;
                        engineRPM = speed * 100;
                        if (engineRPM > MAX_ENGINE_RPM) {
                            engineRPM = MAX_ENGINE_RPM;
                        }
                        System.out.println("Przyśpieszono do: " + speed + " km/h");
                        fuelLevel -= speedIncrease / 10;
                        if (fuelLevel < 0) {
                            fuelLevel = 0;
                        }
                    } else {
                        System.out.println("Przekroczono maksymalną prędkość.");
                    }
                }
            } else {
                System.out.println("Nie można przyśpieszyć. Brak paliwa.");
            }
        } else {
            System.out.println("Nie można przyśpieszyć. Silnik jest wyłączony.");
        }
    }

    public void brake(int speedDecrease) {
        if (engineOn) {
            if (speed > 0) {
                int newSpeed = speed - speedDecrease;
                if (newSpeed >= 0) {
                    speed = newSpeed;
                    engineRPM = speed * 100;
                    if (engineRPM > MAX_ENGINE_RPM) {
                        engineRPM = MAX_ENGINE_RPM;
                    }
                    System.out.println("Zhamowano do: " + speed + " km/h");
                } else {
                    speed = 0;
                    engineRPM = 0;
                    System.out.println("Samochód zatrzymany.");
                }
            } else {
                System.out.println("Nie można zahamować. Prędkość wynosi 0 km/h.");
            }
        } else {
            System.out.println("Nie można zahamować. Silnik jest wyłączony.");
        }
    }



    public void printCarStatus() {
        System.out.println("\n\u001B[34m=====================\u001B[0m");
        System.out.println("   Dane samochodu");
        System.out.println("\u001B[34m=====================\u001B[0m");
        System.out.println("Marka/Model: Honda/Civic");
        System.out.println("Skrzynia biegów: 6- biegowa");
        System.out.println("Predkość max.: 220km/h");
        System.out.println("Status silnika: " + (engineOn ? "\u001B[32mWłączony\u001B[0m" : "Wyłączony"));
        System.out.println("Bieg: " + gear);
        System.out.println("Prędkość: " + speed + " km/h");
        System.out.println("Obroty silnika: " + engineRPM);
        System.out.println("Poziom paliwa: " + fuelLevel + "%");
        System.out.println("Silnik zablokowany: " + (engineBlocked ? "Tak" : "Nie"));
    }



    public void saveData(DatabaseConnector connector) {
        connector.saveCarData(fuelLevel, speed, engineRPM);
    }
}