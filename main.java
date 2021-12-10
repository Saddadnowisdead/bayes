import java.sql.*;

public class main {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "admin";
    private static Connection con;
   private static Statement stmt;
   private static ResultSet rs;
   
   
    public static void main(String[] args){
        String query = "select * from predmet";
        String query2 = "select * from studentsinfo";
       
        double countPassed = 0;
        double countall = 0;
        double countForIntel = 0;
        double intelligentStud = 0;
        
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            
            
            
            while (rs.next()) {
            	++countall;
            	if(rs.getInt("PassOrFail")==1) {
                 ++countPassed;
            	}
            	
            }
            
            if((countPassed/countall*100)>50) {
            System.out.println("Average difficulty");
            }else System.out.println("Hard exam");
            
            rs = stmt.executeQuery(query2);
            countall = 0;
            while (rs.next()) {
            	++countall;
            	if(rs.getInt("ZNOResultMed")>160) {
            		++countForIntel;
            	}
            	if(rs.getInt("ParticipanceInContests")==1) {
            		++countForIntel;
            	}
            	if(rs.getInt("PrivateOrPublicSchool")==1) {
            		++countForIntel;
            	}
            	if(rs.getInt("GoodSchoolGrades")==1) {
            		++countForIntel;
            	}
            	if(countForIntel>=3) {
            		++intelligentStud;
            		countForIntel=0;
            	}
            	
            }
            System.out.println("Percent of above average intelligent students: "+(intelligentStud/countall*100));
            
            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
    }
