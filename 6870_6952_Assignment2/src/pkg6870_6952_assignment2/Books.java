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
public class Books {
    private String callno;
    private String title;
    private String author;
    private String publisher;
    private String quantity;
    private Date dateRegistered;

    public Books(String callno, String title, String author, String publisher, String quantity, Date dateRegisterd) {
        this.callno = callno;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
        this.dateRegistered=new Date(System.currentTimeMillis());
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
     public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
     public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }
    
     public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
      
    public static Books b[] = new Books[100];
    public static int count = 0;

    public static void readFile() throws FileNotFoundException, ParseException {
        //File f = new File("books.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        count = 0;
        Scanner s = new Scanner("books.txt");
        while (s.hasNext()) {
            String callno = s.next();
            String title = s.next();
            String author = s.next();
            String publisher = s.next();
            String quantity = s.next();
            String dateStr = s.next();
            Date d = sdf.parse(dateStr);
            b[count].setDateRegistered(d);
            b[count] = new Books(callno,title,author,publisher,quantity,d);
            count++;
        }
    }

    public static void saveFile() throws FileNotFoundException {
        //File f = new File("books.txt");
        PrintWriter pw = new PrintWriter("books.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");

        for (int i = 0; i < count; i++) {
            pw.print(b[i].getCallno() + " ");
            pw.print(b[i].getTitle() + " ");
            pw.print(b[i].getAuthor() + " ");
            pw.print(b[i].getPublisher() + " ");
            pw.print(b[i].getQuantity() + " ");
            pw.println(sdf.format(b[i].getDateRegistered())+ " \n");
        }
        pw.close();
    }

    public static void addBook(String callno, String title, String author, String publisher, String quantity, Date dateRegistered) throws FileNotFoundException {
       b[count] = new Books(callno,title,author,publisher,quantity,dateRegistered);
        count++;
        saveFile();
    }

    public static void deleteBook(String callno) throws FileNotFoundException {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (b[i].getCallno().equals(callno)) {
                found = true;
                for (int j = i; j < count - 1; j++) {
                    b[j] = b[j + 1];
                }
                count--;
                break;
            }
        }
        if (found) {
            saveFile();
        }
    }
        public static void decBook(String callno) throws FileNotFoundException {
       for (int check = 0; check<count;check++){
       if(b[check].getCallno().equals(callno) && Integer.parseInt(Books.b[count].getQuantity())>0)
       {
           System.out.print(b[check].getQuantity());
           b[check].setQuantity(Integer.toString(Integer.parseInt(Books.b[count].getQuantity())-1));
           System.out.print(b[check].getQuantity());
       }
       }
        saveFile();
    }
    
    
}

