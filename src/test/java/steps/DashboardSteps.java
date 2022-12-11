package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;
public class DashboardSteps extends CommonMethods {
    @Then("user verifies dashboard page")
    public void user_verifies_dashboard_page() {
        System.out.println("Batch 14 is happy now");
    }
    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(DataTable dataTable) {
        List<String>expectedTabs=dataTable.asList();
        List<String>actualTabs=new ArrayList<>();
        for (WebElement element:dashboard.dashboardTabs
             ) {
        actualTabs.add(element.getText());
        }
        //printing the text for both feature file & from application
        System.out.println(expectedTabs);
        System.out.println(actualTabs);
        //.equals is the method we use to compare 2 lists
        //assertTrue is a boolean which returns true when if condition is satisfied
        Assert.assertTrue(expectedTabs.equals(actualTabs));
    }
}
