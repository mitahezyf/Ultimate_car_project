
import java.util.Scanner;

class Menu {
    private final Car car;
    private final DatabaseConnector databaseConnector;
    private final Scanner scanner;
    private final ProgramTerminator programTerminator;

    public Menu() {
        car = new Car();
        scanner = new Scanner(System.in);
        databaseConnector = new DatabaseConnector("car_data.txt");
        programTerminator = new ProgramTerminator();
    }

    public void displayMenu() {
        int choice = 0;

        while (choice != 7) {
            System.out.println("=====================");
            System.out.println("       Menu");
            System.out.println("=====================");
            if (car.isEngineOn()) {
                System.out.println("\u001B[32m1. Włącz silnik\u001B[0m");
            } else {
                System.out.println("1. Włącz silnik");
            }
            System.out.println("2. Wyłącz silnik");
            System.out.println("3. Zmień bieg");
            System.out.println("4. Przyśpiesz");
            System.out.println("5. Zhamuj");
            System.out.println("6. Wyświetl status samochodu");
            System.out.println("7. Wyjście");

            System.out.print("Wybierz opcję: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> car.turnOnEngine();
                case 2 -> car.turnOffEngine();
                case 3 -> {
                    System.out.print("Podaj numer biegu: ");
                    int gear = scanner.nextInt();
                    scanner.nextLine();
                    car.changeGear(gear);
                }
                case 4 -> {
                    System.out.print("Podaj wartość przyspieszenia: ");
                    int speedIncrease = scanner.nextInt();
                    scanner.nextLine();
                    car.accelerate(speedIncrease);
                }
                case 5 -> {
                    System.out.print("Podaj wartość hamowania: ");
                    int speedDecrease = scanner.nextInt();
                    scanner.nextLine();
                    car.brake(speedDecrease);
                }
                case 6 -> car.printCarStatus();
                case 7 -> {
                    car.saveData(databaseConnector);
                    databaseConnector.closeConnection();
                    System.out.println("Do widzenia!");
                    programTerminator.terminateProgram();
                }
                default -> System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }
}
