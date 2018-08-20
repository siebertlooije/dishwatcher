package dishwatcher.nl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import dishwatcher.nl.R;
import dishwatcher.nl.models.Company;

public class HomeActivity extends AppCompatActivity {

    private DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("YourMainActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();

        setupDynamoDBMapper();
        getCompany();
    }

    private void setupDynamoDBMapper(){
        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();

    }

    public void setCompanyInformation(Company company){
        TextView companyNameView = (TextView) findViewById(R.id.company_name);
        companyNameView.setText(company.getName());
    }


    public void getCompany(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                dishwatcher.nl.models.Company company = dynamoDBMapper.load(
                        dishwatcher.nl.models.Company.class,
                        "0016f19aad394d2ca29a0ae8527f58d2",       // Partition key (hash key)
                        "2018-08-10");    // Sort key (range key)

                // Item read
                setCompanyInformation(company);
            }
        }).start();
    }

}
