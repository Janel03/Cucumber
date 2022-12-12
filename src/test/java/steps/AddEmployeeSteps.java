package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(dashboard.pimOption);
    }
    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        // WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
        //addEmployeeOption.click();
        click(dashboard.addEmployeeOption);
    }
    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
        //WebElement firstName = driver.findElement(By.id("firstName"));
        //firstName.sendKeys("soman");
        sendText(addEmployee.firstNameField, "joshpan");
        sendText(addEmployee.lastNameField, "veranullah");
        //WebElement lastName = driver.findElement(By.id("lastName"));
        //lastName.sendKeys("yuria");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton = driver.findElement(By.id("btnSave"));
        //saveButton.click();
        click(addEmployee.saveButton);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee Added");
    }

    @When("user enters {string} and {string}")
    public void user_enters_and(String firstName, String lastName) {
        sendText(addEmployee.firstNameField, firstName);
        sendText(addEmployee.lastNameField, lastName);
    }
    @When("user enters {string} and {string} for adding multiple employees")
    public void users_enter_and_for_adding_multiple_employees(String firstNameValue, String lastNameValue) {
        sendText(addEmployee.firstNameField, firstNameValue);
        sendText(addEmployee.lastNameField, lastNameValue);
    }
    @When("user adds multiple employees and verifies they are added successfully")
    public void user_adds_multiple_employees_and_verifies_they_are_added_successfully(DataTable dataTable) throws InterruptedException {
     List<Map<String,String>>employeeNames=dataTable.asMaps();

     for (Map<String,String>emp:employeeNames){
         String firstNameValue=emp.get("firstName");
         String middleNameValue=emp.get("middleName");
         String lastNameValue=emp.get("lastName");

         sendText(addEmployee.firstNameField, firstNameValue);
         sendText(addEmployee.lastNameField, lastNameValue);
         sendText(addEmployee.middleNameField, middleNameValue);

         click(addEmployee.saveButton);
         Thread.sleep(2000);
         //1 employee is added, HW > add the rest
         click(dashboard.addEmployeeOption);
         Thread.sleep(2000);
     }
    }
    @When("user adds multiple employees from excel using {string} and verifies it")
    public void user_adds_multiple_employees_from_excel_using_and_verifies_it(String sheetName) throws IOException, InterruptedException {
       List<Map<String,String>>empFromExcel=
               ExcelReader.excelListIntoMap(Constants.TESTDATA_FILEPATH,sheetName);
//returns one map from list of maps
        Iterator<Map<String,String>>itr=empFromExcel.iterator();
        //returns the key & value for employees from excel
        while (itr.hasNext()){
            Map<String,String>mapNewEmp=itr.next();
            sendText(addEmployee.firstNameField,mapNewEmp.get("firstName"));
            sendText(addEmployee.middleNameField,mapNewEmp.get("middleName"));
            sendText(addEmployee.lastNameField,mapNewEmp.get("lastName"));
            sendText(addEmployee.photograph,mapNewEmp.get("photo"));

            if (!addEmployee.checkBox.isSelected()){
                click(addEmployee.checkBox);
            }
            sendText(addEmployee.createusernameField,mapNewEmp.get("username"));
            sendText(addEmployee.createpasswordField,mapNewEmp.get("password"));
            sendText(addEmployee.confirmpasswordField,mapNewEmp.get("confirmPassword"));

            click(addEmployee.saveButton);
            //verification is HW
            Thread.sleep(2000);
            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);
        }
      /*  //Verify that the employees were added
        List<String> employeeList = new ArrayList<>();
        for (Map<String,String> emp : empFromExcel) {
            String fullName = emp.get("firstName") + " " + emp.get("middleName") +
                    " " + emp.get("lastName");
            employeeList.add(fullName);
        }
        for (String fullName : employeeList) {
            //Verify that the employee is present in the employee list
            assertTrue(driver.findElement(By.xpath("//table[@id='resultTable']" +
                    "//a[text()='" + fullName + "']")).isDisplayed());
        }*/
    }
}




