import java.io.FileWriter;
import java.io.IOException;

class DatabaseConnector {
    private FileWriter fileWriter;

    public DatabaseConnector(String filePath) {
        try {
            fileWriter = new FileWriter(filePath, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCarData(int fuelLevel, int speed, int engineRPM) {
        String data = fuelLevel + "," + speed + "," + engineRPM + "\n";
        try {
            fileWriter.write(data);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
