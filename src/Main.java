import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(read());
    }
    public static int read() throws FileNotFoundException {
        int meetings = 0;
        File f = new File("Schedules.txt");
        Scanner s = new Scanner(f);
        String schedule = "";
        while (s.hasNext()) {
            while (s.hasNextBoolean()) {
                schedule += s.nextBoolean() + " ";
            }
            AppointmentBook a = new AppointmentBook(AppointmentBook.readSchedule(schedule));
            schedule = "";
            if (a.makeAppointment(s.nextInt(), s.nextInt(), s.nextInt())){
                meetings++;
            }
        }
        return meetings;
    }
}