package at.technikum.slm_24h_to_am_pm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Slm24hToAMPMController {

    public static void main(String[] args) {
        SpringApplication.run(Slm24hToAMPMController.class, args);
    }
}

@RestController
class TimeConversionController {

    @GetMapping("/api/convert")
    public String convertToAMPM(@RequestParam("time") String time) {
        // Split the input time into hours and minutes
        String[] parts = time.split(":");
        if (parts.length == 2) {
            try {
                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);

                // Validate the input
                if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
                    // Convert to AM/PM format
                    String period = (hours < 12) ? "AM" : "PM";
                    if (hours == 0) {
                        hours = 12; // 0:00 is 12:00 AM
                    } else if (hours > 12) {
                        hours -= 12;
                    }
                    return hours + ":" + String.format("%02d", minutes) + " " + period;
                } else {
                    return "Ungültige Zeitangabe.";
                }
            } catch (NumberFormatException e) {
                return "Ungültige Zeitangabe.";
            }
        }
        return "Ungültige Zeitangabe.";
    }
}

