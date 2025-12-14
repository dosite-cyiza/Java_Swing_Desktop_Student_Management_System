package util;

import java.util.regex.Pattern;


public class ValidationUtil {
    
    
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final String PHONE_PATTERN = 
        "^\\d{3}-\\d{3}-\\d{4}$"; 
    
    private static final String NAME_PATTERN = 
        "^[A-Za-z\\s]{2,50}$"; 
    
   
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches(EMAIL_PATTERN, email);
    }
    
        public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches(PHONE_PATTERN, phone);
    }
    
        public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches(NAME_PATTERN, name);
    }
    
        public static boolean isValidGPA(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }
    
        public static boolean isValidYear(int year) {
        int currentYear = java.time.Year.now().getValue();
        return year >= 1900 && year <= currentYear + 1;
    }
    
        public static void main(String[] args) {
        System.out.println("=== RegEx Validation Tests ===\n");
        
        
        System.out.println("Email Validation:");
        System.out.println("john@university.edu -> " + isValidEmail("john@university.edu"));
        System.out.println("invalid.email -> " + isValidEmail("invalid.email"));
        
        
        System.out.println("\nPhone Validation:");
        System.out.println("555-0101 -> " + isValidPhone("555-0101"));
        System.out.println("5550101 -> " + isValidPhone("5550101"));
        
        
        System.out.println("\nName Validation:");
        System.out.println("John Doe -> " + isValidName("John Doe"));
        System.out.println("J0hn -> " + isValidName("J0hn"));
        
        
        System.out.println("\nGPA Validation:");
        System.out.println("3.75 -> " + isValidGPA(3.75));
        System.out.println("5.0 -> " + isValidGPA(5.0));
        

        System.out.println("\nYear Validation:");
        System.out.println("2023 -> " + isValidYear(2023));
        System.out.println("1800 -> " + isValidYear(1800));
    }
}