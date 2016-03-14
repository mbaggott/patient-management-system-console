// Import library for Scanner class.
import java.util.*;

public class PatientManagementSystem {
    public static void main(String[] args) {
        
        // Declare all variables.
        
        // sc for scanner input.
        Scanner sc = new Scanner(System.in);
        //patients[] for Patient class array
        Patient patients[] = new Patient[5];
        //name for use in searchPatient() method
        //menuSelection for use in menu
        //PatientNo for use in searchPatient() method
        //description for use in recordProcedure() method
        String name, menuSelection, patientNo = "", description;
        //exitMenu to determine whether to exit the menu
        boolean exitMenu = false;
        //time for use in recordProcedure() method
        //counter for use in searching by patient name, and displaying number of matches
        //match to assign results of searchPatient() method
        int time, counter = 0, match = 0;
        //result to store the return value of dischargePatient() method
        double result;
        
        // Assign and intialise patients array with 5 patients.
        patients[0] = new Patient("13-0602", "Dempster, S.", "09/04/13", 2, 
                "Wrist Fracture", "A. Jowett");
        patients[1] = new Patient("13-0751", "Toovey, A.", "29/04/13", 2, 
                "Knee Injury", "J. Feller");
        patients[2] = new Patient("13-0908", "Dempster, S.", "13/06/13", 3, 
                "Wrist Fixator Removal", "A. Jowett");
        patients[3] = new Patient("13-1013", "Watson, J.", "08/07/13", 2, 
                "Collarbone Fracture", "B. Reid");
        patients[4] = new Patient("13-1365", "Lynch, Q.", "15/11/13", 1,
                "Finger Fracture", "J. Harvey");
    
        //Output to the screen selected details of all patients in patients
        //array, using accessors from the Patient class.
        for (int x=0; x<patients.length; x++) {
            System.out.printf("%-20s %20s\n", "Patient No: ", 
                    patients[x].getPatientId());
            System.out.printf("%-20s %20s\n", "Patient Name: ", 
                    patients[x].getPatientName());
            System.out.printf("%-20s %20s\n", "Procedure Date: ", 
                    patients[x].getProcedureDate());
            System.out.printf("%-20s %20s\n\n", "Assigned Doctor: ", 
                    patients[x].getDoctorName());
        }
        
        // Get input from the user for a patient name to search for
        System.out.print("Enter Patient Name: ");
        name = sc.nextLine();
        
        // Loop to search for patient records with matching name using the 
        // searchPatient() method. Will repeat while method doesn't return a 
        // -1 or while array length is still valid.
        do {
            match = searchPatient(name, patients, match, "name");
            // Execute if match found
            if (match != -1) {
                counter++;
                // Execute only on first match (will print once)
                if (counter==1) {
                    System.out.printf("\nPatient History for " + 
                            patients[match].getPatientName());
                    System.out.printf("\n-------------------------------------\n\n");
                }
                // Execute for all matches (will print multiple times)
                System.out.printf("%-20s %15s\n", "Procedure Date: ",
                        patients[match].getProcedureDate());
                System.out.printf("%-20s %15s\n", "Assigned Doctor: ",
                        patients[match].getDoctorName());
                System.out.printf("%-20s\n%15s\n", "Patient Notes: ",
                        patients[match].getPatientNotes());
                // Increment match index for next search (in case loop repeats)
                match++;
            }
        }
        while (match != -1 && match<patients.length); 
        // print an error if no match was found.
        if (counter == 0) {
            System.out.println("\nError, patient not found!\n");
        }                
        // print number of records found records found if match was found.
        else {
            System.out.printf("Number of records found: " + counter + "\n\n");
        }

        // Create loop for menu output. Will continue to loop until menu
        // is exited.
        while(exitMenu==false) {
            // Output menu to screen and prompt user for a menu selection input
            System.out.println("Patient Management Menu");
            System.out.println("-----------------------");
            System.out.println("A - Admit Patient");
            System.out.println("B - Record Procedure");
            System.out.println("C - Discharge Patient");
            System.out.println("D - Display all Patient Records");
            System.out.println("X - Exit Menu\n");
            System.out.print("Enter Your Selection: ");
            menuSelection = sc.nextLine();
            System.out.println();
            
            // Execute cases based on menu selection input.
            // Will work for upper-case and lower case inputs.
            switch(menuSelection.toUpperCase()) {
            
                case "A":
                    // Request user input for patient number to search
                    System.out.print("Enter Patient No. to Admit: ");
                    patientNo = sc.nextLine();
                    // Search patient array from start index (0)
                    match = searchPatient(patientNo, patients, 0, "id");
                    // Will execute if match is found
                    if ( match != -1) {
                        // if call to admitPatient() is successful, output result
                        if(patients[match].admitPatient()) {
                                System.out.println("\nPatient " + 
                                        patients[match].getPatientName() + 
                                        " Admitted Successfully.\n");
                        }
                        // if call to admit patient is unsuccessful, output result
                        else {
                            System.out.println("\nPatient " + 
                                    patients[match].getPatientName() + 
                                    " has already been admitted!\n");
                        }
                    }
                    // prints an error if no patient is found
                    else {
                        System.out.println("\nError, patient not found.\n");
                    }
                    break;
                    
                case "B":
                    // Request user input for patient number to search
                    System.out.print("Enter Patient No. of Patient Record to"
                            + " Update: ");
                    patientNo = sc.nextLine();
                    // Search for requested patient number starting from index 0.
                    match = searchPatient(patientNo, patients, 0, "id");
                    // Will execute if match is found.
                    if (match != -1) {
                        // Request user input for arguments to recordProcedure().
                        System.out.print("Enter Procedure Description: ");
                        description = sc.nextLine();
                        System.out.print("Enter Procedure Length (Hours): ");
                        time = sc.nextInt();
                        sc.nextLine();
                        // If call to recordProcedure() is successful, output result.
                        if (patients[match].recordProcedure(description, time)) {
                            System.out.println("\nProcedure details recorded " +
                                    "successfully for patient " +
                                    patients[match].getPatientName() + "\n");
                        }
                        // If call to recordProcedure is unsuccessful, output result
                        else {
                            System.out.println("\nPatient " +
                                    patients[match].getPatientName() + 
                                    " is not Admitted!\n");
                        }
                            
                    }
                    // Print an error if no match was found.
                    else {
                        System.out.println("\nError, patient not found.\n");
                    }
                    break;
                    
                case "C":
                    // Request user input for patient number to search
                    System.out.print("Enter Patient No. of Patient to Discharge: ");
                    patientNo = sc.nextLine();
                    // Search for requested patient number starting from index 0.
                    match = searchPatient(patientNo, patients, 0, "id");
                    // Will execute if match is found.
                    if (match != -1) {        
                        result = patients[match].dischargePatient();
                        // If call to dischargePatient() is not a number, output result
                        // indicating there is no invoice to be made yet
                        if (Double.isNaN(result)) {
                            System.out.println("\nError, " + 
                                    patients[match].getPatientName() + 
                                    " is not currently in Recovery!\n");
                        }
                        // If call to dischargePatient() is number, output resulting
                        // invoice amount and success message.
                        else {
                            System.out.println("\nPatient " + 
                                    patients[match].getPatientName() + 
                                    " has been discharged successfully.");
                            System.out.printf("\n%-25s $%-15.2f\n\n", 
                                    "Final Invoice Amount: ", result);
                        }
                    }
                    // Print an error if no patient was found.
                    else {
                        System.out.println("\nError, patient not found!\n");
                    }
                    break;
                    
                case "D":
                    // Display patient record of all patients in the array
                    System.out.println("\nSummary of all the Patients in" +
                            " the System\n");
                    System.out.println("--------------------------------" +
                            "---------\n\n");
                    for (int x=0; x<patients.length; x++) {
                        patients[x].displayPatientRecord();
                    }
                    break;
                    
                // Exit the menu if 'X' is selected
                case "X":
                    exitMenu = true;
                    break;
                    
                // Print an error for an invalid menu choice.
                default:
                    System.out.println("Error, invalid choice!\n");
            }

        }
        sc.close();
    }
    
    // Method to search for patient name or number. Takes the search string, 
    // the patients array, the starting index for the search, and a search type 
    // ("name" or "id") as arguments. Returns a -1 for no match found,
    // or an index number for relevant match.
    static int searchPatient(String toSearch, Patient patients[], int startIndex,
            String searchType) {
        int x;
        if (startIndex != -1) {
            for (x=startIndex; x<patients.length; x++) {
                if (searchType == "name") {
                    if (toSearch.compareTo(patients[x].getPatientName()) == 0) 
                        return x;
                }
                if (searchType == "id") {
                    if (toSearch.compareTo(patients[x].getPatientId()) == 0)
                        return x;
                }
            }
        }
        return -1;
    }
    
}
