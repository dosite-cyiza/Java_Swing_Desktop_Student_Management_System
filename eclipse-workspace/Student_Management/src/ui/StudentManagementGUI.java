package ui;

import model.Student;
import service.StudentService;
import service.StudentServiceImpl;
import util.ValidationUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Main GUI class using Java Swing
 * Demonstrates use of 8+ Swing components and complete CRUD functionality
 */
public class StudentManagementGUI extends JFrame {
    
    // Service layer
    private StudentService studentService;
    
    // Swing components (MORE THAN 5 REQUIRED)
    private JTextField txtFirstName, txtLastName, txtEmail, txtPhone, txtGPA, txtYear;
    private JComboBox<String> comboDepartment;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnLoad;
    private JTable tableStudents;
    private DefaultTableModel tableModel;
    
    private int selectedStudentId = -1;
    
    public StudentManagementGUI() {
        studentService = new StudentServiceImpl();
        initComponents();
        loadStudentData();
    }
    
    private void initComponents() {
        setTitle("Student Management System - CRUD Application");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Create panels
        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLUE, 2),
            "Student Information Form",
            0, 0, new Font("Arial", Font.BOLD, 14), Color.BLUE));
        panel.setBackground(new Color(240, 248, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Row 0: First Name and Last Name
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("First Name:"), gbc);
        gbc.gridx = 1;
        txtFirstName = new JTextField(20);
        panel.add(txtFirstName, gbc);
        
        gbc.gridx = 2;
        panel.add(createLabel("Last Name:"), gbc);
        gbc.gridx = 3;
        txtLastName = new JTextField(20);
        panel.add(txtLastName, gbc);
        
        // Row 1: Email and Phone
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        panel.add(txtEmail, gbc);
        
        gbc.gridx = 2;
        panel.add(createLabel("Phone (555-0000):"), gbc);
        gbc.gridx = 3;
        txtPhone = new JTextField(20);
        panel.add(txtPhone, gbc);
        
        // Row 2: Department and Year
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Department:"), gbc);
        gbc.gridx = 1;
        String[] departments = {"Computer Science", "Engineering", "Business", 
                               "Mathematics", "Physics", "Chemistry", "Biology", "Arts"};
        comboDepartment = new JComboBox<>(departments);
        comboDepartment.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(comboDepartment, gbc);
        
        gbc.gridx = 2;
        panel.add(createLabel("Enrollment Year:"), gbc);
        gbc.gridx = 3;
        txtYear = new JTextField(20);
        panel.add(txtYear, gbc);
        
        // Row 3: GPA
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createLabel("GPA (0.0-4.0):"), gbc);
        gbc.gridx = 1;
        txtGPA = new JTextField(20);
        panel.add(txtGPA, gbc);
        
        return panel;
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GREEN, 2),
            "Student Records",
            0, 0, new Font("Arial", Font.BOLD, 14), Color.GREEN.darker()));
        
        // Table columns
        String[] columns = {"ID", "First Name", "Last Name", "Email", "Phone", 
                           "Department", "Year", "GPA"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableStudents = new JTable(tableModel);
        tableStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableStudents.setFont(new Font("Arial", Font.PLAIN, 11));
        tableStudents.setRowHeight(25);
        tableStudents.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableStudents.getTableHeader().setBackground(new Color(70, 130, 180));
        tableStudents.getTableHeader().setForeground(Color.WHITE);
        
        // Add selection listener
        tableStudents.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectStudentFromTable();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableStudents);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBackground(new Color(245, 245, 245));
        
        // Create buttons with colors
        btnAdd = createButton("Add Student", new Color(46, 204, 113));
        btnUpdate = createButton("Update Student", new Color(52, 152, 219));
        btnDelete = createButton("Delete Student", new Color(231, 76, 60));
        btnClear = createButton("Clear Fields", new Color(149, 165, 166));
        btnLoad = createButton("Reload Data", new Color(241, 196, 15));
        
        // Add action listeners
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());
        btnLoad.addActionListener(e -> loadStudentData());
        
        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnClear);
        panel.add(btnLoad);
        
        return panel;
    }
    
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(140, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private void addStudent() {
        try {
            if (!validateInput()) {
                return;
            }
            
            Student student = new Student();
            student.setFirstName(txtFirstName.getText().trim());
            student.setLastName(txtLastName.getText().trim());
            student.setEmail(txtEmail.getText().trim());
            student.setPhone(txtPhone.getText().trim());
            student.setDepartment((String) comboDepartment.getSelectedItem());
            student.setEnrollmentYear(Integer.parseInt(txtYear.getText().trim()));
            student.setGpa(Double.parseDouble(txtGPA.getText().trim()));
            
            if (studentService.addStudent(student)) {
                JOptionPane.showMessageDialog(this, 
                    "Student added successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to add student. Email might already exist.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                " Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateStudent() {
        if (selectedStudentId == -1) {
            JOptionPane.showMessageDialog(this, 
                " Please select a student from the table first.", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            if (!validateInput()) {
                return;
            }
            
            Student student = new Student();
            student.setStudentId(selectedStudentId);
            student.setFirstName(txtFirstName.getText().trim());
            student.setLastName(txtLastName.getText().trim());
            student.setEmail(txtEmail.getText().trim());
            student.setPhone(txtPhone.getText().trim());
            student.setDepartment((String) comboDepartment.getSelectedItem());
            student.setEnrollmentYear(Integer.parseInt(txtYear.getText().trim()));
            student.setGpa(Double.parseDouble(txtGPA.getText().trim()));
            
            if (studentService.updateStudent(student)) {
                JOptionPane.showMessageDialog(this, 
                    " Student updated successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this, 
                    " Failed to update student.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteStudent() {
        if (selectedStudentId == -1) {
            JOptionPane.showMessageDialog(this, 
                " Please select a student from the table first.", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this student?\n" +
            "This action cannot be undone!", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (studentService.deleteStudent(selectedStudentId)) {
                JOptionPane.showMessageDialog(this, 
                    "Student deleted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to delete student.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadStudentData() {
        tableModel.setRowCount(0);
        List<Student> students = studentService.getAllStudents();
        
        for (Student student : students) {
            Object[] row = {
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getDepartment(),
                student.getEnrollmentYear(),
                student.getGpa()
            };
            tableModel.addRow(row);
        }
        
        JOptionPane.showMessageDialog(this, 
            "Loaded " + students.size() + " student records", 
            "Data Loaded", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void selectStudentFromTable() {
        int selectedRow = tableStudents.getSelectedRow();
        if (selectedRow >= 0) {
            selectedStudentId = (int) tableModel.getValueAt(selectedRow, 0);
            txtFirstName.setText((String) tableModel.getValueAt(selectedRow, 1));
            txtLastName.setText((String) tableModel.getValueAt(selectedRow, 2));
            txtEmail.setText((String) tableModel.getValueAt(selectedRow, 3));
            txtPhone.setText((String) tableModel.getValueAt(selectedRow, 4));
            comboDepartment.setSelectedItem((String) tableModel.getValueAt(selectedRow, 5));
            txtYear.setText(String.valueOf(tableModel.getValueAt(selectedRow, 6)));
            txtGPA.setText(String.valueOf(tableModel.getValueAt(selectedRow, 7)));
        }
    }
    
    private void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtYear.setText("");
        txtGPA.setText("");
        comboDepartment.setSelectedIndex(0);
        selectedStudentId = -1;
        tableStudents.clearSelection();
    }
    
    private boolean validateInput() {

        if (!ValidationUtil.isValidName(txtFirstName.getText().trim())) {
            JOptionPane.showMessageDialog(this, 
                " Invalid first name!\n" +
                "Use only letters and spaces (2-50 characters).\n" +
                "Example: John", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            txtFirstName.requestFocus();
            return false;
        }
        

        if (!ValidationUtil.isValidName(txtLastName.getText().trim())) {
            JOptionPane.showMessageDialog(this, 
                "Invalid last name!\n" +
                "Use only letters and spaces (2-50 characters).\n" +
                "Example: Doe", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            txtLastName.requestFocus();
            return false;
        }
        
        // Validate Email using RegEx
        if (!ValidationUtil.isValidEmail(txtEmail.getText().trim())) {
            JOptionPane.showMessageDialog(this, 
                " Invalid email format!\n" +
                "Example: john.doe@university.edu", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        
        // Validate Phone using RegEx
        if (!ValidationUtil.isValidPhone(txtPhone.getText().trim())) {
            JOptionPane.showMessageDialog(this, 
                " Invalid phone format!\n" +
                "Use format: 555-0000\n" +
                "Example: 555-0101", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            txtPhone.requestFocus();
            return false;
        }
        
        // Validate Year
        try {
            int year = Integer.parseInt(txtYear.getText().trim());
            if (!ValidationUtil.isValidYear(year)) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid enrollment year!\n" +
                    "Must be between 1900 and " + (java.time.Year.now().getValue() + 1), 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                txtYear.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Year must be a number!\n" +
                "Example: 2023", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            txtYear.requestFocus();
            return false;
        }
        
        // Validate GPA
        try {
            double gpa = Double.parseDouble(txtGPA.getText().trim());
            if (!ValidationUtil.isValidGPA(gpa)) {
                JOptionPane.showMessageDialog(this, 
                    " Invalid GPA!\n" +
                    "Must be between 0.0 and 4.0\n" +
                    "Example: 3.75", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                txtGPA.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "GPA must be a valid number!\n" +
                "Example: 3.75", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            txtGPA.requestFocus();
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            StudentManagementGUI gui = new StudentManagementGUI();
            gui.setVisible(true);
        });
    }
}