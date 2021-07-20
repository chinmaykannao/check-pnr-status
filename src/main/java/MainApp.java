import com.pnr.checker.helper.PNRRequestExecutor;
import com.pnr.checker.model.PNRStatus;
import com.pnr.checker.model.Passenger;
import com.squareup.okhttp.Request;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("Enter PNR: ");
        Scanner scanner = new Scanner(System.in);
        String pnr = scanner.nextLine();

        PNRRequestExecutor requestExecutor = PNRRequestExecutor.getExecutor();
        
        //create request with pnr
        Request request = requestExecutor.createRequestForPNR(pnr);
        
        //execute and get result
        PNRStatus pnrStatus = requestExecutor.executeRequest(request);

        if (null != pnrStatus) {
            System.out.println("\nChart status: " + pnrStatus.getChartStatus());
            for (Passenger passengerStatus : pnrStatus.getPassengers()) {
                System.out.println("Passenger: " + passengerStatus.getName() + " CurrentStatus: " + passengerStatus.getCurrentStatus());
            }
        } else {
            System.out.println("Could not fetch PNR status. Try again..");
        }
    }
}
