import java.sql.*;
import java.util.Scanner;

class Intials{
    protected Statement st;
    protected Connection con;
    public Intials() throws Exception{
        String url = "jdbc:mysql://localhost:3306/DB_NAME";
        String uname = "DB_USER";
        String paswd = "DB_PASSWORD";

        con = DriverManager.getConnection(url, uname, paswd);
        st = con.createStatement();
    }
}

class read_data extends Intials{    
    public read_data(String query) throws Exception{
        super();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
    }
}

class insert_data extends Intials{
    public insert_data(int id, String name) throws Exception{
        super();
        st.execute("INSERT INTO mine VALUES (" + id + ", '" + name + "')");
    }
}

class delete_data extends Intials{
    public delete_data(int id) throws Exception{
        super();
        st.execute("delete from mine where id = "+ id);
    }
}

class update_data extends Intials{
    public update_data(int id, String name) throws Exception{
        super();
        st.execute("update mine set name = '" + name + "' where id = " + id);
    }
}

class delete_table extends Intials{
    public delete_table(String table_name) throws Exception{
        super();
        st.execute("drop table "+table_name);
    }
}

public class demojdbc{
    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        int id;
        String name, response;

        System.out.println("Want to work with the Database (Y/N): ");
        response = sc.nextLine();

        while(response.equalsIgnoreCase("Y")){
            System.out.println("1. Read\t2. Insert\t3. Delete\t4. Update");
            System.out.println("Choose the operation to perform: ");
            int opr = Integer.parseInt(sc.nextLine());
            switch(opr){
                case 1: new read_data("select * from mine");
                        break;
                case 2: System.out.print("Enter the id: ");
                        id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter the name: ");
                        name = sc.nextLine();
                        new insert_data(id, name);
                        break;
                case 3: System.out.println("Enter the id: ");
                        id = Integer.parseInt(sc.nextLine());
                        new delete_data(id);
                        break;
                case 4: System.out.print("Enter the id: ");
                        id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter the name: ");
                        name = sc.nextLine();
                        new update_data(id, name);
                        break;
                }

                System.out.println("Want to continue (Y/N): ");
                response = sc.nextLine();
        }
    }
}