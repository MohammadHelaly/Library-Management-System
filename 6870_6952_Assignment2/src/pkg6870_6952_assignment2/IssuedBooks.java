/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg6870_6952_assignment2;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author TOSHIBA
 */
public class IssuedBooks {
    private String callno;
    private String stID;
    private String stName;
    private String stContact;
    private Date dateIssue;

    public IssuedBooks(String callno, String stID, String stName, String stContact, Date dateIssue) {
        this.callno = callno;
        this.stID = stID;
        this.stName = stName;
        this.stContact =  stContact;
        this.dateIssue=new Date(System.currentTimeMillis());
    }

    public Date getDateIssued() {
        return dateIssue;
    }

    public void setDateIssued(Date dateIssue) {
        this.dateIssue = dateIssue;
    }


    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }
    
    
     public String getStContact() {
        return stContact;
    }

    public void setStContact(String stContact) {
        this.stContact = stContact;
    }
    
     public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }
      
    static IssuedBooks bi[] = new IssuedBooks[100];
    static int count = 0;
    //static int check = 0;

    public static void readFile() throws FileNotFoundException, ParseException {
        //File f = new File("issuebooks.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        count = 0;
        Scanner s = new Scanner("issuebooks.txt");
        while (s.hasNext()) {
            String callno = s.next();
            String stID = s.next();
            String stName = s.next();
            String stContact = s.next();
            String dateStr = s.next();
            Date d = sdf.parse(dateStr);
            bi[count].setDateIssued(d);
            bi[count] = new IssuedBooks(callno,stID,stName,stContact,d);
            count++;
        }
    }

    public static void saveFile() throws FileNotFoundException {
        //File f = new File("issuebooks.txt");
        PrintWriter pw = new PrintWriter("issuebooks.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");

        for (int i = 0; i < count; i++) {
            pw.print(bi[i].getCallno() + " ");
            pw.print(bi[i].getStID() + " ");
            pw.print(bi[i].getStName() + " ");
            pw.print(bi[i].getStContact() + " ");
            pw.println(sdf.format(bi[i].getDateIssued())+ " \n");
        }
        pw.close();
    }

    public static void issueBook(String callno, String stID, String stName, String stContact, Date dateIssued) throws FileNotFoundException {
       bi[count] = new IssuedBooks(callno,stID,stName,stContact,dateIssued);
       for (int check = 0; check<Books.count;check++){
       if(Books.b[check].getCallno() == callno && Integer.parseInt(Books.b[count].getQuantity())>0)
       {
           Books.b[check].setQuantity(Integer.toString(Integer.parseInt(Books.b[count].getQuantity())-1));
       }
       }
       //Books.saveFile();
        count++;
        saveFile();
    }

    public static void returnBook(String clno, String sD) throws FileNotFoundException, IOException {

      List<String> lines = new ArrayList<>();
try (BufferedReader reader = new BufferedReader(new FileReader("issuebooks.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        if (line.contains(clno) && line.contains(sD)) {
        } else {
            lines.add(line);
            //System.out.println(line);
        }
    }
}

// Step 4: Write the modified contents back to the file
try (PrintWriter writer = new PrintWriter(new FileWriter("issuebooks.txt"))) {
    for (String line : lines) {
        writer.println(line);
       // System.out.println(line);
    }
}            
                
    }
}
