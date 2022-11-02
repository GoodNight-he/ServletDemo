import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Connection con = null;
        PreparedStatement sta = null;
        ResultSet rest = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/test";
            con = DriverManager.getConnection(url,"root","hjsjiayou");
            String sql = "select * from user where username = ? and password = ?";
            sta = con.prepareStatement(sql);
            sta.setNString(1,username);
            sta.setNString(2,password);
            rest = sta.executeQuery();
            if(rest.next()){
                req.getRequestDispatcher("success.html").forward(req,resp);
            }else{

                req.getRequestDispatcher("fail.html").forward(req,resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rest != null){
                    rest.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            try {
                if(sta != null){
                    sta.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            try {
                if(con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}