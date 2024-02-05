import java.sql.*;
import java.util.Scanner;

public class HospitalManagement {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "W7301@jqir#";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try
        {           // establishing the connection here with driver manager
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5.View all appointments");
                System.out.println("6. View Appointment by patient");
                System.out.println("7. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch(choice){
                    case 1:
                        // Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        // View Patient
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        // View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        // Book Appointment
                        bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
                        viewAllAppointments(connection);
                        break;

                    case 6:
                        viewAllAppointmentsByPatient(connection, scanner);

                    case 7:
                        System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        break;
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient ,Doctor doctor, Connection connection, Scanner scanner){
        System.out.println("Enter Patient id");
        int patientId=scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)){
            if (checkDoctorAvailability(doctorId,appointmentDate, connection)){
                String query= "Insert into Appointment(patient_id, doctor_id, appointment_date) values (?,?,?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1,patientId);
                    preparedStatement.setInt(2,doctorId);
                    preparedStatement.setString(3,appointmentDate);
                    int rowsAffected=preparedStatement.executeUpdate();
                    if (rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }
                    else {
                        System.out.println("Failed to book Appointment !");

                    }
                }
                catch (SQLException e ){
                    e.printStackTrace();
                }


            }
            else{
                System.out.println("Doctor not available on this date !");

            }
        }
        else{
            System.out.println("Either doctor or patient doesn't exist !");

        }


    }




    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM Appointment WHERE doctor_id = ? AND appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void viewAllAppointments(Connection connection){
        String query="Select * from Appointment";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Appointments: ");
            System.out.println("+------------+--------------------+-------------------+");
            System.out.println("| Patient Id | DoctorId           | Appointment Date  ");
            System.out.println("+------------+--------------------+-------------------+");
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");  // Adjust the column name to match your table
                int doctorId = resultSet.getInt("doctor_id");
                Date appointmentDate = resultSet.getDate("appointment_date");

                System.out.printf("| %-10s | %-18s | %-19s |\n", patientId, doctorId, appointmentDate);
                System.out.println("+------------+--------------------+-------------------+");
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void viewAllAppointmentsByPatient(Connection connection, Scanner scanner ){
        System.out.println(" Enter Patient Id: ");
        int patient_id= scanner.nextInt();
        String query="Select * from Appointment where patient_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,patient_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Appointments: ");
            System.out.println("+------------+--------------------+-------------------+");
            System.out.println("| Patient Id | DoctorId           | Appointment Date  ");
            System.out.println("+------------+--------------------+-------------------+");
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");  // Adjust the column name to match your table
                int doctorId = resultSet.getInt("doctor_id");
                Date appointmentDate = resultSet.getDate("appointment_date");

                System.out.printf("| %-10s | %-18s | %-19s |\n", patientId, doctorId, appointmentDate);
                System.out.println("+------------+--------------------+-------------------+");
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


}