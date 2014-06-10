/*
 * THEORY OF COMPUTATION - HW3
 * 資工系104乙班
 * 沙江益 - Aquile R. Sanchez
 * F74007094
 * File Name: TocHw3.java
 * Run Syntax: java -jar TocHw3.jar URL TownArea StreetName MingguoYear
 * Output: Average of real housing price in Taiwan in one line (int) found in the column ‘總價元’.
 * The program checks the URL provided and start comparing with the data to found the price of the house
 * according to the information provided, then the output is the average of the total house prices found.
*/
import java.net.*;
import java.io.*;
import org.json.*;

public class TocHw3 {
	public static void main(String[] args) {
		//System.out.println("Hello!");
		if (args.length < 4)
		{
			System.out.println("Execution Syntax is incorrect\n"
					+ "Please input this way: "
					+ "java -jar TocHw3.jar URL TownArea StreetName MingguoYear\n"
					+ "Example:\njava –jar TocHW3.jar http://www.datagarage.io/api/5365dee31bc6e9d9463a0057  文山區  辛亥路  103");
			return;
		}
		String MyURL = args[0];
		String TownArea = args[1];
		String Address = args[2];
		int MonthYear = Integer.parseInt(args[3]);
		int TotalData = 0;
		long TotalValue = 0;
		try {
			URL MyURLData = new URL(MyURL);
			Reader MyURLReader = new BufferedReader(new InputStreamReader(MyURLData.openStream(),"UTF-8"));	
			//File MyFile = new File("C:/Users/Aquile/Desktop/jsonfile.json");
			//Reader MyReader = new BufferedReader(new InputStreamReader(new FileInputStream(MyFile),"UTF-8"));
			JSONTokener MyJsonTokener = new JSONTokener(MyURLReader);
			JSONArray MyJsonArray = new JSONArray(MyJsonTokener);
			JSONObject MyJsonObject;
			for (int i = 0; i < MyJsonArray.length(); i++)
			{
				//System.out.println("Doing for loop...");
				MyJsonObject = MyJsonArray.getJSONObject(i);
				if (!MyJsonObject.getString("鄉鎮市區").equals(TownArea)) continue;
				if (MyJsonObject.getString("土地區段位置或建物區門牌").contains(Address) && MyJsonObject.getInt("交易年月") > (MonthYear*100))
				{
					//System.out.println(MyJsonObject.getString("鄉鎮市區") + "\t" + MyJsonObject.getString("土地區段位置或建物區門牌") + "\t" + MyJsonObject.getInt("交易年月") + "\t" + MyJsonObject.getInt("總價元"));
					TotalData++;
					TotalValue += MyJsonObject.getInt("總價元");
				}
			}
			//System.out.println("Total Value: " + TotalValue);
			//System.out.println("Total Data: " + TotalData);
			//System.out.println("Average Price: " + (TotalValue/TotalData));
			System.out.println(TotalValue/TotalData);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
}
