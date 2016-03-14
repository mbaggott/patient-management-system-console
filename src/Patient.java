
public class Patient {
    
    // Declaration and intitalisation of constants
    private final double EXPLORATORY_RATE = 400;
    private final double RECONSTRUCTIVE_RATE = 800;
    private final double FOLLOWUP_RATE = 200;
    
    // Declaration of instance variables
    private String patientId;
    private String patientName;
    private char patientStatus;
    private String procedureDate;
    private int procedureType;
    private String patientNotes;
    private int procedureTotalTime;
    private String doctorName;
    
    // class 'Patient' constructor. Requires arguments Patient Number, Patient Name,
    // Date of Procedure, Type of procedure, Description of Injury, and 
    // Name of Doctor. Sets patient status to 's' Scheduled. Formats notes correctly.
    public Patient (String patientNo, String patientName, String procedureDate, 
            int procedureType, String injuryDescription, String doctorName) {
        this.patientId = patientNo;
        this.patientName = patientName;
        this.procedureDate = procedureDate;
        this.procedureType = procedureType;
        this.patientNotes = "- " + injuryDescription + "\n";
        this.doctorName = doctorName;
        this.patientStatus = 's';
    }
    
    // Class Accessors   
    
    public String getPatientId() {
        return patientId;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public String getProcedureDate() {
        return procedureDate;
    }
    
    public String getPatientNotes() {
        return patientNotes;
    }
    
    public String getDoctorName() {
        return doctorName;
    }
    
    // Class mutators
    
    // This method selects the invoice rate based upon the procedure type set in the
    // constructor, and the predefined constants.
    // The total invoice charge is then calculated from this rate, and the time
    // recorded in the recordProcedure() method.
    public double calculateInvoiceCharge() {
        
        double rate = 0;
        
        // Sets the rate for calculation based on constants, and procedure type
        // set in constructor.
        switch(procedureType) {
            case 1: 
                rate = EXPLORATORY_RATE;
                break;
            case 2:
                rate = RECONSTRUCTIVE_RATE;
                break;
            case 3: 
                rate = FOLLOWUP_RATE;
                break;
            default:
                System.out.println("Error selecting rate!");
                break;
        }
        
        double totalCharge = procedureTotalTime * rate;
        return totalCharge;
    }
    
    // Changes a patient's status from Scheduled to Admitted. Status is used to
    // determine what actions can be taken for the patient.
    public boolean admitPatient() {
        if (Character.toUpperCase(patientStatus) != 'S') {
            return false;
        }
        else {
            patientStatus = 'a';
            return true;
        }
    }
    
    // Records a procedure against the selected patient. Patient must have Admitted
    // Or Recovery status. Takes patient notes, and time of procedure as arguments.
    // Sets patient status to Recovery, and formats notes correctly.
    // Method returns true if successful, false if not.
    public boolean recordProcedure (String procedureNotes, int procedureLength) {
        if (Character.toUpperCase(patientStatus) != 'A' && 
                Character.toUpperCase(patientStatus) != 'R') {
                return false;
        }
        else {
            patientStatus = 'r';
            patientNotes += "- "+ procedureNotes + "\n";
            procedureTotalTime += procedureLength;
            return true;
        }
    }
    
    // Discharges patient, patient must have Recovery status. Updates status to
    // discharged. Calls the method to calculate the invoice amount, and returns
    // this figure is successful. If not successful, Not a Number is returned.
    public double dischargePatient() {
        if (Character.toUpperCase(patientStatus) != 'R') {
            return Double.NaN;
        }
        else {
            patientStatus = 'd';
            return calculateInvoiceCharge();
        }
    }
    
    // Outputs the patient record of a Patient instance to the screen, in a formatted
    // arrangement. Includes the current patient status.
    public void displayPatientRecord() {
        String status;
        
        // Determines patient status, and creates a variable with a descriptive String.
        // Will assign an error if status cannot be determined.
        switch(Character.toUpperCase(patientStatus)) {
            case 'S':
                status = "Procedure Scheduled";
                break;
            case 'A':
                status = "Patient Admitted";
                break;
            case 'R':
                status = "Patient in Recovery";
                break;
            case 'D':
                status = "Patient Discharged";
                break;
            default:
                status = "Error obtaining status";
                break;
        }
        
        // Outputs details in a formatted manner to the screen.
        System.out.printf("%-30s %-25s\n", "Patient No: ", patientId);
        System.out.printf("%-30s %-25s\n", "Patient Name: ", patientName);
        System.out.printf("%-30s %-25s\n\n", "Patient Status: ", status);
        System.out.printf("%-30s %-25s\n", "Date of Procedure: ", procedureDate);
        System.out.printf("%-30s %-25s\n", "Procedure Type: ", procedureType);
        System.out.printf("%-30s %-25s\n", "Assigned Doctor: ", doctorName);
        System.out.printf("%-30s %-25d\n\n", "Procedure Length (Hours): ", 
                procedureTotalTime);
        System.out.printf("%-30s $%-25.2f\n\n", "Total Invoice Charge: "
                , calculateInvoiceCharge());
        System.out.printf("%-20s\n%-45s\n", "Patient Notes: ", patientNotes);
        System.out.println("-------------------------------------------------" +
                "----------------");
    }
}
