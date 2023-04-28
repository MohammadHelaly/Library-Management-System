/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg6870_6952_assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author TOSHIBA
 */
public class Students {
     private int id;
    private String name;
    private Date dateRegistered;
    private String contact;

    public Students(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.dateRegistered=new Date(System.currentTimeMillis());
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
 
     static Students s[] = new Students[100];
    static int count = 0;

    public static void readFile() throws FileNotFoundException, ParseException {
        File f = new File("students.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        count = 0;
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String name = sc.next();
            int id = sc.nextInt();
            String contact = sc.next();
            String dateStr = sc.next();
            Date d = sdf.parse(dateStr);
            s[count] = new Students(id, name, contact);
            s[count].setDateRegistered(d);
            count++;
        }
    }

    public static void saveFile() throws FileNotFoundException {
        File f = new File("students.txt");
        PrintWriter pw = new PrintWriter(f);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");

        for (int i = 0; i < count; i++) {
            pw.print(s[i].getName() + " ");
            pw.print(s[i].getId() + " ");
            pw.print(s[i].getContact() + " \n");
            pw.println(sdf.format(s[i].getDateRegistered()));
        }
        pw.close();

    }

    public static void AddStudent(String name, int ID, String contact) throws FileNotFoundException {
        s[count] = new Students(ID, name, contact );
        count++;
        saveFile();
    }

    public static void deleteStudent(int id) throws FileNotFoundException {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (s[i].getId() == id) {
                found = true;
                for (int j = i; j < count - 1; j++) {
                    s[j] = s[j + 1];
                }
                count--;
                break;
            }
        }
        if (found) {
            saveFile();
        }
    }
}
