package CVRequest;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.json.JSONException;

/**
 * A class to simplify the sending of API requests to the ComicVine API.<br>
 * This class should be called in the following manner:<br>
 * JsonNode = new CVrequest().method("params");<br>
 *
 * @author James
 *
 */
public class CVrequest {

    private static final String API_KEY = "bbfec73a78a7eb3b1c78389c8c13ce66835f5ede";
    private static final String BASE_URL = "http://api.comicvine.com";
    private static JsonNode mResponse = null;
    private static final String LIMIT = "100";
    private static final String FORMAT = "json";
    private static final String VOLUME = "volume";

    /**
     * Default search, limit is 10, no given resource
     *
     * @param qString = the term to search for
     * @return JsonNode of the response body, values are:<br>
     * image JSON array Aliases Gender "site_detail_url" "deck" "origin" array
     * "resource_type" "birth" "description" "real_name" "api_detail_url"
     * "date_added" "name" "publisher" array "id" "first_appeared_in_issue"
     * "count_of_issue_appearances" "date_last_updated"
     */
    public static JsonNode search(String qString) {
        try {
            mResponse = Unirest.get(BASE_URL + "/search")
                    .header("Accept", "application/json")
                    .queryString("api_key", API_KEY)
                    .queryString("client", "cvscrapper")
                    .queryString("query", qString)
                    .queryString("format", FORMAT)
                    .queryString("limit", LIMIT)
                    .asJson().getBody();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mResponse;
    }

    /**
     * This search gives even more search options including query. resource,
     * limit, and field_list
     *
     * @param queryString - String to search for
     * @param resourcesString - String of the resource being searched
     * @param limit - int of how many results to return, max of 100
     * @param fieldListArray - Array of Strings of field names to return in addition
     * to header: character concept origin object location issue story_arc
     * volume publisher person team video
     * @return JsonNode of the response body, depends on resource
     */
    public static JsonNode search(String queryString, String resourcesString, int limit, String[] fieldListArray) {
        String fList = "";

        for (int i = 0; i < fieldListArray.length; i++) {
            fList += fieldListArray[i];
            if (i != fieldListArray.length - 1) {
                fList += ",";
            }
        }

        System.out.println(fList);

        try {
            mResponse = Unirest.get(BASE_URL + "/search")
                    .header("Accept", "application/json")
                    .queryString("api_key", API_KEY)
                    .queryString("client", "cvscrapper")
                    .queryString("query", queryString)
                    .queryString("resources", resourcesString)
                    .queryString("field_list", fList)
                    .queryString("format", FORMAT)
                    .queryString("limit", String.valueOf(limit))
                    .asJson().getBody();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mResponse;
    }

    /**
     * Search with option of limit and resource string.
     *
     * @param queryString - String to search for
     * @param limit - int of how many results to return, max of 100
     * @param resourcesString - what resource to search for
     * @return JsonNode of the response body, depends on resource
     */
    public static JsonNode search(String queryString, int limit, String resourcesString) {
        try {
            mResponse = Unirest.get(BASE_URL + "/search")
                    .header("Accept", "application/json")
                    .queryString("api_key", API_KEY)
                    .queryString("client", "cvscrapper")
                    .queryString("query", queryString)
                    .queryString("resources", resourcesString)
                    .queryString("format", FORMAT)
                    .queryString("limit", String.valueOf(limit))
                    .asJson().getBody();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mResponse;
    }

    /**
     * Easy volume search, returns <b>WITHOUT</b> header.
     *
     * @param qString - what volume to search for
     * @return JSONArray of the search results, <b>WITHOUT</b> header, fields:
     * name<br>start_year<br>publisher array<br>id<br>count_of_issues<br>image
     * array
     *
     */
    public static JSONArray searchVolume(String qString) throws JSONException {
        try {
            mResponse = Unirest.get(BASE_URL + "/search")
                    .header("Accept", "application/json")
                    .queryString("api_key", API_KEY)
                    .queryString("client", "cvscrapper")
                    .queryString("query", qString)
                    .queryString("resources", VOLUME)
                    .queryString("field_list", "name,start_year,publisher,id,count_of_issues,image")
                    .queryString("format", FORMAT)
                    .queryString("limit", LIMIT)
                    .asJson().getBody();
            return mResponse.getObject().getJSONArray("results");
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode getVolumeInfo(String volID) {
        try {
            String query = "http://comicvine.gamespot.com/api/volume/4050-" + volID;
            mResponse = Unirest.get(query)
                    .header("Accept", "application/json")
                    .queryString("api_key", API_KEY)
                    .queryString("client", "cvscrapper")
                    .queryString("field_list", "name,start_year,publisher,image,count_of_issues,id")
                    .queryString("format", FORMAT)
                    .queryString("limit", LIMIT)
                    .asJson().getBody();
            return mResponse;
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mResponse;
    }

    public static JsonNode getVolumeIDs(String volID, int pageNum) {
        try {
            mResponse = Unirest.get(BASE_URL + "/issues")
                    .header("Accept", "application/json")
                    .queryString("api_key", API_KEY)
                    .queryString("client", "cvscrapper")
                    .queryString("format", FORMAT)
                    .queryString("limit", "100")
                    .queryString("field_list", "name,issue_number,id,image")
                    .queryString("filter", VOLUME + ":" + volID)
                    .queryString("page", String.valueOf(pageNum))
                    .asJson().getBody();
            return mResponse;
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mResponse;
    }

    public static JSONObject getIssue(String issId) throws JSONException {
        try {
            String query = "http://comicvine.gamespot.com/api/issue/4000-" + issId;
            HttpResponse<String> temp = Unirest.get(query)
                    .queryString("api_key", API_KEY)
                    .asString();
            JSONObject jo = XML.toJSONObject(temp.getBody());
            jo = jo.getJSONObject("response");
            return (JSONObject) jo.get("results");
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) throws JSONException {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter volume to search for");
		String input = in.nextLine();

		
		JSONArray volObj = CVrequest.searchVolume(input);

		for(int i = 0; i < volObj.length(); i++){
			JSONObject jo = (JSONObject) volObj.get(i);
			//"name,start_year,publisher,id,count_of_issues"
			String name, sYear, pub;
			int id, numIssues;
			
			if(!jo.isNull("name"))
				name = jo.getString("name");
			else name = "missing";
			
			if(!jo.isNull("start_year"))
				sYear = jo.getString("start_year");
			else sYear = "missing";
			
			if(!jo.isNull("publisher")){
				JSONObject temp = jo.getJSONObject("publisher");
				if(!jo.isNull("name"))
					pub = temp.getString("name");
				else pub = "missing";
			} else pub = "missing";
			
			if(!jo.isNull("id"))
				id = jo.getInt("id");
			else id = 9999999;
			
			if(!jo.isNull("count_of_issues"))
				numIssues = jo.getInt("count_of_issues");
			else numIssues = -1;
			
			System.out.println("name: " + name + "\t\t\tstart_year: " + sYear + "\tpublisher: " + pub 
								+ "\t\tid: " + id + "\tcount_of_issues: " + numIssues);

		}

		System.out.println("Enter volume id to search issues");
		 input = in.nextLine();

		JsonNode response = CVrequest.getVolumeIDs(input,1);
		//System.out.println(response);

		JSONArray ja = response.getObject().getJSONArray("results");
		//System.out.println(ja.toString());

		for(int i = 0; i < ja.length(); i++){

			JSONObject jo = (JSONObject) ja.get(i);
			//JSONObject jImg = (JSONObject) jo.get("image");
			String name, issueNum;
			int id;
			if(!jo.isNull("name")){
				name = jo.getString("name");
			} else name = "no name";
			if(!jo.isNull("issue_number")){
				issueNum = jo.getString("issue_number");
			} else issueNum = "-1";

			if(!jo.isNull("id")){
				id = jo.getInt("id");
			} else id = -1;
			System.out.println("issue#: " + issueNum + "\tid: " + id + "\t name: " + name); 
			/*System.out.println("icon_url: " + jImg.get("icon_url"));
			System.out.println("thumb_url: " + jImg.get("thumb_url"));
			System.out.println("tiny_url: " + jImg.get("tiny_url"));
			System.out.println("small_url: " + jImg.get("small_url"));
			System.out.println("super_url: " + jImg.get("super_url"));
			System.out.println("screen_url: " + jImg.get("screen_url"));
			System.out.println("medium_url: " + jImg.get("medium_url"));*/
			System.out.println("***************************END ISSUE**********************************\n\n");
		}
		
		System.out.println("enter issue id");
		input = in.nextLine();
		in.close();
		
		JSONObject joIssue = CVrequest.getIssue(input);
		String []names;
		names = JSONObject.getNames(joIssue);
		
		for(String s: names)
			System.out.println(s + "= " + joIssue.get(s).toString());
		
		JSONObject images = joIssue.getJSONObject("image");
		try {
			String urlStr = images.getString("medium_url");
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
				    "User-Agent",
				    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31"
				    + "(KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
			Image img = ImageIO.read(conn.getInputStream());
			
			JDialog dialog = new JDialog();
			//dialog.setModal(true);
			//dialog.setUndecorated(true);
			JLabel label = new JLabel((Icon) new ImageIcon(img));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.add(label);
			dialog.addWindowListener(new WindowAdapter() { 
			    @Override public void windowClosed(WindowEvent e) { 
			      System.exit(0);
			    }
			  });
			dialog.pack();
			dialog.setVisible(true);
			
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
