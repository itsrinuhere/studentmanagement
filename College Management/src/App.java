
//Libraryr REST API using SQlite db with creating a new Environment
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Scanner;

public class App {
    private Connection con = null;
    private String url = "jdbc:sqlite:./SQLite.db";
    private Statement stmt = null;
    String sql = null;
    String name = null;
    String hallticket = null;
    Scanner sc = new Scanner(System.in);
    boolean connext = false;

    void log() {
        System.out.println("TO view please log-in");
        System.out.println("Enter user name ");
        name = sc.nextLine();
        System.out.println("enter password");
        String password = sc.nextLine();
        // select * from user where name ='srinu' and password ='12345';
        sql = "select name from user where name = \'" + name + "\' and \'" + password + "\';";
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                connext = true;
            }
            stmt.close();
            con.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "something \nError please try again");
            connext = false;
        }
    }

    void connection(int state) {
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            System.out.println("Connection to SQLite has been established.");
            stmt = con.createStatement();
            switch (state) {
                case 1:

                    log();
                    if (connext == true) {
                        System.out.println("Enter HAllticket number\t>>");
                        hallticket = sc.nextLine();
                        System.out.print("Enter new name:\t>>");
                        name = sc.nextLine();
                        sql = "UPDATE student SET name =\'" + name + "\' WHERE hallticket =\'" + hallticket + "\'";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        con.commit();
                        con.close();
                        System.out.println("Successfully updated with new name:\t" + name);
                    } else {
                        log();
                    }
                    break;
                case 2:
                    log();
                    if (connext == true) {
                        System.out.println("Enter name\n>>");
                        name = sc.nextLine();
                        System.out.println("Enter HAllticket number:\t>");
                        hallticket = sc.nextLine();
                        System.out.print("Enter course:\t>>");
                        String course = sc.nextLine();
                        System.out.println("address:\t>>");
                        String address = sc.nextLine();
                        sql = "insert into student(name,hallticket,course,address) values(\'" + name + "\',\'"
                                + hallticket + "\',\'" + course + "\',\'" + address + "\');";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        con.commit();

                    } else {
                        log();
                    }
                    break;
                case 3:
                    log();
                    if (connext == true) {
                        System.out.println("Enter HAllticket number\t>>");
                        hallticket = sc.nextLine();
                        System.out.print("Enter new hallticket:\t>>");
                        String hallticketnew = sc.nextLine();
                        sql = "UPDATE student SET hallticket =\'" + hallticketnew + "\' WHERE hallticket =\'"
                                + hallticket + "\'";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        con.commit();

                        System.out.println("Successfully updated with new hallticket:\t" + hallticketnew);
                    } else {
                        log();
                    }
                    break;
                case 4:
                    ui();
                    // update soon
                    break;
                case 5:
                    Scanner o = new Scanner(System.in);
                    System.out.println("1.score of student");
                    System.out.println("2.Edit subject marks");
                    int value = o.nextInt();
                    if (value == 1) {
                        System.out.print("Enter Hallticket number :\t>>");
                        String ket = sc.nextLine();
                        sql = "select * from score where hallticket =\'" + ket + "\';";

                        stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            while (rs.next()) {
                                System.out.println(rs.getString("hallticket") + "\t" + rs.getString("m1") + "\t"
                                        + rs.getString("m2") + "\t" + rs.getString("physics")
                                        + rs.getString("chemistry"));
                            }
                        } else {
                            System.out.println("not found\n");
                        }

                        stmt.close();
                        con.commit();

                        o.close();
                    } else {
                    }
                    break;
                default:
                    System.out.print("Error");
            }
            con.close();
        } catch (SQLException e) {
            // System.out.println(e.getClass().getName() + ": " + e.getMessage());

        }
        ui();
        System.out.println("Records created successfully");

    }

    void ui() {

        System.out.println("1.change student name");// working
        System.out.println("2.Enter new student Details");// working
        System.out.println("3.change hallticket number");// working but Exception out or array bounds
        System.out.println("4.upload photo");// Error are generating change
                                             // to Hashtable index algo to
                                             // simplified the task
        System.out.println("5.show score");// -->new frame
        System.out.println("6.enter subject and marks");
        System.out.println("7.show all subjects");
        // System.out.println("8.Export to CSV");
        // System.out.println("9.print ID card");
        Scanner a = new Scanner(System.in);
        int ch = a.nextInt();
        new App().connection(ch);
        a.close();
        ui();
    }

    // System.out.println("Session time out\n Please Try Again");
    public static void main(String[] args) {
        new App().ui();

    }
}