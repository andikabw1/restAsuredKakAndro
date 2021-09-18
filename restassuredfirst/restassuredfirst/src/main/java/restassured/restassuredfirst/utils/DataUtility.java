package restassured.restassuredfirst.utils;

import java.io.File;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class DataUtility {

//	public static final String dataFilePath = System.getProperty("user.dir") + File.separator + "resources"
//			+ File.separator + "TestData.xlsx";
	static String env = System.getProperty("env").length() > 0 ? System.getProperty("env") : "stage";
	static String dataFile = env.equalsIgnoreCase("prod") ? "TestDataProd.xlsx" : "TestData.xlsx";
	public static final String dataFilePath = System.getProperty("user.dir") + File.separator + "resources"
			+ File.separator + dataFile;

	public static String getDataFromExcel(String sheetName, String ID) {
		String result = "";
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(dataFilePath);
			String strQuery = "Select * from " + sheetName + " where ID='" + ID + "'";
			Recordset recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {
				result = recordset.getField("Value");
			}

			recordset.close();
			connection.close();
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
