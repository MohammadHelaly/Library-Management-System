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
import java.util.Scanner;

/**
 *
 * @author TOSHIBA
 */
public class Librarians {
    private int id;
    private String name;
    private String password;
    private String email;
    private String address;
    private String city;
    private String contact;

    public Librarians(int id, String name, String password, String email, String address, String city,String contact) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.contact = contact;
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
    
     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
     public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    
    static Librarians l[] = new Librarians[100];
    static int count = 0;

    public static void readFile() throws FileNotFoundException, ParseException {
        File f = new File("librarians.txt");
        count = 0;
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            int id = count + 1;
            String name = s.next();
            String password = s.next();
            String email = s.next();
            String address = s.next();
            String city = s.next();
            String contact = s.next();
            l[count] = new Librarians(id, name,password,email,address,city,contact);
            count++;
        }
    }

    public static void saveFile() throws FileNotFoundException {
        File f = new File("librarians.txt");
        PrintWriter pw = new PrintWriter(f);

        for (int i = 0; i < count; i++) {
            pw.print(l[i].getId() + " ");
            pw.print(l[i].getName() + " ");
            pw.print(l[i].getPassword() + " ");
            pw.print(l[i].getEmail() + " ");
            pw.print(l[i].getAddress() + " ");
            pw.print(l[i].getCity() + " ");
            pw.print(l[i].getContact() + " \n");  
        }
        pw.close();
    }

    public static void addLibrarian(int id, String name, String password, String email, String address, String city, String contact) throws FileNotFoundException {
       l[count] = new Librarians(id,name,password,email,address,city,contact);
       
        count++;
        saveFile();
    }

    public static void deleteLibrarian(int id) throws FileNotFoundException {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (l[i].getId() == id) {
                found = true;
                for (int j = i; j < count - 1; j++) {
                    l[j] = l[j + 1];
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





