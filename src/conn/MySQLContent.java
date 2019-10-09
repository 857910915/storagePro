package conn;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * 数据库连接
 * @author 陈世杰
 *
 */
public class MySQLContent {
	private static Properties p=new Properties();
	private static Connection con=null;
	private static DataSource dataSource;
	static{
		ClassLoader loader=Thread.currentThread().getContextClassLoader();
		InputStream in=loader.getResourceAsStream("dbcp.properties");
		try {
			p.load(in);
			dataSource=BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnect() {
		try {
			con=dataSource.getConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void closeAll(ResultSet rs,PreparedStatement ps,Connection con) {
		try {
			if (rs!=null) {
				rs.close();
			}if (ps!=null) {
				ps.close();
			}if (con!=null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
