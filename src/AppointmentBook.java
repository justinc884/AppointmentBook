import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class AppointmentBook {
    private boolean[][] schedule;
    public AppointmentBook(boolean[][] schedule) {
        this.schedule = schedule;
    }
    private boolean isMinuteFree(int period, int minute) {
        return schedule[period - 1][minute];
    }
    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
        for(int i = startPeriod; i <= endPeriod; i++) {
            int freeBlock = findFreeBlock(i, duration);
            if (freeBlock > -1) {
                reserveBlock(i, freeBlock, duration);
                return true;
            }
        }
        return false;
    }
    public void printPeriod(int period) {
        for(int i = 0; i < schedule[period - 1].length; i++)
            System.out.println(i + " " + schedule[period - 1][i]);
    }
    public int findFreeBlock(int period, int duration) {
        int block = 0;
        int start_position = 0;
        for (int i = 0; i < 60; i++) {
            if (isMinuteFree(period, i)) {
                block++;
                if (block == duration) {
                    return start_position;
                    // return i - duration + 1;
                }
            } else {
                block = 0;
                start_position = i + 1;
            }
        }
        return -1;
    }
    public void reserveBlock(int period, int startMinute, int duration) {
        for(int i = startMinute; i < startMinute + duration; i++) {
            schedule[period - 1][i] = false;
        }
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
        }
        AppointmentBook a = new AppointmentBook(readSchedule(schedule));
        schedule = "";
        if (a.makeAppointment(s.nextInt(), s.nextInt(), s.nextInt())){
            meetings++;
        }
        return meetings;
    }
    public static boolean[][] readSchedule(String lines) {
        Scanner s = new Scanner(lines);
        boolean[][] schedule = new boolean[8][60];
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[i].length; j++) {
                schedule[i][j] = s.nextBoolean();
            }
        }
        return schedule;
    }
}
