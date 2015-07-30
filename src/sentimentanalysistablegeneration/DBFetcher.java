package sentimentanalysistablegeneration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBFetcher {

    public ArrayList<String> getTweets() {
        
        String sql = "select tweet from filtereddata"; // LIMIT 20
        ArrayList<String> tweets = new ArrayList<>();
                
        try {
            PreparedStatement statement = DBConnector.getInstance().connectTODB().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                tweets.add(result.getString("tweet"));
            }
            
            return tweets;
        } catch (SQLException ex) {
            System.out.println("From DBFetcher: " + ex);
        }
        return null;
    }
}
