package dishwatcher.nl.models;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName="address")
public class Address {
    private String _id;
    private String _modified;
    private String _country;
    private String _city;
    private String _lat;
    private String _lng;
    private String _municipality;
    private String _province;
    private String _street;
    private String _street_number;
    private String _company_id;
    private String _district;


    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAttribute(attributeName = "id")
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @DynamoDBRangeKey(attributeName = "modified")
    @DynamoDBAttribute(attributeName = "modified")
    public String get_modified() {
        return _modified;
    }

    public void set_modified(String _modified) {
        this._modified = _modified;
    }
    @DynamoDBAttribute(attributeName = "country")
    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }
    @DynamoDBAttribute(attributeName = "city")
    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    @DynamoDBAttribute(attributeName = "lat")
    public String get_lat() {
        return _lat;
    }

    public void set_lat(String _lat) {
        this._lat = _lat;
    }

    @DynamoDBAttribute(attributeName = "lng")
    public String get_lng() {
        return _lng;
    }

    public void set_lng(String _lng) {
        this._lng = _lng;
    }

    @DynamoDBAttribute(attributeName = "municipality")
    public String get_municipality() {
        return _municipality;
    }

    public void set_municipality(String _municipality) {
        this._municipality = _municipality;
    }
    @DynamoDBAttribute(attributeName = "province")
    public String get_province() {
        return _province;
    }

    public void set_province(String _province) {
        this._province = _province;
    }
    @DynamoDBAttribute(attributeName = "street")
    public String get_street() {
        return _street;
    }

    public void set_street(String _street) {
        this._street = _street;
    }
    @DynamoDBAttribute(attributeName = "street_number")
    public String get_street_number() {
        return _street_number;
    }

    public void set_street_number(String _street_number) {
        this._street_number = _street_number;
    }
    @DynamoDBAttribute(attributeName = "company_id")
    public String get_company_id() {
        return _company_id;
    }

    public void set_company_id(String _company_id) {
        this._company_id = _company_id;
    }

    @DynamoDBAttribute(attributeName = "district")
    public String get_district() {
        return _district;
    }

    public void set_district(String _district) {
        this._district = _district;
    }
}
