package dishwatcher.nl.models;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName="company")
public class Company {
    private String _id;
    private String _modified;
    private String _google_id;
    private String _international_phone_number;
    private String _local_phone_number;
    private String _menukaart_url;
    private String _name;
    private String _search_key;
    private String _website;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAttribute(attributeName = "id")
    public String getId(){
        return _id;
    }

    public void setId(String id){
        _id = id;
    }

    @DynamoDBRangeKey(attributeName = "modified")
    @DynamoDBAttribute(attributeName = "modified")
    public String getModified(){
        return _modified;
    }

    public void setModified(String modified){
        _modified = modified;
    }
    @DynamoDBAttribute(attributeName = "international_phone_number")
    public String getInternational_phone_number(){
        return _international_phone_number;
    }

    public void setInternational_phone_number(String international_phone_number){
        _international_phone_number = international_phone_number;
    }
    @DynamoDBAttribute(attributeName = "menukaart_url")
    public String getMenukaartUrl(){
        return _menukaart_url;
    }

    public void setMenukaartUrl(String menukaartUrl){
        _menukaart_url = menukaartUrl;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName(){
        return _name;
    }
    public void setName(String name){
        _name = name;
    }

    @DynamoDBAttribute(attributeName = "search_key")
    public String getSearchKey(){
        return _search_key;
    }

    public void setSearchKey(String search_key){
        _search_key = search_key;
    }

    @DynamoDBAttribute(attributeName = "website")
    public String getWebsite(){
        return _website;
    }

    public void setWebsite(String website){
        _website = website;
    }

}
