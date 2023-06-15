/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraria;

/**
 *
 * @author Helaly
 */
public class sessionManager {
    private static int librarianId=0;
    private static int adminId=0;
    private static int branchId=0;

    public static int getLibrarianId() {
        return librarianId;
    }

    public static void setLibrarianId(int id) {
        librarianId = id;
    }
    
    public static int getAdminId() {
        return adminId;
    }

    public static void setAdminId(int id) {
        adminId = id;
    }
    
        public static int getBranchId() {
        return branchId;
    }

    public static void setBranchId(int id) {
        branchId = id;
    }
    
}
