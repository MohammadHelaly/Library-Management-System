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
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

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
    static int check = 0;

    public static void readFile() throws FileNotFoundException, ParseException {
        File f = new File("issuebooks.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        count = 0;
        Scanner s = new Scanner(f);
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
        File f = new File("issuebooks.txt");
        PrintWriter pw = new PrintWriter(f);
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
       Books.saveFile();;
        count++;
        saveFile();
    }

    public static void returnBook(String callno, String stID) throws FileNotFoundException {
        boolean found = false;
        for (int i = 0; i < count; i++) {
        if (bi[i].getCallno() == callno && bi[i].stID == stID) {
                found = true;
                       for (int check = 0; check<Books.count;check++){
       if(Books.b[check].getCallno() == callno)
       {
           Books.b[check].setQuantity(Integer.toString(Integer.parseInt(Books.b[count].getQuantity())+1));
       }
       }
                for (int j = i; j < count - 1; j++) {
                    bi[j] = bi[j + 1];
                }
                count--;
                break;
            }
        }
        if (found) {
            saveFile();
            Books.saveFile();
        }
    }
}
